package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2468 {
	private static int N;
	private static int[][] map;
	private static int[][] visit;
	private static int max;
	private static int area;
	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,-1,1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim());
		map = new int[N][N];
		StringTokenizer st;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(!list.contains(map[i][j])) {
					list.add(map[i][j]);
				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			int depth = list.get(i);
			visit = new int[N][N];
			area = 0;
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if(visit[j][k]==0 && map[j][k] > depth) {
						area++;
						dfs(depth,j,k); //물에 안잠긴 구역
					}
				}
			}
			max = Math.max(max, Math.abs(area));
		}
		if(max == 0) System.out.println("1");
		else System.out.println(max);
	}
	
	private static void dfs(int depth, int row, int col) {
		int nr,nc;
		int r = row;
		int c = col;
		for (int i = 0; i < 4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if(nr>-1 && nc>-1 && nr<N && nc<N && visit[nr][nc] == 0 && map[nr][nc]>depth) {
				visit[nr][nc] = area;
				dfs(depth,nr,nc);
			}
		}
	}
}
