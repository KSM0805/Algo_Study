package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_2814 {
	private static int M;
	private static int N;
	private static int[][] map;
	private static boolean[] visit;
	private static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N+1][N+1];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				int r = Integer.parseInt(st.nextToken()); 
				int c = Integer.parseInt(st.nextToken()); 
				map[r][c] = 1;
				map[c][r] = 1;
			}
			visit = new boolean[N+1];
			max = 0;
			//dfs
			for (int i = 1; i <=N; i++) {
				visit[i] = true;
				dfs(i,1);
				visit[i] = false;
			}
			System.out.println("#"+test_case+" "+max);
		}//for end
	}

	private static void dfs(int pos, int cnt) {
		if(cnt>max) {
			max = cnt;
		}
		for (int i = 1; i <= N; i++) {
			if(map[pos][i]==1 && !visit[i]) {
				visit[i] = true;
				dfs(i,cnt+1);
				visit[i] = false;
			}
		}
		
	}

	
	/*private static void dfs() {
		Stack<int[]> stack = new Stack<>();
		stack.push(new int[] {1,1}); //시작이 반드시 1일 필요는 xx
		while(!stack.isEmpty()) {
			int[] num = stack.pop();
			int pos = num[0];
			int cnt = num[1]+1;
			if(!visit[pos]) {
				visit[pos] = true;
				for (int i = 1; i <= N; i++) {
					if(max<num[1]) {
						max = num[1];
					}
					if(map[pos][i]==1 && !visit[i]) {
						System.out.println(i+" "+cnt);
						stack.push(new int[] {i, cnt});
					}
				}
			}
		}
		
	}*/

}
