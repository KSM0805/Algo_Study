package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1197_최소스패닝트리 {
	private static boolean[] visit;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		int V = Integer.parseInt(st.nextToken()); //1~10000 정점의 개수
		int E = Integer.parseInt(st.nextToken()); //1~100000 간선의 개수
		
		int result = 0;
		ArrayList<ArrayList<int[]>> list = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			list.add(new ArrayList<int[]>());
		}
		int a, b, w;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			a = Integer.parseInt(st.nextToken())-1;
			b = Integer.parseInt(st.nextToken())-1;
			w = Integer.parseInt(st.nextToken());
			list.get(a).add(new int[] {b, w});
			list.get(b).add(new int[] {a, w});
		}
		
		visit = new boolean[V];
		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
			
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		for (int i = 0, size = list.get(0).size(); i < size; i++) {
			q.offer(new int[] {0, list.get(0).get(i)[0], list.get(0).get(i)[1]});
		}
		visit[0] = true;
		int cnt = 1;
		while(cnt < V) {
			int[] cur = q.poll();
			if(visit[cur[1]]) continue;
			result += cur[2];
			visit[cur[1]] = true;
			cnt++;
			for (int i = 0, size = list.get(cur[1]).size(); i < size; i++) {
				q.offer(new int[] {cur[1], list.get(cur[1]).get(i)[0], list.get(cur[1]).get(i)[1]});
			}
		}
		System.out.println(result);
	}
}
