package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_5987_달리기 {
	private static int M;
	private static int N;
	private static int[] needs;
	private static long[] memo;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int f,s;
			needs = new int[N];
			memo = new long[1<<N];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				f = Integer.parseInt(st.nextToken())-1;
				s = Integer.parseInt(st.nextToken())-1;
				needs[f] |= (1<<s);
			}
//			System.out.println(Arrays.toString(needs));
			dfs(0);
			System.out.println("#"+test_case+" "+memo[0]);
		}//testcase end
	}//main end

	private static long dfs(int flag) {
		if(flag == (1<<N)-1){
//			result++;
			return 1;
		}
		if(memo[flag] > 0) {
			return memo[flag];
		}
		for (int i = 0; i < N; i++) {
			if((flag & 1<<i) == 0)
				// 나보다 늦은 등수의 번호가 다들어왔는지 확인
				if((flag & needs[i]) == needs[i]) {
					memo[flag] += dfs(flag | 1<<i);
				}
		}
		return memo[flag];
	}
}
