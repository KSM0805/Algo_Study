package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11399 {
	private static int N;
	private static int[] num;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim());
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		num = new int[N];
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= i; j++) {
				result += num[j];
			}
		}
		System.out.println(result);
	}
}
