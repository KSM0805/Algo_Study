package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_8104 {
	private static int N;
	private static int K;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			// 	   1  2     3	  4		5
			//1조 : 1+ 2k + 2k+1 + 3k + 3k+1 +...+(홀수일때)n-1*k + 1
//												 (짝수일때)N*k
			//2조 : 2+ 2k-1 + 2k+2 + .... +홀수일때 (N-1)*K+2
			
			//K조 : k + k+1 + .... + 홀수(N*K)
//									짝수()
			System.out.print("#"+test_case+" ");
			if(N%2 == 0) {
				for (int i = 0; i < K; i++) {
					System.out.print((N*K + 1)*N/2+" ");
				}
				//(n*k + 1)*n/2
			}else {
				for (int i = 1; i <= K; i++) {
					System.out.print(((N+1)*K + 1)*(N-1)/2 + i +" ");
				}
				//n+1*k + 1 * n-1/2 +1
			}
			System.out.println();
		}//for end
	}
}
