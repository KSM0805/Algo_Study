package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11054 {
	private static int N;
	private static int[] num;
	private static int max;
	private static int[][] visit;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim());
		num = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		max = 1;
		visit = new int[N][2]; // 0: 올라갈때 갯수, 1: 내려갈때 갯수
		//최댓값 dfs 해보자
		for (int i = 0; i < N; i++) {
			if(visit[i][0]<=1) {
				visit[i][0] = 1;
				upDfs(i,1);
			}
		}
		for (int i = N-1; i > -1; i--) {
			if(visit[i][1]<=1) {
				visit[i][1] = 1;
				downDfs(i,1);
			}
		}
		for (int i = 0; i < N; i++) {
			int a = visit[i][0] + visit[i][1];
			max = Math.max(max, a);
		}
		System.out.println(max-1);
	}

	private static void downDfs(int pos, int cnt) {
		for (int i = pos-1; i > -1; i--) {
			if(num[pos] < num[i] && visit[i][1] < cnt+1) {
				visit[i][1] = cnt + 1;
				downDfs(i, cnt + 1);
			}
		}
	}

	private static void upDfs(int pos, int cnt) {
		for (int i = pos+1; i < N; i++) {
			if(num[pos] < num[i] && visit[i][0] < cnt+1) {
				visit[i][0] = cnt + 1;
				upDfs(i, cnt + 1);
			}
		}
	}
}
