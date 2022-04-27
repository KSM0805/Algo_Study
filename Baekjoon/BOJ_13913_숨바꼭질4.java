package algo_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_13913_숨바꼭질4 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim(), " ");
		int N = Integer.parseInt(st.nextToken()); // 0 ~ 100,000
		int K = Integer.parseInt(st.nextToken()); // 0 ~ 100,000
		// 예외처리
		if (K < N) {
			StringBuilder sb = new StringBuilder();
			sb.append(N - K + "\n");
			for (int i = N; i >= K; i--) {
				sb.append(i + " ");
			}
			System.out.println(sb.toString().trim());
			return;
		}
		
		int max = (Math.max(N, K) * 2) + 1;
		// 방문한 위치 확인 [0]: 이동횟수, [1]: 이전 위치
		int[][] visit = new int[max][2];
		// 현재 위치 초기화 하기
		visit[N][0] = 1;
		
		LinkedList<int[]> q = new LinkedList<int[]>();
		q.add(new int[]{N, 1});	// [0]: 현재 위치, [1]: 이동 횟수
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			// 도착하면 break;
			if (cur[0] == K) {
				break;
			}
			
			// +1 이나 -1 이나 *2
			int nextPosition = cur[0] + 1;
			int nextMove = cur[1] + 1;
			if (nextPosition < max && visit[nextPosition][0] == 0) {
				visit[nextPosition][0] = nextMove;
				visit[nextPosition][1] = cur[0];
				q.add(new int[] {nextPosition, nextMove});
			}
			
			nextPosition = cur[0] - 1;
			if (nextPosition >= 0 && visit[nextPosition][0] == 0) {
				visit[nextPosition][0] = nextMove;
				visit[nextPosition][1] = cur[0];
				q.add(new int[] {nextPosition, nextMove});
			}
			
			nextPosition = cur[0] * 2;
			if (nextPosition < max && visit[nextPosition][0] == 0) {
				visit[nextPosition][0] = nextMove;
				visit[nextPosition][1] = cur[0];
				q.add(new int[] {nextPosition, nextMove});
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int position = K;
		sb.append(position);
		while(position != N) {
			sb.insert(0, visit[position][1] + " ");
			position = visit[position][1];
		}
		sb.insert(0, (visit[K][0] - 1) + "\n");
		System.out.println(sb.toString().trim());
	}
}
