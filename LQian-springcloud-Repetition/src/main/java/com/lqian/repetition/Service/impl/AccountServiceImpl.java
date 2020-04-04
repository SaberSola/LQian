package com.lqian.repetition.Service.impl;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.lqian.repetition.Service.AccountService;
import com.lqian.repetition.Service.ExcelHeadData;
import com.lqian.repetition.conf.annotation.ConcurrentLimit;
import com.lqian.repetition.entity.AccountDO;
import com.lqian.repetition.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);




    @Override
    @Transactional
    @ConcurrentLimit
    public AccountDO findByUserId(String userId){

        AccountDO accountDO1 = new AccountDO();
        accountDO1.setUserId(userId);
    /*inal AccountDO accountDO = accountMapper.findByUserId(userId);
        try {
        Thread.sleep(1000);
    }catch (InterruptedException e){
        e.printStackTrace();
    }*/
        //LOGGER.debug("异步测试结果为----------------》" +accountDO.getBalance());
        return accountDO1;
}


   /* @Override
    public Boolean exportExcel(HttpServletResponse servletResponse) {

        OutputStream out = null;
        try {
            out = servletResponse.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        String fileName = "zhanglei" + System.currentTimeMillis();
        Sheet sheet1 = new Sheet(1, 0, ExcelHeadData.class);
        sheet1.setSheetName("sheet1");
        List<ExcelHeadData> rows = new ArrayList<>();
        //AccountDO accountDO = accountMapper.findByUserId("10000");

        ExcelHeadData excelHeadData = new ExcelHeadData();
        excelHeadData.setId("dsfafasdfasf");
        excelHeadData.setEmployeeName("sfdaadsfa");
        excelHeadData.setLastWorkDate("dsaffasfasff");
        excelHeadData.setStatus("dfadf");
        excelHeadData.setWorkNum("adsfaf");

        rows.add(excelHeadData);

        writer.write(rows, sheet1);
        writer.finish();
        servletResponse.setContentType("multipart/form-data");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setHeader("Content-disposition", "attachment;filename="+fileName+".xlsx");
        try {
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;*/


}
