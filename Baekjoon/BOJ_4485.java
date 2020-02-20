package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_4485 {
	private static int N;
	private static String[] str;
	private static int[][] visit;
	private static int num;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		num = 1;
		while (true) {
			N = Integer.parseInt(bf.readLine().trim());
			if(N==0) break;
			str = new String[N];
			for (int i = 0; i < N; i++) {
				str[i] = bf.readLine().trim();
			}
			//bfs
			int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
			visit = new int[N][N];
			visit[N-1][N-1] = Integer.MAX_VALUE;
			PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[2]<o2[2]) return -1;
					else if(o1[2] == o2[2]) return 0;
					return 1;
				}

			});
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visit[i][j] = Integer.MAX_VALUE;
				}
			}
			q.offer(new int[] {0,0,str[0].charAt(0)-'0'}); // 0,0 에서 시작하고 0,0의 위치의 루피를 잃는다.
			visit[0][0] = str[0].charAt(0)-'0';
			while(!q.isEmpty()) {
				int[] arr = q.poll();
				int r = arr[0]; // 행
				int c = arr[1]; // 열
				int cnt = arr[2]; // 잃은 루피
				if(visit[N-1][N-1]<=cnt) continue; // 최솟값보다 루피를 더많이 잃으면 종료
				for (int i = 0; i < 4; i++) { //상 하 좌 우
					int nr = r + dir[i][0];
					int nc = c + dir[i][1];
					if(nr<0 || nc<0 || nr>=N || nc>=N) continue; //경계를 벗어나면 종료
					else {
						if(visit[nr][nc] > cnt + str[nr].charAt(nc<<1)-'0') { //처음 왔거나 잃은 루피수가 더 적을 때만
							visit[nr][nc] = cnt + str[nr].charAt(nc<<1)-'0';
							q.offer(new int[] {nr,nc,cnt+(str[nr].charAt(nc<<1)-'0')});
						}
					}
				}
			}
			System.out.println("Problem "+num+": "+visit[N-1][N-1]);
			num++;
		} // 동작 끝
	} // main 끝
}