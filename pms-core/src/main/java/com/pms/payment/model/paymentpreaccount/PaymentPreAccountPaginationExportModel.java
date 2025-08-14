package com.pms.payment.model.paymentpreaccount;

import com.pms.base.vo.Pagination;

import lombok.Data;

@Data
public class PaymentPreAccountPaginationExportModel extends Pagination {

    private String selectKey;

    private String json;

    private String dataType;

    /** 资源id */
    private String resourceId;

    /** 商业区 */
    private String block;

    /**  */
    private String feeItemId;

    /**  */
    private String amt;

}