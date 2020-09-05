package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class BOJ_1941_소문난칠공주 {
	private static int result;
	private static int[][] map;
	private static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	private static ArrayList<int[]> list = new ArrayList<>();
	private static boolean[][] visit;
	private static boolean[][] visited = new boolean[5][5];
	private static LinkedList<int[]> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		map = new int[5][5];
		for (int i = 0; i < 5; i++) {
			String str = bf.readLine().trim();
			for (int j = 0; j < 5; j++) {
				char ch = str.charAt(j);
				if(ch == 'Y') map[i][j] = 1;
				else map[i][j] = 0;
			}
		}
		visit = new boolean[5][5];
		choose(0,0,-1,0);
		System.out.println(result);
	}
	private static void choose(int cnt, int r, int c, int check) {
		if(check > 3) return;
		if(cnt == 7) {
			solve();
			return;
		}
		for (int i = r; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(i == r && j <= c) continue;
				list.add(new int[] {i,j});
				visit[i][j] = true;
				choose(cnt + 1, i, j, check + map[i][j]);
				list.remove(list.size() - 1);
				visit[i][j] = false;
			}
		}
	}
	
	private static void solve() {
		for (boolean[] is : visited) {
			Arrays.fill(is, false);
		}
		q.clear();
		q.add(new int[] {list.get(0)[0], list.get(0)[1]});
		visited[list.get(0)[0]][list.get(0)[1]] = true;
		int cnt = 1;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dir[i][0];
				int nc = cur[1] + dir[i][1];
				if(nr < 0 || nc < 0 || nr >= 5 || nc >= 5 || visited[nr][nc] || !visit[nr][nc]) continue;
				visited[nr][nc] = true;
				cnt++;
				q.add(new int[] {nr, nc});
			}
		}
		if(cnt == 7) {
			result++;
		}
	}
}