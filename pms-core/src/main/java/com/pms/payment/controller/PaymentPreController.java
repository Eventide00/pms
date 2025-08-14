package com.pms.payment.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.base.ActionResult;
import com.pms.base.exception.DataException;
import com.pms.base.util.DynDicUtil;
import com.pms.base.util.JsonUtil;
import com.pms.base.util.UserProvider;
import com.pms.base.vo.PageListVO;
import com.pms.base.vo.PaginationVO;
import com.pms.common.annotation.Log;
import com.pms.common.core.domain.entity.SysUser;
import com.pms.common.enums.BusinessType;
import com.pms.payment.entity.PaymentPreEntity;
import com.pms.payment.model.paymentpre.PaymentPreCrForm;
import com.pms.payment.model.paymentpre.PaymentPreInfoVO;
import com.pms.payment.model.paymentpre.PaymentPreListVO;
import com.pms.payment.model.paymentpre.PaymentPrePagination;
import com.pms.payment.model.paymentpre.PaymentPreRefundForm;
import com.pms.payment.service.PaymentPreAccountService;
import com.pms.payment.service.PaymentPreService;
import com.pms.util.ConstantsUtil;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "预付款管理", description = "")
@RequestMapping("/payment/PaymentPre")
public class PaymentPreController {
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private DynDicUtil dynDicUtil;
    @Autowired
    private PaymentPreService paymentPreService;
    @Autowired
    private PaymentPreAccountService paymentPreAccountService;

    /**
     * 列表
     *
     * @param paymentPrePagination
     * @return
     */
    @PreAuthorize("@ss.hasRole('payee')")
    @GetMapping
    public ActionResult list(PaymentPrePagination paymentPrePagination) throws IOException {
        List<PaymentPreEntity> list = paymentPreService.getList(paymentPrePagination);

        for (PaymentPreEntity entity : list) {
            entity.setPayType(dynDicUtil.getDicName(entity.getPayType()));
        }
        List<PaymentPreListVO> listVO = JsonUtil.getJsonToList(list, PaymentPreListVO.class);
        PageListVO vo = new PageListVO();
        vo.setList(listVO);
        PaginationVO page = JsonUtil.getJsonToBean(paymentPrePagination, PaginationVO.class);
        vo.setPagination(page);
        return ActionResult.success(vo);
    }

    /**
     * 收取预存款
     *
     * @param paymentPreCrForm
     * @return
     */
    @PreAuthorize("@ss.hasRole('payee')")
    @Log(title = "新增预付款", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @Transactional
    public ActionResult deposit(@RequestBody @Valid PaymentPreCrForm paymentPreCrForm) throws DataException {
        SysUser userInfo = userProvider.get();
        PaymentPreEntity entity = JsonUtil.getJsonToBean(paymentPreCrForm, PaymentPreEntity.class);
        paymentPreService.create(entity, ConstantsUtil.PAY_PRE_TYPE_ADD);
        return ActionResult.success("预存成功");
    }

    /**
     * 退还预存款
     *
     * @param paymentPreCrForm
     * @return
     */
    @PreAuthorize("@ss.hasRole('payee')")
    @Log(title = "预付款退款", businessType = BusinessType.UPDATE)
    @PostMapping("/refund")
    @Transactional
    public ActionResult withdraw(@RequestBody @Valid PaymentPreRefundForm paymentPreRefundForm) throws DataException {
        SysUser userInfo = userProvider.get();
        paymentPreService.refund(paymentPreRefundForm);
        return ActionResult.success("预付款退还成功");
    }

    /**
     * 信息
     *
     * @param id
     * @return
     */
    @PreAuthorize("@ss.hasRole('payee')")
    @GetMapping("/{id}")
    public ActionResult<PaymentPreInfoVO> info(@PathVariable("id") String id) {
        PaymentPreEntity entity = paymentPreService.getInfo(id);
        PaymentPreInfoVO vo = JsonUtil.getJsonToBean(entity, PaymentPreInfoVO.class);
        return ActionResult.success(vo);
    }
}
