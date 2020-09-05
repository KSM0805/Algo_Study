package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_3234_준환이의양팔저울 {
	private static int result;
	private static int N;
	private static int[] weight;
	private static boolean[] visit;
	private static int sum;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = 0;
			N = Integer.parseInt(bf.readLine().trim());
			weight = new int[N];
			sum = 0;
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			for (int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
				sum += weight[i];
			}
//			visit = new boolean[N];
			dfs(0,0,0);
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end

	private static void dfs(int cnt, int left, int right) {
		if(cnt == N) result++;
		
		for (int i = cnt; i < N; i++) {
			int temp = weight[i];
			weight[i] = weight[cnt];
			weight[cnt] = temp;
			dfs(cnt + 1, left + weight[cnt], right);
			if(right + weight[cnt] <= left) dfs(cnt + 1, left, right + weight[cnt]);
			weight[cnt] = weight[i];
			weight[i] = temp;
		}
		/*if(left >= sum - left) {
			int fac = 1;
			for (int i = N-cnt; i > 0; i--) {
				fac *= i;
			}
			result += Math.pow(2, N-cnt) * fac;
			return;
		}*/
		/*for (int i = 0; i < N; i++) {
			// 오른쪽이 왼쪽보다 작거나 같아야함
			if(!visit[i]) {
				visit[i] = true;
				dfs(cnt + 1, left + weight[i], right);
				if(right + weight[i] <= left) dfs(cnt + 1, left, right + weight[i]);
				visit[i] = false;
			}
		}*/
	}
}
