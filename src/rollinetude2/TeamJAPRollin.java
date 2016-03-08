/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rollinetude2;

import java.util.HashMap;

/**
 *
 * @author James
 * @author Ali
 * @author Bart
 */
public class TeamJAPRollin extends Rollin {

    private static final int[][][] setIndices = new int[][][]{
        {{0, 1, 2}, {3, 4, 5}},
        {{0, 1, 3}, {2, 4, 5}},
        {{0, 1, 4}, {2, 3, 5}},
        {{0, 1, 5}, {2, 3, 4}},
        {{0, 2, 3}, {1, 4, 5}},
        {{0, 2, 4}, {1, 3, 5}},
        {{0, 2, 5}, {1, 3, 4}},
        {{0, 3, 4}, {1, 2, 5}},
        {{0, 3, 5}, {1, 2, 4}},
        {{0, 4, 5}, {1, 2, 3}}
    };

    public TeamJAPRollin(int[] dice) {
        super(dice);
    }

    /**
     * Determine whether the dice at a given triple of indices form a set.
     *
     * @param indices the indices
     * @return true if the dice at those indices form a set, false otherwise.
     */
    private boolean isSet(int[] indices) {
        // First just get the values at those indices to save typing.
        int a = dice[indices[0]];
        int b = dice[indices[1]];
        int c = dice[indices[2]];
        // All three dice the same is a set
        if (a == b && b == c) {
            return true;
        }
        // If not all three are the same, then any two the same is not a set
        if (a == b || a == c || b == c) {
            return false;
        }

        // If all three are different and largest minus smallest is 2 then it
        // is a set, otherwise not.
        int max = Math.max(a, Math.max(b, c));
        int min = Math.min(a, Math.min(b, c));
        return max - min == 2;
    }
    
        /**
     * Determine whether the dice at a given triple of indices form a set.
     *
     * @param indices the indices
     * @return true if the dice at those indices form a set, false otherwise.
     */
    private boolean isSet(int[] indices, int[] values) {
        // First just get the values at those indices to save typing.
        int a = values[0];
        int b = values[1];
        int c = values[2];
        // All three dice the same is a set
        if (a == b && b == c) {
            return true;
        }
        // If not all three are the same, then any two the same is not a set
        if (a == b || a == c || b == c) {
            return false;
        }

        // If all three are different and largest minus smallest is 2 then it
        // is a set, otherwise not.
        int max = Math.max(a, Math.max(b, c));
        int min = Math.min(a, Math.min(b, c));
        return max - min == 2;
    }

    /**
     * Make change in dice in method
     *
     * @param roll
     * @return
     */
    @Override
    public int handleRoll(int roll) {
        // declare variables
        boolean[][] completeRow = new boolean[9][2];
        int[] incompleteSet = new int[3];
        int[] completeSet = new int[3];
        int row = 0;
        int incompleteCol = 0;
        int[] count = new int[7];
        int replaceIndex;
     
        System.out.println("Roll " + roll);
        // find which rows have sets
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 2; j++) {
                completeRow[i][j] = isSet(setIndices[i][j]);
                System.out.print(completeRow[i][j]);
            }
            System.out.println();
        }

        // store last row with set in row
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 2; j++) {
                if (completeRow[i][j]) {
                    row = i;
                }
            }
        }

        // determine which column has incomplete row
        // initialise incomplete and complete set indices
        if (completeRow[row][0]) {
            incompleteCol = 1;
            completeSet = setIndices[row][0];
            incompleteSet = setIndices[row][1];
        } else {
            incompleteCol = 0;
            completeSet = setIndices[row][1];
            incompleteSet = setIndices[row][0];
        }

        // test print statements
        System.out.println("roll: " + roll);
        System.out.println("row: " + row + ", Incomplete col: " + incompleteCol);
        System.out.print("Complete set: ");
        for (int i : completeSet) {
            System.out.print(i + " ");
        }
        System.out.print("Incomplete set: ");

        for (int i : incompleteSet) {
            System.out.print(i + " ");
        }
        System.out.println();

        // initialise count array
        for (int i = 0; i < incompleteSet.length; i++) {
            count[dice[incompleteSet[i]]]++;
        }

        // test print statements - count array
        System.out.print("count array: ");
        for (int i = 0; i < count.length; i++) {
            System.out.println("dice: " + i + " count: " + count[i]);
        }
        System.out.println();
        
//        for (int i = 0; i < 3; i++) {
//            if (count[])
//        } 

        replaceIndex = isSetOfTwo(roll, incompleteSet);
        if (replaceIndex != -1) {
            return replaceIndex;
        }
         
        if (count[1] == 1 || count[6] == 1) {
            return closeEndedCase(roll, incompleteSet, count);
        }

        return -1;
    }

    private int closeEndedCase(int roll, int[] incompleteSet, int[] count) {
        int[] tempArray = new int[3];
             for (int i = 0; i < 3; i++) {
                tempArray[0] = dice[incompleteSet[0]];
                tempArray[1] = dice[incompleteSet[1]];
                tempArray[2] = dice[incompleteSet[2]];
                tempArray[i] = roll;
                if (isSet(incompleteSet, tempArray)) {
                    dice[incompleteSet[i]] = roll;
                    return incompleteSet[i];
                }
            }
            for (int i = 0; i < 3; i++) {
                if (dice[incompleteSet[i]] == 1 && roll != 6) {
                    dice[incompleteSet[i]] = roll;
                    return incompleteSet[i];
                } else if (dice[incompleteSet[i]] == 6 && roll != 1) {
                    dice[incompleteSet[i]] = roll;
                    return incompleteSet[i];
                }
            }     
            return -1;
    }
    
    private int twoOfAKind(int roll, int[]inCompleteSet, int[] count) {
        return -1;
    }
    
    private int run(int roll, int[]inCompleteSet, int[] count) {
        int a = dice[inCompleteSet[0]];
        int b = dice[inCompleteSet[1]];
        int c = dice[inCompleteSet[2]];
        
        int max = Math.max(a, Math.max(b, c));
        int min = Math.min(a, Math.min(b, c));
        return -1;
    }
    
    private int isSetOfTwo(int roll,int[] indices) {
        // First just get the values at those indices to save typing.
        boolean tOrF = false;
        int a = dice[indices[0]];
        int b = dice[indices[1]];
        int c = dice[indices[2]];
        //temp hold 
        int numberToReplace = roll;
       
        // If not all three are the same, then any two the same is not a set
        if (a == b || a == c || b == c) {
            System.out.println("two sets the same");
            if (a == b){
                dice[indices[2]] = numberToReplace;
                System.out.println("C "+ c + "indexToreplace " + numberToReplace);
                System.out.println("dice at index2 " + dice[indices[2]]);
                return indices[2];
            }else if(a == c){
                dice[indices[1]] = numberToReplace;
                System.out.println("B " + b + "indexToreplace " + numberToReplace );
                System.out.println("dice at index 1 " + dice[indices[1]]);
                return indices[1];
            }else if (b == c){
                dice[indices[0]] = numberToReplace;
                System.out.println("A " + a + "indexToreplace " + numberToReplace);
                System.out.println("dice at index 0 " + dice[indices[0]]);
                return indices[0];
            }
            
           //need to handle case of 
            //System.out.println("dice at index0 " + dice[indices[0]]);
            tOrF = true;
        }
              
        return -1;
    }
}
