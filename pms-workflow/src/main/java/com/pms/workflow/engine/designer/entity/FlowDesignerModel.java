/**
 * Copyright 智慧物业管理系统
 *
 * Licensed under AGPL开源协议
 *
 * Github：https://github.com/Eventide00/pms
 * website：http://pms.example.com  wx： pms-support
 *
 */
package com.pms.workflow.engine.designer.entity;

import com.alibaba.fastjson.JSONObject;

public class FlowDesignerModel {

    private BasicSetting basicSetting;
    private ProcessData processData;
    private JSONObject formData;

    public void setBasicSetting(BasicSetting basicSetting) {
        this.basicSetting = basicSetting;
    }

    public BasicSetting getBasicSetting() {
        return basicSetting;
    }

    public void setProcessData(ProcessData processData) {
        this.processData = processData;
    }

    public ProcessData getProcessData() {
        return processData;
    }

    public JSONObject getFormData() {
        return formData;
    }

    public void setFormData(JSONObject formData) {
        this.formData = formData;
    }
}