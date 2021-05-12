package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13398_연속합2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		 BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		 
		 int n = Integer.parseInt(bf.readLine().trim());
		 
		 int[][] dp = new int[n][2];
		 
		 int num;
		 int max = Integer.MIN_VALUE;
		 
		 StringTokenizer st = new StringTokenizer(bf.readLine().trim(), " ");
		for (int i = 0; i < n; i++) {
			num = Integer.parseInt(st.nextToken());
			if (i == 0) {
				dp[i][0] = num;
				dp[i][1] = num;
				max = num;

			} else {
				dp[i][0] = Math.max(dp[i - 1][0] + num, num);
				dp[i][1] = Math.max(dp[i-1][0], Math.max(dp[i-1][1] + num, num));
			}
			max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
			
		}
		System.out.println(max);
		 
	}

}
