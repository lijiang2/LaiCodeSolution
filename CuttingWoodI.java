package com.example.laicode;

/**
 * Created by lijiang on 6/5/17.
 */

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;

public class CuttingWoodI {

//        public static void main(String s[]) throws NumberFormatException, IOException {
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//            short N = Short.parseShort(br.readLine());
//            short[] A = new short[N];
//            N = 0;
//            for (String str : br.readLine().split(" ")) {
//                A[N++] = Short.parseShort(str);
//            }
//
//            Arrays.sort(A);
//
//            StringBuffer sb = new StringBuffer();
//            System.out.println(N);
//            for (int i = 1; i < N; i++) {
//                if (A[i - 1] != A[i]) {
//                    sb.append((N - i) + "\n");
//                }
//            }
//            // OUTPUT
//            System.out.print(sb);
//        }

    public int minCost(int[] cuts, int length) {

        int[] cutsPad = new int[cuts.length + 2];
        cutsPad[0] = 0;
        for (int i = 0; i < cuts.length; i++) {
            cutsPad[i + 1] = cuts[i];
        }
        cutsPad[cutsPad.length - 1] = length;

        // minCost[i][j]
        int[][] minCost = new int[cutsPad.length][cutsPad.length];

        for (int j = 0; j < cutsPad.length; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (j - i <= 1) {
                    minCost[i][j] = 0;
                    System.out.printf("i = %d, j = %d, minCost = %d \n", i, j, minCost[i][j]);
                } else {
                    minCost[i][j] = Integer.MAX_VALUE;
                    for (int k = i + 1; k <= j - 1; k++) {
                        minCost[i][j] = Math.min(minCost[i][j], minCost[i][k] + minCost[k][j]);
                    }
                    minCost[i][j] += cutsPad[j] - cutsPad[i];
                    System.out.printf("i = %d, j = %d, minCost = %d \n", i, j, minCost[i][j]);
                }
            }
        }
        return minCost[0][cutsPad.length - 1];
    }

    public static void main(String[] args) {
        CuttingWoodI test = new CuttingWoodI();
        int[] cuts = {2, 4, 7};
        int result = test.minCost(cuts, 10);
        System.out.printf("=========== \nResult = %d", result);
    }
}
