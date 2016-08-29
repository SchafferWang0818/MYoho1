package schaffer.myoho.Utils;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import schaffer.myoho.Base.MyApplication;

/**
 * Created by a7352 on 2016/8/24.
 */
public class HttpUtils {

    private final OkHttpClient okHttpClient;
    private final Handler handler;

    public HttpUtils() {
        okHttpClient = new OkHttpClient.Builder().cache(new Cache(MyApplication.app.getCacheDir(), 1024 * 1024 * 8)).build();
        handler = new Handler(Looper.getMainLooper());
    }

    public HttpUtils loadData(String path, String postBody) {
        FormBody.Builder builder = new FormBody.Builder();
        if (!TextUtils.isEmpty(postBody)) {
            String[] split = postBody.split("&");
            for (int i = 0; i < split.length; i++) {
                String[] split1 = split[i].split("=");
                builder.add(split1[0], split1[1]);
            }
        }
        FormBody build = builder.build();
        Request request = new Request.Builder().url(path).post(build).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (onLoadDataListener != null) {
                            onLoadDataListener.loadFailed(e.getMessage());
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String content = response.body().string();
                MLog.w(content);
                if (!TextUtils.isEmpty(content)) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (onLoadDataListener != null) {
                                onLoadDataListener.loadSuccess(content);
                            }
                        }
                    });

                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (onLoadDataListener != null) {
                                onLoadDataListener.loadFailed("得到的数据为空");
                            }
                        }
                    });
                }
            }
        });
        return this;
    }

    public interface OnLoadDataListener {
        void loadSuccess(String content);

        void loadFailed(String errorMsg);
    }

    OnLoadDataListener onLoadDataListener;

    public void setOnLoadDataListener(OnLoadDataListener onLoadDataListener) {
        this.onLoadDataListener = onLoadDataListener;
    }


    public void cancelAll() {
        okHttpClient.dispatcher().cancelAll();
    }

}
