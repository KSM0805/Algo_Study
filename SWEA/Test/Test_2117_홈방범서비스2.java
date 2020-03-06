package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Test_2117_홈방범서비스2 {
	private static int result;
	private static int M;
	private static int N;
	private static int[][] map;
	private static int home;
	private static boolean[][][] visit;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static int[] cost;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		cost = new int[22];
		cost[0] = 0;
		for (int i = 1; i < 22; i++) {
			cost[i] = i*i + (i-1)*(i-1);
		}
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken()); //5~20 도시의 크기
			M = Integer.parseInt(st.nextToken()); //1~10 집이 지불하는 비용
			map = new int[N][N];
			home = 0;
			String str;
			for (int i = 0; i < N; i++) {
				str = bf.readLine().trim();
				for (int j = 0; j < N; j++) {
					if(str.charAt(j<<1) == '1') {
						map[i][j] = M;
						home+=M;
					}
				}
			}
			if(home == M) {
				System.out.println("#"+test_case+" 1");
				continue;
			}
			result = 0;
			//bfs
			visit = new boolean[N][N][22];
			//크고 가운데부터
			for (int i = 21; i > 0; i--) {
				if(home >= cost[i]) {
					if(cost[i]*M < result) continue;
					visit[N/2][N/2][i] = true;
					check(N/2,N/2,i);
					if(result == home) break;
				}
			}
			System.out.println("#"+test_case+" "+(result/M));
		}//testcase end
	}//main end

	private static void check(int r, int c, int rang) {
		LinkedList<int[]> q = new LinkedList<>();
		q.offer(new int[] {r,c,rang});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int range = cur[2]-1;
			int cnt = 0;
			if(cost[cur[2]]*M <= result) return;
			for (int i = cur[0]-range,col = 0; i <= cur[0] + range; i++) {
				for (int j = cur[1]-col; j <= cur[1]+col; j++) {
					if(i<0 || j<0 || i>=N || j>=N) continue;
					if(map[i][j] > 0) {
						cnt += map[i][j];
					}
				}
				if(i<cur[0]) col++;
				else col--;
			}
			if(cnt >= cost[cur[2]]) {
				result = Math.max(result, cnt);
				if(home == cnt) return;
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dir[i][0];
				int nc = cur[1] + dir[i][1];
				if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
				if(!visit[nr][nc][cur[2]]) {
					visit[nr][nc][cur[2]] = true;
					q.offer(new int[] {nr,nc,cur[2]});
				}
			}
		}
	}
}
