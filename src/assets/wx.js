const { env, bundleUrl } = weex.config;
const platform = env.platform().toLowerCase();

const isWeb = platform === "web";
const isIos = platform === "ios";
const isAndroid = platform === "android";

// 导入模块
function M(moduleName) {
  const module = weex.requireModule(moduleName);
  if (module === undefined) {
    console.warn(`模块【${moduleName}】未注册`);
  } else if (module && Object.keys(module).length === 0){
    console.warn(`模块${moduleName}已注册,但环境不支持`);
  }
  return module;
}

// 数据缓存
const Storage = M('storage');
function setStorage(key, value, callback) {
  Storage.setItem(key, value, callback);
}
function getStorage(key, callback) {
  Storage.getItem(key, function (e) {
    callback && callback(e.data || undefined);
  });
}
function removeStorage(key, callback) {
  Storage.removeItem(key, callback);
}
function clearStorage() {
  StorageKeys(arr => arr.forEach(val => removeStorage(val)));
}
function getStorageLength(callback) {
  Storage.length(function (e) {
    callback && callback(e.data);
  });
}
function getStorageKeys(callback) {
  Storage.getAllKeys(function (e) {
    callback && callback(e.data);
  });
}

// 网络请求
const Stream = M('stream');
function request(options, callback, progressCallback) {
  let opt = {
    method: 'POST', // GET | POST
    url: '',
    headers: {},
    dataType: 'form', // form | json
    type: 'json', // json | text | jsonp
    body: '',
  };

  opt = Object.assign(opt, options);
  if (opt === 'GET') {
    opt.url += opt.url.indexOf('?') === -1 ? '?' : '&' + encodedFormUrl(opt.body);
    opt.body = '';
  } else if (opt === 'POST') {
    if (opt.dataType === 'form') {
      opt.headers['Content-Type'] = 'application/x-www-form-urlencoded';
      opt.body = encodedFormUrl(opt.body);
    } else if (opt.dataType === 'json') {
      opt.headers['Content-Type'] = 'application/json';
      opt.body = JSON.stringify(opt.body);
    }
  }

  function encodedFormUrl(data) {
    let arr = [];
    for(var key in data) {
      if (data[key] instanceof Array) {
        for (var i=0,len=data[key].length;i<len; i++){
          arr.push(encodeURIComponent(key + '[' + i + ']') + '=' + encodeURIComponent(data[key][i]))
        }
      } else {
        arr.push(key + '=' + encodeURIComponent(data[key]));
      }
    }
    return arr.join('&')
  }

  // 拦截
  if (request.interceptors.request) opt = request.interceptors.request(opt) || opt;

  Stream.fetch(opt, function (res) {
    // 拦截
    if(res.ok) {
      if (request.interceptors.response) res = request.interceptors.response(res) || res;
    } else {
      if (request.interceptors.responseError) res = request.interceptors.responseError(res) || res;
    }

    callback && callback(res);
  }, progressCallback);
}
request.interceptors = {};
function requestInterceptors (option) {
  request.interceptors = Object.assign({request: null, response: null, responseError: null}, option);
}
function requestPost(url, param, callback) {
  request({method: 'POST', url, body: param}, callback);
}
function requestGet(url, param, callback) {
  request({method: 'GET', url, body: param}, callback);
}

// WebSocket
const Ws = M('webSocket');
function connectSocket(url, protocol = '') {
  Ws.WebSocket(url, protocol);
}
function sendSocketMessage(data) {
  Ws.send(data);
};
function closeSocket(code, reason) {
  Ws.close(code, reason);
}
function onSocketOpen(callback) {
  Ws.onopen = callback;
}
function onSocketMessage(callback) {
  Ws.onmessage = callback;
}
function onSocketClose(callback) {
  Ws.onclose = callback;
}
function onSocketError(callback) {
  Ws.onerror = callback;
}


export default {
  M,
};
