package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_12100_2048_easy {
	private static int result;
	private static int N;
	private static int[][] map;
	private static int[][] dir = {{1,1}, {-1,-1}, {1,1}, {-1,-1}};
	private static LinkedList<Integer> sort = new LinkedList<>();

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;	// 5번 이동해서 만들 수 있는 최대 블록 값	// 4^5 -> 1024
		N = Integer.parseInt(bf.readLine().trim());	//1 ~ 20
		map = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0);	//[0]: 움직인 횟수, [1]: 움직인 방향
		dfs(0, 1);	//[0]: 움직인 횟수, [1]: 움직인 방향
		dfs(0, 2);	//[0]: 움직인 횟수, [1]: 움직인 방향
		dfs(0, 3);	//[0]: 움직인 횟수, [1]: 움직인 방향
		System.out.println(result);
	}

	private static void dfs(int round, int d) {
		if(round == 5) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] > result) {
						result = map[i][j];
					}
				}
			}
			return;
		}
		int[][] copyMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		int start = d % 2 == 0 ? 0 : N - 1;
		int end = d % 2 != 0 ? -1 : N;
		int sum = -1;
		// 상하일때
		if(d < 2) {
			for (int i = 0; i < N; i++) {
				sum = -1;
				int idx = start;
				while(idx != end) {
					if(map[idx][i] != 0) {
						// 1. 전에 들어간 값과 같으면 더한다
						if(sum == map[idx][i]) {
							sort.pollLast();
							sort.add(map[idx][i] * 2);
							sum = -1;
						} else {
							sum = map[idx][i];
							sort.add(map[idx][i]);
						}
					}
					idx += dir[d][0];
				}
				idx = start;
				while(!sort.isEmpty()) {
					map[idx][i] = sort.pop();
					idx += dir[d][0];
				}
				while(idx != end) {
					map[idx][i] = 0;
					idx += dir[d][0];
				}
				
			}
		// 좌우일때
		} else {
			for (int i = 0; i < N; i++) {
				sum = -1;
				int idx = start;
				while(idx != end) {
					if(map[i][idx] != 0) {
						// 1. 전에 들어간 값과 같으면 더한다
						if(sum == map[i][idx]) {
							sort.pollLast();
							sort.add(map[i][idx] * 2);
							sum = -1;
						} else {
							sum = map[i][idx];
							sort.add(map[i][idx]);
						}
					}
					idx += dir[d][1];
				}
				idx = start;
				while(!sort.isEmpty()) {
					map[i][idx] = sort.pop();
					idx += dir[d][1];
				}
				while(idx != end) {
					map[i][idx] = 0;
					idx += dir[d][1];
				}
			}
		}
//		System.out.println("-------------------- " + round + " " + d +" ---------");
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		for (int i = 0; i < 4; i++) {
			dfs(round + 1, i);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = copyMap[i][j];
			}
		}
	}
}
