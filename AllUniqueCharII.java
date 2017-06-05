package com.example.laicode;

/**
 * Created by lijiang on 5/30/17.
 */
public class AllUniqueCharII {
    public boolean ifUnique(String word) {
        // Similar to a 32 bit register
        // All 256 ASCII code can be map to an 8-bit integer number.
        // Each bit has 32 values (Integer length: 32bits)
        int[] register = new int[8];
        // parse input string into separate char's
        char[] array = word.toCharArray();
        for (char c : array) {
            int position = c % 32;
            int regOffset = c / 32;
            if ((register[regOffset]>>> position & 1) != 0) {
                return false;
            }
            register[regOffset] = register[regOffset] |= (1 << position);
        }
        return true;
    }
    public static void main(String[] args) {
        String input = "Jiang.L";
        AllUniqueCharII test = new AllUniqueCharII();
        boolean result = test.ifUnique(input);
        System.out.println(String.valueOf(result));
    }
}
