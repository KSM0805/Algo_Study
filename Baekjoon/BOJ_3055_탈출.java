package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_3055_탈출 {
	private static int result;
	private static int C;
	private static int R;
	private static char[][] map;
	private static int[] Dochi;
	private static int[][] water;
	private static int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};
	private static int[][] visit;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken()); //1~50 R행C열
		map = new char[R][C];
		water = new int[R][C]; //물의 위치
		Dochi = new int[2]; //고슴도치 위치
		String str;
		for (int i = 0; i < R; i++) {
			str = bf.readLine().trim();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j]=='S') {
					Dochi[0] = i;
					Dochi[1] = j;
					map[i][j] = '.';
				}else if(map[i][j] == '*') {
					water[i][j] = 1;
				}
			}
		}
		//물을 미리 계산하기
		waterExtend();
		result = Integer.MAX_VALUE;
		visit = new int[R][C];
		visit[Dochi[0]][Dochi[1]] = 1; //현재시간을 1이라한다.
		LinkedList<int[]> q = new LinkedList<>();
		q.offer(new int[] {Dochi[0],Dochi[1],1}); //고슴도치 위치와 현재시간
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			if(map[arr[0]][arr[1]] == 'D') {
				result = Math.min(result, arr[2]);
				continue;
			}
			if(arr[2] > result) continue;
			for (int i = 0; i < 4; i++) {
				int nr = arr[0] + dir[i][0];
				int nc = arr[1] + dir[i][1];
				if(nr<0 || nc<0 || nr>=R || nc>=C) continue;
				if(map[nr][nc] != '*' && map[nr][nc] != 'X' && (water[nr][nc] > arr[2]+1 || water[nr][nc]==0) && (visit[nr][nc]==0 || visit[nr][nc] > arr[2]+1)) {
					visit[nr][nc] = arr[2]+1;
					q.offer(new int[] {nr,nc,arr[2]+1});
				}
			}
		}
		if(result == Integer.MAX_VALUE) {
			System.out.println("KAKTUS");
		}else System.out.println(result-1);
	}

	private static void waterExtend() {
		int time = 1;
		while(true) {
			boolean remain = false;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(water[i][j] == time) {
						for (int k = 0; k < 4; k++) {
							int nr = i + dir[k][0];
							int nc = j + dir[k][1];
							if(nr<0 || nc<0 || nr>R-1 || nc>C-1) continue;
							if(water[nr][nc]==0 && map[nr][nc]=='.') {
								remain = true;
								water[nr][nc] = time+1;
							}
						}
					}
				}
			}
			time++;
			if(!remain) break; //물이 더이상 퍼질공간이 없으면 out
		}
	}
}
