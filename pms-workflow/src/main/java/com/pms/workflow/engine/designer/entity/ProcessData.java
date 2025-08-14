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

public class ProcessData extends Node{

    private StartProperties properties;

    public StartProperties getProperties() {
        return properties;
    }

    public void setProperties(StartProperties properties) {
        this.properties = properties;
    }
}