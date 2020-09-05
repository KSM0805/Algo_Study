package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070_파이프옮기기1 {
	private static long result;
	private static int N;
	private static int[][] map;
	private static long[][][] mem;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		N = Integer.parseInt(bf.readLine().trim());
		map = new int[N][N];
		mem = new long[N][N][3];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 초기 위치
		if(map[0][0] == 0 && map[0][1] == 0) {
			// dp로 풀자
			result = dfs(0, 1, 0);
		}
		System.out.println(result);
	}
	
	private static long dfs(int r, int c, int pos) {
		if(mem[r][c][pos] != 0) return mem[r][c][pos];
		if(r == N-1 && c == N-1) return 1;
		long result = 0;
		int nr, nc;
		// 1. 가로로 가능한가?
		if(pos != 1) {
			nc = c + 1;
			if(nc < N && map[r][nc] == 0) {
				result += dfs(r, nc, 0);
			}	
		}
		// 2. 세로로 가능한가?
		if(pos != 0) {
			nr = r + 1;
			if(nr < N && map[nr][c] == 0) {
				result += dfs(nr, c, 1);
			}
		}
		// 3. 대각선으로
		nr = r + 1;
		nc = c + 1;
		if(nr < N && nc < N && map[nr][nc] == 0 && map[r][nc] == 0 && map[nr][c] == 0) {
			result += dfs(nr, nc, 2);
		}
		mem[r][c][pos] = result;
		return mem[r][c][pos];
	}
}