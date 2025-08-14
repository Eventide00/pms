package com.pms.payment.model.paymentmeter;

import com.pms.base.vo.Pagination;

import lombok.Data;

@Data
public class PaymentMeterPagination extends Pagination {
    /** 资源名 */
    private String resourceName;

    /** 收费项id */
    private String feeItemId;

}