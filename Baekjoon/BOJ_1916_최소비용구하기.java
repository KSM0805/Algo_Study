package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1916_최소비용구하기 {

	private static int N;
	private static int M;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim());	// 1 ~ 1000 : 도시의 개수
		M = Integer.parseInt(bf.readLine().trim());	// 1 ~ 100000 : 버스의 개수
		ArrayList<Bus>[] list = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[from].add(new Bus(to, w));
		}
		st = new StringTokenizer(bf.readLine().trim()," ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		Bus[] cost = new Bus[N+1];
		for (int i = 0; i <= N; i++) {
			cost[i] = new Bus(i, Integer.MAX_VALUE);
		}
		cost[start].w = 0;
		PriorityQueue<Bus> pq = new PriorityQueue<>(new Comparator<Bus>() {

			@Override
			public int compare(Bus o1, Bus o2) {
				return Integer.compare(o1.w, o2.w);
			}
		});
		boolean[] visit = new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			pq.add(cost[i]);
		}
		while(!pq.isEmpty()) {
			Bus cur = pq.poll();
			if(cur.to == end || cur.w == Integer.MAX_VALUE) break;
			for (int i = 0; i < list[cur.to].size(); i++) {
				Bus next = list[cur.to].get(i);
				if(!visit[next.to] && cost[next.to].w > cost[cur.to].w + next.w) {
					cost[next.to].w = cost[cur.to].w + next.w;
					pq.remove(cost[next.to]);
					pq.add(cost[next.to]);
				}
			}
			visit[cur.to] = true;
		}
		System.out.println(cost[end].w);
	}
}

class Bus{
	int to;
	int w;
	public Bus(int to, int w) {
		this.to = to;
		this.w = w;
	}
}