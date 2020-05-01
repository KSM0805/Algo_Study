package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test_1949_등산로조성 {
	private static int result;
	private static int K;
	private static int N;
	private static int[][] map;
	private static int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};
	private static boolean[][] visit;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken()); // 3~8 : 맵크기
			K = Integer.parseInt(st.nextToken()); // 1~5 : 공사 가능 깊이
			map = new int[N][N];
			
			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(map[i][j], max);
				}
			}
			result = 0;
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == max) {
						dfs(i, j, false, 1);
					}
				}
			}
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end

	private static void dfs(int r, int c, boolean constr, int cnt) {
		result = Math.max(cnt, result);
		visit[r][c] = true;
		int nr, nc;
		for (int i = 0; i < 4; i++) {
			nr = r + dir[i][0];
			nc = c + dir[i][1];
			if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
			if(!visit[nr][nc] && map[nr][nc] < map[r][c] ) {
				dfs(nr, nc, constr, cnt + 1);
			} else if(!visit[nr][nc] && !constr && map[nr][nc] - K < map[r][c] ) {
				int tmp = map[nr][nc];
				map[nr][nc] = map[r][c] - 1;
				dfs(nr, nc, true, cnt + 1);
				map[nr][nc] = tmp;
			}
		}
		visit[r][c] = false;
	}
}
