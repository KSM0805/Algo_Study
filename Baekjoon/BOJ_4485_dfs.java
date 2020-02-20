package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4485_dfs {
	private static int N;
	private static String[] str;
	private static int[][] visit;
	private static int min;
	private static int num;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		num = 1;
		while (true) {
			N = Integer.parseInt(bf.readLine().trim());
			if(N==0) break;
			str = new String[N];
			for (int i = 0; i < N; i++) {
				str[i] = bf.readLine().trim();
			}
			//bfs
			visit = new int[N][N];
			
			min = Integer.MAX_VALUE;
			dfs(0,0,str[0].charAt(0)-'0');
			System.out.println("Problem "+num+": "+min);
			num++;
		} // 동작 끝
	} // main 끝

	private static void dfs(int row, int col, int cost) {
		int nr,nc;
		if(min<cost) return;
		if(row == N-1 && col == N-1) {
			min = Math.min(min, cost);
			return;
		}
		for (int i = 0; i < 4; i++) {
			nr = row + dir[i][0];
			nc = col + dir[i][1];
			if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
			else {
				if(visit[nr][nc] == 0) { //처음 왔거나 잃은 루피수가 더 적을 때만
					visit[nr][nc] = 1;
					dfs(nr,nc,cost+(str[nr].charAt(nc<<1)-'0'));
					visit[nr][nc] = 0;
					
				}
			}
		}
	}
}