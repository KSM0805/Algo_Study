package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_14502 {
	private static int M;
	private static int N;
	private static int[][] map;
	private static ArrayList<int[]> virus;
	private static boolean[][] visit;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static int min;
	private static int wall;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		//벽은 3개
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); //3~8
		map = new int[N][M];
		wall = 0;
		virus = new ArrayList<>();//2~10
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					virus.add(new int[] {i,j});
				}else if(map[i][j]==1) wall++;
			}
		}
//		bfs
		//어떻게 3군데를 고를 것인가?
		LinkedList<int[]> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = i; k < N; k++) {
					for (int l = 0; l < M; l++) {
						if(k==i && l<=j) continue;
						if(k != i || l != j) {
							for (int m = k; m < N; m++) {
								for (int n = 0; n < M; n++) {
									if(m == k && n<=l) continue;
									if(m != k || n != l) {
										q.offer(new int[] {i,j,k,l,m,n});
									}
								}
							}
						}
					}
				}
				
			}
		}
		min = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int[][] map2 = new int[N][M];
			for (int i = 0; i < N; i++) {
				map2[i] = Arrays.copyOf(map[i], M);
//				System.out.println(Arrays.toString(map2[i]));
			}
			
			if(map2[arr[0]][arr[1]] != 0 || map2[arr[2]][arr[3]] != 0 || map2[arr[4]][arr[5]] != 0) continue;
			for (int i = 0; i < 6; i+=2) {
				map2[arr[i]][arr[i+1]] = 1;
			}
			int totalV = 0;
			visit = new boolean[N][M];
			for (int i = 0; i < virus.size(); i++) {
				int numV = 1;
				LinkedList<int[]> vir = new LinkedList<>();
				int[] pos = virus.get(i);
				vir.offer(new int[] {pos[0],pos[1]});
				visit[pos[0]][pos[1]] = true;
				while(!vir.isEmpty()) {
					int[] ar = vir.poll();
					for (int j = 0; j < 4; j++) {
						int nr = ar[0] + dir[j][0];
						int nc = ar[1] + dir[j][1];
						if(nr>-1 && nc>-1 && nr<N && nc<M && !visit[nr][nc] && map2[nr][nc]==0) {
							visit[nr][nc] = true;
							map2[nr][nc] = 2;
							numV++;
							vir.offer(new int[] {nr,nc});
						}
					}
				}
				if(min<numV) {
					totalV = Integer.MAX_VALUE;
					break;
				}
				totalV += numV;
			}
			min = Math.min(min, totalV);
		}
		System.out.println(N*M-wall-3-min);
	}
}
