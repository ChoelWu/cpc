package com.joe.pojo;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/13
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 
// +----------------------------------------------------------------------

import com.joe.entity.IndexChannel;

import java.io.Serializable;
import java.util.List;

public class Channel implements Serializable {

    // 父菜单
    private IndexChannel indexChannel;

    // 子菜单
    private List<IndexChannel> childChannelList;

    public IndexChannel getIndexChannel() {
        return indexChannel;
    }

    public void setIndexChannel(IndexChannel indexChannel) {
        this.indexChannel = indexChannel;
    }

    public List<IndexChannel> getChildChannelList() {
        return childChannelList;
    }

    public void setChildChannelList(List<IndexChannel> childChannelList) {
        this.childChannelList = childChannelList;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "indexChannel=" + indexChannel +
                ", childChannelList=" + childChannelList +
                '}';
    }
}
