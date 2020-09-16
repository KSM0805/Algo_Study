package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_9370_미확인도착지 {

	private static Edge[] dis;
	private static boolean[] visit;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		StringTokenizer st;
		ArrayList<Edge>[] list;
		PriorityQueue<Integer> target = new PriorityQueue<>();
		boolean[] answer;
		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.w, o2.w);
			}
		});
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			int n = Integer.parseInt(st.nextToken());	//2 - 2000 : 교착지개수
			int m = Integer.parseInt(st.nextToken());	//1 - 50,000 : 도로의 개수
			int t = Integer.parseInt(st.nextToken());	//1 - 100 : 목적지 후보의 개수
			
			st = new StringTokenizer(bf.readLine().trim()," ");
			int s = Integer.parseInt(st.nextToken());	//예술가들의 출발지
			int g = Integer.parseInt(st.nextToken());	//목표 교차로
			int h = Integer.parseInt(st.nextToken());	//목표 교차로
			
			list = new ArrayList[n+1];
			for (int j = 0; j <= n; j++) {
				list[j] = new ArrayList<>();
			}
			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				list[a].add(new Edge(b, d));
				list[b].add(new Edge(a, d));
			}
			for (int j = 0; j < t; j++) {
				target.add(Integer.parseInt(bf.readLine().trim()));
			}
			// 최단정점
			dis = new Edge[n+1];
			for (int j = 0; j <= n; j++) {
				dis[j] = new Edge(j, 2000001);	// 최대값 2000 * 1000
			}
			visit = new boolean[n+1];
			answer = new boolean[n+1];
			dis[s].w = 0;
			for (int j = 1; j <= n; j++) {
				pq.add(dis[j]);
			}
			while(!pq.isEmpty()) {
				Edge cur = pq.poll();
				if(cur.w > 2000000) break;
				for (int j = 0; j < list[cur.to].size(); j++) {
					Edge next = list[cur.to].get(j);
					if(!visit[next.to] && dis[next.to].w >= dis[cur.to].w + next.w) {
						if((next.to == g || next.to == h) && (cur.to == g || cur.to == h)) {
							answer[next.to] = true;
							// 목표 교차로를 지난 루트라면
						} else if(answer[cur.to]) {
							answer[next.to] = true;
							// 더 짧은 최단 경우의 수가 들어오면 얘는 false
						} else if(dis[next.to].w > dis[cur.to].w + next.w) answer[next.to] = false;
						dis[next.to].w = dis[cur.to].w + next.w;
						pq.remove(dis[next.to]);
						pq.add(dis[next.to]);
					}
				}
				visit[cur.to] = true;
			}
			while(!target.isEmpty()) {
				int cur = target.poll();
				if(answer[cur]) System.out.print(cur + " ");
			}
			System.out.println();
		}
	}
}
class Edge {
	int to;
	int w;
	public Edge(int to, int w) {
		super();
		this.to = to;
		this.w = w;
	}
}