/**
 * Copyright 智慧物业管理系统
 *
 * Licensed under AGPL开源协议
 *
 * Github：https://github.com/Eventide00/pms
 * website：http://pms.example.com  wx： pms-support
 *
 */
package com.pms.payment.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pms.payment.entity.PaymentTempEntity;
import com.pms.payment.model.paymenttemp.PaymentTempPagination;
import com.pms.payment.model.paymenttemp.PaymentTempRefundForm;

public interface PaymentTempService extends IService<PaymentTempEntity> {

    List<PaymentTempEntity> getList(PaymentTempPagination paymentTempPagination);

    List<PaymentTempEntity> getTypeList(PaymentTempPagination paymentTempPagination, String dataType);

    PaymentTempEntity getInfo(String id);

    void delete(PaymentTempEntity entity);

    void create(PaymentTempEntity entity);

    boolean refund(String id, PaymentTempRefundForm form);
}
