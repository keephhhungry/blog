let proxyObj = {};
proxyObj['/'] = {
    ws: false,
    target: 'http://localhost:9091',
    changeOrigin: true,
    pathRewrite: {
        '^/': ''
    }
}

module.exports = {
    devServer: {
        host: 'localhost',
        port: 9090,
        proxy: proxyObj
    }
}
