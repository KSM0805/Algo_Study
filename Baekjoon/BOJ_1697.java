package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1697 {
	private static int N;
	private static int K;
	private static int[] visit;
	private static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		//bfs
		min = Integer.MAX_VALUE;
		visit = new int[2*Math.max(N, K)];
		LinkedList<int[]> q = new LinkedList<>();
		q.offer(new int[] {N,0}); //시작 위치, cnt
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int pos = arr[0];
			int cnt = arr[1]+1;
			if(pos==K) {
				min = Math.min(min, arr[1]);
				continue;
			}
			if(pos-1 > -1 && (visit[pos-1]==0 || visit[pos-1]>=cnt) ) {
				visit[pos-1] = cnt;
				q.offer(new int[] {pos-1, cnt});
			}
			if(pos+1 < 2*K && (visit[pos+1]==0 || visit[pos+1]>=cnt) ) {
				visit[pos+1] = cnt;
				q.offer(new int[] {pos+1, cnt});
			}
			if(2*pos< 2*K && (visit[2*pos]==0 || visit[2*pos]>=cnt) ) {
				visit[2*pos] = cnt;
				q.offer(new int[] {2*pos, cnt});
			}
		}
		System.out.println(min);
	}
}
