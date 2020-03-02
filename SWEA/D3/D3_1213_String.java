package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class D3_1213 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			int test = Integer.parseInt(bf.readLine().trim());
			String target = bf.readLine().trim();
			String str = bf.readLine().trim();

			int cnt = 0;
			int start = 0;
			start = str.indexOf(target,start);
			while(start != -1) {
				cnt++;
				start = str.indexOf(target,(start+1));
			}
			System.out.println("#"+test+" "+cnt);
		}//for end
	}

}
