const { env, bundleUrl } = weex.config;


const pageHeight = env.deviceHeight / (env.deviceWidth / 750);


// 获取图片在三端上不同的路径
// e.g. 图片文件名是 test.jpg, 转换得到的图片地址为
// - H5      : dev/images/test.jpg    dev和所在html路径同级
// - Android : local:///test          local代表项目各dpi目录,一般放在hdpi里一张即可
// - iOS     : local///test.jpg       local代表从项目中全局扫描 test.jpg可放至项目中任何目录
function local(imgName) {
  const platform = env.platform.toLocaleLowerCase();
  let path = '';

  // 开发模式
  let host = bundleUrl.match(/\/\/([^\/]+?)\//)[1];
  if (host.split('.')[0] === '192') {
    return `/src/dev/images/${imgName}`;
  }

  if (platform === 'android') { // android 不需要后缀
    imgName = imgName.substr(0, imgName.lastIndexOf('.'));
    path = `local:///${imgName}`;
  } else if(platform === 'ios') {
    path = `local:///${imgName}`;
  } else {
    path = `/src/dev/images/${imgName}`;
  }

  return path;
}

function and(val, s) {
  return [val, `${val}-${s}`];
}

// navigator跳转
function page(jsName) {
  const platform = env.platform.toLocaleLowerCase();
  let path = '';

  // 开发模式
  /*let host = bundleUrl.match(/\/\/([^\/]+?)\//)[1];
  if (host.split('.')[0] === '192') {
    return `/dist/${jsName}.js`;
  }*/

  if (platform === 'android') {
    path = `file://assets/dist/${jsName}.js`;
  } else if(platform === 'ios') {
    path = `${bundleUrl.substring(0, bundleUrl.lastIndexOf('/') + 1)}${jsName}.js`;
  } else {
    path = `/${jsName}.html`;
  }

  return path;
}

function M(moduleName) {
  const module = weex.requireModule(moduleName);
  if (module === undefined) {
    console.warn(`模块【${moduleName}】未注册`);
  } else if (module && Object.keys(module).length === 0){
    console.warn(`模块${moduleName}已注册,但环境不支持`);
  }
  return module;
}

function navPush(options, callback) {
  if (typeof options === 'string') {
    options = {
      url: options,
      animated: 'true'
    };
  }

  // console.log('路由跳转 -> ' + options.url);
  this.M('navigator').push(options, callback);
}

function log(...arg) {
  console.info(...arg);
}

export default {
  filters: {
    local,
    and,
  },

  data() {
    return {
      pageHeight,
    };
  },

  methods: {
    local,
    page,
    M,
    navPush,
    log,
  },
};
