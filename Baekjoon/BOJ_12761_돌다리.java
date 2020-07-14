package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_12761 {
	private static int A;
	private static int B;
	private static int N;
	private static int M;
	private static int[] visit;
	private static int min;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken()); //2~30
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); //0~100,000
		min = Integer.MAX_VALUE;
		bfs();
		System.out.println(min-1);
	}

	private static void bfs() {
		//우선순위 큐(거리가 가까운거 먼저)를 사용하면 10배로 오래걸린다. 왜?? 횟수로 백트래킹을 하기 때문에 횟수가 우선순위로 가야함(bfs)
		LinkedList<int[]> q = new LinkedList<>();
		q.offer(new int[] {N,1}); //동규위치, 이동횟수
		visit = new int[100001];
		visit[N] = 1;
		//map보다 크더라도 직접 배열을 선언해서 하는게 훨씬 빠름!
//		Map<Integer, Integer> map = new HashMap<>();
//		map.put(N, 1);
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int posN = arr[0];
			int cnt = arr[1];
			if(posN == M) {
				min = Math.min(min, cnt);
				continue;
			}
			if(min<cnt) continue;
			if(posN * A <100001 && (visit[posN * A] > cnt + 1 || visit[posN * A] == 0)) {
				visit[posN * A] = cnt + 1;
				q.offer(new int[] {posN*A,cnt+1});
			}
			if(posN * B<100001 && (visit[posN * B] > cnt + 1 || visit[posN * B] == 0)) {
				visit[posN * B] = cnt + 1;
				q.offer(new int[] {posN*B,cnt+1});
			}
			if(posN+A<100001 && (visit[posN + A] > cnt + 1 || visit[posN + A] == 0)) {
				visit[posN + A] = cnt + 1;
				q.offer(new int[] {posN+A,cnt+1});
			}
			if(posN+B<100001 && (visit[posN + B] > cnt + 1 || visit[posN + B] == 0)) {
				visit[posN + B] = cnt + 1;
				q.offer(new int[] {posN+B,cnt+1});
			}
			if(posN-A > -1 && (visit[posN - A] > cnt + 1 || visit[posN - A] == 0)) {
				visit[posN - A] = cnt + 1;
				q.offer(new int[] {posN-A,cnt+1});
			}
			if(posN-B > -1 && (visit[posN - B] > cnt + 1 || visit[posN - B] == 0)) {
				visit[posN - B] = cnt + 1;
				q.offer(new int[] {posN-B,cnt+1});
			}
			if(posN+1<100001 && (visit[posN+1] > cnt + 1 || visit[posN+1] == 0)) {
				visit[posN+1] = cnt + 1;
				q.offer(new int[] {posN+1,cnt+1});
			}
			if(posN-1 > -1 && (visit[posN-1] > cnt + 1 || visit[posN -1] == 0)) {
				visit[posN-1] = cnt + 1;
				q.offer(new int[] {posN-1,cnt+1});
			}
		}
		
	}
}
