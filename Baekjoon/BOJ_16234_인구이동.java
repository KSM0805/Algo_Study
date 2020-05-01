package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16234_인구이동 {
	private static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
	private static boolean[][] visit;
	private static int[][] map;
	private static int N;
	private static int L;
	private static int R;
	private static ArrayList<int[]> list = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		boolean check = false;
		visit = new boolean[N][N];
		while(true) {
			for (boolean[] v : visit) { // 방문 배열 초기화
				Arrays.fill(v, false);
			}
			check = false;	// 인구 이동이 가능한지 체크하는 변수
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!visit[i][j]) { // 방문 하지 않은 마을중에
						for (int k = 0; k < 4; k++) {
							int nr = i + dir[k][0];
							int nc = j + dir[k][1];
							if(nr<0 || nc<0 || nr>=N || nc>=N || visit[nr][nc]) continue;
							
							if(Math.abs(map[i][j] - map[nr][nc]) >= L && Math.abs(map[i][j] - map[nr][nc]) <= R) {
								// 인구 이동이 가능한 마을이 있으면 문열기 시작
								check = true;
								movePeople(i,j);
								break;
							}
						}
					}
				}
			}
			if(!check) break;	// 인구 이동이 없으면 탈출
			time++;
		}
		System.out.println(time);
	}

	private static void movePeople(int i, int j) {
		visit[i][j] = true;
		
		int total = 0;
		list.clear();
		list.add(new int[] {i,j});
		
		
		int idx = 0;
		while(idx < list.size()) {
			int[] cur = list.get(idx);
			total += map[cur[0]][cur[1]];	// 이어진 마을의 총 인구
			
			for (int k = 0; k < 4; k++) {
				int nr = cur[0] + dir[k][0];
				int nc = cur[1] + dir[k][1];
				if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
				if(!visit[nr][nc] && Math.abs(map[cur[0]][cur[1]] - map[nr][nc]) >= L && Math.abs(map[cur[0]][cur[1]] - map[nr][nc]) <= R) {
					visit[nr][nc] = true;
					list.add(new int[] {nr,nc});
				}
			}
			idx++;
		}
		total = total / list.size();
		for (int[] l : list) {
			map[l[0]][l[1]] = total;
		}
	}
}
