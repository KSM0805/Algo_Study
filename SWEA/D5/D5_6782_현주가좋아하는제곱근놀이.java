package d5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class D5_6782_현주가좋아하는제곱근놀이 {
	private static int result;
	private static long N;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
//		long[] mem = new long[1000001];
//		for (long i = 0; i < 1000001; i++) {
//			mem[(int) i] = i*i;
//		}
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Long.parseLong(bf.readLine());
			result = 0;
			long pos, pow;
			while(N != 2) {
				pos = (long) Math.sqrt(N);
				pow = pos*pos;
				if(pow == N) {
					N = pos;
					result++;
				}else if(pow > N){
					result += pow - N;
					N = pow;
				}else {
					result += (pos+1) * (pos+1) - N;
					N = (pos+1) * (pos+1);
				}
			}
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end
}
