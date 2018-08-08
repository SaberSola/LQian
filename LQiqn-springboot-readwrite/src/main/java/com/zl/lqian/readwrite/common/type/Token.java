package com.zl.lqian.readwrite.common.type;


/**
 *
 * @Author zl
 * Token 枚举状态
 */
public enum Token {

    /**
     * 表示获取token成功，通过
     */
    PASS,
    /**
     * 表示没有配置元数据
     */
    NO_CONFIG,
    /**
     * 表示获取token失败，熔断
     */
    FUSING,
    /**
     * 表示访问redis出问题了
     */
    ACCESS_REDIS_FAIL;

    public boolean isPass() {
        return this.equals(PASS);
    }

    public boolean isFusing() {
        return this.equals(FUSING);
    }

    public boolean isAccessRedisFail() {
        return this.equals(ACCESS_REDIS_FAIL);
    }

    public boolean isNoConfig() {
        return this.equals(NO_CONFIG);
    }
}
