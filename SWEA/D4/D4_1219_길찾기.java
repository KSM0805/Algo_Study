package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class D4_1219 {
	private static int result;
	private static int N;
	private static int[][] num;
	private static boolean[] visit;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = 0;
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			st.nextToken();
			N = Integer.parseInt(st.nextToken());
			
			num = new int[100][2];
			for (int i = 0; i < 100; i++) {
				Arrays.fill(num[i], -1);
			}
			st = new StringTokenizer(bf.readLine().trim()," ");
			int a,b;
			for (int i = 0; i < N; i++) {
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				if(num[a][0] == -1) {
					num[a][0] = b;
				}else {
					num[a][1] = b;
				}
			}
			//bfs
			LinkedList<Integer> q = new LinkedList<>();
			q.offer(0); // 0이 시작 위치
			visit = new boolean[100];
			visit[0] = true;
			while(!q.isEmpty()) {
				int pos = q.poll();
				if(pos == 99) {
					result++;
					continue;
				}
				for (int i = 0; i < 2; i++) {
					if(num[pos][i] != -1 && !visit[num[pos][i]]) {
						visit[num[pos][i]] = true;
						q.offer(num[pos][i]);
					}
				}
			}
			System.out.println("#"+test_case+" "+result);
		}//for end
	}
}
