package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_2206_벽부수고이동하기 {
	private static int result;
	private static int M;
	private static int N;
	private static int[][][] visit;
	private static char[][] map;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); //1~1000
		map = new char[N][M];
		String str;
		for (int i = 0; i < N; i++) {
			str = bf.readLine().trim();
			map[i] = str.toCharArray();
		}
		result = Integer.MAX_VALUE;
		visit = new int[N][M][2]; //0: 벽을 부수지 않았을때, 1: 벽을 부쉈을때
		LinkedList<int[]> q = new LinkedList<>();
		q.offer(new int[] {0,0,0,1}); //행위치, 열위치, 0:벽을 부수지않음 / 1:벽을부숨 , 움직인 거리
		visit[0][0][0] = 1;
		visit[0][0][1] = 1;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cnt = cur[3]+1;
			if(result < cur[3]) continue;
			if(cur[0]==N-1 && cur[1]==M-1) {
				result = cur[3];
				continue;
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dir[i][0];
				int nc = cur[1] + dir[i][1];
				if(nr<0 || nc<0 || nr>N-1 || nc>M-1) continue;
				if(visit[nr][nc][cur[2]]==0 || visit[nr][nc][cur[2]] > cnt) {
					if(map[nr][nc] == '1' && cur[2]==0) { //벽을 부술 수 있다.
						visit[nr][nc][cur[2]] = cnt;
						q.offer(new int[] {nr,nc,1,cnt});
					}else if(map[nr][nc] == '0') {
						visit[nr][nc][cur[2]] = cnt;
						q.offer(new int[] {nr,nc,cur[2],cnt});
					}
				}
			}
		}
		if(result == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(result);
	}
}
