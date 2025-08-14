package com.pms.statistics.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.base.ActionResult;
import com.pms.base.util.DynDicUtil;
import com.pms.base.util.JsonUtil;
import com.pms.base.util.UserProvider;
import com.pms.base.vo.PageListVO;
import com.pms.base.vo.PaginationVO;
import com.pms.payment.entity.PaymentPayLogEntity;
import com.pms.payment.model.paymentpaylog.PaymentPayLogListVO;
import com.pms.payment.model.paymentpaylog.PaymentPayLogPagination;
import com.pms.payment.service.PaymentPayLogService;
import com.pms.util.ConstantsUtil;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "用户支付统计")
@RequestMapping("/statistics/PaymentPayLog")
public class PaymentPayLogController {
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private DynDicUtil dynDicUtil;
    @Autowired
    private PaymentPayLogService paymentPayLogService;

    @PreAuthorize("@ss.hasPermi('statistics:paymentBill:overdue')")
    @GetMapping
    public ActionResult list(PaymentPayLogPagination paymentPayLogPagination) throws IOException {
        List<PaymentPayLogEntity> list = paymentPayLogService.getList(paymentPayLogPagination);
        // 处理id字段转名称，若无需转或者为空可删除

        for (PaymentPayLogEntity entity : list) {
            entity.setPayMethod(dynDicUtil.getDicName(entity.getPayMethod()));
            if (entity.getType().equals(ConstantsUtil.PAY_LOG_TYPE_PAY)) {
                entity.setType("支付");
            } else {
                entity.setType("退款");
            }
        }
        List<PaymentPayLogListVO> listVO = JsonUtil.getJsonToList(list, PaymentPayLogListVO.class);
        PageListVO vo = new PageListVO();
        vo.setList(listVO);
        PaginationVO page = JsonUtil.getJsonToBean(paymentPayLogPagination, PaginationVO.class);
        vo.setPagination(page);
        return ActionResult.success(vo);
    }
}
