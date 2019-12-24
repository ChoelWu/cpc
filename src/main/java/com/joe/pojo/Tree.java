package com.joe.pojo;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/21
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 
// +----------------------------------------------------------------------

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tree {
    // 节点标题
    private String title;

    // 节点唯一索引值，用于对指定节点进行各类操作
    private String id;

    // 节点字段名
    private String field;

    // 子节点。支持设定选项同父节点
    private List<Tree> children;

    // 点击节点弹出新窗口对应的 url。需开启 isJump 参数
    private String href;

    // 节点是否初始展开，默认 false
    private boolean spread = true;

    // 节点是否初始为选中状态（如果开启复选框的话），默认 false
    private boolean checked;

    // 节点是否为禁用状态。默认 false
    private boolean disabled;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", field='" + field + '\'' +
                ", children=" + children +
                ", href='" + href + '\'' +
                ", spread=" + spread +
                ", checked=" + checked +
                ", disabled=" + disabled +
                '}';
    }
}
