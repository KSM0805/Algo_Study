package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11404_플로이드 {
	private static int N;
	private static int M;
	private static int[][] map;
	final static int max = Integer.MAX_VALUE >> 1;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim()); // 1~100 도시의 개수
		M = Integer.parseInt(bf.readLine().trim()); // 1~100,000버스의 개수
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i != j) {
					map[i][j] = max;
				}
			}
		}
		
		StringTokenizer st;
		int from, to;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			from = Integer.parseInt(st.nextToken()) - 1;
			to = Integer.parseInt(st.nextToken()) - 1;
			map[from][to] = Math.min(map[from][to], Integer.parseInt(st.nextToken()));
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][k] + map[k][j] < map[i][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j] != max ? map[i][j] : "0").append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
