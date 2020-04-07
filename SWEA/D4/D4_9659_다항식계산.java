package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_9659_다항식계산 {
	private static int N;
	private static int[][] tab;
	private static int M;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim()); // 2~50
			tab = new int[N+1][3]; //[0]: ti, [1]: ai, [2]: bi
			StringTokenizer st;
			for (int i = 2; i <= N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				tab[i][0] = Integer.parseInt(st.nextToken());
				tab[i][1] = Integer.parseInt(st.nextToken());
				tab[i][2] = Integer.parseInt(st.nextToken());
			}
			M = Integer.parseInt(bf.readLine().trim());
			System.out.print("#"+test_case+" ");
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int i = 0; i < M; i++) {
				int x = Integer.parseInt(st.nextToken());
				System.out.print(solve(x) + " ");
			}
			System.out.println();
		}//testcase end
	}//main end

	private static long solve(int x) {
		long[] num = new long[N+1];
		num[0] = 1;
		num[1] = x;
		for (int i = 2; i <= N; i++) {
			switch (tab[i][0]) {
				case 1:
					num[i] = (num[tab[i][1]] + num[tab[i][2]]) % 998244353;
					break;
				case 2:
					num[i] = (tab[i][1] * num[tab[i][2]]) % 998244353;
					break;
				case 3:
					num[i] = (num[tab[i][1]] * num[tab[i][2]]) % 998244353;
					break;
			}
		}
		return num[N];
	}
}
