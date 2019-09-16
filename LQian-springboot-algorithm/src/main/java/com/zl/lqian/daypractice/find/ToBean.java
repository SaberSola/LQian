package com.zl.lqian.daypractice.find;




import com.hotels.beans.BeanUtils;
import com.hotels.transformer.annotation.ConstructorArg;
import lombok.Data;

import java.util.List;

/**
 * @Author zl
 * @Date 2019-09-10
 * @Des ${todo}
 */
@Data
public class ToBean {
    final String differentName;
    final int id;
    final List<String> list;



    public ToBean(@ConstructorArg(value = "name") final String differentName,
                  @ConstructorArg(value = "id")   final Integer id,
                  @ConstructorArg(value = "list") final List list){
        this.differentName = differentName;
        this.id = id;
        this.list = list;
    }
    public static void main(String [] args){
        BeanUtils beanUtils = new BeanUtils();
        FromBean fromBean = new FromBean();
        fromBean.setId(1);
        fromBean.setName("zhanglei");
        ToBean toBean = beanUtils.getTransformer().transform(fromBean, ToBean.class);
    }
}
