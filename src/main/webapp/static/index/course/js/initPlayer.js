define(function(require, exports, module){
    var Observer = require('./observer.js');
    var Utils = require('../utils.js');

    var createCopyrightDom = function(){
        var crDom = Utils.createElement('div', 'js-video-copyright');
        crDom.style.width          = '100%';
        crDom.style.height         = '100%';
        crDom.style.position       = 'absolute';
        crDom.style.zIndex         = '599';
        crDom.style.left           = '0';
        crDom.style.top            = '0';
        crDom.style.background     = '#000 url(//coding.imooc.com/static/module/common/img/copyright.png) no-repeat center center';
        crDom.style.backgroundSize = '100% auto';

        return crDom;
    }

    /**
     * 生成视频播放提示框
     * (videoSlot: jqueryObject, navLink: string, nextInfo: object) => HTMLDivElement
     * nextInfo{
     *     navText: string,  按钮文字
     *     tipText: string,  提示文字
     *     current: number,  当前播放节索引
     * }
     */
    var createCompleteDom = function(videoSlot, video_conf){
        var nextInfo   = null;
        var triggers  = [];

        var endDom    = Utils.createElement('div', 'js-video-end-mask');
        var title     = Utils.createElement('p', null);
        var navBtn    = Utils.createElement('a', null, {href: '//coding.imooc.com/class/'+video_conf.cid+'.html'}, 'navigate-btn');
        var replayBtn = Utils.createElement('a', null, {href: 'javascript:;'}, 'view-again');
        var reIcon    = Utils.createElement('i', null, 'imv2-replay');

        videoSlot = videoSlot[0];
        triggers = videoSlot.parentNode.parentNode.querySelectorAll('.js-change-media');

        if(video_conf.current == triggers.length -1){
            nextInfo = {
                tipText      : '试看章节已结束，如果您喜欢本课程，请移步购买。',
                navText      : '查看详情',
                bindCallback : function(e){}
            }
        }else{
            var nextTrigger = triggers[video_conf.current+1];
            console.log(nextTrigger)
            nextInfo = {
                tipText      : '下一节:' + nextTrigger.dataset.title,
                navText      : '下一节',
                bindCallback : function(e){
                    e.preventDefault()
                    this.parentNode.remove();
                    nextTrigger.click();
                }
            }
        }


        title.innerText  = nextInfo.tipText;

        replayBtn.innerText = '重新观看';
        replayBtn.insertBefore(reIcon, replayBtn.lastChild);
        replayBtn.addEventListener('click', function(e){
            video.replay()
        })

        navBtn.innerText = nextInfo.navText;
        navBtn.addEventListener('click', nextInfo.bindCallback)

        endDom.appendChild(title);
        endDom.appendChild(navBtn);
        endDom.appendChild(replayBtn);

        return endDom;
    }

    var video = {
        _copyright: createCopyrightDom(),
        _videoSlot:null,
        _endDom: null,
        _video_conf: {},
        init: function (video_conf, videoSlot) {
            this._videoSlot = videoSlot;
            this._video_conf = videoSlot;
            var protocol=['http:'];
            if(/https/.test(location.protocol)){
                protocol = ['https:'];
            }
            var originsourceUrl = protocol.concat(['//coding.imooc.com/lesson/m3u8h5?mid=', video_conf.mid, '&cid=', video_conf.cid, '&ssl=1', '&cdn=aliyun1']).join('');
            if(window.thePlayer){
                this.destroy();
                window.thePlayer = null;
            }
            window.thePlayer = mocoplayer(videoSlot, {
                url: originsourceUrl,
                title: video_conf.title,
                showAd: video_conf.showAd,
                currentTime: video_conf.ctime,
                // uid:  1,
                // phone: 1,
                events: {
                    onReady: function () {

                        // 触发播放器准备完成推送
                        Observer.fire('onReady');

                        if (this.getPlayType() == "html5") {
                            var _this = this;
                            var _timer = 2000;
                            if (video_conf.ctime || !video_conf.showAd) {
                                _timer = 0;
                            } else {
                                videoSlot.append(video._copyright)
                            }
                            setTimeout(function () {
                                video._copyright.remove();
                                _this.play();
                            }, _timer)
                        } else {
                            _this.play();
                        }

                    },
                    onComplete: function () {
                        this._endDom = createCompleteDom(video._videoSlot,video_conf);
                        video._videoSlot.append(this._endDom);
                        $(this._endDom).addClass('in');

                        // 触发播放器播放完成推送
                        Observer.fire('onComplete',{target: video});
                        return;
                    },
                    onPlay: function () {
                        // 触发播放器开始播放推送
                        Observer.fire('onPlay', {target: video});
                    },
                    onPause: function () {
                        // 触发播放器暂停播放推送
                        Observer.fire('onPause', {target: video});
                    },
                    onError: function (error) {
                        // 触发播放器异常推送
                        Observer.fire('onError', {error:error})
                    }
                }
            })
        },
        destroy: function () {
            if(!window.thePlayer){
                return;
            }
            var errorMask = document.querySelectorAll('.mocoplayer-error');
            if(errorMask.length > 0){
                Array.prototype.forEach.call(errorMask,function(item){
                    item.remove();
                })
            }
            window.thePlayer.pause();
            window.thePlayer.destroy();
            window.thePlayer = null;
        },
        replay: function () {
            this._endDom.remove()
            if (window.thePlayer.getPlayType() == "html5") {
                var _this = this;
                var _timer = 2000;
                if (this._video_conf.ctime || !this._video_conf.showAd) {
                    _timer = 0;
                } else {
                    videoDom.appendChild()
                }
                setTimeout(function () {
                    $("#js-video-copyright").remove();
                    window.thePlayer.play();
                }, _timer)
            } else {
                window.thePlayer.play();
            }
        }
    }



    module.exports = video
})