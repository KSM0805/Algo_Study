package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class D3_8931 {
	private static long result;
	private static int K;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			K = Integer.parseInt(bf.readLine().trim());
			Stack<Integer> st = new Stack<>();
			result = 0;
			for (int i = 0; i < K; i++) {
				int a = Integer.parseInt(bf.readLine().trim());
				if(a==0) {
					st.pop();
				}else {
					st.push(a);
				}
			}
				
			while(!st.isEmpty()) {
				result += st.pop();
			}
			System.out.println("#"+test_case+" "+result);
		}//for end
	}
}
