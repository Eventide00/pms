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
import com.pms.payment.entity.PaymentDepositEntity;
import com.pms.payment.model.paymentdeposit.PaymentDepositCrForm;
import com.pms.payment.model.paymentdeposit.PaymentDepositPagination;
import com.pms.payment.model.paymentdeposit.PaymentDepositRefundForm;

import java.util.*;

/**
 *
 * payment_deposit 版本： V3.1.0 版权： 作者： CYCBERFORM 日期： 2021-09-29 17:54:49
 */
public interface PaymentDepositService extends IService<PaymentDepositEntity> {

    List<PaymentDepositEntity> getList(PaymentDepositPagination paymentDepositPagination);

    List<PaymentDepositEntity> getTypeList(PaymentDepositPagination paymentDepositPagination, String dataType);

    PaymentDepositEntity getInfo(String id);

    void delete(PaymentDepositEntity entity);

    void create(PaymentDepositEntity entity);

    boolean refund(String id, PaymentDepositRefundForm form);
}
