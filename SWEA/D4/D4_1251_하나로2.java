package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class D4_1251_하나로2 {
	private static int N;
	private static int[][] island;
	private static double E;
	private static boolean[] visit;
	private static double min;
	private static double[][] mem;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim()); //1~1000
			island = new int[N][2]; //0~1,000,000
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");;
			for (int i = 0; i < N; i++) {
				island[i][0] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(bf.readLine().trim()," ");;
			for (int i = 0; i < N; i++) {
				island[i][1] = Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(bf.readLine().trim());
			visit = new boolean[N];
			min = 0.0;
			//bfs
			visit[0] = true;
			int cnt = 1;
			PriorityQueue<bridge> q = new PriorityQueue<>(new Comparator<bridge>() {

				@Override
				public int compare(bridge o1, bridge o2) {
					return (int) (o1.dis-o2.dis);
				}
			});
			for (int i = 1; i < N; i++) {
				double d = Math.pow(Math.abs(island[0][0]-island[i][0]),2) + Math.pow(Math.abs(island[0][1]-island[i][1]),2);
				q.offer(new bridge(0, i, d));
			}
			while(!q.isEmpty()) {
				bridge cur = q.poll();
				if(visit[cur.endIs]) continue;
				cnt++;
				min += cur.dis;
				visit[cur.endIs] = true;
				if(cnt==N) break;
				for (int i = 0; i < N; i++) {
					if(!visit[i]) {
						double d = Math.pow(Math.abs(island[cur.endIs][0]-island[i][0]),2) + Math.pow(Math.abs(island[cur.endIs][1]-island[i][1]),2);
						q.offer(new bridge(cur.endIs, i, d));
					}
				}
			}
			min *= E;
			min += 0.5;
			System.out.println("#"+test_case+" " + (long)min);
		}//testcase end
	}//main end
}

class bridge{
	int startIs;
	int endIs;
	double dis;
	public bridge(int startIs, int endIs, double dis) {
		super();
		this.startIs = startIs;
		this.endIs = endIs;
		this.dis = dis;
	}
}
