package com.zl.lqian.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface OrderService {


    /**
     * 下单接口
     * @param accessToken
     * @param passengerName
     * @param passportNo
     * @param sex
     * @param contactMobile
     * @param contactName
     * @param trainInfoNode
     * @return
     */
    String buildOrder(String accessToken, String passengerName,
                      String passportNo, String sex, String contactMobile,
                      String contactName, JsonNode trainInfoNode);

}
