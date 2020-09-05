package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17406_배열돌리기4 {
	private static int result;
	private static int N;
	private static int M;
	private static int K;
	private static int[][] arr;
	private static int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	private static int[][] curl;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		curl = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			curl[i][0] = Integer.parseInt(st.nextToken()) - 1;
			curl[i][1] = Integer.parseInt(st.nextToken()) - 1;
			curl[i][2] = Integer.parseInt(st.nextToken());
		}
		int[] or = new int[K];
		order(0,0, or);
		System.out.println(result);
	}

	private static void order(int cnt, int selected, int[] ordered) {
		if(cnt == K) {
			int[][] copyMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				copyMap[i] = Arrays.copyOf(arr[i], M);
			}
			for (int i = 0; i < K; i++) {
				rotate(curl[ordered[i]], copyMap);
			}
			for (int i = 0; i < N; i++) {
				int total = 0;
				for (int j = 0; j < M; j++) {
					total += copyMap[i][j];
				}
				result = Math.min(result, total);
			}
			return;
		}
		for (int i = 0; i < K; i++) {
			if((selected & 1 << i) == 0) {
				ordered[cnt] = i;
				order(cnt + 1, selected | 1 << i, ordered);
			}
		}
	}

	private static void rotate(int[] cur, int[][] map) {
		if(cur[2] == 0) return;
		int r = cur[0] - cur[2];
		int c = cur[1] - cur[2];
		int tmp = map[r][c];
		int tmp2;
		for (int i = 0; i < 4; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			while(nr >= cur[0] - cur[2] && nr <= cur[0] + cur[2] && nc >= cur[1] - cur[2] && nc <= cur[1] + cur[2]) {
				tmp2 = map[nr][nc];	// 다음위치의 원본 저장
				map[nr][nc] = tmp;	// 다음위치에 덮어쓰기
				tmp = tmp2;
				r = nr;
				c = nc;
				nr = r + dir[i][0];
				nc = c + dir[i][1];
			}
		}
		rotate(new int[] {cur[0], cur[1], cur[2] - 1}, map);
	}
}
