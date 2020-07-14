package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2217 {
	private static int N;
	private static int[] num;
	private static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim());
		num = new int[N];
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(bf.readLine().trim());
		}
		Arrays.sort(num);
		max = 0;
		for (int i = 0; i < N; i++) {
			if(max < num[i]*(N-i)) {
				max = num[i]*(N-i);
			}
		}
		System.out.println(max);
	}
}
