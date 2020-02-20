package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_1234 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			int N = Integer.parseInt(st.nextToken());
//			String str = st.nextToken();
			StringBuilder str = new StringBuilder(st.nextToken());
			boolean ok = true;
			while(ok) {
				ok = false;
				for (int i = 0; i < str.length()-1; i++) {
					if(str.charAt(i)==str.charAt(i+1)) {
						str = str.delete(i, i+2);
						ok = true;
					}
				}
			}
			System.out.println("#"+test_case+" "+str);
		}//for end
	}

}
