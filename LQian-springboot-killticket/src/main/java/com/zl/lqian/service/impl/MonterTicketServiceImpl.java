package com.zl.lqian.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.zl.lqian.configuration.TicketConfig;
import com.zl.lqian.service.LoginService;
import com.zl.lqian.service.MonterTicketService;
import com.zl.lqian.service.OrderService;
import com.zl.lqian.service.StationService;
import com.zl.lqian.utils.HttpClientUtils;
import com.zl.lqian.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MonterTicketServiceImpl implements MonterTicketService {

    private static final Logger logger = LoggerFactory.getLogger(MonterTicketServiceImpl.class);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H:mm");

    private final static  AtomicLong counter = new AtomicLong(1);

    private LoginService loginService;

    private OrderService orderService;

    private StationService stationService;

    private TicketConfig ticketConfig;

    String trainInfoUrl;

    @Autowired
    public  MonterTicketServiceImpl(final LoginService loginService,
                                    final OrderService orderService,
                                    final StationService stationService,
                                    final TicketConfig ticketConfig){

        this.loginService = loginService;
        this.orderService = orderService;
        this.stationService = stationService;
        this.ticketConfig = ticketConfig;
    }


    @Override
    public void monitor() {
        try {
            logger.info("开始进行第 " + counter.getAndIncrement() + " 次检测");
            Map<String, String> headers = new HashMap<>();
            headers.put("Accept", "application/json, text/plain");
            headers.put("Accept-Encoding", "gzip, deflate");
            headers.put("Accept-Language", "zh-CN");
            headers.put("Connection", "keep-alive");
            headers.put("Content-Type", "application/json");
            headers.put("DNT", "1");
            headers.put("Host", "api.12306.com");
            headers.put("Origin", "http://www.12306.com");
            headers.put("Referer", "http://www.12306.com/");
            String maskIp = ((int)(Math.random()*200)+50)+"."+((int)(Math.random()*200)+50)+"."+((int)(Math.random()*200)+50)+"."+((int)(Math.random()*200)+50);
            headers.put("X-Real-IP", maskIp);
            headers.put("X-Forwarded-For", maskIp);
            headers.put("User-Agent", HttpClientUtils.pcUserAgentArray[new Random().nextInt(HttpClientUtils.pcUserAgentArray.length)]);

            //获取列车信息
            JsonNode trainInfosNode = getTrainInfosNode(ticketConfig.fromstation, ticketConfig.tostation,
                    ticketConfig.deptdate, ticketConfig.justgd, headers);

            if (trainInfosNode == null){
                logger.error("没有符合要求的班次,请检查 " + ticketConfig.timerange +
                        " 是否有符合要求的列车信息。目标座位为:" + ticketConfig.seatname);
                return;
            }
            //todo 获取符合列车里的信息
            //获取适合的列车信息（符合配置文件中配置的）
            List<JsonNode> trainNodes = getApplicableTrainInfoNodes(trainInfosNode, ticketConfig.seatname, ticketConfig.timerange);
            if (trainNodes == null) {
                logger.error("没有符合要求的班次,请检查 " + ticketConfig.timerange + " 是否有符合要求的列车信息。目标座位为:" + ticketConfig.seatname);
                return;
            }
            for(JsonNode infoNode : trainNodes){
                //判断是不是可售时间
                if (!isSellTime(infoNode)){
                    continue;
                }
                JsonNode classSeatNode = getClassSeatNode(infoNode, ticketConfig.seatname);
                JsonNode seatNum = classSeatNode.get("seatNum");
                if ("0".equals(seatNum.asText())) {
                    continue;
                }

                //通过登陆获取accessToken
                String accessToken = getAccessTokenByLogin(ticketConfig.account, ticketConfig.password);
                if (StringUtils.isEmpty(accessToken)) {
                    logger.error("登陆失败，请检查username和password");
                    continue;
                }

                //构建订单
                JsonNode orderNode = buildOrder(accessToken, ticketConfig.passengername, ticketConfig.passportno,
                        ticketConfig.sex, ticketConfig.contactmobile, ticketConfig.contactname, infoNode);
                if (orderNode == null) {
                    continue;
                }

                //todo 开始占座
                occupy(accessToken, orderNode, infoNode, ticketConfig.seatname, headers);
            }

        }catch (Exception e){
            logger.error("第 " + counter.get() + " 次检测报错", e);
        }
    }

    @Override
    public void noBrainPlaceOrder() {
        try {
            Map<String, String> headers = new HashMap<>();
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

            //获取列车信息
            JsonNode trainInfosNode = getTrainInfosNode(ticketConfig.fromstation, ticketConfig.tostation,
                    ticketConfig.deptdate, ticketConfig.justgd, headers);
            if (trainInfosNode == null) {
                logger.error("获取列车信息失败");
                return;
            }

            //获取适合的列车信息（符合配置文件中配置的）
            List<JsonNode> trainNodes = getApplicableTrainInfoNodes(trainInfosNode, ticketConfig.seatname,
                    ticketConfig.timerange);
            if (trainNodes == null) {
                logger.error("没有符合要求的班次,请检查 " + ticketConfig.timerange + " 是否有符合要求的列车信息");
                return;
            }

            while (true) {
                for (JsonNode infoNode : trainNodes) {

                    if (!isSellTime(infoNode)) {
                        continue;
                    }

                    JsonNode classSeatNode = getClassSeatNode(infoNode, ticketConfig.seatname);
                    JsonNode seatNum = classSeatNode.get("seatNum");

                    //通过登陆获取accessToken
                    String accessToken = getAccessTokenByLogin(ticketConfig.account, ticketConfig.password);
                    if (StringUtils.isEmpty(accessToken)) {
                        logger.error("登陆失败，请检查username和password");
                        continue;
                    }

                    //构建订单
                    JsonNode orderNode = buildOrder(accessToken, ticketConfig.passengername,
                            ticketConfig.passportno, ticketConfig.sex, ticketConfig.contactmobile, ticketConfig.contactname, infoNode);
                    if (orderNode == null) {
                        continue;
                    }
                    //占座(会一直刷到出结果，成功则程序退出，失败则return,进行下一次)
                    occupy(accessToken, orderNode, infoNode, ticketConfig.seatname, headers);

                }
            }
        } catch (Exception e) {
            logger.error("无脑下单模式报错", e);
        }

    }

    //开始占座
    private void occupy(String accessToken, JsonNode orderNode, JsonNode infoNode,
                        String seatName, Map<String, String> headers) {


        JsonNode trainCodeNode = infoNode.get("trainCode");
        JsonNode deptTimeNode = infoNode.get("deptTime");
        JsonNode classSeatNode = getClassSeatNode(infoNode, seatName);
        JsonNode seatNum = classSeatNode.get("seatNum");

        JsonNode orderDataNode = orderNode.get("data");
        JsonNode orderNoNode = orderDataNode.get("orderNo");
        JsonNode orderDateNode = orderDataNode.get("orderDate");

        String occupyUrl = "http://api.12306.com/v1/train/order-detail/" + orderNoNode.asText() + "?access_token=" + accessToken;

        while (true) {
            String occupyJson = HttpClientUtils.doGet(occupyUrl, null, headers);
            if (StringUtils.isEmpty(occupyJson)) {

                logger.warn("占座时返回空，请检查是否被封，或是否超时");
                continue;
            }
            JsonNode occupyJsonNode = JsonUtils.jsonToJsonNode(occupyJson);
            if (occupyJsonNode == null) {
                logger.warn("占座时返回数据不为json，内容:" + occupyJson);
                continue;
            }
            JsonNode occupyDataNode = occupyJsonNode.get("data");
            JsonNode statusTextNode = occupyDataNode.get("statusText");
            String deptStationName = infoNode.get("deptStationName") == null ? "" : infoNode.get("deptStationName").asText();
            String arrStationName = infoNode.get("arrStationName") == null ? "" : infoNode.get("arrStationName").asText();

            //如果不为占座中，则判断是什么状态  （占座中时，需要重复刷这个接口，直到拿到成功或失败的信息）
            if (!"占座中".equals(statusTextNode.asText())) {
                logger.info("出发时间:" + ticketConfig.deptdate + " " + deptTimeNode.asText());
                logger.info("车次:" + trainCodeNode.asText() + " [从 " + deptStationName + " 开往 " + arrStationName + "]");
                logger.info("票数:" + seatNum.asText() + " [" + seatName + " ￥" + classSeatNode.get("seatPrice").asText() + "]");
                logger.info("状态:" + statusTextNode.asText());
                if (statusTextNode.asText().contains("占座成功")) {
                    System.exit(0);
                    return;
                }
                //不是占座成功一般都是占座失败，跳出循环
                break;
            }

        }


    }
    private JsonNode buildOrder(String accessToken, String passengerName, String passportNo, String sex, String contactMobile, String contactName, JsonNode infoNode) {
        String orderJson = orderService.buildOrder(accessToken, passengerName, passportNo, sex, contactMobile, contactName, infoNode);
        if (StringUtils.isEmpty(orderJson)) {
            logger.error("构建订单失败，请检查passengerName, passportNo, sex, contactMobile, contactName。重点检查passengerName和passportNo是否对应。或者是网络问题");
            return null;
        }
        JsonNode orderNode = JsonUtils.jsonToJsonNode(orderJson);
        JsonNode codeNode = orderNode.get("code");

        //{"message":"","code":"00000","data":{"orderNo":"T201804071229380152344","orderDate":1523075378841}}

        if (codeNode.asText().equals("00007")) {
            logger.warn("存在正在占座的订单 , sleep 100 ms , continue");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        if (codeNode.asText().equals("00005")) {
            logger.warn("存在未支付订单，请先去 www.12306.com 取消订单");
            System.exit(0);
        }

        if (codeNode.asText().equals("00000")) {
            return orderNode;
        }
        return null;
    }


    private String getAccessTokenByLogin(String account, String password) {
        Map<String, String> loginInfo = loginService.login(account, password);
        if (loginInfo == null) {
            return null;
        }
        String accessToken = loginInfo.get("access_token");
        if (StringUtils.isEmpty(accessToken)) {
            return null;
        }
        return accessToken;
    }

    private List<JsonNode> getApplicableTrainInfoNodes(JsonNode trainInfosNode, String seatName,
                                                       String timeRange) {

        List<JsonNode> trainInfoNodes = new ArrayList<>();
        //下边选择符合我们需要的
        for (JsonNode infoNode : trainInfosNode){
            JsonNode trainCodeNode = infoNode.get("trainCode");
            if (trainCodeNode == null) {
                logger.error("解析车次trainCode为空，请注意,info:" + infoNode.toString());
                continue;
            }

            JsonNode deptTimeNode = infoNode.get("deptTime");
            if (deptTimeNode == null) {
                logger.error("解析车次deptTime为空，请注意,info:" + deptTimeNode.toString());
                continue;
            }

            //不是设定时间范围内的车次过滤
            if (!isTimeRange(deptTimeNode.asText(), timeRange)) {
                continue;
            }
            JsonNode seatList = infoNode.get("seatList");
            if (seatList == null) {
                logger.error("解析车次:" + trainCodeNode.asText() + " 出发时间:" + deptTimeNode.asText() + " 的座位列表有误，info:" + deptTimeNode.toString());
                continue;
            }

            JsonNode classSeatNode = getClassSeatNode(infoNode, seatName);

            if (classSeatNode == null) {
                logger.warn("车次:" + trainCodeNode.asText() + " 出发时间:" + deptTimeNode.asText() + "无对应的座位:" + seatName);
                continue;
            }
            JsonNode seatNum = classSeatNode.get("seatNum");
            if (seatNum == null) {
                logger.warn("车次:" + trainCodeNode.asText() + " 出发时间:" + deptTimeNode.asText() + "无对应的座位号码");
                continue;
            }

            trainInfoNodes.add(infoNode);
        }
        if (trainInfoNodes.size() > 0){
            return trainInfoNodes;
        }


        return null;
    }



    private JsonNode getClassSeatNode(JsonNode infoNode, String seatName) {
        JsonNode seatList = infoNode.get("seatList");
        if (seatList == null) {
            return null;
        }
        JsonNode seatNameNode;
        for (JsonNode seatNode : seatList) {
            seatNameNode = seatNode.get("seatName");
            if (seatNameNode != null && seatName.equals(seatNameNode.asText().trim())) {
                return seatNode;
            }
        }
        return null;
    }


    private boolean isTimeRange(String deptTime, String timeRange) {
        //如果timeRange为空，则不限制时间
        if (StringUtils.isEmpty(timeRange) || "null".equals(timeRange)) {
            return true;
        }
        try {

            //当timeRange格式不对时，也认为不限制时间
            String[] split = timeRange.split("-");
            if (split.length != 2) {
                logger.warn("timeRange格式不对，不进行时间限制");
                return true;
            }
            //此时可能会有线程安全的问题
            Date deptTimeDate = simpleDateFormat.parse(deptTime);
            Date startDate = simpleDateFormat.parse(split[0]);
            Date endDate = simpleDateFormat.parse(split[1]);
            if (deptTimeDate.getTime() >= startDate.getTime() && deptTimeDate.getTime() <= endDate.getTime()) {
                return true;
            }
        } catch (ParseException e) {
            logger.warn("解析12306返回的车次时间或传入的timeRange出错", e);
            return true;
        }
        return false;
    }

    private boolean isSellTime(JsonNode infoNode) {
        JsonNode reasonNode = infoNode.get("reason");
        if (reasonNode != null && "不在服务时间".equals(reasonNode.asText())) {
            //当23:00到6:00时是不能买票的，缓一缓
            JsonNode trainCodeNode = infoNode.get("trainCode");
            logger.info(trainCodeNode.asText() + "\t 不在服务期间内 （23:00到6:00时是不能买票的），sleep 100ms  ，跳过");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    public JsonNode getTrainInfosNode(String fromStation, String toStation,
                                      String deptDate, String justGD, Map<String, String> headers) {

        if (trainInfoUrl == null) {
            String fromStationCode = stationService.findCodeByCityName(fromStation);
            String toStationCode = stationService.findCodeByCityName(toStation);
            try {
                trainInfoUrl = "http://api.12306.com/v1/train/trainInfos?arrStationCode=" + toStationCode + "&deptDate=" + deptDate + "&deptStationCode=" + fromStationCode + "&findGD=" + justGD.toLowerCase();

            } catch (Exception e) {
                logger.error("火车票查询页的url转换trainInfo接口的url出错，请看填写url是否正确", e);
                return null;
            }
        }

        if (StringUtils.isEmpty(trainInfoUrl)) {
            logger.error("火车票查询页的url转换trainInfo接口的url出错，请看填写url是否正确");
            return null;
        }

        String json = HttpClientUtils.doGet(trainInfoUrl, null, headers);

        if (StringUtils.isEmpty(json)) {
            logger.error("返回车次信息json数据为空，请看是否被封ip,url:" + trainInfoUrl);
            return null;
        }

        if ("{\"message\":\"请求过于频繁\",\"code\":\"00001\",\"data\":\"\"}".equals(json)){
            logger.warn("请求过于频繁");
            return null;
        }

        JsonNode jsonNode = JsonUtils.jsonToJsonNode(json);
        if (jsonNode == null) {
            return null;
        }

        JsonNode dataNode = jsonNode.get("data");
        if (dataNode == null) {
            logger.error("解析车次信息json内的data数据为空，请看返回json是否有误，或者IP是否被封, url:" + trainInfoUrl + " ,json:" + json);
            return null;
        }

        JsonNode trainInfosNode = dataNode.get("trainInfos");
        if (trainInfosNode == null) {
            logger.error("解析车次信息json内data下面的trainInfos数据为空，请看返回json是否有误，或者IP是否被封, url:" + trainInfoUrl + " ,json:" + json);
            return null;
        }

        return trainInfosNode;
    }
}
