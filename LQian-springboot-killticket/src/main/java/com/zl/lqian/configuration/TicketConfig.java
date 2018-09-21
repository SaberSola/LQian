package com.zl.lqian.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "killticket.module")
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
