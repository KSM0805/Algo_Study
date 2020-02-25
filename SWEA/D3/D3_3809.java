package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_3809 {
	private static int result;
	private static int N;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = 0;
			N = Integer.parseInt(bf.readLine());
			StringTokenizer st;
			StringBuilder str = new StringBuilder();
			while(result != N) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				while(st.hasMoreTokens()) {
					str.append(st.nextToken());
					result++;
				}
			}
			int num = 0;
			while(true) {
				int a = str.indexOf(Integer.toString(num));
				if(a==-1) break;
				else num++;
			}
			System.out.println("#"+test_case+" "+num);
		}//for end
	}
}
