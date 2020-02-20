package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_1863 {
	private static int[] map;

	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new int[n+1]; // 1번 부터 n번 까지
		makeSet();
		int cnt = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			link(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		for (int i = 1; i <= n; i++) {
			if(map[i]<0) cnt++;
		}
		System.out.println(cnt);
	}

	private static void link(int num1, int num2) {
		int u = findRoot(num1);
		int v = findRoot(num2);
		int root1 = map[u];
		int root2 = map[v];
		if(u == v) return;
		if(root1 < root2) { //root1이 더 깊다.
			map[u] += map[v];
			map[v] = u;
		}else {
			map[v] += map[u];
			map[u] = v;
		}
	}

	private static int findRoot(int a) {
		if(map[a] < 0) return a;
		else return findRoot(map[a]);
	}

	private static void makeSet() {
		for (int i = 0; i < map.length; i++) {
			map[i] = -1;
		}
	}
}