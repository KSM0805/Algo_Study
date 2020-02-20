package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D4_7965 {
	private static int N;
	private static long result;
	//static을 선언하면 접근하는데 오래걸리고 final static인 경우 상수풀에 들어가서 접근 시간이 줄어듬
	private static int dv = 1000000007;
	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			
			N = Integer.parseInt(bf.readLine().trim());
			result = 0;
			for (int i = 1; i <= N; i++) {
				result += power(i,i);
			}
			result = result % dv;
			System.out.print("#"+test_case+" ");
			System.out.println(result);
		}//for end
		
	}

	private static long power(int n, int cnt) {
		if(cnt==1) return n;
		else if(cnt == 0) return 1;
		
		long result = power(n, cnt>>1);
		result = (result * result) % dv;
		if(cnt%2 == 1) {
			result = (result * n) % dv;
		}
		return result;
	}
}
