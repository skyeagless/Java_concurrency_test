package com.skyeagle.concurrency;

import java.util.HashMap;
import java.util.Map;

//不可变对象
public class ImmutableExample1 {
    private final static Integer a = 1;
    private final static String b = "2";
    //赋值之后不能指向另外的对象
    private final static Map<Integer,Integer> map = new HashMap<>();
    //静态构造函数
    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }
    public static void main(String[] args) {
        map.put(4,5);
        //map = new HashMap<>();

    }


}
