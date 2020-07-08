package com.xue.aop.common.aop_impl;

import com.xue.aop.common.enums.DBTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数据源获取
 * @author xuejh
 * @description
 * @create 2020-07-07 18:32
 **/
@Slf4j
public class DataSourceHolder {

    private static final ThreadLocal<DBTypeEnum> threadLocal = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static DBTypeEnum get() {
        return threadLocal.get();
    }

    public static void set(DBTypeEnum typeEnum) {
        threadLocal.set(typeEnum);
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
        log.info("==================切换到master================>");
    }

    public static void slave() {
        //  轮询
//        int index = counter.getAndIncrement() % 2;
//        if (counter.get() > 9999) {
//            counter.set(-1);
//        }
//        if (index == 0) {
//            set(DBTypeEnum.SLAVE1);
//            System.out.println("切换到slave1");
//        }else {
//            set(DBTypeEnum.SLAVE2);
//            System.out.println("切换到slave2");
//        }

        set(DBTypeEnum.SLAVE1);
        log.info("==================切换到slave1================>");
    }

    //防止ThreadLocal内存泄漏，垃圾回收时弱引用只回收了key对应那块内存value的那块内存依然占有且不会被回收
    public static void clear() {
        threadLocal.remove();
    }
}
