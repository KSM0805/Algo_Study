package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class D4_7699_수지의수지맞는여행_2 {
	private static int result;
	private static int C;
	private static int R;
	private static int[][] map;
	private static int[] visit;
	private static int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};
	static int v;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			R = Integer.parseInt(st.nextToken()); //1~20
			C = Integer.parseInt(st.nextToken()); //1~20
			map = new int[R][C];
			String str;
			for (int i = 0; i < R; i++) {
				str = bf.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = str.charAt(j)-'A';
				}
			}
			result = 0;
//			visit = new int[26 + 1];
//			visit[map[0][0]] = 1;
			v = 1<<map[0][0];
			dfs(0,0,1);
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end

	private static void dfs(int r, int c, int cnt) {
		if(result < cnt) {
			result = cnt;
		}
		if(cnt==26) return;
		for (int i = 0; i < 4; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			if(nr>-1 && nc>-1 && nr<R && nc<C && (1<<map[nr][nc] & v) == 0) {
//			if(nr>-1 && nc>-1 && nr<R && nc<C && visit[map[nr][nc]]==0) {
//				visit[map[nr][nc]] = 1;
				v = v | 1<<map[nr][nc];
				dfs(nr,nc,cnt+1);
				v = v ^ 1<<map[nr][nc];
//				visit[map[nr][nc]] = 0;
				
			}
		}
	}
}
