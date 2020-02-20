package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_5215 {
	private static int N;
	private static int L;
	private static int[] score;
	private static int[] cal;
	private static boolean[] visit;
	private static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			score = new int[N];
			cal = new int[N];
			max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				score[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}
			dfs(-1,0,0);
			System.out.print("#"+test_case+" ");
			System.out.println(max);
		}//for end
	}

	private static void dfs(int num, int totalCal, int totalScore) {
		if(L < totalCal ) {
			return;
		}
		else {
			max = Math.max(max, totalScore);
		}
		if(num==N-1) {
			return;
		}
		for (int i = 0; i < 2; i++) {
			dfs(num+1, totalCal + i*cal[num+1], totalScore + i*score[num+1]);
		}
	}

	
}
