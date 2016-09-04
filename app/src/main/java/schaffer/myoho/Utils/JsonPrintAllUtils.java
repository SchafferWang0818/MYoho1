package schaffer.myoho.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import schaffer.myoho.Base.MyApplication;

/**
 * Created by a7352 on 2016/9/3.
 */
public class JsonPrintAllUtils {
    public static void printJson(String json) {
        File filesDir = MyApplication.app.getCacheDir();
        File filesDir1 = MyApplication.app.getFilesDir();
        File filesDir2 = MyApplication.app.getExternalCacheDir();
        File file = new File(filesDir1, "json.txt");
        MLog.w(file.toString());
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(json.getBytes());
            fos.flush();
            fos.close();
            MToast.notifys("json打印完成");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
