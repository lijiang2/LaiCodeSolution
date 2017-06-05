package com.example.laicode;
/**
 * Created by lijiang on 5/27/17.
 */

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public List<List<Integer>> solveNQueens(int n) {
        // Assumption: n > 0
        // Allocate memory for result list
        List<List<Integer>> solList = new ArrayList<List<Integer>>();
        // Allocate memory for single solution in result list, each solution is a list of integers
        List<Integer> curSol = new ArrayList<Integer>();

        nqueensHelper(curSol, n, solList);
        return solList;
    }

    private void nqueensHelper(List<Integer> curSol, int n, List<List<Integer>> solList) {
        // Base Case:
        // If position for all rows are decided, add current solution to result list and return
        int row = curSol.size();
        if (row == n) {
            // ArrayList.add(E e): Appends the specified element to the end of this list
            solList.add(new ArrayList<Integer>(curSol));
            return;
        }
        // Recursive Rule:
        // start from empty solution, since no row position is decided.
        // In each row, there are n columns (n choices).
        // Iterate through every columns for each row
        for (int col = 0; col < n; col++) {
            // if place a queen on position (row, col) in this solution if legal
            if (legal(curSol, col)) {
                curSol.add(col);
                nqueensHelper(curSol, n, solList);
                // return to initial state of current solution
                curSol.remove(curSol.size()-1);
            }
        }
    }

    // check if current position is legal to put queen
    private Boolean legal(List<Integer> curSol, int col) {
        // number of rows having queens placed so far
        int numPlaced = curSol.size();
        for (int i = 0; i < numPlaced; i++) {
            // legal rule
            if (curSol.get(i) == col || Math.abs(curSol.get(i) - col) == numPlaced - i) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        NQueens test = new NQueens();
        List<List<Integer>> solution = test.solveNQueens(3);
    }

}
