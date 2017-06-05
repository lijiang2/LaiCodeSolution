package com.example.laicode;

/**
 * Created by lijiang on 5/30/17.
 */
public class PowerOfTwo {
    public boolean isPowerOfTwo(int number) {
        // bit operation
//        int x =  number & number-1;
        return (number > 0) && ((number & number-1) == 0);
    }
    public static void main(String[] args) {
        PowerOfTwo test = new PowerOfTwo();
        boolean result = test.isPowerOfTwo(8);
        System.out.println(String.valueOf(result));
    }
}
