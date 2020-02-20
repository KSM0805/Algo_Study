package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_8338 {
	private static int N;
	private static int[] num;
	private static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim());
			num = new int[N];
			result = 0;
			StringTokenizer st = new StringTokenizer(bf.readLine().trim(), " ");
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
				if( (result+num[i]) >= (result*num[i]) ) result += num[i];
				else result *= num[i];
			}
			System.out.println("#"+test_case+" "+result);
		}//for end
	}
}
