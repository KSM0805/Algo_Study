package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test_2112_보호필름 {
	private static int result;
	private static int K;
	private static int W;
	private static int D;
	private static int[][] film;
	private static boolean[] visit;
	private static int[] change;
	private static int[] tmp = new int[2];

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			D = Integer.parseInt(st.nextToken());	// 3 ~ 13 보호 필름의 두께 
			W = Integer.parseInt(st.nextToken());	// 1 ~ 20 보호 필름의 가로의 길이
			K = Integer.parseInt(st.nextToken());	// 1 ~ D 합격 기준
			film = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visit = new boolean[D];
			change = new int[D];
			dfs(0, -1);
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end

	private static void dfs(int cnt, int idx) {
		if(result <= cnt) return;
		// 1. 합격기준 통과 체크
		boolean check = true;
		for (int i = 0; i < W; i++) {
			if(visit[0]) tmp[0] = change[0];
			else tmp[0] = film[0][i];
			tmp[1] = 1;
			for (int j = 1; j < D; j++) {
				if(tmp[1] >= K) break;
				if(visit[j]) {
					if(change[j] != tmp[0]) {
						tmp[0] = change[j];
						tmp[1] = 1;
					} else {
						tmp[1]++;
					}
				} else {
					if(film[j][i] != tmp[0]) {
						tmp[0] = film[j][i];
						tmp[1] = 1;
					} else {
						tmp[1]++;
					}
				}
			}
			if(tmp[1] < K) {
				check = false;
				break;
			}
		}
		// 2. 불합인 경우 주입
		if(!check) {
			for (int i = idx + 1; i < D; i++) {
				if(!visit[i]) {
					visit[i] = true;
					for (int j = 0; j < 2; j++) {
						// 주입한 경우가 없을 때만
							change[i] = j;
							dfs(cnt + 1, i);
					}
					visit[i] = false;
				}
			}
		} else {
			result = cnt;
		}
	}
}
