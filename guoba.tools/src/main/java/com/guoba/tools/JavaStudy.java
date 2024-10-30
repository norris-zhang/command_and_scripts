package com.guoba.tools;

import java.util.HashMap;
import java.util.Map;

public class JavaStudy {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("a", map.computeIfAbsent("a", k -> 0) + 2);
            System.out.println(map.get("a"));
        }
    }
}
