package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Test_1953_탈주범검거 {
	private static int result;
	private static boolean[][] pos = {	{false, false, false, false},
										{true, true, true, true},
										{true, true, false, false},
										{false, false, true, true},
										{true, false, false, true},
										{false, true, false, true},
										{false, true, true, false},
										{true, false, true, false}
									};
	private static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	private static int N;
	private static int M;
	private static int R;
	private static int C;
	private static int L;
	private static int[][] map;
	private static boolean[][] visit;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());	
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());	// 맵의 행
			M = Integer.parseInt(st.nextToken());	// 맵의 열
			R = Integer.parseInt(st.nextToken());	// 맨홀 위치
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());	//1~20 : 소요시간
			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visit = new boolean[N][M];
			LinkedList<int[]> q = new LinkedList<>();
			q.add(new int[] {R, C});
			visit[R][C] = true;
			result = 1;
			int time = 1;

			while(!q.isEmpty() && time < L) {
//				System.out.println(time + " " + result);
				for (int i = 0, step = q.size(); i < step; i++) {
					int[] cur = q.poll();	// 현재 가능한 위치
					for(int j = 0; j < 4; j++) {
						// 현재 위치에서 움직일 수 있는 방향이라면
						if(pos[map[cur[0]][cur[1]]][j]) {
							int nr = cur[0] + dir[j][0];
							int nc = cur[1] + dir[j][1];
							// 범위 밖이거나 움직일 공간에서 파이프가 연결되어 있지 않다면 못간다
							if(nr < 0 || nc < 0 || nr >= N || nc >= M || !pos[map[nr][nc]][j%2==0 ? j + 1 : j - 1]) continue;
							// 방문하지 않은 곳만 가능
							if(!visit[nr][nc]) {
								visit[nr][nc] = true;
								result++;
								q.add(new int[] {nr, nc});
							}
						}
					}
				}
				time++;
			}
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end
}
