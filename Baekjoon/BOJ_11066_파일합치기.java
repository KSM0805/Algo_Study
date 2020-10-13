package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11066_파일합치기 {

	private static int K;
	private static int[] size;
	private static int[][] dp;
	private static int[] sum;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T =Integer.parseInt(bf.readLine().trim());
		for (int i = 0; i < T; i++) {
			K = Integer.parseInt(bf.readLine().trim());
			size = new int[K + 1];
			dp = new int[K + 1][K + 1];
			sum = new int[K + 1];
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 1; j <= K; j++) {
				size[j] = Integer.parseInt(st.nextToken());
				sum[j] = sum[j - 1] + size[j];
			}
			// 파일을 하나씩 더해갈것이다
			System.out.println(dfs(1,K));
		}
	}

	private static int dfs(int start, int end) {
		if(dp[start][end] > 0) return dp[start][end];
		if(start == end) return 0;
		dp[start][end] = Integer.MAX_VALUE;
		for (int i = start; i < end; i++) {
			dp[start][end] = Math.min(dp[start][end], dfs(start,i) + dfs(i+1, end) + sum[end] - sum[start - 1]);
		}
		return dp[start][end];
	}
}
