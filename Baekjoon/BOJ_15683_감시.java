package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15683_감시 {
	private static int result;
	private static int M;
	private static int N;
	private static int[][] map;
	private static ArrayList<int[]> cctv;
	private static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	private static ArrayList<int[]> list = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctv = new ArrayList<>();
		int cnt = 0;
		// 1. 감시카메라를 ArrayList에 담는다
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) cnt++;
				else if(map[i][j] < 6) cctv.add(new int[] {i,j, map[i][j]});
			}
		}
		// 2. 하나씩 꺼내서 dfs를 돌린다.
		dfs(0, cnt, 0); //[0]: 카메라 인덱스, [1]: 사각지대 갯수, [2]: map
		
		System.out.println(result);
	}

	private static void dfs(int idx, int cnt, int step) {
		if(idx == cctv.size()) {
			result = Math.min(result, cnt);
			return;
		}
		// 3. 카메라의 번호
		int[] pos = cctv.get(idx);
		switch (pos[2]) {
		case 1:
			// 3-1. 1번인 경우 상, 하, 좌, 우 이렇게 4개
			for (int i = 0; i < 4; i++) {
				int num = solve(i, pos);
				dfs(idx + 1, cnt - num, step + num);
				clear(step);
			}
			break;
		case 2:
			// 3-2. 2번인 경우 상하, 좌우 이렇게 2개
			for (int i = 0; i < 3; i+=2) {
				int num = solve(i, pos) + solve(i + 1, pos);
				dfs(idx + 1, cnt - num, step + num);
				clear(step);
			}
			break;
		case 3:
			// 3-3. 3번인 경우 상우, 우하, 좌하, 좌상 이렇게 4개
			int num = 0, num2 = 0;
			for (int i = 0; i < 2; i++) {
				num = solve(i, pos);
				for (int j = 2; j < 4; j++) {
					num2 = solve(j,pos);
					dfs(idx + 1, cnt - (num + num2), step + num + num2);
					clear(step + num);
				}
				clear(step);
			}
			break;
		case 4:
			// 3-4. 4번인 경우 상하좌, 상하우, 하좌우, 상좌우 이렇게 4개
			for (int i = 0; i < 4; i++) {
				num = 0;
				for (int j = 0; j < 4; j++) {
					if(j == i) continue;
					num += solve(j, pos);
				}
				dfs(idx + 1, cnt - num, step + num);
				clear(step);
			}
			break;
		case 5:
			// 3-5. 5번인 경우 상하좌우 1개
			num = 0;
			for (int i = 0; i < 4; i++) {
				num += solve(i, pos);
			}
			dfs(idx + 1, cnt - num, step + num);
			clear(step);
			break;
		}
	}

	private static void clear(int step) {
		for (int i = list.size() - 1; i >= step; i--) {
			int[] cur = list.get(i);
			map[cur[0]][cur[1]] = 0;
			list.remove(i);
		}
	}

	private static int solve(int d, int[] pos) {
		int num = 0;
		int nr = pos[0] + dir[d][0];
		int nc = pos[1] + dir[d][1];
		if(nr < 0 || nc < 0 || nr >= N || nc >= M) return 0;
		// 4. 카메라 진행 방향
		// 4-1. 벽만 뚫을 수 없다
		while(map[nr][nc] != 6) {
			if(map[nr][nc] == 0) {
				map[nr][nc] = -1;
				num++;
				list.add(new int[] {nr, nc});
			}
			nr += dir[d][0];
			nc += dir[d][1];
			if(nr < 0 || nc < 0 || nr >= N || nc >= M) break;
		}
		return num;
	}
}
