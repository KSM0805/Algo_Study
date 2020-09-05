package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17136_색종이붙이기 {
	private static int result;
	private static int[][] map;
	private static int total;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = Integer.MAX_VALUE;
		// 색종이 : 1-5
		// map 크기 : 10*10
		map = new int[10][10];
		total = 0;
		StringTokenizer st;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) total++;
			}
		}
		dfs(0, total, new int[] {0, 0, 0, 0, 0}, 0);
		System.out.println(result == Integer.MAX_VALUE ? "-1" : result);
	}
	private static void dfs(int cnt, int empty, int[] num, int row) {
		if(cnt >= result) return;
		if(empty == 0) {
			result = cnt;
			return;
		}
		for (int i = row; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(map[i][j] == 1) {
					for (int k = 5; k > 0; k--) {
						if(num[k-1] < 5 && possible(i, j, k)) {
							for (int l = i; l < i + k; l++) {
								for (int m = j; m < j + k; m++) {
									map[l][m] = 0;
								}
							}
							num[k-1]++;
							dfs(cnt + 1, empty - (k*k), num, i);
							num[k-1]--;
							for (int l = i; l < i + k; l++) {
								for (int m = j; m < j + k; m++) {
									map[l][m] = 1;
								}
							}
						}
					}
					return;
				}
			}
		}
	}
	private static boolean possible(int r, int c, int size) {
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if(i >= 10 || j >= 10) return false;
				if(map[i][j] == 0) return false;
			}
		}
		return true;
	}
}
