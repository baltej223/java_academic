package assignment_1;

public class Main{
	public static void main(String[] args) {
    q1(2024);
    q3(10);
    q4(20);
    q5(48, 18);
    q6(21, 30);
    q7(3453);
    q8(124325);
    q9(2, 30);
    q10(101);
    q10(102);
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
