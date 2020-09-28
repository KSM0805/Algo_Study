package BOJ;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1920_수찾기 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] num = new int[N];
		
		for (int i = 0; i < N; i++) {
			num[i] = sc.nextInt();
		}
		Arrays.sort(num);
		int M = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int idx = Arrays.binarySearch(num, x);
			if(idx < 0) {
				sb.append("0");
			} else sb.append("1");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
