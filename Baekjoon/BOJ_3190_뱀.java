package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_3190_뱀 {
	private static int result;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static int N;
	private static int K;
	private static int[][] map;
	private static boolean[][] visit;
	private static HashMap<Integer, String> changeDir = new HashMap<Integer, String>();
	private static LinkedList<int[]> snake = new LinkedList<>();
	private static int L;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;	// 게임이 끝나는 시간
		N = Integer.parseInt(bf.readLine().trim());	//2-100 보드의 크기
		K = Integer.parseInt(bf.readLine().trim());	//0-100 사과의 개수
		map = new int[N][N];
		visit = new boolean[N][N];
		map[0][0] = -1;
		snake.add(new int[] {0,0});	//초기 뱀의 위치
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = i + 1;	//사과
		}
		L = Integer.parseInt(bf.readLine().trim());	//1-100 방향전환
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			changeDir.put(Integer.parseInt(st.nextToken()), st.nextToken());
		}
		solve(1,3);
		System.out.println(result);
	}

	private static void solve(int time, int d) {
//		System.out.println("---"+time);
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		if(result < time) result = time;
		int[] head = snake.get(0);
		int nr = head[0] + dir[d][0];
		int nc = head[1] + dir[d][1];
		if(nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == -1) return;
		if(changeDir.containsKey(time)) {
			// 0일때 L이면 2 R 3
			switch (d) {
			case 0:
				d = changeDir.get(time).equals("L") ? 2 : 3;
				break;
			case 1:
				d = changeDir.get(time).equals("L") ? 3 : 2;
				break;
			case 2:
				d = changeDir.get(time).equals("L") ? 1 : 0;
				break;
			case 3:
				d = changeDir.get(time).equals("L") ? 0 : 1;
				break;
			}
		}
		// 사과가 있다면
		if(map[nr][nc] > 0) {
			map[nr][nc] = -1;
			snake.add(0, new int[] {nr, nc});
			solve(time + 1, d);
		} else {
			map[nr][nc] = -1;
			int[] last = snake.pollLast();
			map[last[0]][last[1]] = 0;
			snake.add(0, new int[] {nr, nc});
			solve(time + 1, d);
		}
	}
}
