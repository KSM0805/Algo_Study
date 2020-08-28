package BOJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_17086_아기상어2_2 {

	private static int result;
	private static int N;
	private static int M;
	private static int[][] map;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		result = Integer.MIN_VALUE;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int max = Math.max(N, M);
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num == 1 ? -1 : max - 1;
			}
		}
		LinkedList<int[]> q = new LinkedList<>();
		// 상어가 있는 지점부터 넓혀간다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
//				System.out.println(i);
				if(map[i][j] == -1) {
					q.clear();
					q.add(new int[] {i,j,1});
					while(!q.isEmpty()) {
						int[] cur = q.poll();
//						System.out.println(Arrays.toString(cur));
						for (int k = 0; k < 8; k++) {
							int nr = cur[0] + dir[k][0];
							int nc = cur[1] + dir[k][1];
							if(nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] <= cur[2] ) continue;
							// 들릴 수 있는 곳
							map[nr][nc] = cur[2];
							q.add(new int[] {nr, nc, cur[2] + 1});
						}
					}
				}
			}
		}
		for (int[] is : map) {
//			System.out.println(Arrays.toString(is));
			for (int is2 : is) {
				result = Math.max(result, is2);
			}
		}
		System.out.println(result);
	}
}