/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rollinetude2;

import java.util.Random;

/**
 *
 * @author James
 */
public class RollinEtude2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int numSteps = 0;
        int replaceIndex = -1;
        int [] dice = new int [6];       
        Random rn = new Random();
        
        for(int i = 0; i < dice.length; i++){
            dice[i] = rn.nextInt(6-1 +1)+1;
        }
              
        TeamJAPRollin tjr = new TeamJAPRollin(dice);
        
        dice = tjr.getDice();
//        while (!tjr.isComplete()) {
//            numSteps++;
//            int roll = rn.nextInt(6-1 +1)+1;
//            replaceIndex = tjr.handleRoll(roll);            
//        }
        int roll = rn.nextInt(6-1 +1)+1;
        replaceIndex = tjr.handleRoll(roll);            

        for(int d : dice){
        System.out.println(d);
        }
    }
    
}
