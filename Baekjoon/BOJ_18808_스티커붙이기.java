package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_18808_스티커붙이기 {
	private static int result;
	private static int N;
	private static int M;
	private static boolean[][] map;
	private static int K;
	private static ArrayList<boolean[][]> sticker;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); // 1~40
		map = new boolean[N][M];
		K = Integer.parseInt(st.nextToken()); // 1~100 : 스티커 개수
		sticker = new ArrayList<boolean[][]>();
		
		int a, b;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			sticker.add(new boolean[a][b]);
			for (int j = 0; j < a; j++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int k = 0; k < b; k++) {
					sticker.get(i)[j][k] = st.nextToken().equals("1") ? true : false;
				}
			}
		}
		for (int i = 0; i < K; i++) {
			boolean[][] cur = sticker.get(i);
			// 4번 회전하고 반복, 만약 붙이면 break;
			top:
			for (int j = 0; j < 4; j++) {
				// 붙이기 도전
				for (int r = 0; r <= N-cur.length; r++) {
					for (int c = 0; c <= M-cur[0].length; c++) {
						//붙일 수 있는지 확인
						patch:
						for (int k = 0; k < cur.length; k++) {
							for (int l = 0; l < cur[0].length; l++) {
								if(cur[k][l] && map[r+k][c+l]) {
									break patch;
								}
								//붙일 수 있다면 내 종이에 체크하기
								if(l == cur[0].length-1 && k == cur.length-1) {
									for (int m = 0; m < cur.length; m++) {
										for (int n = 0; n < cur[0].length; n++) {
											if(cur[m][n]) {
												map[r+m][c+n] = true;
											}
										}
									}
									break top;
								}
							}
						}
					}
				}
				// 붙이기 실패
				cur = turn(cur);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]) result++;
			}
		}
		System.out.println(result);
	}

	private static boolean[][] turn(boolean[][] arr) {
		boolean[][] temp = new boolean[arr[0].length][arr.length];
		for (int i = 0; i < arr[0].length; i++) { //5
			for (int j = 0; j < arr.length; j++) { //2
				temp[i][j] = arr[arr.length - 1 - j][i];
			}
		}
		return temp;
	}
}
