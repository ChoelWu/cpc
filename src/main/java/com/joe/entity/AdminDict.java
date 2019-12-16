package com.joe.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author joe
 * @since 2019-11-17
 */
@TableName("cpc_admin_dict")
public class AdminDict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典ID
     */
    @TableId(value = "dict_id", type = IdType.AUTO)
    private Long dictId;

    /**
     * 字典编号 ADNO+yyyymmddhhmmss+1位随机大写字母+5位随机数字
     */
    private String dictNo;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典键名
     */
    private String dictKey;

    /**
     * 字典键值
     */
    private String dictValue;

    /**
     * 字典键值
     */
    private String dictIndex;

    /**
     * 字典键值
     */
    private String menuNo;

    /**
     * 备用字段
     */
    private String bak;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 添加人
     */
    private String addUserNo;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateUserNo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getDictNo() {
        return dictNo;
    }

    public void setDictNo(String dictNo) {
        this.dictNo = dictNo;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictIndex() {
        return dictIndex;
    }

    public void setDictIndex(String dictIndex) {
        this.dictIndex = dictIndex;
    }

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddUserNo() {
        return addUserNo;
    }

    public void setAddUserNo(String addUserNo) {
        this.addUserNo = addUserNo;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserNo() {
        return updateUserNo;
    }

    public void setUpdateUserNo(String updateUserNo) {
        this.updateUserNo = updateUserNo;
    }

    @Override
    public String toString() {
        return "AdminDict{" +
                "dictId=" + dictId +
                ", dictNo='" + dictNo + '\'' +
                ", dictName='" + dictName + '\'' +
                ", dictKey='" + dictKey + '\'' +
                ", dictValue='" + dictValue + '\'' +
                ", dictIndex='" + dictIndex + '\'' +
                ", menuNo='" + menuNo + '\'' +
                ", bak='" + bak + '\'' +
                ", addTime=" + addTime +
                ", addUserNo='" + addUserNo + '\'' +
                ", updateTime=" + updateTime +
                ", updateUserNo='" + updateUserNo + '\'' +
                '}';
    }
}
