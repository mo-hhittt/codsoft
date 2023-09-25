import java.util.Random;
import java.util.Scanner;
public class NumberGame89
{
 public static void main(String[] args) {
  System.out.println("NUMBER GAME");
  Scanner sc= new Scanner(System.in);
  int max=100;
  int min=1;
  int num = (int)(Math.random()*(max-min+1)+min);
  System.out.println("Guess My Number between 1 to 100: ");
  for(int i=0;i<11;i++){
      int guess= sc.nextInt();
      if(num==guess){
          System.out.println("You Guessed Correctly!");
          break;
      }
      else if(guess>num){
          System.out.println("Your Guess is too high!");
          
      }
      else if(guess<num){
          System.out.println("Your Guess is too low");
      }
      if(i>=10){
          System.out.println("Too many attempts.");
          System.out.println("My number was "+num);
      }
  }
 }
}