package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D4_4050_재관이의대량할인 {
	private static long result;
	private static int N;
	private static int[] clothes;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim()); //1~100000 옷의 벌 수 
			clothes = new int[N]; // 1~100000
			result = 0; //-> long
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			for (int i = 0; i < N; i++) {
				clothes[i] = Integer.parseInt(st.nextToken());
				result += clothes[i];
			}
			Arrays.sort(clothes);
			for (int i = N-3; i > -1; i-=3) {
				result -= clothes[i];
			}
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end
}
