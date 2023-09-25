import java.util.Scanner;
public class StudentGradeCalculator
{
 public static void main(String[] args) {
     float marks;
     float T_marks=0;
     float A_per=0;
  System.out.println("STUDENT GRADE CALCULATOR");
  Scanner sc=new Scanner(System.in);
  System.out.println("Please provide total number of subjects:");
  int n=sc.nextInt();
  System.out.println("Please provide the marks obtained in each subject(Out of 100):");
  for(int i=0;i<n;i++){
     marks= sc.nextInt();
   
     T_marks += marks;
    
     A_per =(T_marks/n);
  }System.out.println("Total Marks is: "+T_marks);
  System.out.println("Average Percentage is: "+A_per+"%");
  if(90<A_per && A_per<101){
      System.out.println("Grade is: O");
  }if(80<A_per && A_per<91){
      System.out.println("Grade is: E");
  }if(70<A_per && A_per<81){
      System.out.println("Grade is: A");
  }if(60<A_per && A_per<71){
      System.out.println("Grade is: B");
  }if(50<A_per && A_per<61){
      System.out.println("Grade is: C");
  }if(40<A_per && A_per<51){
      System.out.println("Grade is: D");
  }if(A_per<41){
      System.out.println("Grade is: F");
  }
  
  }
 }