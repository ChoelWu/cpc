define(function(require, exports, module){
    var schid = scheduleId;
    schid == 0 && (schid = "")
    var remind = parseInt(classScheduleRemindAt) == -1 ? 2000 : parseInt(classScheduleRemindAt);
    var currentTip = 0;
    var switchState = classScheduleRemindAt == -1 ? 0 : 1;
    var subscribe = 0;
    var wechatcode = "";

    // 获取公众号关注状态，二维码
    $.ajax({
        url:'//www.imooc.com/wechat/qrcode',
        type: 'get',
        dataType: 'json'
    }).done(function(resp){
        if(resp.result === 0){
            subscribe = resp.data.subscribe;
            wechatcode = resp.data.qrcode;
        }
    })

    var tipSelect = function () {
        var _html = ''+
            '<div>'+
                '<h4 class="select-title">设置学习提醒时间，可促进你学习任务的完成</h4>'+
                '<div class="select_options">'+
                    '<div class="tip_switch">'+
                        '<span>打开提醒：</span>'+
                        '<div class="switch_btn js-tips-switch '+ (Boolean(switchState) ? "on" : "") +'"></div>'+
                    '</div>'+
                    '<div class="select-wrap '+ (Boolean(switchState) ? "" : 'disabled') +'">'+
                        '<span>每天学习提醒：</span>'+
                        '<div class="select-times js-tip-time">'+
                            (Boolean(remind) && remind > 0 ? remind / 100 + ':00' : '20:00') +
                        '</div>'+
                        '<div class="select-arrow">'+
                            '<i class="imv2-arrow1_u js-change-plantime" data-type="1"></i>'+
                            '<i class="imv2-arrow1_d js-change-plantime" data-type="-1"></i>'+
                        '</div>'+
                    '</div>'+
                '</div>'+
                '<p class="select-desc">关注慕课公众号，或安装登录慕课网APP并开启通知，会收到学习提醒。</p>'+
            '</div>'
        return _html
    }

    var QRcodeTip = function(img){
        var _html = ''+
            '<div class="code_container">'+
                '<h4 class="sub_title js-cur-title">扫码添加微信学习提醒</h4>'+
                '<div class="wechat_qrcode js-cur-wxqrcode"><img src="'+ img +'"/></div>'+
                '<div class="wechat_qrcode js-cur-appcode" style="display:none;"><img src="/static/img/u/studyplan/appload.png"/></div>'+
                '<div class="tip_text js-cur-text">扫码安装APP可同步课表学习 →</div>'+
            '</div>'+
            '<div class="change_qrcode js-change-tip-content"></div>'

        return _html
    }

    var timeChange = function(event){
        if($('.select-wrap').hasClass('disabled')){return;}
        var timeEle = $('.js-tip-time');
        var timestring = timeEle.text().split(':')[0];
        var time = parseInt(timestring) + $(this).data('type');
        time = time > 23 ?  0 : time < 0 ? 23 : time;

        timeEle.text(time + ':00');
        remind = time * 100;
    }

    var qrcodeChange = function(event){
        var $title = $('.js-cur-title');
        var $qrcode = $('.js-cur-wxqrcode');
        var $appcode = $('.js-cur-appcode');
        var $text = $('.js-cur-text');
        if(currentTip === 0){
            $qrcode.hide();
            $appcode.show();
            $title.text('下载慕课网APP');
            $text.text('扫码添加微信学习提醒 →');
            currentTip = 1;
        }else{
            $qrcode.show();
            $appcode.hide();
            $title.text('扫码添加微信学习提醒');
            $text.text('扫码安装APP可同步课表学习 →')
            currentTip = 0;
        }
    }

    var tipsswith = function (event) {
        var $_tar = $(this);
        var $_wrap = $('.select-wrap')
        if(switchState === 0){
            $_tar.addClass('on');
            $_wrap.removeClass('disabled')
            switchState = 1;
        }else{
            $_tar.removeClass('on');
            $_wrap.addClass('disabled');
            switchState = 0;
        }
    }

    var overwriteStyle = function(){
        var style = document.createElement('style');
        style.id = 'OVERWRITE';
        style.innerText = ''+
        '.moco-modal-wrap {'+
            'width: 394px !important;'+
        '}'+
        '.js-modal-cancel {'+
            'border-color: #EDEEEF;'+
            'background: #EDEEEF;'+
        '}'+
        '.moco-modal-title span{'+
            'font-size: 14px;'+
            'color: #545C63;'+
            'line-height: 24px;'+
            'font-weight: 400;'+
        '}'+
        '.moco-modal-title a.moco-modal-close{'+
            'font-size: 22px;'+
            'font-weight: bold;'+
        '}'

        return style;
    }

    var bindEvent = function(type){
        var style = overwriteStyle();
        if(type === 'add'){
            $('head').append(style);
            $(document).on('click', '.js-change-plantime', timeChange)
            $(document).on('click', '.js-change-tip-content', qrcodeChange)
            $(document).on('click', '.js-tips-switch', tipsswith)
        }else{
            $('#OVERWRITE').remove();
            $(document).off('click', '.js-change-plantime', timeChange)
            $(document).off('click', '.js-change-tip-content', qrcodeChange)
            $(document).off('click', '.js-tips-switch', tipsswith)
        }
    }

    var popDialog = function(){
        bindEvent('add');
        $.confirm(
            '定制课表',
            {
                info: tipSelect(),//提示备注文字，支持超链接、html代码
                submit: '保存',//确定按钮文字
                modal: true,//是否模态窗口，true时显示蒙版层
                callback: function(){
                    (subscribe == 1) && bindEvent('remove');
                    var remindAt = switchState === 0 ? -1 : remind
                    $.ajax({
                        url: '/u/make_schedule?schId='+ schid +'&remindAt=' + remindAt,
                        type: 'get',
                        dataType: 'json'
                    }).done(function(resp){
                        if(resp.result === 0){
                            let prefix = schid ? '修改' : '添加'
                            if(subscribe != 1){
                                $.dialog(QRcodeTip(wechatcode), {
                                    title: prefix + '课表成功！请到 “个人中心 - 我的课表” 查看！',
                                    modal: true,
                                    draggable: false,
                                    callback: function(){
                                        bindEvent('remove');
                                        window.location.reload();
                                    }
                                })
                                subscribe = 1;
                            }else{
                                $.prompt(prefix + '课表成功！')
                                setTimeout(function(){
                                    window.location.reload();
                                }, 1000)
                            }
                            schid = resp.data.schId
                        }
                    },function(err){})
                    // remind =
                },//点击确定时回调方法
                cancelcallback: function(){
                    bindEvent('remove')
                },//点击取消时回调方法
            }
        )
    }

    var testMain = function(){
        $('head').append(overwriteStyle());
        bindEvent('add')
        $.dialog(QRcodeTip(qrcodeChange), {
            title: '添加课表成功！请到 “个人中心 - 我的课表” 查看！',
            modal: true,
            draggable: false,
            callback: function(){
                bindEvent('remove')
            }
        })
    }
    // testMain();

    module.exports = popDialog
})