package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_17142_연구소3 {
	private static int result;
	private static int M;
	private static int N;
	private static int[][] map;
	private static int total;
	private static ArrayList<int[]> virus;
	private static LinkedList<int[]> onVirus;
	private static boolean[][] visit;
	private static int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		total = N * N;
		virus = new ArrayList<>();
		onVirus = new LinkedList<>();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1)	total--;	// 벽인 경우
				else if(map[i][j] == 2) virus.add(new int[] {i,j});	// 바이러스인 경우
			}
		}
		visit = new boolean[N][N];
		select(-1, 0);
		System.out.println(result == Integer.MAX_VALUE ? "-1" : result);
	}

	private static void select(int idx, int cnt) {
		if(cnt == M) {
			solve();
			return;
		}
		for (int i = idx + 1; i < virus.size(); i++) {
			int[] cur = virus.get(i);
			onVirus.add(new int[] {cur[0], cur[1], 0});
			select(i, cnt + 1);
			onVirus.pollLast();
		}
		
	}

	private static void solve() {
		int cnt = virus.size();
		for (int i = 0; i < onVirus.size(); i++) {
			int[] cur = onVirus.get(i);
			visit[cur[0]][cur[1]] = true;
		}
		LinkedList<int[]> q = (LinkedList<int[]>) onVirus.clone();
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(result <= cur[2]) break;
			if(cnt == total) {
				result = q.isEmpty() ? cur[2] : q.pollLast()[2];
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dir[i][0];
				int nc = cur[1] + dir[i][1];
				if(nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc] || map[nr][nc] == 1) continue;
				visit[nr][nc] = true;
				if(map[nr][nc] == 0) {
					cnt++;
				}
				q.add(new int[] {nr, nc, cur[2] + 1});
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visit[i][j] = false;
			}
		}
	}
}
