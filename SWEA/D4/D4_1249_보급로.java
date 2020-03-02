package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class D4_1249 {
	private static int result;
	private static int N;
	private static String[] str;
	private static int[][] visit;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim());
			str = new String[N];
			for (int i = 0; i < N; i++) {
				str[i] = bf.readLine().trim();
			}
			visit = new int[N][N];
			visit[0][0] = 1; // 처음에 시작했다는 뜻
			result = Integer.MAX_VALUE;
			bfs();
			System.out.println("#"+test_case+" "+(result-1));
		}//for end
	}

	private static void bfs() {
		LinkedList<int[]> q = new LinkedList<>();
		q.offer(new int[] {0,0,1});
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int r = arr[0];
			int c = arr[1];
			int cnt = arr[2];
			if(result<cnt) continue;
			if(r==N-1 && c==N-1) {
				result = Math.min(result, cnt);
			}else {
				for (int i = 0; i < 4; i++) {
					int nr = r + dir[i][0];
					int nc = c + dir[i][1];
					if(nr>-1 && nc>-1 && nr<N && nc<N && (visit[nr][nc] > (str[nr].charAt(nc)-'0') + cnt || visit[nr][nc]==0)){
						visit[nr][nc] = (str[nr].charAt(nc)-'0') + cnt;
						q.offer(new int[] {nr,nc,(str[nr].charAt(nc)-'0') + cnt});
					}
				}
			}
		}
	}
}
