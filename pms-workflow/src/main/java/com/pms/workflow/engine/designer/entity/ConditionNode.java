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

import java.util.List;

public class ConditionNode extends Node {

    private ConditionProperties properties;

    public ConditionProperties getProperties() {
        return properties;
    }

    public void setProperties(ConditionProperties properties) {
        this.properties = properties;
    }
}