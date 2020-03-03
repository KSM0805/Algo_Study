package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BOJ_10026_적록색약 {

	private static boolean[][] visit;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static char[][][] map;
	private static int N;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int origin = 0;
		int solve = 0;
		N = Integer.parseInt(bf.readLine().trim());
		map = new char[N][N][2]; // 0:은 정상, 1:적록색약
		String str;
		for (int i = 0; i < N; i++) {
			str = bf.readLine().trim();
			for (int j = 0; j < N; j++) {
				if(str.charAt(j)=='G') {
					map[i][j][0] = 'G';
					map[i][j][1] = 'R';
				}else {
					map[i][j][0] = str.charAt(j);
					map[i][j][1] = str.charAt(j);
				}
			}
		}
//		bfs
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visit[i][j]) {
					visit[i][j] = true;
					check(i,j,0);
					origin++;
				}
			}
		}
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visit[i][j]) {
					visit[i][j] = true;
					check(i,j,1);
					solve++;
				}
			}
		}
		System.out.println(origin + " " + solve);
	}

	private static void check(int r, int c, int mode) {
		char comp = map[r][c][mode];
		LinkedList<int[]> q = new LinkedList<>();
		q.offer(new int[] {r,c});
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			for (int k = 0; k < 4; k++) {
				int nr = pos[0] + dir[k][0];
				int nc = pos[1] + dir[k][1];
				if(nr<0 || nc<0 || nr>N-1 || nc>N-1) continue;
				if(map[nr][nc][mode]==comp && !visit[nr][nc]) {
					visit[nr][nc] = true;
					q.offer(new int[] {nr,nc});
				}
			}
		}
	}
}
