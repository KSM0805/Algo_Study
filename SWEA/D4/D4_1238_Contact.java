package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class D4_1238_Contact {
	private static int result;
	private static int numMax;
	private static int N;
	private static int start;
	private static HashSet<Integer> visit = new HashSet<>();
	private static boolean[][] link = new boolean[100][100];

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken()) - 1;
			st = new StringTokenizer(bf.readLine().trim()," ");
			int from = -1;
			int to = -1;
			for (boolean[] b : link) {
				Arrays.fill(b, false);
			}
			visit.clear();
			for (int i = 0; i < N; i+=2) {
				from = Integer.parseInt(st.nextToken()) - 1;
				to = Integer.parseInt(st.nextToken()) - 1;
				link[from][to] = true;
			}
			result = 0;
			LinkedList<int[]> q = new LinkedList<>();
			q.offer(new int[] {start, 0}); // [0]: 현재 위치, [1]: 시간
			visit.add(start);
			
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				if(result < cur[1]) {
					result = cur[1];
					numMax = cur[0];
				}else if( result == cur[1]) {
					numMax = Math.max(numMax, cur[0]);
				}
				for (int i = 0; i < 100; i++) {
					if(link[cur[0]][i] && !visit.contains(i)) {
						visit.add(i);
						q.offer(new int[] {i, cur[1] + 1});
					}
				}
			}
			System.out.println("#"+test_case+" "+(numMax+1));
		}//testcase end
	}//main end
}
