/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rollinetude2;

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
    
    @Override
    public int handleRoll(int roll) {

        boolean[][] completeRow = new boolean[9][2];
        int [] incompleteSet = new int[3];
        int [] completeSet = new int[3];
        int row = 0;
        int col = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 2; j++) {
                
                completeRow[i][j] = isSet(setIndices[i][j]);
                System.out.print(completeRow[i][j]);
            }
            System.out.println();
        }
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 2; j++) {
                if (completeRow[i][j]) {
                    row = i;                }
            }
        }
        
        if (completeRow[row][0]) {
            col = 1;
            completeSet = setIndices[row][0];
            incompleteSet = setIndices[row][1];
        } else {
            col = 0;
            completeSet = setIndices[row][1];
            incompleteSet = setIndices[row][0];
        }
        
        System.out.println("row: " + row + ", Incomplete col: " + col);
        System.out.print("Complete set: ");
        for (int i : completeSet) {
            System.out.print(i + " ");
        }
        System.out.print("Incomplete set: ");
        
        for (int i : incompleteSet) {
            System.out.print(i + " ");
        }
        System.out.println();

        return 0;
    }

}
