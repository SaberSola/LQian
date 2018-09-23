package com.zl.lqian.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 *
 * 线程安全的时间类
 */
public abstract  class DateUtils {

    private static final String date_format = "H:mm" ;

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<>();

    public static DateFormat getInstance(){

        DateFormat df = threadLocal.get();
        if (df == null){
            df = new SimpleDateFormat(date_format);
            threadLocal.set(df);
        }
        return df;
    }

    public static String formatDate(Date date) throws ParseException {
        return getInstance().format(date);
    }


    public static Date parse(String strDate) throws ParseException {
        return getInstance().parse(strDate);
    }
}
