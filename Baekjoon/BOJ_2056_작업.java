package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_2056_작업 {

	private static int result;
	private static int N;
	private static ArrayList<Integer>[] task;
	private static int[] arr;
	private static int[] cost;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		N = Integer.parseInt(bf.readLine().trim());	// 3 ~ 10000 : 작업 갯수 
		task = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			task[i] = new ArrayList<Integer>();
		}
		cost = new int[N];	// 각 작업에 걸리는 시간
		arr = new int[N];	// 위상정렬할 배열로 선수 작업이 몇 개 인지 체크할 배열
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			cost[i] = Integer.parseInt(st.nextToken());
			for (int j = 0, end = Integer.parseInt(st.nextToken()); j < end; j++) {
				int before = Integer.parseInt(st.nextToken()) - 1;
				task[before].add(i);
				arr[i]++;
			}
		}
		int cnt = 0;
		LinkedList<Integer> clear = new LinkedList<>();
		while(cnt < N) {	// 작업할 태스크 만큼
			for (int i = 0; i < N; i++) {
				if(arr[i] == 0) {	// 작업이 가능하다면
					arr[i] = -1;
					clear.add(i);
				}
			}
			for (int i = 0, end = clear.size(); i < end; i++) {
				int cur = clear.poll();
				cost[cur]--;
				// 작업이 완료되면
				if(cost[cur] == 0) {
//					System.out.println(" 완료된 작업 : " + cur + " 현재 시간 : " + result);
					cnt++;
					for (int j = 0; j < task[cur].size(); j++) {
						arr[task[cur].get(j)]--;
					}
					// 작업이 완료되지 않았으면
				} else clear.add(cur);
			}
			// 클리어한거 선수과정인거 계산
//			while(!clear.isEmpty()) {
//				int cur = clear.poll();
//				for (int i = 0; i < task[cur].size(); i++) {
//					arr[task[cur].get(i)]--;
//				}
//			}
			result++;
		}
		System.out.println(result);
	}
}
