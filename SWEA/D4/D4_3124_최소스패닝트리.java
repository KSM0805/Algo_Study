package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class D4_3124_최소스패닝트리 {
	private static int V;
	private static int E;
	private static long result;
	private static boolean[] visit = new boolean[100000];
	private static ArrayList<ArrayList<Link>> list = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int i = 0; i < 100000; i++) {
			list.add(new ArrayList<Link>());
		}
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			V = Integer.parseInt(st.nextToken()); //1~100000 정점의 개수
			E = Integer.parseInt(st.nextToken()); //1~200000 간선의 개수
			
			result = 0;
			for (int i = 0; i < V; i++) {
				list.get(i).clear();
			}
			int a, b, w;
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				a = Integer.parseInt(st.nextToken())-1;
				b = Integer.parseInt(st.nextToken())-1;
				w = Integer.parseInt(st.nextToken());
				list.get(a).add(new Link(a, b, w));
				list.get(b).add(new Link(b, a, w));
			}
			
			Arrays.fill(visit, false);
			PriorityQueue<Link> q = new PriorityQueue<>(new Comparator<Link>() {

				@Override
				public int compare(Link o1, Link o2) {
					return Integer.compare(o1.weight, o2.weight);
				}
			});
			
			q.addAll(list.get(0));
			visit[0] = true;
			int cnt = 1;
			while(cnt < V) {
				Link cur = q.poll();
				if(visit[cur.to]) continue;
				result += cur.weight;
				visit[cur.to] = true;
				cnt++;
				ArrayList<Link> pos = list.get(cur.to); 
				for (int i = 0, size = pos.size(); i < size; i++) {
					if(!visit[pos.get(i).to]) q.offer(pos.get(i));
				}
			}
			System.out.println("#"+test_case+" "+result);
		}
	}
}
class Link{
	int from;
	int to;
	int weight;
	
	public Link(int from, int to, int weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}