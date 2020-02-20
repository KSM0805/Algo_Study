package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_1860 {
	private static int N;
	private static int M;
	private static int K;
	private static int[] peo;
	private static int pos;
	private static boolean ok;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(bf.readLine().trim()," ");
			peo = new int[N];
			for (int i = 0; i < N; i++) {
				peo[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(peo);
			ok = false;
			pos = 0; //0번째 사람
			for (int i = 0; i < N; i++) {
				if(peo[pos] / M == 0) {
					ok = true;
					break;
				}
				if((peo[pos] / M)*K < (pos+1)) {
					ok = true;
					break;
				}
				pos++;
			}
			System.out.print("#"+test_case+" ");
			if(ok) System.out.println("Impossible");
			else System.out.println("Possible");
			
		}//for end
	}
}
