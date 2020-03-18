package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class D4_1227_미로2 {
	private static int result;
	private static boolean[][] visit;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			bf.readLine();
			int[][] map = new int[100][100];
			int[] start = new int[2];
			int[] end = new int[2];
			String str;
			for (int i = 0; i < 100; i++) {
				str = bf.readLine().trim();
				for (int j = 0; j < 100; j++) {
					map[i][j] = str.charAt(j) - '0';
					if(map[i][j]==2) {
						start[0] = i;
						start[1] = j;
					}else if(map[i][j]==3) {
						end[0] = i;
						end[1] = j;
					}
				}
			}
			result = 0;
			LinkedList<int[]> q = new LinkedList<>();
			q.offer(new int[] {start[0],start[0]});
			visit = new boolean[100][100];
			visit[start[0]][start[0]] = true;
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				if(cur[0]==end[0] && cur[1]==end[1]) {
					result = 1;
					break;
				}
				for (int i = 0; i < 4; i++) {
					int nr = cur[0] + dir[i][0];
					int nc = cur[1] + dir[i][1];
					if(nr<0 || nc<0 || nr>=100 || nc>=100 || map[nr][nc]==1) continue;
					if(!visit[nr][nc]) {
						visit[nr][nc] = true;
						q.offer(new int[] {nr,nc});
					}
				}
			}
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end
}
