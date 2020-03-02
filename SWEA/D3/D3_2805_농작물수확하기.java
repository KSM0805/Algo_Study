package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class D3_2805 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			int size = Integer.parseInt(bf.readLine().trim());
			int middle = size/2;
			int start = middle;
			int end = middle;
			String str;
			int sum = 0;
			for (int i = 0; i < size; i++) {
				str = bf.readLine().trim();
				for (int j = start; j <=end; j++) {
					sum += str.charAt(j)-'0';
				}
				if(i<=middle-1) {
					start--;
					end++;
				}
				else {
					start++;
					end--;
				}
			}
			System.out.println(sum);
		}//for end
	}

}
