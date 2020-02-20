package swea.d3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class D3_1208 {
	public static void main(String[] args) throws FileNotFoundException { 
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = 10;
//		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int[] arr = new int[100];
			int dump = sc.nextInt(); // 덤프 횟수
			for (int i = 0; i < 100; i++) {
				arr[i] = sc.nextInt();
			}
			for (int i = 0; i < dump; i++) {
				Arrays.sort(arr);
				arr[99]--;
				arr[0]++;
			}
			Arrays.sort(arr);
			System.out.println("#"+test_case+" "+(arr[99]-arr[0]));
		}//for end
	}
}
