package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_7964 {
	private static int D;
	private static int N;
	private static int cnt;
	private static boolean[] map;

	//비트 마스크해서 검색할 것  
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
			
		for (int test_case = 1; test_case <= T; test_case++) {
			// D = 2 인경우 0101010101
			// D = 3 인 경우 1001001001001 이 최적임
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			map = new boolean[N];
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int i = 0; i < N; i++) {
				map[i] = (st.nextToken().equals("1")? true : false );
			}
			cnt = 0;
			int idx = 0;
			while(idx<N) {
//				System.out.println(idx);
				for (int i = idx; i < idx+D; i++) {
					if(map[i]) {
						idx = i+1;
						break;
					}
					if(i==idx+D-1) {
						cnt++;
						idx = i+1;
						break;
					}
				}
			}
			System.out.println("#"+test_case+" "+cnt);
		}//for end
	}

}

