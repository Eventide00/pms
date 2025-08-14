package com.pms.base.util;

import org.springframework.stereotype.Component;

import com.pms.common.core.domain.entity.SysUser;
import com.pms.common.utils.SecurityUtils;

@Component
public class UserProvider {

    public SysUser get() {
    	SysUser user = SecurityUtils.getLoginUser().getUser();
        return user;
    }

   
}
