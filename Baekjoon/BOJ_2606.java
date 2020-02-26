package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_2606 {
	private static int N;
	private static boolean[][] map;
	private static boolean[] com;
	private static int result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine().trim());
		int pair = Integer.parseInt(bf.readLine().trim());
		map = new boolean[N][N];
		StringTokenizer st;
		for (int i = 0; i < pair; i++) {
			st = new StringTokenizer(bf.readLine().trim(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = true;
			map[b][a] = true;
		}
		com = new boolean[N];
		com[0] = true;
//		dfs(0);
		bfs();
		result = 0;
		for (int i = 0; i < N; i++) {
			if(com[i]) result++;
		}
		System.out.println(result-1);
	}

	private static void bfs() {
		LinkedList<Integer> q = new LinkedList<>();
		q.offer(0);
		while(!q.isEmpty()) {
			int a = q.poll();
			for (int i = 0; i < N; i++) {
				if(map[a][i] && !com[i]) {
					com[i] = true;
					q.offer(i);
				}
			}
		}
		
	}

	private static void dfs(int pos) {
		for (int i = 0; i < N; i++) {
			if(map[pos][i] && !com[i]) {
				com[i] = true;
				dfs(i);
			}
		}
		
	}
}
