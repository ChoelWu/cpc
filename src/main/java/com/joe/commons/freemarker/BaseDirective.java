package com.joe.commons.freemarker;

import freemarker.template.*;
import org.apache.commons.lang3.StringUtils;

/**
 * platform：中欧班列长安号综合服务平台
 * Author：guojun.chen
 * createTime： 2018/8/28 21:20
 * version：1.0
 * description：freemarker自定义标签基础转换类
 */
public class BaseDirective {

    public String getString(TemplateModel paramValue) throws TemplateModelException {
        if (paramValue == null) {
            return "";
        } else {
            if (paramValue instanceof TemplateScalarModel) {  //是字符串
                String value = ((TemplateScalarModel) paramValue).getAsString();
                return value;
            } else {
                throw new TemplateModelException("String转换异常!");
            }
        }
    }

    public long getLong(TemplateModel paramValue) throws TemplateModelException {
        if (paramValue == null) {
            return 0;
        } else {
            if (paramValue instanceof TemplateScalarModel)  //是字符串
            {
                String value = ((TemplateScalarModel) paramValue).getAsString();
                return Long.valueOf(value);
            } else if (paramValue instanceof TemplateNumberModel)  //数字
            {
                return ((TemplateNumberModel) paramValue).getAsNumber().longValue();
            } else {
                throw new TemplateModelException("Long转换异常!");
            }
        }
    }

    public int getInt(TemplateModel paramValue) throws TemplateModelException {
        if (paramValue == null) {
            return 0;
        } else {
            if (paramValue instanceof TemplateScalarModel)  //是字符串
            {
                String value = ((TemplateScalarModel) paramValue).getAsString();
                return Integer.valueOf(value);
            } else if (paramValue instanceof TemplateNumberModel)  //数字
            {
                return ((TemplateNumberModel) paramValue).getAsNumber().intValue();
            } else {
                throw new TemplateModelException("int转换异常!");
            }
        }
    }

    public boolean getBoolean(TemplateModel paramValue) throws TemplateModelException {
        if (paramValue == null) {
            return false;
        } else {
            if (paramValue instanceof TemplateScalarModel)  //是字符串
            {
                String value = ((TemplateScalarModel) paramValue).getAsString();
                return Boolean.valueOf(value);
            } else if (paramValue instanceof TemplateBooleanModel)  //boolean
            {
                return ((TemplateBooleanModel) paramValue).getAsBoolean();
            } else {
                throw new TemplateModelException("boolean转换异常!");
            }
        }
    }

    /**
     * 检查是否有空值
     * @param values
     * @return
     */
    public boolean checkValueIsNull(String ... values) {
        for(String str : values) {
            if(StringUtils.isBlank(str)) {
                return  false;
            }
        }
        return true;
    }




}
