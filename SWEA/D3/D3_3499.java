package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_3499 {
	private static int N;
	private static String[] str;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim());
			str = new String[N];
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			//N = 5일때  0 2 4 1 3 순서
			for (int i = 0, idx = 0; i < N; i++,idx+=2) {
				if(idx>=N) idx = 1;
				str[idx] = st.nextToken();
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N-1; i++) {
				sb.append(str[i]+" ");
			}
			sb.append(str[N-1]);
			System.out.println("#"+test_case+" "+sb.toString());
		}//for end
	}
}
