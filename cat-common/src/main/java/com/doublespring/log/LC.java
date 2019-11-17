package com.doublespring.log;

public enum LC {

    /**
     * 功能枚举类
     * 号段使用者分类
     * 100 - 1000 : 非业务类型日志
     * 1000 - 1999 ：
     * 2000 - 2999 ：
     * 3000 - 3999 ：
     */
    C_000("000", ""),
    C_110("110", "异常错误"),
    C_100("100", "测试消息"),
    BSP("1000", "KQS Bootstrap启动类"),
    KQS("1001", "KQS Netty服务端"),
    ZK_SERVICE("1002", "ZK缓存操作"),
    ZK_COUNTER("1003", "ZK计数器"),
    CONTEXT("1004", "Application会话管理"),
    CLUSTER("1005", "集群连接客户端"),
    SEND_MESSAGE_TO_CLIENT("1006", "向客户端发送消息"),
    KQC("1007", "kingQserver连接客户端"),
    KQP("1008", "kingQ 生产者"),


    ;

    /**
     * 功能编码
     */
    private String code;


    /**
     * 功能名称
     */
    private String name;

    LC(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public LC setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public LC setName(String name) {
        this.name = name;
        return this;
    }
}
