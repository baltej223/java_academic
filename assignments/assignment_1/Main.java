package assignment_1;
import java.util.Scanner;

public class Main{
  public static void main(String[] args) {
      Scanner s = new Scanner(System.in);

      System.out.print("Which question (1-10)? ");
      int q = s.nextInt();

      switch (q) {
          case 1:
              System.out.print("Enter year: ");
              int year = s.nextInt();
              q1(year);
              break;

          case 2:
              System.out.print("Enter Char: ");
              char c = s.next().charAt(0);;
              q2(c);
              break;

          case 3:
              System.out.print("Enter number: ");
              int n3 = s.nextInt();
              q3(n3);
              break;

          case 4:
              System.out.print("Enter number: ");
              int n4 = s.nextInt();
              q4(n4);
              break;

          case 5:
              System.out.print("Enter two numbers: ");
              int a = s.nextInt();
              int b = s.nextInt();
              q5(a, b);
              break;

          case 6:
              System.out.print("Enter two numbers: ");
              int x = s.nextInt();
              int y = s.nextInt();
              q6(x, y);
              break;

          case 7:
              System.out.print("Enter number: ");
              int n7 = s.nextInt();
              q7(n7);
              break;

          case 8:
              System.out.print("Enter number: ");
              int n8 = s.nextInt();
              q8(n8);
              break;

          case 9:
              System.out.print("Enter base and power: ");
              int base = s.nextInt();
              int power = s.nextInt();
              q9(base, power);
              break;

          case 10:
              System.out.print("Enter number: ");
              int n10 = s.nextInt();
              q10(n10);
              break;

          default:
              System.out.println("Invalid question number.");
      }

      s.close();
  }

  public static void q1(int year) {
  if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
    System.out.println("Leap!");
    }
    else {
      System.out.println("Not Leap!");
    }
	}
  
  public static void q2(char x) {
    if ((x>='a' && x<= 'z') || (x>'A' && x<'Z')) {
      System.out.println("Char!");
    }
    else {
      System.out.println("No Char!");
    }
  }
  public static void q3(int num) {
    int res = 1;
    for (int i=1; i<=num; i++) {
      res = res * i;
    }
    System.out.println("The factorial is: " + res);
  }

  public static void q4(int n) {
    int x1 = 0;
    int x2 = 1;
    System.out.print(x1+ " "+x2 + " ");
    for (int i=0; i<n; i++) {
      int next = x1 + x2;
      System.out.print(next + " ");
      x1 = x2;
      x2 = next;
   }
   System.out.println("");
  }

  public static void q5(int x, int y) {
    int first = x;
    int sec = y;
    int rem;
    while (true) {
      rem = first % sec;
      if (rem == 0) {
        System.out.println("Remainder: " + sec);
        break;
      }
      first = sec;
      sec = rem;
    }
  }

  public static void q6(int a, int b){
    int g = Math.max(a, b); 
    int s = Math.min(a, b);

    int ans = a*b;
    for (int i = g; i <= a * b; i += g) {
        if (i % s == 0)
          ans = i;
    }
    System.out.println("LCM: "+ ans);
  }

  public static void q7(int num) {
    int count = 0;
    while (true) {
      num /= 10;
      count++;
      if(num == 0) {
        System.out.println("The count is: " + count);
        break;
      }
    }
  }

  public static int q8(int num) {
    int rest = num;
    int digit;

    int count = 0;
    while(true) {
      digit = rest%10;
      rest = rest/10;
      count++;
      if (rest == 0) {
        break;
      }
    }

    rest = num;
    digit = 0;
    int f = 0;
    while(count>0) {
      digit = rest%10;
      rest = rest/10;
      f += ( Math.pow(10, count-1) * digit );
      
      if (rest == 0) {
        f = f+rest;
        break;
      }
      count--;
    }
    System.out.println("Reversed:"+f);
    return f;
  }

    public static void q9(int num, int pow) {
      if (num == 2) {
        System.out.println(1<<pow);
        return;
      }
      int n = 1;
      for (int i=0; i<pow; i++) {
        n *= num; 
      }
      System.out.println(n);
    }

    public static void q10(int number) {
      if (number == q8(number)) {
        System.out.println("Palindrome!");
      }
      else {
        System.out.println("Not palindrome!");
      }
    }
}
