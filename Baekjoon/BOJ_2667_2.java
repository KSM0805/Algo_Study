package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_2667_2 {
	private static int N;
	private static int[][] MAP;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim());
		MAP = new int[N][N];
		int no = 1;
		
		for (int i = 0; i < N; i++) {
			String str = bf.readLine().trim();
			for (int j = 0; j < N; j++) {
//				MAP[i][j] = Character.getNumericValue(str.charAt(j));
				MAP[i][j] = str.charAt(j)-'0';
			}
		}
		LinkedList<int[]> q = new LinkedList<>();
		int dir[][] = {{-1,0},{1,0},{0,1},{0,-1}};
//		배열을 탐색하면서 0이 아닌 아파트를 찾고, 아파트를 찾았으면 인접 아파트를 찾기 위해 BFS로 검색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(MAP[i][j] == 1 ) { //아파트이면서 방문하지 않은 곳
					MAP[i][j] = ++no;
					//bfs코드
					q.offer(new int[] {i,j});
					while(!q.isEmpty()) {
						int[] node = q.poll();
						int r = node[0];
						int c = node[1];
						for (int k = 0; k < 4; k++) { // 상하좌우에 인접된 아파트 면서 방문안한 아파트를 탐색
							int nr = r + dir[k][0];
							int nc = c + dir[k][1];
							// 경계 안에 있으면서 상하좌우에 인접된 아파트
							if(nr > -1 && nr<N && nc>-1 && nc<N && MAP[nr][nc] == 1 ) {
								MAP[nr][nc] = no;
								q.offer(new int[] {nr,nc});
							}
						}
					}//bfs끗
				}
			}
		}
		System.out.println(--no);
		
		int[] count = new int[no+1]; //0번 안쓰고 사용
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(MAP[i][j] > 1) {
					count[MAP[i][j]-1]++;
				}
			}
			
		}
		Arrays.sort(count);
		for (int i = 1; i < count.length; i++) {
			System.out.println(count[i]);
		}
	}
}
