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

public class ChildNode extends Node{

    private ChildProperties properties;
    //外层id
    private String outerNodeId;

    public ChildProperties getProperties() {
        return properties;
    }

    public void setProperties(ChildProperties properties) {
        this.properties = properties;
    }

    public String getOuterNodeId() {
        return outerNodeId;
    }

    public void setOuterNodeId(String outerNodeId) {
        this.outerNodeId = outerNodeId;
    }
}