define(function(require, exports, module){
    var Observer = require('./observer.js');
    var video = require('./initPlayer.js');
    var Utils = require('../utils');

    module.exports = function(config){
        // 订阅修改播放广播
        Observer.on('changeMediaItem', function(e){
            // 拼合所需参数
            var video_conf = {
                mid: e.args.mid,
                cid: config.course,
                title: e.args.title,
                current: e.args.index,
                ctime: 0,
                showAd: true,
            }
            // 如果当前页面存在播放器实例，清空实例
            if(window.thePlayer){
                if (Utils.isIE()){
                    window.thePlayer = null;
                }
                try{
                    video.destroy();
                }catch(err){
                    console.log(err);
                }
            }
            // 注入播放器
            video.init(video_conf, $(e.args.videoSlot))
        })

        // 订阅closePlayer事件
        Observer.on('closePlayer', function(e){
            if(window.thePlayer){
                if (Utils.isIE()){
                    window.thePlayer = null;
                }
            }
            try {
                video.destroy();
                config.onClose(e);
            } catch (error) {
                console.log(error);
            }

        })

        // 订阅容器挂载完成广播
        Observer.on('containerMounted', config.onMounted);

        // 订阅播放器准备完成事件
        Observer.on('onReady', config.onReady);

        // 订阅播放器播放完成事件
        Observer.on('onComplete', config.onComplete);

        // 订阅播放器开始播放事件
        Observer.on('onPlay', config.onPlay);

        // 订阅播放器暂停播放事件
        Observer.on('onPause', config.onPause);

        // 订阅播放器异常事件
        Observer.on('onErro', config.onError);
    }
})