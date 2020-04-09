package com.ithwua.util;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner=new Scanner(System.in);
    //从键盘返回一个字符串
    public static String next(){
    	return scanner.next();
    }
    //从键盘返回一个整型
    public static int nextInt(){
    	return scanner.nextInt();
    }
    
    //从键盘返回一个double
    public static double nextDouble(){
    	return scanner.nextDouble();
    }
    
  //从键盘返回一个long
    public static double nextLong(){
    	return scanner.nextLong();
    }
}
