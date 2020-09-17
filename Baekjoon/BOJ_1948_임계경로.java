package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1948_임계경로 {

	private static int N;
	private static int M;
	private static int[][] order;
	private static int start;
	private static int end;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(bf.readLine().trim());	//1-10000 : 도시의 개수
		M = Integer.parseInt(bf.readLine().trim());	//1-100000 : 도로의 개수
		ArrayList<Edge>[] road = new ArrayList[N+1];
		ArrayList<Integer>[] before = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			road[i] = new ArrayList<>();
			before[i] = new ArrayList<>();
		}
		order = new int[N+1][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine().trim(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			road[from].add(new Edge(to, Integer.parseInt(st.nextToken())));
			order[to][0]++;
		}
		st = new StringTokenizer(bf.readLine().trim()," ");
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		// 처음에 빈거 없애기
		LinkedList<Integer> check = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if(order[i][0] == 0 && start != i) check.add(i);
		}
		while(!check.isEmpty()) {
			int cur = check.poll();
			for (int i = 0; i < road[cur].size(); i++) {
				order[road[cur].get(i).to][0]--;
				if(order[road[cur].get(i).to][0] == 0) check.add(road[cur].get(i).to);
			}
		}
		
		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		q.add(new int[] {start,0});	// [0]: 현재위치, [1]: 걸린 시간
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			// 도착했을 때
			for (int i = 0; i < road[cur[0]].size(); i++) {
				order[road[cur[0]].get(i).to][0]--;
				if(order[road[cur[0]].get(i).to][1] == cur[1] + road[cur[0]].get(i).w) {
					before[road[cur[0]].get(i).to].add(cur[0]);
				} else if(order[road[cur[0]].get(i).to][1] < cur[1] + road[cur[0]].get(i).w){
					order[road[cur[0]].get(i).to][1] = cur[1] + road[cur[0]].get(i).w;
					before[road[cur[0]].get(i).to].clear();
					before[road[cur[0]].get(i).to].add(cur[0]);
				}
				// 마지막으로 도착한거
				if(order[road[cur[0]].get(i).to][0] == 0) {
					q.add(new int[] {road[cur[0]].get(i).to, order[road[cur[0]].get(i).to][1]});
				}
			}
		}
		boolean[] visit = new boolean[N+1];
		check.add(end);
		visit[end] = true;
		int result = 0;
		while(!check.isEmpty()) {
			int cur = check.poll();
			for (int i = 0; i < before[cur].size(); i++) {
				if(!visit[before[cur].get(i)]) {
					visit[before[cur].get(i)] = true;
					check.add(before[cur].get(i));
				}
				result++;
			}
		}
		System.out.println(order[end][1]);
		System.out.println(result);
	}
}
class Edge{
	int to;
	int w;
	public Edge(int to, int w) {
		super();
		this.to = to;
		this.w = w;
	}
}