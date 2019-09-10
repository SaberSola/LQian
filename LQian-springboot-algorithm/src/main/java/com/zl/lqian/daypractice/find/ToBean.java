package com.zl.lqian.daypractice.find;




import com.hotels.beans.BeanUtils;
import com.hotels.transformer.annotation.ConstructorArg;

import java.util.List;

/**
 * @Author zl
 * @Date 2019-09-10
 * @Des ${todo}
 */
public class ToBean {
    private  String differentName;
    private  int id;
    private List<String> list;



    public ToBean(@ConstructorArg("name") final String differentName,
                  @ConstructorArg("id")final Integer id,
                  @ConstructorArg("list")final List list){
        this.differentName = differentName;
        this.id = id;
        this.list = list;
    }

    public ToBean(){}
    public static void main(String [] args){
        BeanUtils beanUtils = new BeanUtils();
        FromBean fromBean = new FromBean();
        fromBean.setId(1);
        fromBean.setName("zhanglei");
        ToBean toBean = beanUtils.getTransformer().transform(fromBean, ToBean.class);
    }


    public String getDifferentName() {
        return differentName;
    }

    public void setDifferentName(String differentName) {
        this.differentName = differentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
