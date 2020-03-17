/**
 * 公共方法文件
 */
define(function(require, exports, module){
    'use strict'
    /**
     *  deep merge config
     */
    var mergeConfig = function(baseConfig, customConfig){
        // 遇到相同元素级属性，以后者（main）为准
        // 不返还新Object，而是main改变
        for (var key in customConfig) {
            if (baseConfig[key] === undefined) { // 不冲突的，直接赋值
                baseConfig[key] = customConfig[key];
                continue;
            }
            // 出现命名冲突，如果是对象，执行递归赋值，否则直接赋值
            if (isObject(customConfig[key])) {
                // 递归调用
                mergeConfig(customConfig[key], baseConfig[key]);
            }else{
                baseConfig[key] = customConfig[key]
            }
        }

        return baseConfig;
    }
    module.exports.mergeConfig = mergeConfig;

    /**
     * create Document Node
     */
    module.exports.createElement = function(tagname, id, properties, classes){
        // 参数重组；properties为非必传项
        if(typeof properties === 'string' || Array.isArray(properties)){
            classes = properties;
            properties = undefined;
        }
        var dom = document.createElement(tagname);

        id && (dom.id = id);

        if(isObject(properties)){
            for(var key in properties){
                var value = properties[key];
                if(typeof value === 'undefined'){
                    continue;
                }
                dom.setAttribute(key, value);
            }
        }

        if(typeof classes === 'undefined'){
            return dom
        }else if(typeof classes === 'string'){
            dom.className = classes
        }else if(Array.isArray(classes)){
            dom.className = classes.join(' ');
        }

        return dom;
    }

    module.exports.isIE = function() {
        if (!!window.ActiveXObject || "ActiveXObject" in window){
            return true;
        }else{
            return false;
        }
    }

    /**
     *  对象类型判断
     */
    function isObject(target) {
        return typeof target == "object" && target.constructor == Object;
    }

})