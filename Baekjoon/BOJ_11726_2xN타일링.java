package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_11726 {
	private static int N;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim()); // 1~1000
		// d[n] = d[n-1] + d[n-2];
		if(N <= 2) {
			System.out.println(N);
		}else {
			int[] d = new int[N+1];
			d[0] = 0;
			d[1] = 1;
			d[2] = 2;
			for (int i = 3; i <= N; i++) {
				d[i] = (d[i-1] + d[i-2]) % 10007;
			}
			System.out.println(d[N]);
		}
	}
}
