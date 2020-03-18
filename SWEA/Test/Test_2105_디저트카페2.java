package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Test_2105_디저트카페2 {
	private static int result;
	private static int N;
	private static int[][] map;
	private static int[][] dir = {{-1,1},{1,1},{1,-1},{-1,-1}};
	private static boolean[] visit;
	private static int sc;
	private static int sr;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim()); //4~20 지역크기
			map = new int[N][N];
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); //1~100
				}
			}
			result = 0;
			//dfs
			visit = new boolean[101];
			for (int i = 1; i < N-1; i++) {
				for (int j = 0; j < N-2; j++) {
					sr = i;
					sc = j;
					dfs(i,j,0,1);
				}
			}
			System.out.println("#"+test_case+" "+(result==0 ? "-1" : result));
		}//testcase end
	}//main end

	private static void dfs(int r, int c, int d, int cnt) {
		visit[map[r][c]] = true;
		for (int i = d; i < Math.min(d+2,4); i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			if(nr==sr && nc==sc && i==3) {
				result = Math.max(result, cnt);
				break;
			}
			if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
			if(!visit[map[nr][nc]]) dfs(nr,nc,i,cnt+1);
		}
		visit[map[r][c]] = false;
	}
}
