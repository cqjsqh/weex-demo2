package com.weex.app.extend;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;



public class MyWebviewComponent extends WXComponent<WebView> {
    // webview
    private WebView webView;
    // 当前weex实例
    private WXSDKInstance instance;
    // 链接
    private String url = "";
    private static final String TAG = "webview";


    // 构造方法，获得当前weex实例instance
    public MyWebviewComponent(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
        super(instance, dom, parent);
        this.instance = instance;
    }
    public MyWebviewComponent(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, int type) {
        super(instance, dom, parent, type);
        this.instance = instance;
    }
    @Override
    protected WebView initComponentHostView(@NonNull Context context) {
        if (getDomObject().getAttrs().get("src") != null) {
            this.url = getDomObject().getAttrs().get("src").toString();
        }

        this.webView = new WebView(context);
        init();
        return this.webView;
    }

    // 初始化WebView设置参数
    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    private void init() {
        webView.setInitialScale(100);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(false);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setAllowFileAccess(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);

        webView.addJavascriptInterface(this,"native");

        // 加载网页
        webSettings.setDefaultTextEncodingName("utf-8");
        webView.loadUrl(url);

        // WebViewClient设置
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(TAG, "网页打开链接：" + url);

                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                Log.d(TAG,"网页开始加载");
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                Log.d(TAG, "网页加载完成" + url);
                // 触发组件的finish事件
                fireEvent("finish");
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

                Log.d(TAG,"http错误：" + errorCode + " " + description);
            }
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);

                Log.d(TAG,"https错误：" + error);
            }
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                // 可以拦截请求资源
                return super.shouldInterceptRequest(view, url);
            }
        });

        // WebChromeClient设置
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder builder = new AlertDialog.Builder(instance.getContext());
                builder.setTitle("Alert")
                        .setMessage(message);
                builder.setPositiveButton("确定", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });

                builder.setCancelable(false)
                        .create()
                        .show();

                return true;
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d(TAG, "Console[" + consoleMessage.messageLevel() + "] " + consoleMessage.message());

                return super.onConsoleMessage(consoleMessage);
            }
        });

    }

    @Override
    protected void onCreate() {
        super.onCreate();
    }
    @Override
    public void onActivityDestroy() {
        super.onActivityDestroy();
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onFinishLayout() {
        super.onFinishLayout();
    }

    // 设置组件的src属性
    @WXComponentProp(name = "src")
    public void setSrc(String url) {
        this.url = url;
    }


    // native调用js
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @JSMethod
    public void evaluateJavascript(String jsStr, final JSCallback callback){
        Log.d(TAG, "执行js字符串 " + jsStr);
        if (!TextUtils.isEmpty(jsStr)) {
            webView.evaluateJavascript("javascript:" + jsStr, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    // value为js返回的结果
                    if(callback != null){
                        callback.invoke(value);
                    }
                }
            });
        }
    }

    // js调用native
    @JavascriptInterface
    public void postMessage(String method, String param){
        Log.d(TAG, "js调用native method:" + method + " param:" + param);
    }
}
