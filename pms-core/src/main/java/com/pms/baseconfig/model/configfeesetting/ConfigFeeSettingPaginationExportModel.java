package com.pms.baseconfig.model.configfeesetting;


import com.pms.base.vo.Pagination;

import lombok.Data;

@Data
public class ConfigFeeSettingPaginationExportModel extends Pagination {

    private String selectKey;

    private String json;

    private String dataType;

}