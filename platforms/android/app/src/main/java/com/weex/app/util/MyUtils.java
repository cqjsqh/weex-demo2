package com.weex.app.util;

import android.content.Context;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyUtils {
    // String转InputStream
    public static InputStream stringToInputStream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    // InputStream转String
    public static String inputStreamToString(InputStream input) {
        try {
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[4096];
            for (int n; (n = input.read(b)) != -1; ) {
                out.append(new String(b, 0, n));
            }

            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    // 获取文件里的文本
    public static String getFileToString(Context context, String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            return inputStreamToString(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

