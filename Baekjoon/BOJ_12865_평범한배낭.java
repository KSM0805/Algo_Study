package BOJ;

import java.util.Scanner;

public class BOJ_12865_평범한배낭 {

	private static int N;
	private static int K;
	private static int[] productsW;
	private static int[] productsV;
	private static int[] dp;
//	private static int[][] dp;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	// 물품 수 : 1 - 100
		K = sc.nextInt();	// 최대 무게 : 1 - 100,000
		productsW = new int[N + 1];	// 물건 무게
		productsV = new int[N + 1];	// 물건 가치
		for (int i = 1; i <= N; i++) {
			productsW[i] = sc.nextInt();
			productsV[i] = sc.nextInt();
		}
		// knapsack 알고리즘
		// 두가지 케이스가 있다. 
		// 1. 현재꺼를 챙기는것
		// 현재꺼를 챙길때 현재 무게에서 초과가 되지 않아야한다.
		// 2. 현재꺼를 안챙기는것
		// 무게에서 최댓값 그대로
//		dp = new int[N + 1][K + 1];
		dp = new int[K + 1];
		knapsack();
//		System.out.println(dp[N][K]);
		System.out.println(dp[K]);
	}

	private static void knapsack() {
//		for (int i = 1; i <= N; i++) {	//물건을 하나씩
//			// 물건당 무게 dp 구하기
//			for (int j = 1; j <= K; j++) {
//				// 현재꺼를 챙길 수 있는 경우
//				if(productsW[i] <= j) {
//					dp[i][j] = Math.max(dp[i-1][j], dp[i - 1][j - productsW[i]] + productsV[i]);
//				} else dp[i][j] = dp[i - 1][j];
//			}
//		}
		for(int i = 1; i <= N; i++){
	        for(int j = K; j >= 1; j--){
	            if(productsW[i] <= j){
	                dp[j] = Math.max(dp[j], dp[j-productsW[i]] + productsV[i]);
	            }
	        }
	    }
	}
}
