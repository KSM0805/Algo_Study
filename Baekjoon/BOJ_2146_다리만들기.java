package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_2146_다리만들기 {
	private static int result;
	private static int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};
	private static int N;
	private static int[][] map;
	private static int cnt;
	private static boolean[][] visit;

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
		cnt = 2;	// 단지 번호
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1) dangiMaker(i,j);
			}
		}
		//짧은 다리 구하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] > 1) bfs(i,j,map[i][j]);
			}
		}
		System.out.println(result-1);
	}
	private static void bfs(int r, int c, int dangi) {
		visit = new boolean[N][N];
		LinkedList<int[]> q = new LinkedList<>();
		q.add(new int[] {r,c,0});
		visit[r][c] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(result < cur[2]) return;
			if(map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] != dangi) {
				result = cur[2];
				return;
			}
			int nr, nc;
			for (int i = 0; i < 4; i++) {
				nr = cur[0] + dir[i][0];
				nc = cur[1] + dir[i][1];
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if(map[nr][nc] != dangi && !visit[nr][nc]) {
					visit[nr][nc] = true;
					q.add(new int[] {nr, nc, cur[2] + 1});
				}
			}
		}
	}

	private static void dangiMaker(int i, int j) {
		LinkedList<int[]> q = new LinkedList<>();
		q.add(new int[] {i,j});
		map[i][j] = cnt;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int nr, nc;
			for (int k = 0; k < 4; k++) {
				nr = cur[0] + dir[k][0];
				nc = cur[1] + dir[k][1];
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if(map[nr][nc] == 1) {
					map[nr][nc] = cnt;
					q.add(new int[] {nr,nc});
				}
			}
		}
		cnt++;
	}
}
