package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15684_사다리조작 {

	private static int result;
	private static int N;
	private static int M;
	private static int H;
	private static boolean[][] ladder;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		ladder = new boolean[N + 1][H + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			int a = Integer.parseInt(st.nextToken());
			ladder[Integer.parseInt(st.nextToken())][a] = true;
		}
		dfs(0);
		System.out.println(result == Integer.MAX_VALUE ? "-1" : result);
	}

	private static void dfs(int cnt) {
		if(cnt >= result) return;
		// 1. 일단 올바르게 가는지 체크
		for (int i = 1; i <= N; i++) {
			int pos = i;
			for (int j = 1; j <= H; j++) {
				if(pos < N && ladder[pos][j]) {
					pos++;
				} else if(pos > 1 && ladder[pos - 1][j]) {
					pos--;
				}
			}
			if(pos != i) break;
			if(i == N) {
				result = Math.min(result, cnt);
				return;
			}
		}
		if(cnt >= 3) return; 
		// 2. 올바르지 않다면 다리를 하나 두는데
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= H; j++) {
				if(!ladder[i][j]) {
					// 3. 연속되지 않게 체크!
					if(i == 1 && !ladder[2][j]) {
						ladder[i][j] = true;
						dfs(cnt + 1);
						ladder[i][j] = false;
					} else if(i == N - 1 && !ladder[N - 2][j]) {
						ladder[i][j] = true;
						dfs(cnt + 1);
						ladder[i][j] = false;
					} else if(!ladder[i - 1][j] && !ladder[i + 1][j]) {
						ladder[i][j] = true;
						dfs(cnt + 1);
						ladder[i][j] = false;
					}
				}
			}
		}
		
	}
}
