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

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pms.baseconfig.entity.ConfigFeeItemEntity;
import com.pms.payment.entity.PaymentBillEntity;
import com.pms.payment.entity.PaymentContractEntity;
import com.pms.payment.model.paymentbill.PaymentBillBatchForm;
import com.pms.payment.model.paymentbill.PaymentBillGenerateForm;
import com.pms.payment.model.paymentbill.PaymentBillPagination;

/**
 *
 * payment_bill 版本： V3.1.0 版权： 作者： CYCBERFORM 日期： 2021-08-28 17:46:03
 */
public interface PaymentBillCreateService extends IService<PaymentBillEntity> {

    // 新增收费数据
    void create(PaymentBillEntity entity);

    // 批量新增收费数据预览
    List<PaymentBillEntity> getBatchCreateData(@Valid PaymentBillBatchForm paymentBillBatchForm);

    // 批量新增收费数据
    void batchCreate(@Valid PaymentBillBatchForm paymentBillBatchForm);

    // 自动生成收费数据
    List<PaymentBillEntity> getOrCreateGenerateData(@Valid PaymentBillGenerateForm paymentBillGenerateForm,
        boolean create) throws ParseException;

    // 生成抄表收费数据
    List<PaymentBillEntity> getOrCreateMeterData(@Valid PaymentBillGenerateForm paymentBillGenerateForm, boolean create)
        throws ParseException;
}
