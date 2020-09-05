package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11047_동전0 {
	private static int result;
	private static int N;
	private static int K;
	private static int[] coin;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coin = new int[N];
		for (int i = N - 1; i >= 0; i--) {
			coin[i] = Integer.parseInt(bf.readLine().trim());
		}
		for (int i = 0; i < N; i++) {
			if(K >= coin[i]) {
				result += K / coin[i];
				K = K % coin[i];
			}
		}
		System.out.println(result);
	}
}
