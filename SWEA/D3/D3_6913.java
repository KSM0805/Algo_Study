package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_6913 {
	private static int M;
	private static int N;
	private static int max;
	private static int people;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			StringBuilder sb;
			max = 0;
			people = 0;
			for (int i = 0; i < N; i++) {
				sb = new StringBuilder(bf.readLine().trim());
				int pos = sb.indexOf("1");
				int cnt = 0;
				while( pos != -1 ) {
					cnt++;
					sb.replace(0, pos+1, "");
					pos = sb.indexOf("1");
				}
				if(max<cnt) {
					max = cnt;
					people = 1;
				}else if(max == cnt) {
					people++;
				}
			}
			System.out.println("#"+test_case+" "+people+" "+max);
		}//for end
	}
}
