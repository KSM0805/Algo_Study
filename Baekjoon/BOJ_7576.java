package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_7576 {
	private static int M;
	private static int N;
	private static int[][] map;
	private static int[][] tomato;
	private static int num;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		tomato = new int[N*M][2];
		num = 0;
		int pos = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > -1) {
					if(map[i][j]==1) {
						tomato[pos][0] = i;
						tomato[pos][1] = j;
						pos++;
						num++;
					}
					else num++;
				}
			}
		}
		int day = 1;
		LinkedList<int[]> q = new LinkedList<>();
		q.offer(new int[] {0,pos}); // 처음부터
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int start = arr[0];
			int end = arr[1];
			int x = 0;
			boolean check = false;
			for (int i = start; i < end; i++) {
				int r = tomato[i][0];
				int c = tomato[i][1];
				if(r-1>-1 && map[r-1][c]==0) {
					map[r-1][c] = 1;
					tomato[end+x][0] = r-1;
					tomato[end+x][1] = c;
					x++;
					check = true;
				}
				if(r+1<N && map[r+1][c]==0) {
					map[r+1][c] = 1;
					tomato[end+x][0] = r+1;
					tomato[end+x][1] = c;
					x++;
					check = true;
				}
				if(c-1>-1 && map[r][c-1]==0) {
					map[r][c-1] = 1;
					tomato[end+x][0] = r;
					tomato[end+x][1] = c-1;
					x++;
					check = true;
				}
				if(c+1<M && map[r][c+1]==0) {
					map[r][c+1] = 1;
					tomato[end+x][0] = r;
					tomato[end+x][1] = c+1;
					x++;
					check = true;
				}
			}
			if(check) {
				q.offer(new int[] {end, end+x});
				day++;
			}
		}
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==1) cnt++;
			}
		}
		if(num==cnt) System.out.println(day-1);
		else System.out.println("-1");
	}
}
