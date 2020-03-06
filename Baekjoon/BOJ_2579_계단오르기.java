package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_2579_계단오르기 {
	private static int N;
	private static int[] stairs;
	private static int[][] score;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim());
		stairs = new int[N];
//		d[n] = (d[n-1] || d[n-2])+d[n]
		for (int i = 0; i < N; i++) {
			stairs[i] = Integer.parseInt(bf.readLine().trim());
		}
		score = new int[N][2]; //각 계단 마다 칸의 점수, 0: 칸의 최대 점수 1: 연속해서 한 칸 오른 수
		if(N==1) System.out.println(stairs[0]);
		else {
			score[0][0] = stairs[0]; //한 칸 올랐을때
			score[0][1] = 0; // 두 칸 올랐을때
			score[1][0] = score[0][0] + stairs[1];
			score[1][1] = stairs[1]; 
			for (int i = 2; i < N; i++) {
				score[i][0] = score[i-1][1] + stairs[i];
				score[i][1] = Math.max(score[i-2][0], score[i-2][1]) + stairs[i];
				
			}
			System.out.println(Math.max(score[N-1][0], score[N-1][1]));
		}
	}
}
