package com.aid.example.first_aid;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 裴冲
 * @DateTime: 2023/3/16 14:51
 * @Description:
 */
public class Test {


    public static void main(String[] args) {
        System.out.println(Test.getMaxsub("afsfsafjfl"));
    }



    public static String getMaxsub(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        int start = 0;
        int maxlen = 0;
        int len = 0;
        int startMaxIndex = 0;
        Map<Character, Integer> map = new HashMap<>();
        int i;
        for (i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Integer value = map.get(ch);
            if (value != null && value >= start) {
                start = value + 1;
                len = i - value;
            } else {
                len++;
                if (len > maxlen) {
                    maxlen = len;
                    startMaxIndex = start;
                }
            }
            map.put(ch, i);
        }
        return s.substring(startMaxIndex, (startMaxIndex + maxlen));


    }
}
