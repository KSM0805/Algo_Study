package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1753_최단경로_nonpq {
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
		
		int[] we = new int[V];
		for (int i = 0; i < V; i++) {
			we[i] = Integer.MAX_VALUE;
		}
		we[start] = 0;
		for (int i = 0; i < V; i++) {
			int min = Integer.MAX_VALUE;
			int index = -1;
			for (int j = 0; j < V; j++) {
				if(!visit[j] && min > we[j]) {
					min = we[j];
					index = j;
				}
			}
			if(index == -1) {
				break;
			}
			for (Edge j : list[index]) {
				if(!visit[j.to] && we[index] + j.w < we[j.to]) {
					we[j.to] = we[index] + j.w;
				}
			}
			visit[index] = true;
		}
		for (int edge : we) {
			System.out.println(edge == Integer.MAX_VALUE ? "INF" : edge);
		}
	}
	static class Edge{
		int to;
		int w;
		public Edge(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}
	}
}
