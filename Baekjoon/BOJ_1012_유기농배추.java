package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1012_유기농배추 {
	private static int M;
	private static int N;
	private static int K;
	private static int[][] plants;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static boolean[][] visit;
	private static boolean[][] map;
	private static int min;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			M = Integer.parseInt(st.nextToken()); //가로길이
			N = Integer.parseInt(st.nextToken()); //세로길이
			K = Integer.parseInt(st.nextToken()); //배추 갯수
			map = new boolean[N][M]; //맵
			plants = new int[K][2];
			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				plants[j][1] = Integer.parseInt(st.nextToken());
				plants[j][0] = Integer.parseInt(st.nextToken());
				map[plants[j][0]][plants[j][1]] = true;
			}
			min = 1;
			//dfs
			visit = new boolean[N][M];
			int pos = 0;
			while(pos<K) {
				int r = plants[pos][0];
				int c = plants[pos][1];
				if(!visit[r][c]) {
					visit[r][c] = true;
//					dfs(r,c);
					//bfs
					LinkedList<int[]> q = new LinkedList<>();
					q.offer(new int[] {r,c});
					while(!q.isEmpty()) {
						int[] arr = q.poll();
						for (int j = 0; j < 4; j++) {
							int nr = arr[0] + dir[j][0];
							int nc = arr[1] + dir[j][1];
							if(nr>-1 && nc>-1 && nr<N && nc<M && !visit[nr][nc] && map[nr][nc]) {
								visit[nr][nc] = true;
								q.offer(new int[] {nr,nc});
							}
						}
					}
					min++;
				}
				pos++;
			}
			System.out.println(min-1);
		}
	}

	private static void dfs(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			if(nr>-1 && nc>-1 && nr<N && nc<M && !visit[nr][nc] && map[nr][nc]) {
				visit[nr][nc] = true;
				dfs(nr,nc);
			}
		}
	}
}
