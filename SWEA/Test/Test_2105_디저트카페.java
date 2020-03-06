package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Test_2105_디저트카페 {
	private static int result;
	private static int N;
	private static int[][] map;
	private static HashSet<Integer> list;
	private static int[][] dir = {{-1,1},{1,1},{1,-1},{-1,-1}};
	private static int max;
	private static boolean[] visit;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		list = new HashSet<>();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			list.clear();
			N = Integer.parseInt(bf.readLine().trim()); //4~20 지역크기
			map = new int[N][N];
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); //1~100
					list.add(map[i][j]);
				}
			}
			max = list.size();
			result = 0;
			//dfs
			visit = new boolean[101];
			for (int i = 1; i < N-1; i++) {
				for (int j = 0; j < N-2; j++) {
					visit[map[i][j]] = true;
					square(i,j,0,0,0); //[0][1]: 현재위치, [2]: 방향, [3]: 1번 변의 길이, [4]: 2번 변의 길이
					visit[map[i][j]] = false;
				}
			}
			if(result == 0) System.out.println("#"+test_case+" -1");
			else	System.out.println("#"+test_case+" "+(result*2));
		}//testcase end
	}//main end

	private static void square(int r, int c, int d, int len1, int len2) {
		if(result*2 == max) return;
		if(d>1 && result > (len1+len2)) return;
		int nr,nc;
		if(d<2) { //완성되기전
			//1. 방향유지
			nr = r+dir[d][0];
			nc = c+dir[d][1];
			if(nr>-1 && nc>-1 && nr<N && nc<N && !visit[map[nr][nc]]) {
				visit[map[nr][nc]] = true;
				if(d==1) square(nr, nc, d, len1, len2+1);
				else square(nr, nc, d, len1+1, len2);
				visit[map[nr][nc]] = false;
			}
			//2. 방향전환
			if((d==0 && len1>0) || (d==1 && len2>0)) {
				nr = r+dir[d+1][0];
				nc = c+dir[d+1][1];
				if(nr>-1 && nc>-1 && nr<N && nc<N && !visit[map[nr][nc]]) {
					visit[map[nr][nc]] = true;
					if(d==1) square(nr, nc, d+1, len1, len2);
					else square(nr, nc, d+1, len1, len2+1);
					visit[map[nr][nc]] = false;
				}
			}
		}else { //사각형 회전
			list.clear();
			boolean possible = true;
			nr = r;
			nc = c;
			for (int i = 2; i < 4; i++) {
				for (int j = 1; j < ((i==2) ? len1 : len2); j++) {
					nr += dir[i][0];
					nc += dir[i][1];
					if(nr<0 || nc<0 || nr>=N || nc>=N || visit[map[nr][nc]]) {
						possible = false;
						break;
					}
					visit[map[nr][nc]] = true;
					list.add(map[nr][nc]);
				}
			}
			if(possible) {
				result = Math.max(result, len1+len2);
			}
			Iterator<Integer> a = list.iterator();
			while(a.hasNext()) {
				visit[a.next()] = false;
			}
		}
	}
}
