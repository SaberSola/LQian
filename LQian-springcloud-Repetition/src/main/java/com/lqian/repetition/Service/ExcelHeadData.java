package com.lqian.repetition.Service;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;


/**
 * @Author zl
 * @Date 2018-12-06
 * @Des 导出Excel表头
 */
@Data
public class ExcelHeadData extends BaseRowModel {


    @ExcelProperty(value = "申请单编号", index = 0)
    private String id;

    @ExcelProperty(value = "姓名", index = 1)
    private String employeeName; //姓名

    @ExcelProperty(value = "工号", index = 2)
    private String workNum; //工号

    @ExcelProperty(value = "工作状态", index = 3)
    private String  status;//工作状态

    @ExcelProperty(value = "最后工作时间", index = 4)
    private String lastWorkDate; // 最后工作时间


}
