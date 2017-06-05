package com.example.laicode;

/**
 * Created by lijiang on 5/30/17.
 */
public class NumberOfDifferentBits {
    public int diffBits(int a, int b) {
        int diff = a ^ b;
        int count = 0;
        // diff And with bitmask 0x1 and logically right shift till it turns to 0
        while (diff != 0) {
            count += diff & 1;
            diff >>>= 1;
        }
        return count;
    }
    public static void main(String[] args) {
        NumberOfDifferentBits test = new NumberOfDifferentBits();
        // 5 = 0x0101
        // 8 = 0x1000
        int a = 5;
        int b = 8;
        // expected result = 3
        int result =  test.diffBits(a, b);
        System.out.println(String.valueOf(result));
    }
}
