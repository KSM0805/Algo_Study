package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_2817 {
	private static int N;
	private static int K;
	private static int cnt;
	private static int[] num;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			cnt = 0;
			num = new int[N];
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			//입력 끝
			sum(-1,0);
			System.out.println("#" + test_case+" " + cnt);
		}//for end
	}
	private static void sum(int n, int sum) {
		if(sum == K) {
			cnt++;
			return;
		}else if(K<sum) return;
		else if(n<N-1) {
			sum(n + 1,sum + num[n+1]);
			sum(n + 1,sum);
		}
		
	}

}
