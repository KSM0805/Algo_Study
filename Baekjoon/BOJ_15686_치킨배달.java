package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15686_치킨배달 {
	private static int result;
	private static int[][] map;
	private static boolean[] visits;
	private static ArrayList<int[]> homes = new ArrayList<>();
	private static ArrayList<int[]> store = new ArrayList<>();
	private static int N;
	private static int M;
	private static int[][] mem;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		homes.clear();
		store.clear();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					homes.add(new int[] {i,j});
				} else if(map[i][j] == 2) {
					store.add(new int[] {i,j});
				}
			}
		}
		result = Integer.MAX_VALUE;
		visits = new boolean[store.size()];
		
		mem = new int[homes.size()][store.size()];
		for (int i = 0; i < homes.size(); i++) {
			for (int j = 0; j < store.size(); j++) {
				mem[i][j] = Math.abs(store.get(j)[0] - homes.get(i)[0]) + Math.abs(store.get(j)[1] - homes.get(i)[1]);
			}
		}
		for (int i = 1, size = 1<<store.size(); i < size; i++) {
			int cnt = 0;
			Arrays.fill(visits, false);
			for (int j = 0; j < store.size(); j++) {
				if((i & 1<<j) != 0) {
					cnt++;
					visits[j] = true;
				}
			}
			if(cnt <= M) {
				solve();
			}
		}
		System.out.println(result);
	}
	
	private static void solve() {
		int total = 0;
		for (int i = 0; i < homes.size(); i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < store.size(); j++) {
				if(visits[j])
					min = Math.min(min, mem[i][j]);
			}
			total += min;
		}
		result = Math.min(result, total);
	}
}