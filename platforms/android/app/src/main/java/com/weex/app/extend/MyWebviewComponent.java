package com.weex.app.extend;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
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
import android.webkit.WebResourceRequest;
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
import com.weex.app.util.MyUtils;

import java.util.Arrays;


public class MyWebviewComponent extends WXComponent<WebView> {
    // webview
    private WebView webView;
    // 当前weex实例
    private WXSDKInstance instance;
    // 来源链接
    private String url = "";
    // 当前链接
    private String currentUrl = "";
    // tag
    private final String TAG = "webview";
    //polyfill
    private String polyfillJsStr = "";

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

        this.polyfillJsStr = MyUtils.getFileToString(instance.getContext(), "static/polyfill-7.2.5.min.js");

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
                currentUrl = url;
                super.onPageStarted(view, url, favicon);

                Log.d(TAG,"网页开始加载>>>>>>>>>>>>>" + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                Log.d(TAG, "网页加载完成");
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
            public void  onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                // 可以拦截请求资源
                WebResourceResponse response = super.shouldInterceptRequest(view, request);
                String url = request.getUrl().toString();

                return response;
            }
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                // 可以拦截请求资源
                WebResourceResponse response = super.shouldInterceptRequest(view, url);

                /*if (!TextUtils.isEmpty(url) && url.equals("https://xxxx/xxx")) {
                    Log.d(TAG, "拦截请求资源" + url);

                    InputStream input = response.getData();
                    Log.d(TAG, inputStreamToString(input).substring(0, 10));
                }*/

                return response;
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
                String level = consoleMessage.messageLevel().toString().toLowerCase();
                String msg = "【console." + level + "】 " + consoleMessage.message();

                if (level.equals("error"))
                    Log.e(TAG, msg);
                else
                    Log.d(TAG, msg);

                return super.onConsoleMessage(consoleMessage);
            }

            @Override
            public void onProgressChanged(WebView view, int progress) {
                // 加载进度
                super.onProgressChanged(view, progress);
                Log.d(TAG, "加载进度：" + progress + "/100 ");

                //evaluateJs("console.log(window._AAA + ' " + progress + "' + ' = ' + '" + currentUrl  + "' + ' = ' + '" + view.getUrl().toString() + "' + ' = ' + location.href);window._AAA = 'log'");
                evaluateJs("(function (url) {\n" +
                        "  if (url == location.href && !window._PAPE_INIT) {\n" +
                        "    window._PAPE_INIT = 1;\n" +
                        "    return 1;\n" +
                        "  }\n" +
                        "})(\"" + currentUrl + "\");", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        if (value.equals("1")) {
                            onPageInitialized(webView, currentUrl);
                        }
                    }
                });
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


    // 新增WebViewClient回调事件
    public void onPageInitialized(WebView view, String url) {
        Log.d(TAG, "网页初始化");

        // 黑名单
        final String[] blackLists = new String[] {
                "https://www.saxotrader.com/disclaimer"
        };
        if (Arrays.asList(blackLists).contains(url)) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) { // Android 5.1以下
                Log.d(TAG, "执行js");
                evaluateJs(this.polyfillJsStr);
            }
        }
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

    // 执行js
    private void evaluateJs(String jsStr) {
        evaluateJs(jsStr, null);
    }
    private void evaluateJs(String jsStr, final ValueCallback<String> callback) {
        String script = "javascript:" + jsStr;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // Android 4.4及以上

            webView.evaluateJavascript(script, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    if (callback != null) {
                        callback.onReceiveValue(value);
                    }
                    Log.d(TAG, "evaluateJs返回值：" + value);
                }
            });

        } else {
            webView.loadUrl(script);
        }
    }
}
