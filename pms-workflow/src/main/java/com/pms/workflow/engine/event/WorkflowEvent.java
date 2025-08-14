/**
 * Copyright 智慧物业管理系统
 *
 * Licensed under AGPL开源协议
 *
 * Github：https://github.com/Eventide00/pms
 * website：http://pms.example.com  wx： pms-support
 *
 */
package com.pms.workflow.engine.event;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.context.ApplicationEvent;
import com.pms.baseconfig.entity.ConfigHouseBlockEntity;
import lombok.Data;

@Data
public class WorkflowEvent extends ApplicationEvent {

    private static final long serialVersionUID = -7343153614939095945L;
    private ProcessInstance processInstance;
    public static final String EVENT_CANCEL_APPLY = "cancelApply"; //撤销申请
    private String eventName;

    public WorkflowEvent(Object source, ProcessInstance process, String eventName) {
        super(source);
        this.eventName = eventName;
        this.processInstance = process;
    }

}
