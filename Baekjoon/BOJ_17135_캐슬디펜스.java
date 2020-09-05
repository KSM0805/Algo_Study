package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17135_캐슬디펜스 {
	private static int result;
	private static int N;
	private static int M;
	private static int D;
	private static int[][] map;
	private static int enemies;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		enemies = 0;
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) enemies++;
			}
		}
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				for (int k = j + 1; k < M; k++) {
					defence(new int[] {i,j,k});
				}
			}
		}
		System.out.println(result);
	}

	private static void defence(int[] archer) {
		ArrayList<int[]> enemy = new ArrayList<>();
		int[][] tmpMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			tmpMap[i] = Arrays.copyOf(map[i], M);
		}
		int tmpEnemy = enemies;
		int kill = 0;
		while(tmpEnemy > 0) {
			for (int i = 0; i < 3; i++) {
				int[] cur = {100,-1,-1};
				//왼쪽부터 탐색
				for (int j = 0; j < M; j++) {
					for (int k = N - 1; k > -1; k--) {
						if(tmpMap[k][j] == 1) {
							int dis = Math.abs(k - N) + Math.abs(j - archer[i]);
							if(dis <= D && dis < cur[0]) {
								cur[0] = dis;
								cur[1] = j;
								cur[2] = k;
							}
							break;
						}
					}
				}
				if(cur[1] != -1) {
					enemy.add(new int[] {cur[1],cur[2]});
				}
			}
			for (int[] e : enemy) {
				if(tmpMap[e[1]][e[0]] == 1) {
					kill++;
					tmpEnemy--;
					tmpMap[e[1]][e[0]] = 0;
				}
			}
			enemy.clear();
			for (int i = N - 1; i > -1; i--) {
				for (int j = 0; j < M; j++) {
					if(tmpMap[i][j] == 1) {
						tmpMap[i][j] = 0;
						if(i == N - 1) tmpEnemy--;
						else tmpMap[i + 1][j] = 1;
					}
				}
			}
		}
		result = Math.max(result, kill);
	}
}
