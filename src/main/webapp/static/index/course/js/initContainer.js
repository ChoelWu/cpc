/**
 * 创建弹出框容器文件
 */
define(function(require, exports, module){
    'use strict'
    var Utils = require('../utils.js');
    var styleSheel = require('../style/video-themes.js');
    var Obsever = require('./observer.js');
    /**
     * 入口函数
     */
    var initContainer = function(config){
        var mediaList = config.mediaSourceList;
        return createContainer(mediaList);
    }

    /**
     * 创建播放器容器、播放列表
     */
    var createContainer = function(sourceList){
        if(!Array.isArray(sourceList) || sourceList.length < 1){
            console.warn('param "mediaSourceList" is empty or is not Array')
            sourceList = [];
        }
        // 创建元素
        // (tagname:string, id:string|null, properties?:object, classes?: string|string[]) => HTMLDivElement
        var mask           = Utils.createElement('div', 'pop-video-mask');
        var container      = Utils.createElement('div', 'pop-video-container');
        var containerTitle = Utils.createElement('div', null, 'pop-video-title');
        var titleText      = Utils.createElement('h2', null);
        var closeBtn       = Utils.createElement('div', null, {'data-target': '#pop-video-mask'}, ['pop-video-close']);
        var listBox        = Utils.createElement('ul', null, 'chapter-list');
        var listItem       = createPlayListDom(sourceList);

        // 插入内容
        titleText.innerText = '课程预览';

        // 绑定事件
        closeBtn.addEventListener('click', function(){
            mask.remove();
            Obsever.fire('closePlayer', {}) // 触发关闭Player消息推送
        })

        // 拼合DOM
        Array.prototype.forEach.call(listItem,function(item){
            listBox.appendChild(item);
        })
        containerTitle.appendChild(closeBtn);
        containerTitle.appendChild(titleText);
        container.appendChild(containerTitle);
        container.appendChild(listBox);
        mask.appendChild(container);

         // 挂载完成触发挂载完成消息推送
        Obsever.fire('containerMounted', {target: mask});

        // 挂载样式表 容器
        initStyleSheel();
        document.body.appendChild(mask);



        return mask;
    }

    /**
     *  根据媒体信息列表遍历生成列表项DOM
     *  ( sourceList: any[] ) => HTMLDivElement[]
     */
    var createPlayListDom = function(sourceList){
        var listItem = [];
        if(sourceList.length < 1){
            return listItem;
        }
        Array.prototype.forEach.call(sourceList,function(item, index){
            var li        = Utils.createElement('li', null, {'data-current':index }, 'chapter-item');
            var icon      = Utils.createElement('i', null, 'imv2-video_circle');
            var title     = Utils.createElement('div', null, 'chapter-item-title')
            var state     = Utils.createElement('div', null, 'chapter-item-state');
            var videoSlot = Utils.createElement('div', 'chapter-player-'+index, 'chapter-player-slot');
            var liBtn     = Utils.createElement('a', null, {
                'href': 'javascript:;',
                'data-mid': item.media_id,
                'data-title': item.chapter_seqid + ' - ' + item.seqid + item.name,
                'data-idx': index
            },['js-change-media','chapter-item-trigger']);

            liBtn.addEventListener('click', function(e){
                var idx = this.dataset.idx;
                var isActive = /active/ig.test(this.className);
                Array.prototype.forEach.call(this.parentNode.parentNode.querySelectorAll('.chapter-item-trigger'), function(item, index){
                    if(index != idx){
                        item.className = item.className.replace(/active/ig, 'close');
                    }
                })

                if(!isActive){
                    if(/close/ig.test(this.className)){
                        this.className = this.className.replace('close', 'active')
                    }else{
                        this.className += ' active'
                    }
                }else{
                    this.className = this.className;
                }
                // this.className = /active/ig.test(this.className) ? this.className : this.className += ' active'

                // 触发切换媒体事件广播
                Obsever.fire('changeMediaItem', {
                    videoSlot:videoSlot,
                    index:index,
                    mid: item.media_id,
                    title:item.chapter_seqid + ' - ' + item.seqid + item.name
                })
            })

            state.innerText = '正在观看';
            title.innerText = item.name;
            liBtn.appendChild(icon);
            liBtn.appendChild(title);
            liBtn.appendChild(state);
            li.appendChild(liBtn);
            li.appendChild(videoSlot);

            listItem.push(li);
        })

        return listItem;
    }

    /**
     * 注入样式表
     */
    var initStyleSheel = function(){
        var style = document.createElement('style');
        style.id = 'POP-VIDEO-STYLESHEEL';
        style.innerText = styleSheel.join('');
        document.head.appendChild(style);
    }

    module.exports = initContainer
})