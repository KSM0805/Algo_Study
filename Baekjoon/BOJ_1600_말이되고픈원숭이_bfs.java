package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600_bfs {
	private static int K;
	private static int W;
	private static int H;
	private static int[][] m;
	private static int minMoveCnt;
	private static boolean[][][] visit;
	static int[] dc = {-2,-1,1,2,2,1,-1,-2,0,0,-1,1};
	static int[] dr = {-1,-2,-2,-1,1,2,2,1,-1,1,0,0};//말이동, 상하좌우

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(bf.readLine().trim());
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		m = new int[H][W];
		for (int i = 0; i < H; i++) {
			String str = bf.readLine().trim();
			for (int j = 0; j < W; j++) {
				m[i][j] = str.charAt(j<<1)-'0';
			}
		}
		minMoveCnt = Integer.MAX_VALUE;
		visit = new boolean [H][W][K+1];//방문 배열, 남은 이동횟수
		visit[0][0][K] = true;
		
		//bfs
		Queue<int[]> q = new LinkedList<>(); // 뒤에 int[] q의 타입을 생략해도 가능
		//{r,c,k(말처럼 이동할 수 있는 횟수), moveCnt};
//		int[] a = {0,0,K,0};
		q.offer(new int[] {0,0,K,0});
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int r = arr[0];
			int c = arr[1];
			int kk = arr[2];
			int moveCnt = arr[3];
			
			if(r== H-1 && c == W-1) { // 우측 하단 도착시 끝
				minMoveCnt = Math.min(minMoveCnt, moveCnt);
				break;
			}
			//상하좌우
			for (int i = 8; i < 12; i++) {
				int nr = r + dr[i];
				int nc = c+ dc[i];
				if(nr>=0 && nc>=0 && nr<H && nc<W && !visit[nr][nc][kk] && m[nr][nc]==0) {
					visit[nr][nc][kk] = true;
					q.offer(new int[] {nr,nc,kk,moveCnt+1});
				}
			}
			//말처럼 이동
			if(kk == 0) continue; //말처럼 이동할 수 없으면 다음 차수로 넘어가라
			for (int i = 0; i < 8; i++) {
				int nr = r + dr[i];
				int nc = c+ dc[i];
				if(nr>=0 && nc>=0 && nr<H && nc<W && !visit[nr][nc][kk-1] && m[nr][nc]==0) {
					visit[nr][nc][kk-1] = true;
					q.offer(new int[] {nr,nc,kk-1,moveCnt+1});
				}
			}
		}//while end
		System.out.println(minMoveCnt);
	
	}


	 /**
	 * 
	 * @param r 행 좌표
	 * @param c 열 좌표
	 * @param k 말처럼 이동할 수 있는 남은 횟수
	 * @param moveCnt 현재까지 이동한 횟수
	 */
	/*static int[] dr = {-1,-2,-2,-1,1,2,2,1,-1,1,0,0};//말이동, 상하좌우
	static int[] dc = {-2,-1,1,2,2,1,-1,-2,0,0,-1,1};
	private static void dfs(int r, int c, int k, int moveCnt) {
		//모두 변하는 숫자! 변하지 않는 숫자는 매개변수로 지정할 필요가 없다.
		if(r==H-1 && c == W-1) { //종료파트 , 우측 하단 도착
			minMoveCnt = Math.min(minMoveCnt, moveCnt);
			// 안의 내용을 종료파트 조건에 넣으면 종료가 되지않는다.
			return;
		}
		if(minMoveCnt<moveCnt) return;
		//재귀파트
		//말처럼 이동
		if(k>0) {
			for (int i = 0; i < 8; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				//배열의 범위 체크, 방문 정점, 벽인지 체크
				if(nr>-1 && nc>-1 && nr<H && nc<W && 
						visit[nr][nc] == 0 && m[nr][nc]==0) {
					visit[nr][nc] = 1;
					dfs(nr,nc,k-1,moveCnt+1);
					visit[nr][nc] = 0;
				}
			}
		}
		//상하좌우
		for (int i = 8; i < 12; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			//배열의 범위 체크, 방문 정점, 벽인지 체크
			if(nr>-1 && nc>-1 && nr<H && nc<W && 
					visit[nr][nc] == 0 && m[nr][nc]==0) {
				visit[nr][nc] = 1;
				dfs(nr,nc,k,moveCnt+1);
				visit[nr][nc] = 0;
			}
		}
	}*/
}
