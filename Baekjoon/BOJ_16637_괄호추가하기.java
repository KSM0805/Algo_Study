package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_16637_괄호추가하기 {
	private static int result;
	private static char[] str;
	private static int N;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = Integer.MIN_VALUE;
		// 0-9숫자와  + - *
		N = Integer.parseInt(bf.readLine().trim());
		str = bf.readLine().trim().toCharArray();
		dfs(0,'+',0);
		System.out.println(result);
	}

	private static void dfs(int pos, char math, int total) {
//		System.out.println(pos + " " + total);
		if(pos == N - 1) {
			result = Math.max(result, calc(total, math, str[pos] - '0'));
			return;
		} else if(pos > N - 1) {
			result = Math.max(result, total);
			return;
		}
		// 괄호의 경우
		// 1 + 3 + 4
		int sum;
		if(pos + 4 < N) {
			sum = calc(str[pos]-'0', str[pos + 1] , str[pos + 2]-'0');
			sum = calc(total, math, sum);
			dfs(pos + 4, str[pos + 3], sum);
		}
		if(pos + 3 == N) {
			sum = calc(str[pos]-'0', str[pos + 1] , str[pos + 2]-'0');
			sum = calc(total, math, sum);
			dfs(pos + 4,'+', sum);
		}
		// 괄호가 아닌 경우
		sum = calc(total, math, str[pos]-'0');
		dfs(pos + 2, str[pos + 1], sum);
	}

	private static int calc(int num1, char cal, int num2) {
//		System.out.println("calc : " + num1 + " " + cal + " " + num2);
		int result = num1;
		switch (cal) {
			case '+':
				result += num2;
				break;
			case '-':
				result -= num2;
				break;
			case '*':
				result *= num2;
				break;
		}
		return result;
	}
}
