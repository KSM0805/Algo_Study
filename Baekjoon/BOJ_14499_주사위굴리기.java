package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499_주사위굴리기 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		// 위 아래 앞 뒤 좌 우  -> 마킹해두기
		// 0 1  2 3 4 5 -> 인덱스로 접근
		int[] dice = {0,0,0,0,0,0};
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		int N = Integer.parseInt(st.nextToken());	// 맵크기
		int M = Integer.parseInt(st.nextToken());
		int diceX = Integer.parseInt(st.nextToken());	// 주사위 위치
		int diceY = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());	// 1~1000 : 명령의 갯수
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(bf.readLine().trim()," ");
		// 1 동 , 2 서, 3 북, 4 남
		for (int i = 0; i < K; i++) {
			int tmp;
			boolean check = true;
			switch (st.nextToken()) {
				case "1":
					if(diceY + 1 >= M) {
						check = false;
						break;
					}
					diceY++;
					tmp = dice[0];
					dice[0] = dice[4];
					dice[4] = dice[1];
					dice[1] = dice[5];
					dice[5] = tmp;
					break;
				case "2":
					if(diceY - 1 < 0) {
						check = false;
						break;
					}
					diceY--;
					tmp = dice[0];
					dice[0] = dice[5];
					dice[5] = dice[1];
					dice[1] = dice[4];
					dice[4] = tmp;
					break;
				case "3":
					if(diceX - 1 < 0) {
						check = false;
						break;
					}
					diceX--;
					tmp = dice[0];
					dice[0] = dice[3];
					dice[3] = dice[1];
					dice[1] = dice[2];
					dice[2] = tmp;
					break;
				case "4":
					if(diceX + 1 >= N) {
						check = false;
						break;
					}
					diceX++;
					tmp = dice[0];
					dice[0] = dice[2];
					dice[2] = dice[1];
					dice[1] = dice[3];
					dice[3] = tmp;
					break;
			}
			if(check) {
				if(map[diceX][diceY] == 0) {
					map[diceX][diceY] = dice[1];
				} else {
					dice[1] = map[diceX][diceY];
					map[diceX][diceY] = 0;
				}
				System.out.println(dice[0]);
			}
		}
	}
}
