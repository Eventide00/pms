package com.pms.statistics.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.base.ActionResult;
import com.pms.base.exception.DataException;
import com.pms.base.util.JsonUtil;
import com.pms.base.vo.PageListVO;
import com.pms.base.vo.PaginationVO;
import com.pms.baseconfig.entity.ConfigFeeItemEntity;
import com.pms.baseconfig.mapper.ConfigHouseMapper;
import com.pms.baseconfig.service.ConfigFeeItemService;
import com.pms.baseconfig.service.impl.ConfigFeeItemServiceImpl;
import com.pms.payment.entity.PaymentBillEntity;
import com.pms.payment.model.paymentbill.PaymentBillListVO;
import com.pms.payment.model.paymentbill.PaymentBillPagination;
import com.pms.payment.model.paymentcontract.PaymentContractFeeListVO;
import com.pms.payment.service.PaymentBillService;
import com.pms.payment.service.PaymentContractFeeService;
import com.pms.statistics.mapper.PaymentStatisticsMapper;
import com.pms.statistics.service.DashboardService;
import com.pms.util.ConstantsUtil;
import com.pms.util.DateUtils;
import com.pms.util.InputCheckUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "欠费统计", description = "")
@RequestMapping("/statistics/paymentBill")
public class PaymentBillStatisticsController {

    @Autowired
    private PaymentBillService paymentBillService;

    // 欠费明细表
    @PreAuthorize("@ss.hasPermi('statistics:paymentBill:overdue')")
    @GetMapping("/overdue")
    public ActionResult list(PaymentBillPagination paymentBillPagination) {
        QueryWrapper<PaymentBillEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().and(t -> t.eq(PaymentBillEntity::getEnabledMark, 1));
        queryWrapper.lambda().and(t -> t.lt(PaymentBillEntity::getDeadline, new Date()));
        queryWrapper.lambda().and(t -> t.eq(PaymentBillEntity::getPayState, '0'));
        if (InputCheckUtil.isNotEmpty(paymentBillPagination.getResourceName())) {
            queryWrapper.lambda()
                .and(t -> t.like(PaymentBillEntity::getResourceName, paymentBillPagination.getResourceName()));
        }

        if (InputCheckUtil.isNotEmpty(paymentBillPagination.getResourceId())) {
            queryWrapper.lambda()
                .and(t -> t.eq(PaymentBillEntity::getResourceId, paymentBillPagination.getResourceId()));
        }

        if (InputCheckUtil.isNotEmpty(paymentBillPagination.getFeeItemId())) {
            queryWrapper.lambda().and(t -> t.eq(PaymentBillEntity::getFeeItemId, paymentBillPagination.getFeeItemId()));
        }
        queryWrapper.lambda().orderByDesc(PaymentBillEntity::getDeadline);

        Page<PaymentBillEntity> page =
            new Page<>(paymentBillPagination.getCurrentPage(), paymentBillPagination.getPageSize());
        IPage<PaymentBillEntity> userIPage = paymentBillService.page(page, queryWrapper);
        List<PaymentBillEntity> list = paymentBillPagination.setData(userIPage.getRecords(), userIPage.getTotal());

        List<PaymentBillListVO> listVO = JsonUtil.getJsonToList(list, PaymentBillListVO.class);
        PageListVO vo = new PageListVO();
        vo.setList(listVO);
        PaginationVO pageVO = JsonUtil.getJsonToBean(paymentBillPagination, PaginationVO.class);
        vo.setPagination(pageVO);
        return ActionResult.success(vo);
    }
}
