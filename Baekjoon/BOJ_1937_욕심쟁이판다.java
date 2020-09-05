package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1937_욕심쟁이판다 {

	private static int result;
	private static int N;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static int[][] map;
	private static int[][] dp;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		N = Integer.parseInt(bf.readLine().trim());	// ~500 : 맵크기
		map = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result = Math.max(result, dfs(i,j));
			}
		}
		System.out.println(result);
	}

	private static int dfs(int r, int c) {
		if(dp[r][c] != 0) return dp[r][c];
		for (int i = 0; i < 4; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			if(nr < 0 || nc < 0 || nr >= N || nc >= N || map[r][c] >= map[nr][nc]) continue;
			dp[r][c] = Math.max(dp[r][c],dfs(nr,nc) + 1);
		}
		if(dp[r][c] == 0) dp[r][c] = 1;
		return dp[r][c];
	}

	// 백트래킹과 DP의 차이를 정확히 해두자
//	private static void dfs(int r, int c, int cnt) {
//		if(result < cnt) result = cnt;
//		for (int i = 0; i < 4; i++) {
//			int nr = r + dir[i][0];
//			int nc = c + dir[i][1];
//			if(nr < 0 || nc < 0 || nr >= N || nc >= N || map[r][c] >= map[nr][nc] || dp[nr][nc] >= cnt + 1) continue;
//			// 대나무가 많고 더 오래 살아있는 경우
//			dp[nr][nc] = cnt + 1;
//			dfs(nr, nc, cnt + 1);
//		}
//	}
}
