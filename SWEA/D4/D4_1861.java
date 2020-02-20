package swea.d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1861 {
	private static int N;
	private static int[][] map;
	private static int max;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static int maxPos;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			max = 0;
			maxPos = -1;
			N = Integer.parseInt(bf.readLine().trim());
			if(N==1) System.out.println("#"+test_case+" 1 1");
			map = new int[(N*N)+1][2]; // 0은 쓰지 않는다.
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < N; j++) {
					int a = Integer.parseInt(st.nextToken());
					map[a][0] = i;
					map[a][1] = j;
				}
				
			}
			int pos = 1;
			int r,c,nr,nc,cur = -1;
			int cnt = 0;
			while(pos < N*N) {
				boolean ok = false;
				r = map[pos][0];
				c = map[pos][1];
				pos++;
				for (int i = 0; i < 4; i++) {
					nr = r + dir[i][0];
					nc = c + dir[i][1];
					if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
					if(nr == map[pos][0] && nc == map[pos][1]) {
						ok = true;
						if(cnt == 0) {
							cur = pos-1;
						}
						cnt++;
						break;
					}
				}
				if(pos == N*N) {
					if(max<cnt) {
						max = cnt;
						maxPos = cur;
					}
				}
				if(!ok) {
					if(max<cnt) {
						max = cnt;
						maxPos = cur;
					}
					cnt = 0;
				}
			}
			System.out.println("#"+test_case+" "+maxPos + " " +(max+1));
		}//for end
	}
}
