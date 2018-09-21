package com.zl.lqian.service.impl;
import com.zl.lqian.service.StationService;
import com.zl.lqian.utils.HttpClientUtils;
import com.zl.lqian.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StationServiceImpl implements StationService {


    private static final Logger logger = LoggerFactory.getLogger(StationServiceImpl.class);

    public Map<String, String> cityMap = new ConcurrentHashMap<>();

    @Override
    public String findCodeByCityName(String cityName) {

        if (cityMap == null || cityMap.size() == 0){

            Map headers = new HashMap<>();
            headers.put("Accept", "application/json, text/plain");
            headers.put("Accept-Encoding", "gzip, deflate");
            headers.put("Accept-Language", "zh-CN");
            headers.put("Connection", "keep-alive");
            headers.put("Content-Type", "application/json");
            headers.put("DNT", "1");
            headers.put("Host", "api.12306.com");
            headers.put("Origin", "http://www.12306.com");
            headers.put("Referer", "http://www.12306.com/");
            headers.put("User-Agent", HttpClientUtils.pcUserAgentArray[new Random().nextInt(HttpClientUtils.pcUserAgentArray.length)]);
            String stationsUrl = "http://api.12306.com/v1/train/stations";
            String json = HttpClientUtils.doGet(stationsUrl, null, headers);
            JsonNode jsonNode = JsonUtils.jsonToJsonNode(json);
            JsonNode dataNode = jsonNode.get("data");
            if (dataNode == null) {
                //TODO 这里入队列
                logger.error("解析城市信息json内的data数据为空，请看返回json是否有误，或者IP是否被封, url:" + stationsUrl + " ,json:" + json);
                return null;
            }
            for (JsonNode node : dataNode) {
                String name = node.get("cityName").asText();
                String cityCode = node.get("cityCode").asText();
                cityMap.put(name, cityCode);
            }
        }

        return  cityMap.get(cityName);
    }


    private void  doRetry(String cityName){

        if (cityMap == null || cityMap.size() == 0){
            Map headers = new HashMap<>();
            headers.put("Accept", "application/json, text/plain");
            headers.put("Accept-Encoding", "gzip, deflate");
            headers.put("Accept-Language", "zh-CN");
            headers.put("Connection", "keep-alive");
            headers.put("Content-Type", "application/json");
            headers.put("DNT", "1");
            headers.put("Host", "api.12306.com");
            headers.put("Origin", "http://www.12306.com");
            headers.put("Referer", "http://www.12306.com/");
            headers.put("User-Agent", HttpClientUtils.pcUserAgentArray[new Random().nextInt(HttpClientUtils.pcUserAgentArray.length)]);
            String stationsUrl = "http://api.12306.com/v1/train/stations";
            String json = HttpClientUtils.doGet(stationsUrl, null, headers);
            JsonNode jsonNode = JsonUtils.jsonToJsonNode(json);
            JsonNode dataNode = jsonNode.get("data");
            for (JsonNode node : dataNode) {
                String name = node.get("cityName").asText();
                String cityCode = node.get("cityCode").asText();
                cityMap.put(name, cityCode);
            }
        }
    }

    public static void main(String[] args){
        StationServiceImpl stationService = new StationServiceImpl();

        String name = stationService.findCodeByCityName("上海虹桥");
        System.out.println(name);
    }
}
