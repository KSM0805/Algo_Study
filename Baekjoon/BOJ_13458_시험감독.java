package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13458_시험감독 {
	private static long result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;

		int N = Integer.parseInt(bf.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(bf.readLine().trim()," ");
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			if(A[i] <= B) {
				result += 1;
			}else if((A[i] - B) % C == 0) {
				result += (A[i] - B) / C + 1;
			} else {
				result += ((A[i] - B) / C) + 2;
			}
		}
		System.out.println(result);
	}
}

