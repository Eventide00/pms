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

public class Conditions {

    private String condition;
    private String conditonLabel;

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setConditonLabel(String conditonLabel) {
        this.conditonLabel = conditonLabel;
    }

    public String getConditonLabel() {
        return conditonLabel;
    }

}