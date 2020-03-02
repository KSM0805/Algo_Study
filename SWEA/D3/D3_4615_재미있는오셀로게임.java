package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_4615 {
	static int N;
	static int M;
	static int[][] MAP; 
	static int[] cnt = new int[2];
						// 상 하 좌 우 좌상 좌하 우상 우하
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{1,-1},{-1,1},{1,1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			cnt[0] = 0;
			cnt[1] = 0;
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			MAP = new int[N][N];
			// 1은 흑돌 , 2는 백돌
			MAP[N/2][N/2-1] = 1;
			MAP[N/2][N/2] = 2;
			MAP[N/2-1][N/2-1] = 2;
			MAP[N/2-1][N/2] = 1;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				game(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
			}
			count();
			System.out.println("#"+test_case+" "+cnt[0]+" "+cnt[1]);
		}//for end
	}
	private static void count() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(MAP[i][j] == 1) cnt[0]++;
				else if(MAP[i][j] == 2) cnt[1]++;
			}
		}
	}
	private static void game(int row, int col, int stone) {
		int r, c, mr, mc;
		MAP[row][col] = stone;
		for (int i = 0; i < 8; i++) {
			r = row;
			c = col;
			//항상 한칸만 사이에 둔건 아니고 여러칸!
			for (int j = 1; j < N; j++) {
				r = r + dir[i][0];
				c = c + dir[i][1];
				if(r >= N || c >= N || r < 0 || c < 0) break;
				else {
					if(MAP[r][c] == stone) {
						change(row,col,r,c,i,stone); 
					}
				}
			}
		}
	}
	private static void change(int currentR, int currentC, int targetR, int targetC, int d, int st) {
		int dis;
		int r = currentR;
		int c = currentC;
		boolean x = true;
		if(d<2) {
			dis = Math.abs(targetR-currentR);
		}else {
			dis = Math.abs(targetC-currentC);
		}
		for (int i = 1; i < dis; i++) {
			r = r + dir[d][0];
			c = c + dir[d][1];
			if(MAP[r][c] != (st*2)%3) x = false;
		}
		r = currentR;
		c = currentC;
		if(x) {
			for (int i = 1; i < dis; i++) {
				r = r + dir[d][0];
				c = c + dir[d][1];
				MAP[r][c] = st;
			}
		}
	}
}
