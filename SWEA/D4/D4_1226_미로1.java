package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class D4_1226 {
	private static int result;
	private static boolean[][] map;
	private static int stC;
	private static int endR;
	private static int endC;
	private static int stR;
	private static boolean[][] visit;
	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,-1,1};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int tc = Integer.parseInt(bf.readLine().trim());
			map = new boolean[16][16];
			String str;
			for (int i = 0; i < 16; i++) {
				str = bf.readLine().trim();
				for (int j = 0; j < 16; j++) {
					char ch = str.charAt(j);
					if(ch == '0') map[i][j] = true;
					else if(ch == '2') {
						stR = i;
						stC = j;
						map[i][j] = true;
					}else if(ch == '3') {
						endR = i;
						endC = j;
						map[i][j] = true;
					}
				}
			}
			result = 0;
			visit = new boolean[16][16];
			LinkedList<int[]> q = new LinkedList<>();
			q.offer(new int[] {stR,stC});
			visit[stR][stC] = true;
			int r,c;
			while(!q.isEmpty()) {
				int[] arr = q.poll();
				r = arr[0];
				c = arr[1];
				if(r == endR && c == endC) {
					result = 1;
					break;
				}
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if(nr>-1 && nc>-1 && nr<16 && nc<16 && map[nr][nc] && !visit[nr][nc]) {
						visit[nr][nc] = true;
						q.offer(new int[] {nr,nc});
					}
				}
			}
			System.out.println("#"+test_case+" "+result);
		}//for end
	}
/*	private static void dfs(int r, int c) {
		if (result == 1) return;
		if(r == endR && c == endC) {
			result = 1;
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr>-1 && nc>-1 && nr<16 && nc<16 && map[nr][nc] && !visit[nr][nc]) {
				visit[nr][nc] = true;
				dfs(nr,nc);
				visit[nr][nc] = false;
			}
		}
	}*/
}
