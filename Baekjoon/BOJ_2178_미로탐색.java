package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_2178 {
	private static int N;
	private static int M;
	private static String[] str;
	private static int[][] visit;
	private static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		str = new String[N];
		for (int i = 0; i < N; i++) {
			str[i] = bf.readLine().trim();
		}
		visit = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visit[i][j] = Integer.MAX_VALUE;
			}
		}
		min = Integer.MAX_VALUE;
		bfs();
		
		System.out.println(min);
	}

	private static void bfs() {
		LinkedList<int[]> q = new LinkedList<>();
		q.offer(new int[] {0,0,1}); //[0]: 0행, [1]: 0열, [2]: 1번째 움직임
		visit[0][0] = 1;
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int r = arr[0];
			int c = arr[1];
			int cnt = arr[2]+1;
			if(r==N-1 && c==M-1) {
				min = Math.min(min, arr[2]);
				continue;
			}
			
			if(r-1>-1 && visit[r-1][c]>cnt && str[r-1].charAt(c)=='1') { //상
				visit[r-1][c] = cnt;
				q.offer(new int[] {r-1,c,cnt});
			}
			if(r+1<N && visit[r+1][c]>cnt && str[r+1].charAt(c)=='1') { //하
				visit[r+1][c] = cnt;
				q.offer(new int[] {r+1,c,cnt});
			}
			if(c-1>-1 && visit[r][c-1]>cnt && str[r].charAt(c-1)=='1') {
				visit[r][c-1] = cnt;
				q.offer(new int[] {r,c-1,cnt});
			}
			if(c+1<M && visit[r][c+1]>cnt && str[r].charAt(c+1)=='1') {
				visit[r][c+1] = cnt;
				q.offer(new int[] {r,c+1,cnt});
			}
		}
	}


}
