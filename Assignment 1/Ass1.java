import java.util.Scanner;

public class Ass1 {
	static int getvalue(String text,int min,int max) {
		int a = 0;
		boolean check = false;
		char ch=0;
		Scanner input = new Scanner(System.in);
		while(!check) {
			System.out.printf(text);
			try {
				a = input.nextInt();
				if(a>=min && a<=max)
					check = true;
				else 
					System.out.printf("Invalid Input please Enter value between %d - %d\n",min,max);
			}
			catch(Exception err){
				System.out.printf("Invalid Input please Enter value between %d - %d\n",min,max);
				input.nextLine(); 
				
			}
		}
		return a;
		

		
	}
	static void show_factorial(int n,int n2) {
		System.out.printf("%d! is recursive case. Answer = %d * recursion of %d! \n",n,n,n2);
		System.out.printf("\tRecursion to calculate %d!\n",n2);
		
		
	}
	static void show_factorial2(int n,int n2,long back,long x){
		System.out.printf("\tReturn answer from %d! =  %d to calculate %d! = [ %d * %d!] = %d * %d = %d\n",n2,back,n,n,n2,n,back,x);
	}
	static long factorial(int n) {
		long x,back;
		int n2;
		if(n == 0) {//base
			System.out.printf("0! is base case return answer of 0! = 1\n");
			System.out.printf("Calculate 0! complete. \n");
			return 1;
			}
		else { // recursive
			show_factorial(n,n-1);
			x = n*factorial(n-1);
			back = x / n;
			if(n-1>0) {
				n2 = n-1;
				System.out.printf("Calculate %d! complete. \n",n2);
				}
			show_factorial2(n,n-1,back,x);
			return x;
			}
	}
	public static void main(String[] args) {
		int n;
		long ans;
		char  ch = 0;
		Scanner in = new Scanner(System.in);
		System.out.printf("My Recursion Program. \n");
		System.out.printf("Calculate Factorial n! by recursion \n");
		do {
			n = getvalue("Enter the value of n = ",0,15);
			ans = factorial(n);
			System.out.printf("Complete calculation of %d! , answer = %d\n",n,ans);
			System.out.printf("press[y] to continue, others to exit. ");
			if (in.hasNext())
				ch = in.next().charAt(0);
			
		}while(ch == 'y' || ch == 'Y');
		System.out.println("End Program.");
		System.out.println("Program written by 63070501067 Sorathorn Kaewchotchuangkul CPE Regular.");
	}

}
