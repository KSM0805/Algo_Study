package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class D4_3124_최소스패닝트리_kru {
	private static int V;
	private static int E;
	private static long result;
	private static int[] parents = new int[100000];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			V = Integer.parseInt(st.nextToken()); //1~100000 정점의 개수
			E = Integer.parseInt(st.nextToken()); //1~200000 간선의 개수
			
			result = 0;
			
			PriorityQueue<Link> q = new PriorityQueue<>(new Comparator<Link>() {
				
				@Override
				public int compare(Link o1, Link o2) {
					return Integer.compare(o1.weight, o2.weight);
				}
			});
			
			int a, b, w;
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				a = Integer.parseInt(st.nextToken())-1;
				b = Integer.parseInt(st.nextToken())-1;
				w = Integer.parseInt(st.nextToken());
				q.add(new Link(a, b, w));
			}
			makeSet();
			int cnt = 1;
			while(cnt < V) {
				Link cur = q.poll();
				if(union(cur.to, cur.from)) {
					cnt++;
					result += cur.weight;
				}
			}
			System.out.println("#"+test_case+" "+result);
		}
	}

	private static boolean union(int i, int j) {
		int rooti = find(i);
		int rootj = find(j);
		if(rooti == rootj) return false;
		parents[rooti] = rootj;
		return true;
	}

	private static int find(int i) {
		if(parents[i] == i) return i;
		parents[i] = find(parents[i]); //메모이제이션
		return parents[i];
	}

	private static void makeSet() {
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}
}
/*class Link{
	int from;
	int to;
	int weight;
	
	public Link(int from, int to, int weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}*/