package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class D3_7728 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			String str = bf.readLine().trim();
			LinkedList<Integer> a = new LinkedList<>();
			for (int i = 0; i < str.length(); i++) {
				if(!a.contains(str.charAt(i)-'0')) {
					a.add(str.charAt(i)-'0');
				}
			}
			System.out.println("#"+test_case+" "+a.size());
		}//for end
	}
}
