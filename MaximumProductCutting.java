package com.example.laicode;

/**
 * Created by lijiang on 5/23/17.
 */
public class MaximumProductCutting {

    public int maxProd (int n) {
        // Corner case
        if (n <= 0) {
            return -1;
        }
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        // Memoization
        int[] m = new int[n + 1];
        // Base Case
        m[0] = 0;
        m[1] = 0;
        m[2] = 1;
        // Filling in
        for (int i = 3; i < m.length; i++) {
            for (int j = 1; j <= i/2; j++) {
                m[i] = Math.max(m[i], j * Math.max(i - j, m[i-j]));
            }
        }
        return m[n];
    }

    public static void main(String[] args) {
        MaximumProductCutting test = new MaximumProductCutting();
        for(int i = 1; i <= 10; i++) {
            int result = test.maxProd(i);
            System.out.printf("Max Production is: %d \n", result);
        }
//        int result = test.maxProd(4);
//        System.out.printf("Max Production is: %d \n", result);
    }
}
