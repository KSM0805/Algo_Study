package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_5431 {
	private static int K;
	private static int N;
	private static int[] con;
	private static int[] num;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			num = new int[N];
			con = new int[N-K];
			st = new StringTokenizer(bf.readLine().trim(), " ");
			for (int i = 0; i < K; i++) {
				num[Integer.parseInt(st.nextToken())-1] = 1;
			}
			int pos = 0;
			for (int i = 0; i < N; i++) {
				if(num[i] == 0) {
					con[pos] = i+1;
					pos++;
				}
			}			
			Arrays.sort(con);
			System.out.print("#"+test_case+" ");
			for (int i = 0; i < N-K; i++) {
				System.out.print(con[i]+" ");
			}
			System.out.println();
		}//for end
	}
}
