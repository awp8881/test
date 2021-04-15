package com.example.demo3.controller;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MaoPao {


    public static void main(String[] args) {
//      int[] arr = {-12,3,2,34,5,8,1};
//
//      for(int i = 0;i< arr.length-1;i++){
//          for (int j = i; j < arr.length-i-1;j++ ){
//              if(arr[j] >arr[j+1]){
//                    int temp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = temp;
//              }
//          }
//      }
//        Arrays.sort(arr);
//        for(int t = 0 ; t< arr.length;t++){
//            System.out.println(arr[t]);
//        }


        //一列数的规则如下: 1、1、2、3、5、8、13、21、34… 求第30位数是多少， 用递归算法实现

        int res = diGui(9);
        System.out.println(res);

    }

    private static int diGui(int i) {
        if(i <= 0 ){
            return  0;
        }else if(i <= 2){
            return 1;
        }return diGui(i-1) + diGui(i-2);

    }


}
