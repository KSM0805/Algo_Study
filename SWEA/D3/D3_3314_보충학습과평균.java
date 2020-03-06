package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_3314_보충학습과평균 {
	private static int result;
	

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			result = 0;
			for (int i = 0; i < 5; i++) {
				int num = Integer.parseInt(st.nextToken());
				result += num<40 ? 40 : num;
			}
			System.out.println("#"+test_case+" "+(result/5));
		}//testcase end
	}//main end
}
