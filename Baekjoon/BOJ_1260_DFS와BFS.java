package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1260 {
	private static int N;
	private static int M;
	private static int V;
	private static int[][] map;
	private static boolean[] visted;

	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visted = new boolean[N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			map[x][y] = 1;
			map[y][x] = 1;
		}
		visted[V-1] = true;
		dfs(V-1);
		visted = new boolean[N];
		System.out.println();
		bfs();
	}

	private static void bfs() {
		LinkedList<Integer> q = new LinkedList<>();
		q.add(V-1);
		visted[V-1] = true;
		while(!q.isEmpty()) {
			int a = q.poll();
			System.out.print(a+1+" ");
			for (int i = 0; i < N; i++) {
				if(map[a][i] == 1 && !visted[i]) {
					visted[i] = true;
					q.add(i);
				}
			}
		}
		
	}

	private static void dfs(int pos) {
		System.out.print(pos+1+" ");
		for (int i = 0; i < N; i++) {
			if(map[pos][i] == 1 && !visted[i]) {
				visted[i] = true;
				dfs(i);
				
			}
		}
	}
}
