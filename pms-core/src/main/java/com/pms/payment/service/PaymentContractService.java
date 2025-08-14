/**
 * Copyright 肇新智慧物业管理系统
 *
 * Licensed under AGPL开源协议
 *
 * gitee：https://gitee.com/fanhuibin1/pms
 * website：http://pms.zhaoxinms.com  wx： zhaoxinms
 *
 */
package com.pms.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pms.base.exception.DataException;
import com.pms.payment.entity.PaymentContractEntity;
import com.pms.payment.model.paymentcontract.PaymentContractCrForm;
import com.pms.payment.model.paymentcontract.PaymentContractPagination;

import java.util.*;

public interface PaymentContractService extends IService<PaymentContractEntity> {

    List<PaymentContractEntity> getList(PaymentContractPagination paymentContractPagination);

    List<PaymentContractEntity> getTypeList(PaymentContractPagination paymentContractPagination, String dataType);

    List<PaymentContractEntity> getListByResourceIds(List<String> ids);

    PaymentContractEntity getInfo(String id);

    /**
     * 取消房屋租赁合同
     * 
     * @param id
     * @param entity
     * @return
     */
    void cancelContract(String houseId) throws DataException;

    /**
     * 创建合同
     * 
     * @param form
     * @throws DataException
     */
    void create(PaymentContractCrForm form) throws DataException;

    /**
     * 更新合同
     * 
     * @param form
     * @throws DataException
     */
    void update(String id, PaymentContractCrForm form) throws DataException;

    PaymentContractEntity getByResourceName(String resourceName);

    List<PaymentContractEntity> getByResourceNameTips(String resourceName);

    List<PaymentContractEntity> getByOwnerId(String ownerId);

    List<PaymentContractEntity> getDisabledByOwnerId(String ownerId);
}
