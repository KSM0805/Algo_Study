package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1600_말이되고픈원숭이 {
	private static int result;
	private static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
	private static int[][] horse = {{1,2},{1,-2},{-1,2},{-1,-2},{2,-1},{2,1},{-2,1},{-2,-1}};
	private static int K;
	private static int H;
	private static int W;
	private static boolean[][] map;
	private static boolean[][][] visit;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = Integer.MAX_VALUE;
		K = Integer.parseInt(bf.readLine().trim());
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new boolean[H][W];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) == 1 ? false : true;
			}
		}
		visit = new boolean[H][W][K + 1];
		LinkedList<int[]> q = new LinkedList<>();
		q.add(new int[] {0,0,K,1});
		visit[0][0][K] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cnt = cur[3] + 1;
			if(result <= cur[3]) continue;
			if(cur[0] == H-1 && cur[1] == W-1) {
				result = cur[3];
				break;
			}
			int nr, nc;
			for (int i = 0; i < 4; i++) {
				nr = cur[0] + dir[i][0];
				nc = cur[1] + dir[i][1];
				if(nr < 0 || nc < 0 || nr >= H || nc >= W) continue;
				if(map[nr][nc] && !visit[nr][nc][cur[2]]) {
					visit[nr][nc][cur[2]] = true;
					q.add(new int[] {nr, nc, cur[2], cnt});
				}
			}
			if(cur[2] > 0) {
				int k = cur[2] - 1;
				for (int i = 0; i < 8; i++) {
					nr = cur[0] + horse[i][0];
					nc = cur[1] + horse[i][1];
					if(nr < 0 || nc < 0 || nr >= H || nc >= W) continue;
					if(map[nr][nc] && !visit[nr][nc][k]) {
						visit[nr][nc][k] = true;
						q.add(new int[] {nr, nc, k, cnt});
					}
				}
			}
		}
		System.out.println(result == Integer.MAX_VALUE ? "-1" : result-1);
	}
}
