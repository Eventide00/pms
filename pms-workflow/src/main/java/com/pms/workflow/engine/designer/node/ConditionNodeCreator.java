/**
 * Copyright 智慧物业管理系统
 *
 * Licensed under AGPL开源协议
 *
 * Github：https://github.com/Eventide00/pms
 * website：http://pms.example.com  wx： pms-support
 *
 */
package com.pms.workflow.engine.designer.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.activiti.bpmn.model.ExclusiveGateway;

import com.pms.workflow.engine.designer.entity.ConditionNode;

public class ConditionNodeCreator {
    
    public static final String GATEWAY_SUFFIX = "-gateway";
    
    public static List<ExclusiveGateway> createGateWay(List<ConditionNode> conditions) {
        List<ExclusiveGateway> gateways = new ArrayList<ExclusiveGateway>();
        
        Map<String, List<ConditionNode>> prev = conditions.stream().collect(Collectors.groupingBy(ConditionNode::getPrevId));
        for (String s: prev.keySet()) {
            ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
            exclusiveGateway.setId(s+ConditionNodeCreator.GATEWAY_SUFFIX);
            gateways.add(exclusiveGateway);
        }
        
        return gateways;
    }
}
