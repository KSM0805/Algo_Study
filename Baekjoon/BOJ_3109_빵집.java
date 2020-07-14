package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3109 {
	private static int C;
	private static int R;
	private static int max;
	private static int[] dr = {-1,0,1}; //오른쪽위 부터 오른쪽 오른쪽아래
	private static boolean[][] map;
	private static boolean check;
	private static boolean[][] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		visit = new boolean[R][C];
		map = new boolean[R][C];
		String str;
		for (int i = 0; i < R; i++) {
			str = bf.readLine().trim();
			for (int j = 0; j < C; j++) {
				if(str.charAt(j)=='.') map[i][j] = true;
			}
		}
//		for (int i = 0; i < R; i++) {
//			map[i] = bf.readLine().toCharArray();
//		}
		max = 0;
		//0열에서 마지막 열까지 오른쪽, 오른쪽아래, 오른쪽 위 3가지만 이동가능
		for (int i = 0; i < R; i++) {
			check = false;
			dfs(i,0);
		}
		System.out.println(max);
	}

	private static void dfs(int row, int col) {
		int r = row;
		int c = col;
		if(col == C-1) {
			max++;
			check = true;
			return;
		}
		for (int i = 0; i < 3; i++) {
			int nr = r + dr[i];
			int nc = c + 1;
			if(nr<R && nc<C && nr>-1 && nc>-1  && map[nr][nc]) {
				map[nr][nc] = false;
				dfs(nr,nc);
				if(check) return;
			}
		}
	}
}
