package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1932_정수삼각형 {
	private static int result;
	private static int[][] score;
	private static int N;
	private static ArrayList<Integer>[] list;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		N = Integer.parseInt(bf.readLine().trim());
		list = new ArrayList[N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j <= i; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		score = new int[N][N];
		result = solve(0,0);
		System.out.println(result);
	}

	private static int solve(int r, int c) {
		if(score[r][c] != 0) return score[r][c];
		else if(r == N - 1) {
			score[r][c] = list[r].get(c);
		} else {
			
			score[r][c] = Math.max(solve(r+1,c), solve(r+1,c+1)) + list[r].get(c);
		}
		return score[r][c];
	}
}
