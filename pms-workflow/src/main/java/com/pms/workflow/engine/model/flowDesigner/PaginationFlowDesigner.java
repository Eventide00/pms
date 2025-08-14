package com.pms.workflow.engine.model.flowDesigner;

import com.pms.base.vo.Pagination;

import lombok.Data;

@Data
public class PaginationFlowDesigner extends Pagination {
    private String enabledMark;
    private String fullName;
}
