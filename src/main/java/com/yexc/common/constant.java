package com.yexc.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/8/21.
 */

@Component
public class constant {
    @Value("#{configProperties['bathPath']}")
    private String bathPath;

    @Value("#{configProperties['localBasePath']}")
    private String localBasePath;

    private String ssoServerLogin=bathPath+"/ssoServer/sso/page/login";
    private String ssoServerVerify=bathPath+"/ssoServer/sso/token/verify";
    private String ssoServerLoginOut=bathPath+"/ssoServer/sso/user/loginOut";
    private String returnUrl=localBasePath+"/ssoClient/user/login";
    private String loginOutUrl=localBasePath+"/ssoClient/user/loginOut";

}
