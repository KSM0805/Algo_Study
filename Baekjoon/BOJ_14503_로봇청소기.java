package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_로봇청소기 {
	private static int result;
	private static int[][] dir = {{-1,0}, {0,-1}, {1,0}, {0,1},{-1,0},{0,-1}, {1,0}, {0,1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(bf.readLine().trim()," ");
		int posX = Integer.parseInt(st.nextToken());
		int posY = Integer.parseInt(st.nextToken());
		//0 북, 1 동, 2 남, 3 서 
		int d = Integer.parseInt(st.nextToken());
		if(d % 2 != 0) d = (d + 2) % 4;
		
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		top:
		while(true) {
			if(map[posX][posY] == 0) {
				result++;
				map[posX][posY] = 2;	// 청소된 위치
			}
			int nr, nc;
			for(int i = 1; i < 5; i++) {
				nr = posX + dir[d + i][0];
				nc = posY + dir[d + i][1];
				if(map[nr][nc] == 0) {	// 청소가 안됐으면
					posX = nr;
					posY = nc;
					d = d + i > 3 ? d + i - 4 : d + i;
					continue top;
				}
			}
			nr = posX + dir[d + 2][0];
			nc = posY + dir[d + 2][1];
			if(map[nr][nc] == 2) {
				posX = nr;
				posY = nc;
			} else break;
		}
		System.out.println(result);
	}
}
