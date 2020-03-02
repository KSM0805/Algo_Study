package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_7102 {
	private static int N;
	private static int M;
	private static int[] cnt;
	private static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			cnt = new int[N+M+1];
			max = -1;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					cnt[i+j]++;
					if(max<cnt[i+j]) max = cnt[i+j];
				}
			}
			System.out.print("#"+test_case+" ");
			for (int i = 1; i < N+M+1; i++) {
				if(max == cnt[i]) {
					System.out.print(i+" ");
				}
			}
			System.out.println();
		}//for end
	}
}
