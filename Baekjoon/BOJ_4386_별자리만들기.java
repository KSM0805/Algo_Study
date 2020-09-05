package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4386_별자리만들기 {

	private static double result;
	private static int N;
	private static boolean[] visit;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0.0;
		N = Integer.parseInt(bf.readLine().trim());
		ArrayList<double[]> stars = new ArrayList<>();
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			stars.add(new double[] {Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())});
		}
		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return Double.compare(o1.dis, o2.dis);
			}
		});
		visit = new boolean[N];
		double[] start = stars.get(0);
		for (int i = 1; i < N; i++) {
			double[] cur = stars.get(i);
			pq.add(new Edge(0, i, Math.sqrt(Math.pow(start[0] - cur[0], 2) + Math.pow(start[1] - cur[1],2))));
		}
		visit[0] = true;
		int cnt = 1;
		while(cnt < N) {
			Edge cur = pq.poll();
			if(visit[cur.v]) continue;
			visit[cur.v] = true;
			result += cur.dis;
			for (int i = 0; i < N; i++) {
				if(cur.v != i) pq.add(new Edge(cur.v, i, Math.sqrt(Math.pow(stars.get(cur.v)[0] - stars.get(i)[0], 2) + Math.pow(stars.get(cur.v)[1] - stars.get(i)[1],2))));
			}
			cnt++;
		}
		System.out.println(result);
	}
}

class Edge {
	int u;
	int v;
	double dis;
	
	public Edge(int u, int v, double dis) {
		super();
		this.u = u;
		this.v = v;
		this.dis = dis;
	}
}