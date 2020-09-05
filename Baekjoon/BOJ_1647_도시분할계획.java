package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1647_도시분할계획 {

	private static long result;
	private static int N;
	private static int M;
	private static boolean[] map;
	private static ArrayList<int[]>[] cost;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());	// 2 ~ 10만개 : 집의 갯수
		M = Integer.parseInt(st.nextToken());	// 1 ~ 100만개 : 길의 갯수
		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		cost = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			cost[i] = new ArrayList<int[]>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			int cos = Integer.parseInt(st.nextToken());
			cost[A].add(new int[] {B, cos});
			cost[B].add(new int[] {A, cos});
		}
		map = new boolean[N];	// 연결됐는지 확인할 배열
		int max = -1;			// 가장 가중치가 큰 값 저장
		// 일단 0번째 노드에서 시작하는 간선 저장
		for (int i = 0, end = cost[0].size(); i < end; i++) {
			q.add(new int[] {0, cost[0].get(i)[0], cost[0].get(i)[1]});
		}
		map[0] = true;
		int cnt = 1;
		while(cnt < N ) {
			int[] cur = q.poll();
			if(map[cur[1]]) continue;
			result += cur[2];
			max = Math.max(max, cur[2]);
			map[cur[1]] = true;
			for (int i = 0, end = cost[cur[1]].size(); i < end; i++) {
				q.add(new int[] {cur[1], cost[cur[1]].get(i)[0], cost[cur[1]].get(i)[1]});
			}
			cnt++;
		}
		// 일단 간선의 최소화 -> MST 다시 공부하고 내일 시도하기
		// 유니온셋? No -> 갯수만 카운트 할거야(Prim)
		System.out.println(result - max);
	}
}


