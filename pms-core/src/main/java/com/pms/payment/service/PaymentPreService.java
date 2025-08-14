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

import com.baomidou.mybatisplus.extension.service.IService;
import com.pms.payment.entity.PaymentPreEntity;
import com.pms.payment.model.paymentpre.PaymentPrePagination;
import com.pms.payment.model.paymentpre.PaymentPreRefundForm;

import java.util.*;

/**
 *
 * payment_pre 版本： V3.1.0 版权： 作者： CYCBERFORM 日期： 2021-10-13 18:27:17
 */
public interface PaymentPreService extends IService<PaymentPreEntity> {

    List<PaymentPreEntity> getList(PaymentPrePagination paymentPrePagination);

    List<PaymentPreEntity> getTypeList(PaymentPrePagination paymentPrePagination, String dataType);

    PaymentPreEntity getInfo(String id);

    void create(PaymentPreEntity entity, String type);

    void refund(PaymentPreRefundForm paymentPreRefundForm);

    void minus(PaymentPreEntity entity, String type);
}
