package com.project.board.global.util;

import java.util.Map;

public class OrderUtils {
    public static void order(int index, String[] orders, Map<String,Integer> orderMap, int n, int ans){
        if(index==n) return;

        if(charToInt(orders[index+1])==0){
            orderMap.put(orders[index + 1].substring(1),0);

            order(index+1,orders,orderMap,n,ans);
        }
        if(charToInt(orders[index+1])>charToInt(orders[index])) {
            orderMap.put(orders[index + 1].substring(1),++ans);

            order(index+1,orders,orderMap,n,ans);
        }
        orderMap.put(orders[index + 1].substring(1),ans);

        order(index+1,orders,orderMap,n,ans);
    }
    public static int charToInt(String string){
        return string.charAt(0) - '0';
    }

}
