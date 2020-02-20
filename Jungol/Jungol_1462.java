package jungol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Jungol_1462 {
	private static int N;
	private static int M;
	private static boolean[][] map;
	private static int[][] visit;
	private static int moveMax;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		String str;
		for (int i = 0; i < N; i++) {
			str = bf.readLine().trim();
			for (int j = 0; j < M; j++) {
				if(str.charAt(j)=='L') map[i][j] = true; //L은 육지 W는 바다
			}
		}
		moveMax = 0;
		//최장 거리를 찾기! (그러나 최단거리)
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]) {
					visit = new int[N][M];
					bfs(i,j,1);
				}
			}
		}
		System.out.println(moveMax);
		//1. L에서 연결된 다른 L로 넘어갈때 걸어가는 횟수 cnt(최단 경로로 -> bfs L의 갯수만큼?)
		
		//2. L의 위치마다 cnt를 다르게 세면서 최댓값을 못넘는 경우 out
		
	}

	private static void bfs(int row, int col, int cnt) {
		int sum = 0;
		int[] end = new int[2];
		LinkedList<int[]> q = new LinkedList<>();
		q.offer(new int[] {row,col,cnt});
		visit[row][col] = cnt; //첫번째 위치 방문
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int r = arr[0];
			int c = arr[1];
			int count = arr[2]+1;
			boolean check = false;
			if(r-1>-1 && (visit[r-1][c]==0 || visit[r-1][c]> count) && map[r-1][c]) {
				visit[r-1][c] = count;
				q.offer(new int[] {r-1,c,count});
				check = true;
			}
			if(r+1<N && (visit[r+1][c]==0 || visit[r+1][c]> count) && map[r+1][c]) {
				visit[r+1][c] = count;
				q.offer(new int[] {r+1,c,count});
				check = true;
			}
			if(c-1>-1 && (visit[r][c-1]==0 || visit[r][c-1]> count) && map[r][c-1]) {
				visit[r][c-1] = count;
				q.offer(new int[] {r,c-1,count});
				check = true;
			}
			if(c+1<M && (visit[r][c+1]==0 || visit[r][c+1]> count) && map[r][c+1]) {
				visit[r][c+1] = count;
				q.offer(new int[] {r,c+1,count});
				check = true;
			}
			if(check) {
				if(sum<arr[2]) {
					sum = arr[2];
				}
			}
		}// while 문 끝
		if(sum>moveMax) {
			moveMax = sum;
		}
	}
}
