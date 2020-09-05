package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888_연산자끼워넣기 {
	private static int max;
	private static int min;
	private static int N;
	private static int[] num;
	private static int[] calc;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		N = Integer.parseInt(bf.readLine().trim());
		num = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		calc = new int[4];
		st = new StringTokenizer(bf.readLine().trim()," ");
		for (int i = 0; i < 4; i++) {
			calc[i] = Integer.parseInt(st.nextToken());
		}
		solve(num[0], 1, calc);
		System.out.println(max);
		System.out.println(min);
	}

	private static void solve(int total, int pos, int[] cal) {
		if(pos == N) {
			max = Math.max(max, total);
			min = Math.min(min, total);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if(cal[i] > 0) {
				cal[i]--;
				switch (i) {
				case 0:
					solve(total + num[pos], pos + 1, cal);
					break;
				case 1:
					solve(total - num[pos], pos + 1, cal);
					break;
				case 2:
					solve(total * num[pos], pos + 1, cal);
					break;
				case 3:
					solve(total / num[pos], pos + 1, cal);
					break;
				}
				cal[i]++;
			}
		}
	}
}
