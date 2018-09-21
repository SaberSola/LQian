package com.zl.lqian.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class RetryDto implements Serializable {

    private String url;

    private Map<String, String> param;

    private Map<String, String> headers;

    private String cityName;
}
