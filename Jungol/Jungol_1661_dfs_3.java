package jungol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//터지는거
public class Jungol_1661_dfs_3 {
	private static int Y;
	private static int X;
	private static int startX;
	private static int startY;
	private static int endX;
	private static int endY;
	private static int max;
	private static int[][] visited;
	
	static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	private static String[] str;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = 1;
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			Y = Integer.parseInt(st.nextToken()); //8 열
			X = Integer.parseInt(st.nextToken()); //7 행
			st = new StringTokenizer(bf.readLine().trim()," ");
			startY = Integer.parseInt(st.nextToken())-1;
			startX = Integer.parseInt(st.nextToken())-1;
			endY = Integer.parseInt(st.nextToken())-1;
			endX = Integer.parseInt(st.nextToken())-1;
			
			max = Integer.MAX_VALUE;
			
			visited = new int[X][Y];
			str = new String[X];
			for (int i = 0; i < X; i++) {
				str[i] = bf.readLine().trim();
			}
			for (int i = 0; i < X; i++) {
				for (int j = 0; j < Y; j++) {
					visited[i][j] = Integer.MAX_VALUE;
				}
			}
			//dfs
			visited[startX][startY] = 0;
			dfs(startX,startY,0);

			System.out.println(max);
		}//for end
	}

	private static void dfs(int row, int col, int cnt) {
		if(row == endX && col == endY) {
			max = Math.min(max, cnt);
			return ;
		}
		for (int i = 0; i < 4; i++) {
			int nr = row + dir[i][0];
			int nc = col + dir[i][1];
			int count = cnt + 1;
			if(nr>-1 && nc>-1 && nr< X && nc<Y ) {
				int temp = visited[nr][nc]; 
				if(visited[nr][nc]>count && str[nr].charAt(nc)=='0') {
					visited[nr][nc] = count;
					dfs(nr,nc,count);
					visited[nr][nc] = temp;
			}
			}
		}
	}
}
