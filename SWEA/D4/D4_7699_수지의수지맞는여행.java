package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class D4_7699_수지의수지맞는여행 {
	private static int result;
	private static int C;
	private static int R;
	private static char[][] map;
	private static boolean[][] visit;
	private static HashSet<Character> list;
	private static int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			R = Integer.parseInt(st.nextToken()); //1~20
			C = Integer.parseInt(st.nextToken()); //1~20
			map = new char[R][C];
			String str;
			for (int i = 0; i < R; i++) {
				str = bf.readLine();
				map[i] = str.toCharArray();
			}
			result = 0;
			visit = new boolean[R][C];
			visit[0][0] = true;
			list = new HashSet<>();
			list.add(map[0][0]);
			dfs(0,0,1);
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end

	private static void dfs(int r, int c, int cnt) {
		if(result < cnt) {
			result = cnt;
		}
		for (int i = 0; i < 4; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			if(nr>-1 && nc>-1 && nr<R && nc<C && !list.contains(map[nr][nc]) && !visit[nr][nc]) {
				visit[nr][nc] = true;
				list.add(map[nr][nc]);
				dfs(nr,nc,cnt+1);
				visit[nr][nc] = false;
				list.remove(map[nr][nc]);
			}
		}
	}
}
