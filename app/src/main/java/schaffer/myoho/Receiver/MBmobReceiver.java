package schaffer.myoho.Receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import schaffer.myoho.Activity.WebActivity;
import schaffer.myoho.Base.MyApplication;
import schaffer.myoho.Bean.NotificationBean;
import schaffer.myoho.R;
import schaffer.myoho.Utils.MLog;

/**
 * Created by a7352 on 2016/9/8.
 */
public class MBmobReceiver extends BroadcastReceiver {

    private Bitmap bitmap;

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        MLog.e("接受到的信息-->" + msg);
        NotificationBean notificationBean = new Gson().fromJson(msg, NotificationBean.class);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);

        RemoteViews remoteViews = new RemoteViews(MyApplication.app.getPackageName(), R.layout.notification_romoteview);
        Intent intent1 = new Intent(context, WebActivity.class);
        intent.putExtra("url", notificationBean.getTurnPath());
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = builder.setTicker(notificationBean.getTitle1()).setContent(remoteViews).setContentIntent(pendingIntent).setContentTitle(notificationBean.getTitle2()).setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(bitmap).build();
        remoteViews.setTextViewText(R.id.romote_title, notificationBean.getTitle1());
        remoteViews.setTextViewText(R.id.romote_content, notificationBean.getContent());
        Picasso.with(context).load(notificationBean.getImgPath()).into(remoteViews, R.id.romote_iv, 1, notification);
        nm.notify(1, notification);
    }
}
