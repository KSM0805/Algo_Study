package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노2 {
	private static int result;
	private static int[][] map;
	private static int N;
	private static int M;
	private static int[] dirX = {0,0,0,0,0,1,2,3,0,0,1,2,0,0,1,2,0,1,1,1,0,1,1,1,0,1,2,2,0,1,2,2,0,0,0,1,0,0,0,1,0,0,1,1,0,1,1,2
								,0,1,1,2,0,0,1,1,0,0,1,1,0,0,0,1,0,1,1,1,0,1,1,2,0,1,1,2};
	private static int[] dirY = {0,1,2,3,0,0,0,0,0,1,1,1,0,-1,-1,-1,0,0,-1,-2,0,0,1,2,0,0,0,-1,0,0,0,1,0,1,2,2,0,-1,-2,-2,0,1,0,1,
								0,0,1,1,0,0,-1,-1,0,-1,-1,-2,0,1,1,2,0,1,2,1,0,-1,0,1,0,-1,0,0,0,1,0,0};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int [N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int nr, nc;
				for (int k = 0; k < dirX.length; k+=4) {
					int total = 0;
					for (int l = 0; l < 4; l++) {
						nr = i + dirX[k+l];
						nc = j + dirY[k+l];
						if(nr < 0 || nr >= N || nc < 0 || nc >= M) break;
						total += map[nr][nc];
						if(l == 3) {
							result = Math.max(result, total);
						}
					}
				}
			}
		}
		System.out.println(result);
	}
}
