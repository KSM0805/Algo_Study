package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class D3_4406 {
	static char[] ch = {'a','e','i','o','u'};
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringBuilder sb = new StringBuilder(bf.readLine().trim());
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < sb.length(); j++) {
					if(sb.charAt(j) == ch[i]) {
						sb.replace(j, j+1, "");
						j--;
					}
				}
			}
			System.out.println("#"+test_case+" "+sb.toString());
		}//for end
	}
}
