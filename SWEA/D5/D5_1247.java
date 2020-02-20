package swea.d5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D5_1247 {
	private static int N;
	private static int[][] list;
	private static int dis;
	private static boolean[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim());
			list = new int[N+2][2];
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			for (int i = 0; i < N+2; i++) {
				list[i][0] = Integer.parseInt(st.nextToken());
				list[i][1] = Integer.parseInt(st.nextToken());
			}
			int[] tmp = list[1];
			list[1] = list[N+1];
			list[N+1] = tmp;
			//list[0] 회사의 좌표
			//list[N-1] 집의 좌표
			dis = Integer.MAX_VALUE;
			visit = new boolean[N+2];
			dfs(0,0,0);
			System.out.println("#"+test_case+" "+dis);
		}//for end
	}

	private static void dfs(int num, int cnt, int d) {
		int r = list[num][0];
		int c = list[num][1];
		if(cnt == N) {
			d += Math.abs(r - list[N+1][0]) + Math.abs(c - list[N+1][1]);
			dis = Math.min(dis, d);
			return;
		}else if(dis<d) return;
		for (int i = 1; i <N+1; i++) {
			if(!visit[i]) {
				visit[i] = true;
				dfs(i,cnt + 1, d + Math.abs(r - list[i][0]) + Math.abs(c - list[i][1]) );
				visit[i] = false;
			}
		}
	}
}
