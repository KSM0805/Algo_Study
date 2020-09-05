package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class D4_5643_키순서 {
	private static int result;
	private static int M;
	private static int N;
	private static int[] arr;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = 0;
			N = Integer.parseInt(bf.readLine().trim());
			M = Integer.parseInt(bf.readLine().trim());
			ArrayList<Integer>[] list = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				list[i] = new ArrayList<>();
			}
			StringTokenizer st;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				list[from].add(to);
			}
			arr = new int[N];
			LinkedList<Integer> q = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				boolean[] visit = new boolean[N];
				q.clear();
				q.add(i);
				visit[i] = true;
				while(!q.isEmpty()) {
					int cur = q.poll();
					arr[cur]++;
					arr[i]++;
					for (Integer j : list[cur]) {
						if(!visit[j]) {
							visit[j] = true;
							q.add(j);
						}
					}
				}
			}
//			System.out.println(Arrays.toString(arr));
			for (int i = 0; i < N; i++) {
				if(arr[i] == N+1) result++;
			}
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end
}
