import java.util.Scanner;
import java.util.Random;

public class  Quincunx_Ou {
   public static void main(String[] args){
      Scanner input = new Scanner(System.in);
      System.out.println("Enter the number of balls to drop: ");
      int numOfBalls = input.nextInt();
      
      System.out.println("Enter the number of pegs in the bean machine: ");
      int numOfPegs = input.nextInt();
    
      int[] result = new int[numOfPegs];//creates the result array depending on the numOfPegs
      
      /*determines the outcome sequence depending on the number of ball and
      calculates the slot position the ball will end up*/
      for(int i = 1; i <= numOfBalls; i++){
         String eachOutcome = determineOutcome(numOfPegs);
         String direction = determineDirection(eachOutcome);
         int slotPosition = calculatePosition(eachOutcome);
 
         //System.out.println("Ball " + i + " sequence: " +eachOutcome + " " + direction + " " + slotPosition);
         System.out.println("Ball " + i + " sequence: " + eachOutcome);
         
         //if the ball is mostly travelling left in the sequence
         //the ball will most likely end up on the left side of the peg
         //vice versa
         if(direction.equals("Left"))
           result[result.length - (slotPosition + 1)]++; //going left in the array and increase the count
         else if(direction.equals("Right"))
           result[slotPosition]++; //going right in the array and increase the count
      }
      
      /*below shows the visual histogram of the bean machine*/
      System.out.println("-------------------------------------------");
      printSpace(numOfPegs-1, 0);
      System.out.println (" | |");
      
      for(int i = 0; i < numOfPegs-1; i++){
         printSpace(numOfPegs-1, i);
         System.out.print("/ *");
         printStars(i);
         System.out.print(" \\");
         System.out.println();
      }
   
      System.out.print(" "); 
      System.out.println("|" + intArrToString(result) + "|");
   }
   
   /*this method is used to generate the sequence outcome per ball depending
   on the number of pegs is provided*/
   public static String determineOutcome(int numPeg){
      String[] slots = new String[numPeg - 1]; //the amount of slots is always minus 1 due to the triangle shape
      Random random = new Random();
         for(int j = 0; j < slots.length; j++){
            int chooseRightLeft = random.nextInt(2); //generate either 1 or 0
            if(chooseRightLeft == 0)
               slots[j] = "L";
            else 
               slots[j] = "R";
         }

      return String.join("", slots); //change string array into a string
   }
   
   /*this method is used to determine the direction the of the ball is heading in a sequence
   it returns the higher occurrence of either 'L' or 'R' in a sequence*/
   public static String determineDirection(String str){
      int leftCount = 0;
      int rightCount = 0;
      //loops through the sequence to check which occurrence occurs the most
      for(int i = 0; i < str.length(); i++){
         if(str.charAt(i) == 'L')
            leftCount++;
         else 
            rightCount++;
      }
      //returns left if condition is true else right
      return (leftCount > rightCount) ? "Left" : "Right"; 
   }
   
   /*this method is similar to the above method instead it determines the 
   slot position the ball will fall into*/
   public static int calculatePosition(String str){
      int leftCount = 0;
      int rightCount = 0;
      //loops through the sequence to check which occurrence occurs the most
      for(int i = 0; i < str.length(); i++){
         if(str.charAt(i) == 'L')
            leftCount++;
         else 
            rightCount++;
      }
      //returns left count if condition is true else right count
      return (leftCount > rightCount) ? leftCount : rightCount;
   }
   
   /*converts an int array to a string*/
   public static String intArrToString(int[] arr){
      String[] res = new String[arr.length];
      /*loops through each element in the int array and convert every integer element 
      to a string element and reassign to the new string array*/
      for(int i = 0; i < arr.length; i++)
         res[i] = String.valueOf(arr[i]);
         
      removeZero(res); //remove all zeros in the string array
      return String.join("|", res); //return string array as a string
   }
   
  //remove all zeros in a string array and replace it with "_"
  static void removeZero(String[] arr){
      for(int i = 0; i < arr.length; i++){
         if(arr[i].equals("0"))
            arr[i] = "_";
      }   
   }
   
   /*print a certain amount of space depending on the number of pegs 
   and offset the space*/
   static void printSpace(int numPeg, int pos){
     for(int i = numPeg; i > pos; i--)
        System.out.print(" ");
   }
   
   //prints out stars for the representation of histogram of the bean machine
   static void printStars(int pos){
       for(int i = 0; i < pos; i++)
         System.out.print(" *");
   }
}