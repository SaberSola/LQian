package com.zl.lqian.service.impl;

import com.zl.lqian.common.CommonConst;
import com.zl.lqian.service.SsoAuthService;
import org.springframework.stereotype.Service;

@Service
public class SsoAuthServiceImpl implements SsoAuthService {

    @Override
    public Class factoryOf(final String clientId){

        try {
            if (clientId.equals(CommonConst.SAAS_AGENT_CLIENT_ID)){
                return AgentLoginServiceImpl.class;
            }else {
                //TODO 不同的clientId 以后这里直接else即可
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
