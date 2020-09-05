package boj;

import java.io.FileInputStream;
import java.util.Scanner;

public class BOJ_14501_퇴사 {
	private static int result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		Scanner sc = new Scanner(System.in);
		result = 0;
		int N = sc.nextInt();
		int[] T = new int[N];
		int[] P = new int[N];
		int[] maxP = new int[N];
		for (int i = 0; i < N; i++) {
			T[i] = sc.nextInt();
			P[i] = sc.nextInt();
			maxP[i] = P[i];
		}
		for (int i = 0; i < N; i++) {
			for (int j = i + T[i]; j < N; j++) {
				maxP[j] = Math.max(maxP[j], P[j] + maxP[i]);
			}
		}
		for (int i = 0; i < N; i++) {
			if(i + T[i] <= N) {
				result = Math.max(result, maxP[i]);
			}
		}
		System.out.println(result);
	}
}
