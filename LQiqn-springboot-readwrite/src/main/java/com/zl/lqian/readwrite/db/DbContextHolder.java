package com.zl.lqian.readwrite.db;

/**
 * @Author zl
 * 主从key
 */
public class DbContextHolder {

    public enum  DbType {
        DBMaster,
        DBSlave
    }

    /**
     * 有人使用ConcurrentHashMap，在这里使用ThreadLocal
     */
    private static final ThreadLocal<DbType> contextHolder = new ThreadLocal<>();

    /**
     * @param dbType
     */
    public static void setDbType(DbType dbType) {
        if(dbType == null){
            throw new NullPointerException();
        }
        contextHolder.set(dbType);
    }

    public static DbType getDbType() {
        return contextHolder.get()== null ? DbType.DBMaster : contextHolder.get();
    }

    public static void clearDbType() {
        contextHolder.remove();
    }
}
