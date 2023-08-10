package com.yh.utils.thread;


import com.yh.model.user.pojos.ApUser;

public class AppThreadLocalUtil {

    private final static ThreadLocal<ApUser> AP_USER_THREAD_LOCAL = new ThreadLocal<>();

    //存入线程中
    public static void setUser(ApUser apUser){
        AP_USER_THREAD_LOCAL.set(apUser);
    }
    //在线程中获取
    public static ApUser getUser(){
        return AP_USER_THREAD_LOCAL.get();
    }

    //清理
    public static void clear(){
        AP_USER_THREAD_LOCAL.remove();
    }
}
