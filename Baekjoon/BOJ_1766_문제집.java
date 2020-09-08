package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1766_문제집 {

	private static int N;
	private static int M;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());	// 1-32000 : 문제의 갯수
		M = Integer.parseInt(st.nextToken());	// 1-100000 : 선 문제
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		
		int[] count = new int[N+1];	// 먼저 풀어야하는 문제의 갯수
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			int a = Integer.parseInt(st.nextToken());	// a가 b보다 먼저 풀어야 한다.
			int b = Integer.parseInt(st.nextToken());
			if(!map.containsKey(a)) map.put(a, new ArrayList<Integer>());
			map.get(a).add(b);
			count[b]++;
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int cnt = 0;
		while(cnt < N) {
			for (int i = 1; i <= N; i++) {
				if(count[i] == 0) {
					count[i] = -1;
					sb.append(i + " ");
					cnt++;
					if(map.containsKey(i)) {
						for (Integer integer : map.get(i)) {
							count[integer]--;
						}
						i = 0;
					}
				}
			}
		}
		System.out.println(sb.toString());
	}
}
