package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2293_동전1 {

	private static int[] dp;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());	// 최대 10000개
		int[] coin = new int[n];
		dp = new int[k+1];
		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(bf.readLine().trim());
		}
		for (int i = 0; i < n; i++) {
			if(coin[i] <= k ) dp[coin[i]]++;
			for (int j = 1; j <= k; j++) {
				int num = j + coin[i];
				if(dp[j] != 0 && num <= k) {
					dp[num] += dp[j];
				}
			}
		}
		System.out.println(dp[k]);
	}
}
