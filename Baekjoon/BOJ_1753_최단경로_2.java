package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753_최단경로_2 {

	private static int V;
	private static int E;
	private static int start;
	private static Edge[] shortest;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		V = Integer.parseInt(st.nextToken());	// 1 ~ 20,000 : 정점의 갯수
		E = Integer.parseInt(st.nextToken());	// 1 ~ 300,000 : 간선의 갯수
		
		start = Integer.parseInt(bf.readLine().trim());
		
		shortest = new Edge[V + 1];	// 최단거리를 담을 배열
		HashMap<Integer, ArrayList<int[]>> hashMap = new HashMap<>();
		for (int i = 0; i < E; i++) {
			// u, v, w : u에서 v로 가는 간선의 가중치 W
			st = new StringTokenizer(bf.readLine().trim()," ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			if(!hashMap.containsKey(u)) {
				hashMap.put(u, new ArrayList<>());
			}
			hashMap.get(u).add(new int[] {v, w});
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			shortest[i] = new Edge(i, Integer.MAX_VALUE);
		}
		shortest[start].w = 0;
		PriorityQueue<Edge> q = new PriorityQueue<>();
		if(hashMap.containsKey(start)) {
			boolean[] visit = new boolean[V + 1];
			for (int i = 1; i <= V; i++) {
				q.add(shortest[i]);
			}
			visit[start] = true;
			while(!q.isEmpty()) {
				Edge cur = q.poll();
				if(cur.w == Integer.MAX_VALUE) break;	// 매우매우 중요! 합 가중치가 최댓값인 것은 연결이 안된 것이므로 더이상 이을 수 없다.
				// 연결된 간선이 있나?
				if(hashMap.containsKey(cur.num)) {
					for (int[] i : hashMap.get(cur.num)) {
						// 방문하지 않았고 간선이 작은거만
						if(!visit[i[0]] && shortest[i[0]].w > shortest[cur.num].w + i[1]) {
							shortest[i[0]].w = shortest[cur.num].w + i[1];
							q.remove(shortest[i[0]]);
							q.add(shortest[i[0]]);
						}
					}
				}
				visit[cur.num] = true;
			}
		}
		for (int i = 1; i <= V; i++) {
			if(shortest[i].w == Integer.MAX_VALUE) sb.append("INF\n");
			else sb.append(shortest[i].w + "\n");
		}
		System.out.println(sb);
	}
}
class Edge implements Comparable<Edge>{
	int num;
	int w;
	public Edge(int num, int w) {
		super();
		this.num = num;
		this.w = w;
	}
	@Override
	public int compareTo(Edge o) {
		return this.w - o.w;
	}
}