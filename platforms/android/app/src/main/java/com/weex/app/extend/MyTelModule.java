package com.weex.app.extend;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import com.weex.app.WXPageActivity;


public class MyTelModule extends WXModule {
    private static final String TAG = "dev";


    @JSMethod(uiThread = false)
    public void call(String number) {
        if (TextUtils.isEmpty(number)) {
            return;
        }

        // 检查权限
        if (ActivityCompat.checkSelfPermission(mWXSDKInstance.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, " 没有授权");
            // 申请授权
            ActivityCompat.requestPermissions((WXPageActivity) mWXSDKInstance.getContext(), new String[]{Manifest.permission.CALL_PHONE},0);
        }

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        mWXSDKInstance.getContext().startActivity(intent);
    }
}
