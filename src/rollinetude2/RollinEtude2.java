package rollinetude2;

import java.util.Random;

/**
 *
 * @author James
 * @author Prabhat
 * @author Ali
 *
 * Main method for project:
 * Creates a TeamJAPRollin dice object
 * Calls handleRoll() method until a complete set is created
 * 
 */
public class RollinEtude2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        float count = 100000;
        float average = 0;
        float total = 0;
        int[] dice = new int[6];
        int numSteps = 0;
        int replaceIndex = -1;
        Random rn = new Random();

        for (int j = 0; j < count; j++) 
        {
        numSteps = 0;
        replaceIndex = -1;
        rn = new Random();
        for (int i = 0; i < dice.length; i++) {
            dice[i] = rn.nextInt(6 - 1 + 1) + 1;
//            System.out.println(dice[i]);     
        }

        TeamJAPRollin tjr = new TeamJAPRollin(dice);

        dice = tjr.getDice();
        while (!tjr.isComplete()) {
//            for (int d : dice) {
//                System.out.println(d);
//            }
            numSteps++;
            int roll = rn.nextInt(6 - 1 + 1) + 1;
            replaceIndex = tjr.handleRoll(roll);
//            for (int d : dice) {
//                System.out.println(d);
//            }
//            System.out.println();
//            System.out.println("*******************************");
        }
//        System.out.println("numSteps: " + numSteps);
        total += numSteps;
        }
        average = total/count;
        System.out.println("total = " + total);
        System.out.println("count = " + count);
        System.out.println("average = " + average);
    }
    

}
