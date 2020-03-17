define(function(require, exports, module){

    var Observer = (function(){
        var _massage = {};
        // 消息订阅
        var regist = function(type, fn){
            // 如果消息队列中不存在对应事件回调函数，则创建数组保存传入的回调
            // 否则直接将回调推入数组中
            if(typeof _massage[type] === 'undefined'){
                _massage[type] = [fn]
            }else{
                _massage[type].push(fn)
            }
        }
        // 事件触发
        var fire = function(type, params){
            // 如果消息队列中不存在对应类型，直接退出
            if(!_massage[type]){
                return;
            }
            var events = {
                type: type,
                args: params
            }
            // 执行所有已订阅的回调
            for(var i in _massage[type]){
                _massage[type][i].call(this,events)
            }
        }
        // 注销订阅
        var remove = function(type, fn){
            if(Array.isArray(_massage[type])){
                var i = _massage[type0].length - 1;
                for(; i >= 0; i--){
                    try{
                    _massage[type][i] === fn && _massage[type].splice(i,1);
                    }catch(e){
                        console.log(e);
                    }
                }
            }
        }

        var checkQueue = function(){
            return _massage;
        }

        return {
            on: regist,
            fire: fire,
            off: remove,
            see: checkQueue
        }
    })()

    module.exports = Observer

})