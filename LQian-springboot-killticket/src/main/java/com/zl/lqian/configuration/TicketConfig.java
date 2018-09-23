package com.zl.lqian.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "killticket.module")
@Component
@Data
public class TicketConfig {

    public String fromstation;

    public String tostation;

    public String justgd;

    public String deptdate;

    public String timerange;

    public String seatname;

    public String account;

    public String password;

    public String passengername;

    public String passportno;

    public String sex;

    public String contactmobile;

    public String contactname;

    public String mode;

}
