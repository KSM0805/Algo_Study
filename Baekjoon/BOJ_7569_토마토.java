package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_7569_토마토 {
	private static int result;
	private static int M;
	private static int N;
	private static int H;
	private static int[][][] box;
	private static int[][] dir = {{-1,0,0}, {1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};
	private static int totalCnt;
	private static int cnt;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		box = new int[N][M][H];
		totalCnt = 0;
		cnt = 0;
		LinkedList<int[]> tomato = new LinkedList<>();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int k = 0; k < M; k++) {
					box[j][k][i] = Integer.parseInt(st.nextToken());
					if(box[j][k][i] == 1) {
						tomato.add(new int[] {j,k,i});
						totalCnt++;
						cnt++;
					} else if(box[j][k][i] == 0) {
						totalCnt++;
					}
				}
			}
		}
		result = -1;
		while(!tomato.isEmpty()) {
			int size = tomato.size();
			for (int i = 0; i < size; i++) {
				int[] cur = tomato.poll();
				for (int j = 0; j < 6; j++) {
					int nr = cur[0] + dir[j][0];
					int nc = cur[1] + dir[j][1];
					int nz = cur[2] + dir[j][2];
					if(nr < 0 || nc < 0 || nz < 0 || nr >= N || nc >= M || nz >= H || box[nr][nc][nz] == -1) continue;
					if(box[nr][nc][nz] == 0) {
						cnt++;
						box[nr][nc][nz] = 1;
						tomato.add(new int[] {nr, nc, nz});
					}
				}
			}
			result++;
		}
		System.out.println(cnt == totalCnt ? result : "-1");
	}
}
