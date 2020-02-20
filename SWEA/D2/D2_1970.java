package swea.d2;

import java.util.Scanner;

public class D2_1970 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		int[] a = {50000,10000,5000,1000,500,100,50,10};
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int money = sc.nextInt();
			int[] num = new int[8];
			for (int i = 0; i < num.length; i++) {
				if(money % a[i] != money) {
					num[i] = money/a[i];
					money = money%a[i];
				}
			}
			System.out.println("#"+test_case);
			for (int i : num) {
				System.out.print(i+" ");
			}
			System.out.println();
		}//for end
	}

}
