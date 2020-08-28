package BOJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_17086_아기상어2 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		int result = Integer.MIN_VALUE;
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num == 1 ? -1 : 0;
			}
		}
		int[][] dir = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};
		// -1이 아니면 bfs하면서 만나면 break
		LinkedList<int[]> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					// bfs
					q.clear();
					boolean[][] visit = new boolean[N][M];
					q.add(new int[] {i,j,0});
					visit[i][j] = true;
					top:
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						for (int k = 0; k < 8; k++) {
							int nr = cur[0] + dir[k][0];
							int nc = cur[1] + dir[k][1];
							if(nr < 0 || nc < 0 || nr >= N || nc >= M || visit[nr][nc] ) continue;
							if(map[nr][nc] == -1) {
								map[i][j] = cur[2] + 1;
								result = Math.max(result, cur[2] + 1);
								break top;
							}
							q.add(new int[] {nr, nc, cur[2] + 1});
							visit[nr][nc] = true;
						}
					}
				}
			}
		}
		System.out.println(result);
	}
}

