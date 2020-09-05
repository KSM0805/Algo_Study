package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {
	private static int result;
	private static int N;
	private static int[][] map;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = Integer.MAX_VALUE;
		N = Integer.parseInt(bf.readLine().trim());
		map = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		System.out.println(total);
		select(-1, 0, 0);
		System.out.println(result);
	}

	private static void select(int pos, int cnt, int selected) {
		if(cnt == N/2) {
			solve(selected);
			return;
		}
		for (int i = pos + 1; i < N; i++) {
			if((selected & 1 << i) == 0) {
				select(i, cnt + 1, selected | 1 << i);
			}
		}
	}

	private static void solve(int selected) {
//		System.out.println("" + Integer.toBinaryString(selected));
		int sum1 = 0;
		int sum2 = 0;
		for (int i = 0; i < N; i++) {
			if((selected & 1 << i) == 0) {
				for (int j = i + 1; j < N; j++) {
					if((selected & 1 << j) == 0) {
						sum1 += map[i][j];
						sum1 += map[j][i];
					}
				}
			} else {
				for (int j = i + 1; j < N; j++) {
					if((selected & 1 << j) != 0) {
						sum2 += map[i][j];
						sum2 += map[j][i];
					}
				}
			}
		}
//		System.out.println(sum1 + " " + sum2);
		result = Math.min(result, Math.abs(sum1 - sum2));
	}
}
