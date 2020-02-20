package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1931 {
	private static int N;
	private static int[][] com;
	private static int max;
	private static int[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim());
		com = new int[N][2];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim(), " ");
			com[i][0] = Integer.parseInt(st.nextToken());
			com[i][1] = Integer.parseInt(st.nextToken());
		}
		// 병합정렬
		Arrays.sort(com, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]<o2[1]) return -1;
				else if (o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}
				return 1; // 양수여야만 한다. 0은 음수와 동일 취급.
			}

		});

		int end = -1;
		for (int i = 0; i < N; i++) {
			if (end <= com[i][0]) {
//				System.out.println(end+" "+com[i][1]);
				end = com[i][1];
				max++;
			}
		}
		System.out.println(max);
	}
}
