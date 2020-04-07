package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 문제가 이상함
 *  -> 정확하게 풀면 1 9999999999 2 2 인 경우 stackoverflow
 */
public class D4_7194_화섭이의미생물배양 {
	private static long s;
	private static long t;
	private static long a;
	private static long b;
	private static long result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			s = Long.parseLong(st.nextToken());
			t = Long.parseLong(st.nextToken());
			a = Long.parseLong(st.nextToken());
			b = Long.parseLong(st.nextToken()); // 1~10^9
			System.out.print("#"+test_case+" ");
			if(b==1) {
				if((t - s) % a == 0) System.out.println((t-s)/a);
				else System.out.println("-1");
				continue;
			}
			result = Integer.MAX_VALUE;
			solve(t, 0);
			System.out.println((result == Integer.MAX_VALUE ? "-1":result));
		}//testcase end
	}//main end

	private static void solve(long num, long cnt) {
		if(result<cnt) return;
		if(num == s) {
			result = Math.min(result, cnt);
			return;
		}
		if(num < b) {
			if((num - s) % a == 0) result = Math.min(result, (num-s)/a);
			return;
		}
		if(num % b == 0 && num / b >= s) {
			long div = num/b;
			solve(div, cnt + 1);
		}
		if(num - a > s) {
			solve(num - a, cnt + 1);
		}else if(num - a == s) {
			result = Math.min(result, cnt + 1);
		}
	}
}
