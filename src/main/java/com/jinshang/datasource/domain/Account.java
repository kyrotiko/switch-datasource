package com.jinshang.datasource.domain;

/**
 * @author yuanyang(417168602@qq.com)
 * @date 2019/4/3 13:09
 */
public class Account {
    private int id;
    private String dbName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
