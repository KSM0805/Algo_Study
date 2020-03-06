package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_2156_포도주시식 {
	private static int result;
	private static int N;
	private static int[][] Wine;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim());
		if(N<=3) {
			Wine = new int[3][3];
		}
		else Wine = new int[N][3];
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(bf.readLine().trim());
			Wine[i][0] = num;
		}
		Wine[0][1] = Wine[0][0]; //연속된 경우
		Wine[1][1] = Wine[0][1] + Wine[1][0];
		Wine[1][2] = Wine[1][0]; //연속되지않은경우
		Wine[2][1] = Wine[1][2] + Wine[2][0];
		Wine[2][2] = Wine[0][0] + Wine[2][0];
		if(N<2) System.out.println(Wine[0][0]);
		else if (N<3) {
			System.out.println(Wine[1][1]);
		}else if (N<4) {
			result = Math.max(Wine[1][1], Wine[2][1]);
			result = Math.max(result, Wine[2][2]);
			System.out.println(result);
		}else {
			for (int i = 3; i < N; i++) {
				Wine[i][1] = Wine[i-1][2] + Wine[i][0];
				//중간에 2개를 선택하지 않는 경우도 고려!
				Wine[i][2] = Math.max(Wine[i-3][1], Math.max(Wine[i-2][1], Wine[i-2][2])) +  Wine[i][0];
				
			}
			result = Math.max(Wine[N-2][1], Wine[N-1][1]);
			result = Math.max(result, Wine[N-1][2]);
			System.out.println(result);
		}
	}
}
