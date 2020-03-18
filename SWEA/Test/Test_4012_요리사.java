package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test_4012_요리사 {
	private static int result;
	private static int N;
	private static int[][] map;
	private static boolean[] visit;
	private static int totalSum;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim());//4~16 식재료의 갯수
			map = new int[N][N];
			totalSum = 0;
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					totalSum += map[i][j];
				}
			}
			//dfs타기
			visit = new boolean[N];
			result = Integer.MAX_VALUE;
			dfs(-1,0,0,totalSum); //선택한 재료 위치, 선택한 갯수, 선택한 값의 합, 선택하지 않은 값의 합
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end

	private static void dfs(int pos, int cnt, int sum, int notSum) {
		if(result == 0) return;
		if(cnt == N/2) {
			result = Math.min(result, Math.abs(sum-notSum));
			return;
		}
		for (int i = pos+1, size = N-(N/2 - cnt); i <= size; i++) {
			int numa = 0;
			int numb = 0;
			for (int j = 0; j < i; j++) {
				if(visit[j]) {
					numa += map[j][i] + map[i][j];
				}
			}
			for (int j = 0; j < N; j++) {
				if(!visit[j]) {
					numb += map[i][j] + map[j][i];
				}
			}
			visit[i] = true;
			dfs(i,cnt+1,sum + numa, notSum - numb);
			visit[i] = false;
		}
	}
}
