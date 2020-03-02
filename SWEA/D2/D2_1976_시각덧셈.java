package swea.d2;

import java.util.Scanner;

public class D2_1976 {
	
	public static void main(String[] args) { 
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int h1 = sc.nextInt();
			int m1 = sc.nextInt();
			
			int h2 = sc.nextInt();
			int m2 = sc.nextInt();
			
			int sumH = h1+ h2;
			int minH = m1 + m2;
			if(minH>=60) {
				sumH = sumH + 1;
				minH = minH - 60;
			}
			if(sumH>12) {
				sumH = sumH - 12;
			}
			System.out.println("#"+test_case+" "+sumH+" "+minH);
		}//for end
	}

}
