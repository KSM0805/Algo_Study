package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_6730 {
	private static int downMax;
	private static int upMax;
	private static int N;
	private static int[] block;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			upMax = 0;
			downMax = 0;
			N = Integer.parseInt(bf.readLine().trim());
			
			block = new int[N];
			StringTokenizer st = new StringTokenizer(bf.readLine().trim(), " ");
			for (int i = 0; i < N; i++) {
				block[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < N-1; i++) {
				if(block[i]<block[i+1]) {
					upMax = Math.max(upMax, block[i+1]-block[i]);
				}else {
					downMax = Math.max(downMax, block[i]-block[i+1]);
				}
			}
			System.out.println("#"+test_case+" "+upMax+" "+downMax);
		}//for end
	}
}
