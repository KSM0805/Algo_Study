package swea.d2;

import java.util.Arrays;
import java.util.Scanner;

public class D2_1984 {
	public static void main(String[] args) { 
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int[] arr = new int [10];
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				arr[i] = sc.nextInt();
			}
			Arrays.sort(arr);
			for (int i = 1; i < arr.length -1; i++) {
				sum += arr[i];
			}
			int avg = sum / 8;
			if(sum%8 >= 4) {
				avg++;
			}
			System.out.println("#"+test_case+" "+avg);
		}//for end
	}

}
