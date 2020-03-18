package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class D4_5604_구간합 {
	private static BigInteger result;
	private static String A;
	private static String B;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			A = st.nextToken();
			B = st.nextToken();
			result = BigInteger.ZERO;
			// B까지의 모든 숫자의 자릿수 합 - A까지의 모든 숫자의 자릿수합
			result = result.add(solve(B)).add(solve(A).negate());
			// 숫자 B가 포함되지 않았으므로
			int sum = 0;
			for (int i = 0; i < B.length(); i++) {
				sum += B.charAt(i)-'0';
			}
			result = result.add(BigInteger.valueOf(sum));
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end
	private static BigInteger solve(String a) {
		BigInteger num = new BigInteger("0");
		for (int i = 0; i < a.length(); i++) {
			int x = a.charAt(i)-'0';
			//자신은 아랫숫자만큼 등장
			if(i<a.length()-1) num = num.add(BigInteger.valueOf(x).multiply(new BigInteger(a.substring(i+1))));
			//자기보다 작은 숫자 10^n만큼 등장
			for (int j = x-1; j > 0; j--) {
				num = num.add(BigInteger.valueOf(j).multiply(BigInteger.valueOf(10).pow(a.length()-1-i)));
			}
			//앞의 숫자만큼 0~9등장
			if(i>0) num = num.add(new BigInteger(a.substring(0, i)).multiply(BigInteger.valueOf(10).pow(a.length()-1-i)).multiply(BigInteger.valueOf(45)));
		}
		return num;
	}
}
