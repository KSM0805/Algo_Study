package d5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class D5_9843_촛불이벤트 {
	private static long N;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Long.parseLong(bf.readLine().trim());
			long root = (long) Math.floor(Math.sqrt(2*N));
			if( 2*N == (root * (root + 1)))
				System.out.println("#"+test_case+" "+(long)root);
			else
				System.out.println("#"+test_case+" "+"-1");
		}//testcase end
	}//main end
}
