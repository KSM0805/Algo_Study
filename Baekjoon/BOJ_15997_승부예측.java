package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15997_승부예측 {
	private static double[] result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		int N = st.countTokens();
		String[] team = new String[N];
		for (int i = 0; i < N; i++) {
			team[i] = st.nextToken();
		}
		result = new double[N];
		int round = ((N + 1) * N) / 2;
		// 승점은 3점, 비기는 경우 1점
		// A B W D L : A가 B에게 이기는 확률 W, 비길 확률 D, 지는 확률 L
		System.out.println(result);
	}
}
