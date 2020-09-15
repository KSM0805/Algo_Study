package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1504_특정한최단경로 {

	private static int result;
	private static int N;
	private static int E;
	private static int V1;
	private static int V2;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = Integer.MAX_VALUE;
		// 1번 정점에서 -> N번 정점
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());	// 2 - 800 : 정점의 개수
		E = Integer.parseInt(st.nextToken());	// 0 - 200,000 : 간선의 개수
		ArrayList<Edge>[] edge = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			edge[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edge[a].add(new Edge(b, c));
			edge[b].add(new Edge(a, c));	// 양방향 길이므로
		}
		st = new StringTokenizer(bf.readLine().trim()," ");
		V1 = Integer.parseInt(st.nextToken());
		V2 = Integer.parseInt(st.nextToken());
		Edge[][] dis = new Edge[3][N+1];	// 양방향으로 반대도 할수있다. [0]: 1번 정점에서 시작, [1]: 중간 정점에서 시작, [2]: 끝점에서 시작
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j <= N; j++) {
				dis[i][j] = new Edge(j, 800001);
			}
		}
		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.w, o2.w);
			}
		});
		boolean[] visit = new boolean[N+1];
		for (int i = 0; i < 3; i++) {
			pq.clear();
			Arrays.fill(visit, false);
			// 나의 정점은 0이다.
			dis[i][i == 0 ? 1 : i == 1 ? V1 : N].w = 0;
			visit[i == 0 ? 1 : i == 1 ? V1 : N] = true;
			for (int j = 1; j <= N; j++) {
				pq.add(dis[i][j]);
			}
			while(!pq.isEmpty()) {
				Edge cur = pq.poll();
				if(cur.w == 800001) break;
				if(visit[V1] && visit[V2]) break;
				for (int j = 0; j < edge[cur.to].size(); j++) {
					Edge next = edge[cur.to].get(j);
					if(!visit[next.to] && dis[i][next.to].w > dis[i][cur.to].w + next.w) {
						dis[i][next.to].w = dis[i][cur.to].w + next.w;
						pq.remove(dis[i][next.to]);
						pq.add(dis[i][next.to]);
					}
				}
				visit[cur.to] = true;
			}
		}
		// 1 -> V1 -> V2 -> N
		result = Math.min(dis[0][V1].w + dis[1][V2].w + dis[2][V2].w, dis[0][V2].w + dis[1][V2].w + dis[2][V1].w);
		// 혹은 1 -> V2 -> V1 -> N
		System.out.println(result >= 800001 ? "-1" : result);
	}
}
class Edge{
	int to;
	int w;
	public Edge(int to, int w) {
		super();
		this.to = to;
		this.w = w;
	}
}
