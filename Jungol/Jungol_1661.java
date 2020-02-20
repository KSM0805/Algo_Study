package jungol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Jungol_1661 {
	private static int Y;
	private static int X;
	private static int startX;
	private static int startY;
	private static int endX;
	private static int endY;
	private static int max;

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
			
			int[][] visited = new int[X][Y];
			String[] str = new String[X];
			for (int i = 0; i < X; i++) {
				str[i] = bf.readLine().trim();
			}
			
			//bfs 시작
			max = Integer.MAX_VALUE;
			
			LinkedList<int[]> q = new LinkedList<>();
			q.offer(new int[] {startX,startY, 0});
//			for (int i = 0; i < X; i++) {
//				for (int j = 0; j < Y; j++) {
//					visited[i][j] = Integer.MAX_VALUE;
//				}
//				
//			}
			visited[startX][startY] = 1;
			
			int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
			while(!q.isEmpty()) {
				int[] pos = q.poll();
				int r = pos[0];
				int c = pos[1];
				
				if(r==endX && c == endY && max > pos[2]) max = pos[2];
				else {
					for (int i = 0; i < 4; i++) {
						int nr = r + dir[i][0];
						int nc = c + dir[i][1];
						int cnt = pos[2]+1;
						
						if(nr>-1 && nc>-1 && nr<X && nc<Y && visited[nr][nc] == 0 && str[nr].charAt(nc)=='0') {
							visited[nr][nc] = 1;
							q.offer(new int[] {nr,nc,cnt});
						}
						
					}
					
				}
			}
			System.out.println(max);
		}//for end
	}
}
