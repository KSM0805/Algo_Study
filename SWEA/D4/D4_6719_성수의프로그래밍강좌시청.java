package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D4_6719_성수의프로그래밍강좌시청 {
	private static double result;
	private static int N;
	private static int K;
	private static int[] nums;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			nums = new int[N];
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(nums);
			result = 0;
			for (int i = nums.length-K; i < nums.length; i++){
				result  = (result+nums[i]) / 2;
			}
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end
}
