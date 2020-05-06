package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17472_다리만들기2 {
	private static int result;
	private static int M;
	private static int N;
	private static int[][] map;
	private static boolean[][] visit;
	private static int cnt;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static ArrayList<ArrayList<int[]>> island = new ArrayList<>();
	private static int[][] bridge;
	private static boolean[] visitIsland;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		cnt = 1; // 단지의 갯수
		island.clear();
		island.add(new ArrayList<>());
		
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visit = new boolean[N][M];
		// 1. 단지를 만들어서 섬의 갯수를 파악한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				
				if(map[i][j] == 1 && !visit[i][j])
				dangiMaker(i,j);
			}
		}
		// 3. 가능한 다리를 모두 놓는다.
		bridge = new int[island.size()][island.size()];
		for (int i = 1; i < island.size(); i++) {
			// 각 섬의 칸의 위치에서 가로, 세로를 모두 놓아본다 -> 다른 섬과 연결되는 것을 채택하여 넣어놓는다. 최소 길이가 2인 것
			for (int j = 0; j < island.get(i).size(); j++) {
				int[] cur = island.get(i).get(j);
				int nr, nc, len;
				for (int k = 0; k < 4; k++) {
					nr = cur[0] + dir[k][0];
					nc = cur[1] + dir[k][1];
					if(nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] > 0) continue;
					len = 1;
					// 일직선으로 다리를 놓을 수 있는 동안
					while(map[nr][nc] == 0) {
						nr += dir[k][0];
						nc += dir[k][1];
						if(nr < 0 || nc < 0 || nr >= N || nc >= M) break;
						if(map[nr][nc] > 0 && len > 1) {
//							System.out.println(i + " " + len + " " + map[nr][nc]);
							bridge[i][map[nr][nc]] = bridge[i][map[nr][nc]] == 0 ? len : Math.min(len, bridge[i][map[nr][nc]]);
							bridge[map[nr][nc]][i] = bridge[map[nr][nc]][i] == 0 ? len : Math.min(len, bridge[map[nr][nc]][i]);
							break;
						}
						len++; // 다리를 놓을 수 있다면 길이를 늘린다.
					}
				}
			}
		
		}
		// 4. 연결을 어떻게? 프림? 크루스칼?
		visitIsland = new boolean[island.size()];
		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		// 첫번째 마을에서 가장 짧은 다리를 먼저 놓는다.
		for (int i = 2; i < island.size(); i++) {
			if(bridge[1][i] > 1) q.add(new int[] {1, i, bridge[1][i]});
		}
		visitIsland[1] = true;
		int count = 2;
		while(count < island.size() && !q.isEmpty()) {
			int[] cur = q.poll();
			if(visitIsland[cur[1]]) continue;
			result += cur[2];
//			System.out.println(Arrays.toString(cur)+" "+count);
			visitIsland[cur[1]] = true;
			count++;
			for (int i = 1; i < island.size(); i++) {
				if(bridge[cur[1]][i] > 1 && !visitIsland[i]) {
					q.add(new int[] {cur[1], i, bridge[cur[1]][i]});
				}
			}
		}
		System.out.println(count < island.size() ? "-1" : result);
	}

	private static void dangiMaker(int i, int j) {
		island.add(new ArrayList<>());
		LinkedList<int[]> q = new LinkedList<>();
		q.add(new int[] {i,j});
		visit[i][j] = true;
		map[i][j] = cnt;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			// 2. ArrayList에 각 섬에 해당하는 위치를 넣어놓는다.
			island.get(cnt).add(cur);
			for (int k = 0; k < 4; k++) {
				int nr = cur[0] + dir[k][0];
				int nc = cur[1] + dir[k][1];
				if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				if(map[nr][nc] == 1 && !visit[nr][nc]) {
					visit[nr][nc] = true;
					map[nr][nc] = cnt;
					q.add(new int[] {nr,nc});
				}
			}
			
		}
		cnt++;
	}
}
