package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_2193_이친수 {
	private static int N;
	//출력값의 범위를 생각해보기 - 지금의 경우 N=90일때 int형의 범위를 벗어남
	private static long[][] num;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim()); // 1~90
		// 1. 0으로 시작하지 않는다. -> 0번째 자리수는 1
		// 2. 1이 두 번 연속으로 나타나지 않는다.
		num = new long[N][2]; //N자릿수 까지 0: 0으로끝나는 갯수, 1: 1로끝나는갯수
		if(N<3) {
			System.out.println("1");
			return;
		}
		num[0][0] = 0;
		num[0][1] = 1; // 첫번째 자릿수는 무조건 0으로 시작
		num[1][0] = 1;
		num[1][1] = 0;
		num[2][0] = 1;
		num[2][1] = 1;
		for (int i = 2; i < N; i++) {
			num[i][0] = num[i-1][1] + num[i-1][0];
			num[i][1] = num[i-1][0];
		}
		System.out.println(num[N-1][0]+num[N-1][1]);
	}
}
