package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17281_baseball {
	private static int result;
	private static int N;
	private static int[][] arr;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		// 완탐
		// 1-9번타자 순서 -> 1번 선수 : 4번 타자 고정	
		N = Integer.parseInt(bf.readLine().trim());
		arr = new int[N][9];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());	// 1: 안타, 2: 2루타, 3: 3루타, 4: 홈런, 0: 아웃
			}
		}
		// 순서 고르기
		int[] order = new int[9];
		order[3] = 0;	// 1번 선수 고정
		selectPlayer(order, 0, 1);
		System.out.println(result);
	}

	private static void selectPlayer(int[] order, int cnt, int selected) {
		if(cnt == 9) {
			solve(order);
			return;
		}
		if(cnt == 3) {
			selectPlayer(order, cnt + 1, selected);
			return;
		}
		for (int i = 1; i < 9; i++) {
			if((selected & 1 << i) == 0) {
				order[cnt] = i;
				selectPlayer(order, cnt + 1, selected | 1 << i);
			}
		}
	}

	private static void solve(int[] order) {
		int win = 0;
		int taga = 0;					// 타자는 1번째 부터
		for (int i = 0; i < N; i++) {	// 이닝, 공격으로 시작하는 경우
			// 이닝이 시작되면 1루,2루,3루,아웃코스 초기화
			int[] ru = {-1,-1,-1};
			int out = 0;
			while(out < 3) {			// 아웃을 3번 당하는 순간 이닝이 끝난다.
				// 1: 안타, 2: 2루타, 3: 3루타, 4: 홈런, 아웃: 0
				switch (arr[i][order[taga]]) {
					case 0:
						out++;
						break;
					case 1:
						if(ru[2] != -1) {
							ru[2] = -1;
							win++;
						}
						for (int j = 1; j > -1; j--) {
							if(ru[j] != -1) {
								ru[j + 1] = ru[j];
								ru[j] = -1;
							}
						}
						ru[0] = taga;
						break;
	
					case 2:
						for (int j = 1; j < 3; j++) {
							if(ru[j] != -1) {
								ru[j] = -1;
								win++;
							}
						}
						if(ru[0] != -1) {
							ru[2] = ru[0];
							ru[0] = -1;
						}
						ru[1] = taga;
						break;
						
					case 3:
						for (int j = 0; j < 3; j++) {
							if(ru[j] != -1) {
								ru[j] = -1;
								win++;
							}
						}
						ru[2] = taga;
						break;
						
					case 4:
						for (int j = 0; j < 3; j++) {
							if(ru[j] != -1) {
								ru[j] = -1;
								win++;
							}
						}
						win++;
						break;
						
				}
				taga++;
				if(taga > 8) taga = 0;
			}
		}
		result = Math.max(result, win);
	}
}
