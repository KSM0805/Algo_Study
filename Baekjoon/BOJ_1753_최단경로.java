package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753_최단경로 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		int V = Integer.parseInt(st.nextToken()); // 1~20000 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 1~300000 간선의 개수
		int start = Integer.parseInt(bf.readLine().trim()) - 1; // 시작 정점
		
		ArrayList<Edge>[] list = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			list[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			list[Integer.parseInt(st.nextToken())-1].add(new Edge(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())));
		}
		boolean[] visit = new boolean[V];
		PriorityQueue<Edge> q = new PriorityQueue<>();
		
		Edge[] we = new Edge[V];
		for (int i = 0; i < V; i++) {
			we[i] = new Edge(i, Integer.MAX_VALUE);
			q.add(we[i]);
		}
		q.remove(we[start]);
		we[start].w = 0;
		q.add(we[start]);
		visit[start] = true;
		while(!q.isEmpty()) {
			Edge cur = q.poll();
			if(cur.w == Integer.MAX_VALUE) break;
			// 현재 노드의 간선 탐색
			for (Edge next : list[cur.to]) {
									// 현재 정해져 있는 경로의 가중치가 지금 연결 할 수 있는 가중치보다 크다면
				if(!visit[next.to] && we[next.to].w > we[cur.to].w + next.w ) {
					we[next.to].w = we[cur.to].w + next.w;
					q.remove(we[next.to]);
					q.add(we[next.to]);
				}
			}
			visit[cur.to] = true;
		}
		for (Edge edge : we) {
			System.out.println(edge.w == Integer.MAX_VALUE ? "INF" : edge.w);
		}
	}
	static class Edge implements Comparable<Edge>{
		int to;
		int w;
		public Edge(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
		
	}
}
