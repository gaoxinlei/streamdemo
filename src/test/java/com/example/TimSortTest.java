package com.example;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * jdk8系列,TimSort是jdk8开始的数组排序主流方式.本例测试其中部分源码.
 */
public class TimSortTest {

    private static  final Logger  LOGGER = LoggerFactory.getLogger(TimSortTest.class);

    /**
     * {@link java.util.TimSort#minRunLength(int)} 逻辑验证.
     * 源码的逻辑如下:
     * 给定一个长度n,返回值介于1-32之间.
     * 当n不大于32时,返回值介于1-31之间.且n等于32时返回16,小于32时返回自身.
     * 当n大于32时,返回值介于1-32之间.
     * 当n大于32时,返回值的计算结果是不停将n折半,若折半过程中不出现奇数,则折半
     * 到1-31之间的一个数为止,若折半过程中的n出现奇数,则将返回值加1.
     */
    @Test
    public void testMinRunLength(){
        LOGGER.info("30:{}",minRunLength(30));
        LOGGER.info("32:{}",minRunLength(32));
        LOGGER.info("64:{}",minRunLength(64));
        LOGGER.info("96:{}",minRunLength(96));
        LOGGER.info("98:{}",minRunLength(98));
        LOGGER.info("100:{}",minRunLength(100));
        LOGGER.info("101:{}",minRunLength(101));
        LOGGER.info("103:{}",minRunLength(103));
        LOGGER.info("95:{}",minRunLength(95));
        LOGGER.info("159:{}",minRunLength(159));
        LOGGER.info("63:{}",minRunLength(63));
        LOGGER.info("62:{}",minRunLength(62));
        LOGGER.info("63+64:{}",minRunLength(63+64));
        LOGGER.info("62+64:{}",minRunLength(62+64));//126和它的一半63等同.
        //专测一个数的折分.
        int n = 186;
        int m = 0;
        while(n>32){
            LOGGER.info("拆分一次,拆分前:{},折分出:{},拆分后:{}",n,m=minRunLength(n),n=n-m);
        }

    }
    //照搬源码逻辑.
    private static int minRunLength(int n) {
        assert n >= 0;
        int r = 0;      // Becomes 1 if any 1 bits are shifted off
        while (n >= 32) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

}
