package assignment_1;

public class Main{
	public static void main(String[] args) {
    q1(2024);
    q3(10);
    q4(20);
    q5(48, 18);
    q7(3453);
    q8(124325);
    q9(2, 30);
  }

  public static void q1(int year) {
  if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
    System.out.println("Leap!");
    }
    else {
      System.out.println("Not Leap!");
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

  public static void q8(int num) {
    int rev = 0;
    int power = 0;
    int digit;
    while(true){
      digit = num%10;
      num /= 10;
      power++;
      if (num == 0) {
        System.out.println("Reversed: " + rev);
        break;
      }
    }
  }

    public static void q9(int num, int pow) {
      if (num == 2) {
        System.out.println(1<<pow);
      }
      int n = 1;
      for (int i=0; i<pow; i++) {
        n *= num; 
      }
      System.out.println("");
      System.out.println(n);
    }

    public static void q10(int number) {
    }
}
