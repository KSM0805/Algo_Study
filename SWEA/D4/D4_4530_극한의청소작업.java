package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_4530_극한의청소작업 {
	private static long result;
	private static String A;
	private static String B;
	private static long[] fourNum;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		// 10^12-1 -> 12자리
		// 1자리 : 1 ex)4
		// 2자리 : (1자리 * 9) + 10 => 십의 자리 숫자가 4를 제외한거 + 십의 자리 숫자가 4일때 모두 제외
		// 3자리 : (2자리 * 9) + 100
		fourNum = new long[13];
		fourNum[0] = 0;
		fourNum[1] = 1;
		for (int i = 2; i < 13; i++) {
			fourNum[i] = fourNum[i-1] * 9 + (long) Math.pow(10, i-1);
		}
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			A = st.nextToken();
			B = st.nextToken(); // -10^12+1 ~ 10^12-1 
			
			result = 0;
			if(A.charAt(0) == '-') {
				if(B.charAt(0) == '-') {
					result = solve(A.substring(1)) - solve(B.substring(1));
				}else {
					result = solve(A.substring(1)) + solve(B) - 1;
				}
			}else {
				result = solve(B) - solve(A);
			}
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end

	private static long solve(String floor) {
		long sum = Long.parseLong(floor);
		int len = floor.length();
		int curNum = -1;
		// 가장 큰 자릿수부터 4가 있는 층수 제외
		for (int i = 0; i < len; i++) {
			curNum = floor.charAt(i)-'0';
			if(i == len-1) {
				sum = curNum > 4 ? sum - 1 : sum;
				break;
			}
			if(curNum < 4) {
				sum -= (curNum) * fourNum[len - 1 - i];
			}else {
				sum -= (curNum-1) * fourNum[len - 1 - i] + Math.pow(10, len - 1 - i);
			}
		}
		return sum;
	}
}
