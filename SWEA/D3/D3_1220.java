package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_1220 {
	static int size;
	static int[][] map;
	static int[][] dir = {{1,1},{-1,2}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			size = Integer.parseInt(bf.readLine().trim());
			map = new int[size][size];
			for (int i = 0; i < size; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < size; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//2. 1을 먼저 아래로(맨 아래행 부터 차례대로 내리기)
			for (int i = 99; i > -1; i--) {
				for (int j = 0; j < size; j++) {
					if(map[i][j]==1) move(i,j,0);
				}
			}
			//3. 2를 위로
			//3. 2-1~2-3 반복
			for (int i = 0; i <size; i++) {
				for (int j = 0; j < size; j++) {
					if(map[i][j]==2) move(i,j,1);
				}
			}
			//4. 한 열마다 1을 찾으면서 다음에 2가 나올때 카운트 하고 끗
			int result = count();
			System.out.println("#"+test_case+" "+result);
//			for (int[] a : map) {
//				for (int b : a) {
//					System.out.print(b+" ");
//				}
//				System.out.println();
//			}
		}//for end
	}
	private static int count() {
		int cnt = 0;
		for (int i = 0; i < size; i++) { // 열반복
			for (int j = 0; j < size - 1; j++) { //행반복
				if(map[j][i]==1 && map[j+1][i]==2) cnt++; 
			}
		}
		return cnt;
	}
	//1. 1은아래로 2는 위로 탐색
	private static void move(int row, int col, int magnet) {
		int r = row;
		int nr;
		while(true) {
			nr = r+dir[magnet][0];
			//2-2. 맨아래행에 닿으면 그 1은 제거
			if( nr==100 | nr==-1 ) {
				map[r][col] = 0;
				break;
			}else if(map[nr][col]==0) {
				map[r][col] = 0;
				r = nr;
				map[r][col] = dir[magnet][1];
			}else {
				//2-1. 2를 만나면 stop
				map[r][col] = dir[magnet][1];
				break;
			}
		}
		
	}
}
