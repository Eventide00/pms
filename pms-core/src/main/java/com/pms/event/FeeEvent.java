package com.pms.event;

import org.springframework.context.ApplicationEvent;

import com.pms.baseconfig.entity.ConfigFeeItemEntity;
import com.pms.payment.entity.PaymentContractEntity;

import lombok.Data;

@Data
public class FeeEvent extends ApplicationEvent {

    public static final String STATE_DELETE = "delete";
    private String state;
    private ConfigFeeItemEntity item;

    public FeeEvent(Object source, ConfigFeeItemEntity item, String state) {
        super(source);
        this.state = state;
        this.item = item;
    }
}
