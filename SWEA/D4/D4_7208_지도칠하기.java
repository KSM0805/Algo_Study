package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_7208_지도칠하기 {
	private static int result;
	private static int N;
	private static int[] color;
	private static int[] changecolor;
	private static boolean[][] arr;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = Integer.MAX_VALUE;
			N = Integer.parseInt(bf.readLine().trim());
			color = new int[N];
			changecolor = new int[N];
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			for (int i = 0; i < N; i++) {
				color[i] = Integer.parseInt(st.nextToken());
			}
			arr = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < N; j++) {
					if(st.nextToken().equals("1")) arr[i][j] = true;
				}
			}
//			dfs(0);
			select(0);
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end

	private static void select(int cnt) {
		if(cnt == N) {
			solve();
			return;
		}
		for (int i = 1; i < 5; i++) {
			int tmp = changecolor[cnt];
			changecolor[cnt] = i;
			select(cnt + 1);
			changecolor[cnt] = tmp;
		}
	}

	private static void solve() {
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if(arr[i][j] && changecolor[i]==changecolor[j]) return;
			}
		}
		// 가능하다면
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if(color[i] != changecolor[i]) cnt++;
		}
		result = Math.min(result, cnt);
	}

	/*private static void dfs(int cnt) {
		if(result <= cnt) return;
		boolean check = true;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				if(arr[i][j]) {
					if(color[i] == color[j]) {
						check = false;
						int tmp = color[j];
						for (int k = 1; k < 5; k++) {
							if(k != tmp) {
								color[j] = k;
								dfs(cnt + 1);
							}
						}
						color[j] = tmp;
					}
				}
			}
		}
		if(check) result = cnt;
	}*/
}
