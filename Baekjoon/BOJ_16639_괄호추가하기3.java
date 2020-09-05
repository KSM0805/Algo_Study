package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_16639_괄호추가하기3 {
	private static int result;
	private static int N;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = Integer.MIN_VALUE;
		N = Integer.parseInt(bf.readLine().trim());
		String[] arr = new String[N];
		String str = bf.readLine().trim();
		for (int i = 0; i < N; i++) {
			arr[i] = str.charAt(i)+"";
		}
		dfs(arr);
		System.out.println(result);
	}

	private static void dfs(String[] arr) {
		if(arr.length == 1) {
//			System.out.println(result);
			result = Math.max(result, Integer.parseInt(arr[0]));
			return;
		}
		String[] copy = new String[arr.length - 2];
		for (int i = 1; i < arr.length; i+=2) {
			int res = 0;
			switch (arr[i]) {
				case "+":
					res = Integer.parseInt(arr[i-1]) + Integer.parseInt(arr[i+1]);
					break;
				case "-":
					res = Integer.parseInt(arr[i-1]) - Integer.parseInt(arr[i+1]);
					break;
				case "*":
					res = Integer.parseInt(arr[i-1]) * Integer.parseInt(arr[i+1]);
					break;
			}
			int pos = 0;
			for (int j = 0; j < i - 1; j++) {
				copy[pos] = arr[j];
				pos++;
			}
			copy[i-1] = res + "";
			pos++;
			for (int j = i + 2; j < arr.length; j++) {
				copy[pos] = arr[j];
				pos++;
			}
			dfs(copy);
		}
	}
}
