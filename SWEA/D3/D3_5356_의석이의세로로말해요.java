package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class D3_5356 {
	private static String[] str;
	private static int[] len;
	private static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			str = new String[5];
			len = new int[5];
			max = 0;
			for (int i = 0; i < 5; i++) {
				str[i] = bf.readLine().trim();
				len[i] = str[i].length();
				if(max<len[i]) max = len[i];
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < max; i++) {
				for (int j = 0; j < 5; j++) {
					if(i<len[j]) {
						sb.append(str[j].charAt(i));
					}
				}
			}
			System.out.println("#"+test_case+" "+sb);
		}//for end
	}
}
