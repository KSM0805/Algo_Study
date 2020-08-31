package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1520_내리막길 {

	private static int result;
	private static int M;
	private static int N;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static int[][] map;
	private static int[][][] dp;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		// DP 개념으로 접근할거야
		// 어떻게? 각 노드에서 갈 수 있는 경우의 수!
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		dp = new int[M][N][4];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = dfs(0,0,1) + dfs(0,0,3);
		System.out.println(result);
	}

	private static int dfs(int r, int c, int myD) {
		if(dp[r][c][myD] != 0) return dp[r][c][myD] == -1 ? 0 : dp[r][c][myD];
		// 0이면 탐색을 안 한 경우
		int no = myD % 2 == 0 ? myD + 1 : myD - 1;
		int nr = r + dir[myD][0];
		int nc = c + dir[myD][1];
		if(nr < 0 || nc < 0 || nr >= M || nc >= N || map[nr][nc] >= map[r][c]) return 0;
		if(nr == M - 1 && nc == N -1) dp[r][c][myD] = 1;
		else {
			for (int i = 0; i < 4; i++) {
				if(i != no) dp[r][c][myD] += dfs(nr, nc, i);
			}
		}
		dp[r][c][myD] = dp[r][c][myD] == 0 ? -1 : dp[r][c][myD];
		return dp[r][c][myD] == -1 ? 0 : dp[r][c][myD];
	}
}
