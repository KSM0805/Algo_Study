package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_8958 {
	private static String str;
	private static int N;
	private static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			
			int result = 0;
			str = bf.readLine().trim();
			N = str.length();
			arr = new int[N];
			if(str.charAt(0)=='O') {
				arr[0] = 1;
				result += 1;
			}
			else arr[0] = 0;
			
			for (int i = 1; i < N; i++) {
				if(str.charAt(i)=='X') arr[i] = 0;
				else {
					arr[i] = arr[i-1] + 1;
					result += arr[i];
				}
			}
			System.out.println(result);
			
		}//for end
	}
}
