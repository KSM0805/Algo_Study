	package boj;
	
	import java.io.BufferedReader;
	import java.io.FileInputStream;
	import java.io.InputStreamReader;
	import java.util.Comparator;
	import java.util.PriorityQueue;
	import java.util.StringTokenizer;
	
	public class BOJ_1197_최소스패닝트리_kru {
		private static int[] parents;
		private static int V;
		private static int E;
	
		public static void main(String[] args) throws Exception{
			System.setIn(new FileInputStream("Input.txt"));
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			V = Integer.parseInt(st.nextToken()); //1~10000 정점의 개수
			E = Integer.parseInt(st.nextToken()); //1~100000 간선의 개수
			
			int result = 0;
			
			PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
				
				@Override
				public int compare(int[] o1, int[] o2) {
					return Integer.compare(o1[2], o2[2]);
				}
			});
			
			int a, b, w;
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				a = Integer.parseInt(st.nextToken())-1;
				b = Integer.parseInt(st.nextToken())-1;
				w = Integer.parseInt(st.nextToken());
				q.add(new int[] {a, b, w});
				q.add(new int[] {b, a, w});
			}
			makeSet();
			int cnt = 1;
			while(cnt < V) {
				int[] cur = q.poll();
				if(union(cur[0], cur[1])) {
					cnt++;
					result += cur[2];
				}
			}
			System.out.println(result);
		}
	
		private static void makeSet() {
			parents = new int[V];
			for (int i = 0; i < V; i++) {
				parents[i] = i;
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
			parents[i] = find(parents[i]);
			return parents[i];
		}
		
	}
