package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_14502_class {
	private static int M;
	private static int N;
	private static int[][] map;
	private static ArrayList<int[]> virus;
	private static boolean[][] visit;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static int min;
	private static int wall;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		//벽은 3개
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); //3~8
		map = new int[N][M];
		wall = 0;
		virus = new ArrayList<>();//2~10
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					virus.add(new int[] {i,j});
				}else if(map[i][j]==1) wall++;
			}
		}
		min = Integer.MAX_VALUE;
		wall(0,0);
		System.out.println(N*M-wall-3-min);
	}

	private static void wall(int cnt,int idx) {
		if(cnt==3) {
			virus();
		}else{
			for (int i = idx; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j]==0) {
						map[i][j] = 1;
						wall(cnt + 1, i);
						map[i][j] = 0;
					}
				}
			}
		}
	}

	private static void virus() {
		//bfs 로 할거임
		int numVir = 0;
		boolean[][] visit = new boolean[N][M];
		LinkedList<int[]> q = new LinkedList<>();
		for (int i = 0; i < virus.size(); i++) {
			int[] ar = virus.get(i);
			q.offer(new int[] {ar[0],ar[1]});
			visit[ar[0]][ar[1]] = true;
			numVir++;
		}
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			if(min<numVir) return;
			for (int i = 0; i < 4; i++) {
				int nr = pos[0] + dir[i][0];
				int nc = pos[1] + dir[i][1];
				if(nr>-1 && nc>-1 && nr<N && nc<M && !visit[nr][nc] && map[nr][nc]==0) {
					visit[nr][nc] = true;
					numVir++;
					q.offer(new int[] {nr,nc});
				}
			}
		}
		min = Math.min(min, numVir);
	}
}
