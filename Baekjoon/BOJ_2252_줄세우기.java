package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2252_줄세우기 {
	private static int N;
	private static int M;
	private static boolean[] visit;

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ArrayList<HashSet<Integer>> list = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list.add(new HashSet<>());
		}
		int[] enter = new int[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if(!list.get(from).contains(to)) {
				list.get(from).add(to);
				enter[to]++;
			}
		}
		StringBuilder sb = new StringBuilder();
		visit = new boolean[N + 1];
		visit[0] = true;
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if(!visit[i] && enter[i] == 0) {
				visit[i] = true;
				q.add(i);
			}
		}
		while(!q.isEmpty()) {
			int target = q.poll();
			for (Integer integer : list.get(target)) {
				enter[integer]--;
				if(enter[integer] == 0) {
					q.add(integer);
				}
			}
			sb.append(target + " ");
		}
		System.out.println(sb.toString());
	}
}
