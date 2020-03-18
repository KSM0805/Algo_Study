package d5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class D5_7793_오나의여신님 {
	private static int result;
	private static int N;
	private static int M;
	private static char[][] map;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static int[][] visit;
	private static int[][] devilMap;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken()); //2~50
			map = new char[N][M];
			int[] su = new int[2]; //수연이의 위치
			ArrayList<int[]> devil = new ArrayList<>();
			String str;
			devilMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				str = bf.readLine().trim();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j]=='S') {
						su[0] = i;
						su[1] = j;
						map[i][j] = '.';
					}else if(map[i][j]=='*') {
						devil.add(new int[] {i,j});
						devilMap[i][j] = 1;
					}
				}
			}
			//devil이 확장하는걸 숫자로 표현하기
			int start = 0;
			int end = devil.size();
			int time = 2;
			while(start < end) {
				for (int j = start; j < end; j++) {
					int[] pos = devil.get(j);
					for (int i = 0; i < 4; i++) {
						int nr = pos[0] + dir[i][0];
						int nc = pos[1] + dir[i][1];
						if(nr<0 || nc<0 || nr>=N || nc>=M) continue;
						if(map[nr][nc]=='.' && devilMap[nr][nc]==0) {
							devilMap[nr][nc] = time;
							devil.add(new int[] {nr,nc});
						}
					}
				}
				start = end;
				end = devil.size();
				time++;
			}
			//bfs
			result = Integer.MAX_VALUE;
			visit = new int[N][M];
			LinkedList<int[]> q = new LinkedList<>();
			q.offer(new int[] {su[0],su[1],1}); // 수연이의 위치, 움직인 횟수
			visit[su[0]][su[1]] = 1;
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				if(map[cur[0]][cur[1]] == 'D') {
					result = Math.min(result, cur[2]);
					continue;
				}
				if(map[cur[0]][cur[1]] == 'X') continue;
				for (int i = 0; i < 4; i++) {
					int nr = cur[0] + dir[i][0];
					int nc = cur[1] + dir[i][1];
					if(nr<0 || nc<0 || nr>=N || nc>=M) continue;
					if((devilMap[nr][nc] > cur[2]+1 || devilMap[nr][nc]==0) && (visit[nr][nc]==0 || visit[nr][nc] > cur[2]+1)) {
						visit[nr][nc] = cur[2]+1;
						q.offer(new int[] {nr,nc,cur[2]+1});
					}
				}
			}
			System.out.println("#"+test_case+" "+(result==Integer.MAX_VALUE ? "GAME OVER" : (result-1)));
		}//testcase end
	}//main end
}
