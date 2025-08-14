package com.pms.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.base.ActionResult;
import com.pms.baseconfig.service.ConfigFeeItemService;
import com.pms.common.annotation.Log;
import com.pms.common.enums.BusinessType;
import com.pms.payment.entity.PaymentPayLogEntity;
import com.pms.payment.model.paymentbill.PaymentBillPayForm;
import com.pms.payment.model.paymentbill.PaymentBillRefundForm;
import com.pms.payment.service.PaymentBillService;
import com.pms.payment.service.PaymentPayLogService;
import com.pms.payment.service.PaymentPreAccountService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "收费数据支付", description = "")
@RequestMapping("/payment/PaymentBillPay")
public class PaymentBillPayController {
    @Autowired
    private PaymentBillService paymentBillService;

    @PreAuthorize("@ss.hasRole('payee')")
    @PostMapping("/payCalc")
    public ActionResult calc(@RequestBody PaymentBillPayForm payForm) throws IllegalAccessException {
        paymentBillService.payCalc(payForm);
        return ActionResult.success(payForm);
    }

    @PreAuthorize("@ss.hasRole('payee')")
    @PostMapping("/payChceck")
    public ActionResult check(@RequestBody PaymentBillPayForm payForm) throws IllegalAccessException {
        paymentBillService.payCalc(payForm);
        paymentBillService.payCheck(payForm);
        return ActionResult.success();
    }

    @PreAuthorize("@ss.hasRole('payee')")
    @Log(title = "收费数据支付", businessType = BusinessType.PAY)
    @PostMapping("/payBill")
    @Transactional
    public ActionResult pay(@RequestBody PaymentBillPayForm payForm) throws IllegalAccessException {
        paymentBillService.payCalc(payForm);
        paymentBillService.payCheck(payForm);
        PaymentPayLogEntity payLog = paymentBillService.paySave(payForm);
        return ActionResult.success(payLog);
    }
    
    @PreAuthorize("@ss.hasRole('manager')")
    @Log(title = "收费数据支付", businessType = BusinessType.REFUND)
    @PostMapping("/refundBill")
    @Transactional
    public ActionResult refund(@RequestBody PaymentBillRefundForm refundForm) throws IllegalAccessException {
        paymentBillService.refundBill(refundForm);
        return ActionResult.success("退款成功");
    }
}
