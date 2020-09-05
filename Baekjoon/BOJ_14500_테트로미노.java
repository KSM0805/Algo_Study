package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노 {
	private static int result;
	private static int[][] dir = {{1,0}, {0,1}, {0,-1}};
	static LinkedList<int[]> tetromino = new LinkedList<>();
	private static int[][] map;
	private static boolean[][] visit;
	private static int N;
	private static int M;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int [N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tetromino.add(new int[] {i,j});
				visit[i][j] = true;
				dfs(map[i][j]);
				visit[i][j] = false;
				tetromino.pollLast();
			}
		}
		System.out.println(result);
	}

	private static void dfs(int total) {
		if(tetromino.size() == 4) {
			result = Math.max(result, total);
			return;
		}
		for (int i = 0, size = tetromino.size(); i < size; i++) {
			int[] cur = tetromino.get(i);
			for (int j = 0; j < 3; j++) {
				int nr = cur[0] + dir[j][0];
				int nc = cur[1] + dir[j][1];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || visit[nr][nc]) continue;
				tetromino.add(new int[] {nr, nc});
				visit[nr][nc] = true;
				dfs(total + map[nr][nc]);
				visit[nr][nc] = false;
				tetromino.pollLast();
			}
		}
	}
}
