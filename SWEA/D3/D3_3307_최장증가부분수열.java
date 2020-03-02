package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_3307 {
	private static int result;
	private static int N;
	private static String str;
	private static int[] visit;
	private static int[] num;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim());
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			num = new int[N];
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			result = 0;
			visit = new int[N];
			for (int i = 0; i < N; i++) {
				if(visit[i] == 0) visit[i] = 1;
				dfs(i,1); // i: 현재 숫자 위치 , 0: 현재 최장 길이
			}
			System.out.println("#"+test_case+" "+result);
		}//for end
	}

	private static void dfs(int pos, int cnt) {
		if(result<cnt) {
			result = Math.max(result, cnt);
		}
		for (int i = pos+1; i < N; i++) {
			if(num[pos] < num[i] && visit[i]<cnt+1) {
				visit[i] = cnt+1;
				dfs(i,cnt+1);
			}
		}
	}
}
