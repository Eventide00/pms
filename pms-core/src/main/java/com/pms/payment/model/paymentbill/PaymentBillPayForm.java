package com.pms.payment.model.paymentbill;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pms.payment.entity.PaymentBillEntity;
import com.pms.payment.model.paymentpaylog.PaymentPayLogCrForm;
import com.pms.payment.model.paymentpreaccount.PaymentPreAccountPayForm;

import lombok.Data;

@Data
public class PaymentBillPayForm extends PaymentPayLogCrForm {

    /** 支付的缴费单 */
    @JsonProperty("paymentBills")
    private List<PaymentBillListVO> paymentBills;

    @JsonProperty("accountForms")
    private List<PaymentPreAccountPayForm> accountForms;
}