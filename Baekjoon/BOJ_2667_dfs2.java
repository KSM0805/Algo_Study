package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_2667_dfs2 {
	private static int N;
	private static int[][] MAP;
	private static int[][] Visited;
	private static int no;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim());
		MAP = new int[N][N];
		Visited = new int [N][N];
		no = 2;
		
		for (int i = 0; i < N; i++) {
			String str = bf.readLine().trim();
			for (int j = 0; j < N; j++) {
//				MAP[i][j] = Character.getNumericValue(str.charAt(j));
				MAP[i][j] = str.charAt(j)-'0';
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(MAP[i][j]==1) { //단지 번호가 부여되지 않은 아이들
					MAP[i][j] = no;
					dfs(i,j);
					no += 1;
//					MAP[i][j] = ++no;
//					dfs(i,j,no);
				}
			}
		}

		
		int[] count = new int[no]; //0번 안쓰고 사용
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(MAP[i][j]>0) {
					count[MAP[i][j]-1]++;
				}
			}
			
		}
		System.out.println(no-1);
		Arrays.sort(count);
		for (int i = 1; i < count.length; i++) {
			System.out.println(count[i]);
		}
	}
	static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
	/*private static void dfs(int row, int col, int num) {

		for (int i = 0; i < 4; i++) {
			int nr = row + dir[i][0];
			int nc = col + dir[i][1];
			if(nr>-1 && nr<N && nc>-1 && nc<N && MAP[nr][nc]==1) {
				MAP[nr][nc] = num;
				dfs(nr,nc,num);
			}
		}
	}*/
	private static void dfs(int row, int col) {
		for (int i = 0; i < 4; i++) {
			int nr = row + dir[i][0];
			int nc = col + dir[i][1];
			if(nr>-1 && nc>-1 && nr<N && nc< N && MAP[nr][nc]==1) {
				MAP[nr][nc] = no;
				dfs(nr,nc);
			}
		}
	}
}
