package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17143_낚시왕 {
	private static int result;

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[R][C];
		for (int[] is : map) {
			Arrays.fill(is, -1);
		}
		ArrayList<int[]> sharks = new ArrayList<>();
		// rcsdz
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			sharks.add(new int[] {r, c, Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			map[r][c] = i;
		}
		for (int i = 0; i < C; i++) {
			// 1. 열에서 가까운 상어를 잡는다.
			for (int j = 0; j < R; j++) {
				if(map[j][i] != -1) {
					result += sharks.get(map[j][i])[4];
					sharks.set(map[j][i], null);
					map[j][i] = -1;
					break;
				}
			}
			// 2. 상어 이동
			for(int s = 0; s < M; s++) {
				int[] shark = sharks.get(s);
				if(shark != null) {
					if(map[shark[0]][shark[1]] == s) map[shark[0]][shark[1]] = -1;
					int move = shark[2];
					while(move > 0) {
						switch (shark[3]) {
							case 1:
								if(shark[0] - move < 0) {
									shark[3] = 2;
									move -= shark[0];
									shark[0] = 0;
								} else {
									shark[0] -= move;
									move = 0;
								}
								break;
							case 2:
								if(shark[0] + move >= R) {
									shark[3] = 1;
									move -= R - 1 - shark[0];
									shark[0] = R - 1;
								} else {
									shark[0] += move;
									move = 0;
								}
								break;
							case 3:
								if(shark[1] + move >= C) {
									shark[3] = 4;
									move -= C - 1 - shark[1];
									shark[1] = C - 1;
								} else {
									shark[1] += move;
									move = 0;
								}
								break;
							case 4:
								if(shark[1] - move < 0) {
									shark[3] = 3;
									move -= shark[1];
									shark[1] = 0;
								} else {
									shark[1] -= move;
									move = 0;
								}
								break;
						}
					}
					sharks.set(s, shark);
					if(map[shark[0]][shark[1]] != -1 && map[shark[0]][shark[1]] < s) {
						if(shark[4] > sharks.get(map[shark[0]][shark[1]])[4]) {
							sharks.set(map[shark[0]][shark[1]], null);
							map[shark[0]][shark[1]] = s;
						} else {
							sharks.set(s, null);
						}
					} else {
						map[shark[0]][shark[1]] = s;
					}
				}
			}
		}
		System.out.println(result);
	}
}
