package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_4466 {
	private static int N;
	private static int[] score;
	private static int K;
	private static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			score = new int[N];
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int i = 0; i < N; i++) {
				score[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(score);
			result = 0;
			for (int i = 0,idx = N-1; i < K; i++,idx--) {
				result += score[idx];
			}
			System.out.println("#"+test_case+" "+result);
		}//for end
	}
}
