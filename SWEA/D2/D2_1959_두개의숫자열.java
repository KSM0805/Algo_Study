package swea.d2;

import java.util.Scanner;

public class D2_1959 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int numa = sc.nextInt();
			int numb = sc.nextInt();
			int num1 = 0;
			int num2 = 0;
			if(numa<numb) {
				num1 = numa;
				num2 = numb;
			}
			else {
				num2 = numa;
				num1 = numb;
			}
			int[] array1 = new int[num1];
			int[] array2 = new int[num2];
			int max = 0;
			if(numa<numb) {
				num1 = numa;
				num2 = numb;
				for (int i = 0; i < num1; i++) {
					array1[i] = sc.nextInt();
				}
				for (int i = 0; i < num2; i++) {
					array2[i] = sc.nextInt();
				}
			}
			else {
				num2 = numa;
				num1 = numb;
				for (int i = 0; i < num2; i++) {
					array2[i] = sc.nextInt();
				}
				for (int i = 0; i < num1; i++) {
					array1[i] = sc.nextInt();
				}
			}
			int sum = 0;
			for (int i = 0; i <= num2-num1; i++) {
				int[] mvarray1 = new int[num2];
				sum = 0;
				for (int j = 0; j < num1; j++) {
					mvarray1[i+j] = array1[j];
					sum = sum + (mvarray1[i+j]*array2[i+j]);
				}
				max = Math.max(max, sum);
			}
			System.out.println("#"+test_case+" "+max);
		}
	}

}
