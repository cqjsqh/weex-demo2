const { env, bundleUrl } = weex.config;
const platform = env.platform.toLowerCase();


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
// 基础
const U = {
  typeof(obj) {
    return Object.prototype.toString.call(obj).slice(8, -1).toLowerCase();
  },
  isFunction(obj) {
    return U.typeof(obj) === 'function';
  },
  isString(obj) {
    return U.typeof(obj) === 'string';
  },
  isObject(obj) {
    return U.typeof(obj) === 'object';
  },
  isEmptyObject(obj) {
    return U.isObject(obj) && Object.keys(obj).length === 0;
  },
};
// 环境
const E = {
  isWeb: platform === 'web',
  isIos: platform === 'ios',
  isAndroid: platform === 'android',

  isTaobao: /(tb|taobao|淘宝)/i.test(env.appName),
  isTmall: /(tm|tmall|天猫)/i.test(env.appName),
  isTrip: env.appName === 'LX',
  isAlipay: env.appName === 'AP',
};
E.isIPhoneX = E.isIos && [2436, 2688, 1792].includes(env.deviceHeight);
E.isAliWeex = E.isTmall || E.isTrip || E.isTaobao;



// 数据缓存
let Storage = {
  setStorage(key, value, callback)
  {
    M('storage').setItem(key, value, callback);
  },
  getStorage(key, callback) {
    M('storage').getItem(key, function (e) {
      if (U.isFunction(callback)) callback(e.data || undefined);
    });
  },
  removeStorage(key, callback) {
    M('storage').removeItem(key, callback);
  },
  clearStorage() {
    Storage.StoragegetStorageKeys(arr => arr.forEach(val => Storage.removeStorage(val)));
  },
  getStorageLength(callback) {
    M('storage').length(e => {
      if (U.isFunction(callback)) callback(e.data);
    });
  },
  getStorageKeys(callback) {
    M('storage').getAllKeys(e => {
      if (U.isFunction(callback)) callback(e.data);
    });
  },
};

// 网络请求
let _interceptors = {};
let Request = {
  request(options, callback, progressCallback){
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
      for (var key in data) {
        if (data[key] instanceof Array) {
          for (var i = 0, len = data[key].length; i < len; i++) {
            arr.push(encodeURIComponent(key + '[' + i + ']') + '=' + encodeURIComponent(data[key][i]))
          }
        } else {
          arr.push(key + '=' + encodeURIComponent(data[key]));
        }
      }
      return arr.join('&')
    }

    // 拦截
    if (_interceptors.request) opt = _interceptors.request(opt) || opt;

    M('stream').fetch(opt, function (res) {
      // 拦截
      if (res.ok) {
        if (_interceptors.response) res = _interceptors.response(res) || res;
      } else {
        if (_interceptors.responseError) res = _interceptors.responseError(res) || res;
      }

      if (U.isFunction(callback)) callback(res);
    }, progressCallback);
  },
  requestInterceptors(option) {
    _interceptors = Object.assign({request: null, response: null, responseError: null}, option);
  },
  requestPost(url, param, callback) {
    Request.request({method: 'POST', url, body: param}, callback);
  },
  requestGet(url, param, callback) {
    Request.request({method: 'GET', url, body: param}, callback);
  },
}

// WebSocket
let _ws;
let WebSocket = {
  connectSocket(url, protocol = '') {
    _ws = M('webSocket');
    _ws.WebSocket(url, protocol);
  },
  sendSocketMessage(data) {
    _ws.send(data);
  },
  closeSocket(code, reason) {
    _ws.close(code, reason);
  },
  onSocketOpen(callback) {
    _ws.onopen = callback;
  },
  onSocketMessage(callback) {
    _ws.onmessage = callback;
  },
  onSocketClose(callback) {
    _ws.onclose = callback;
  },
  onSocketError(callback) {
    _ws.onerror = callback;
  },
};


export default {
  M,
  U,
  E,

  ...Storage,
  ...Request,
  ...WebSocket,
};
