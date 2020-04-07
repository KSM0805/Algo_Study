package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class D3_5607_조합 {
	private static BigInteger result;
	private static int N;
	private static int R;
	private static final long mod = 1234567891;
	private static BigInteger m = new BigInteger("" + mod);

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		long[] fact = new long[1000001];
		fact[0] = 1;
		for (int i = 1; i <= 1000000; i++) { //100만
			fact[i] = fact[i-1] * i % mod;
		}
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken()); //100만
			R = Integer.parseInt(st.nextToken());
			
			result = BigInteger.valueOf(fact[N]);
			result = result.multiply(BigInteger.valueOf(fact[N-R]).modPow(BigInteger.valueOf(1234567889), m)).mod(m);
			result = result.multiply(BigInteger.valueOf(fact[R]).modPow(BigInteger.valueOf(1234567889), m)).mod(m);
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end
}
