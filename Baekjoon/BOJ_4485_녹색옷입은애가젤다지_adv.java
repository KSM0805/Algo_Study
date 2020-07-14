package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/**
 * 녹색 옷입은 애가 젤다지?
 * 최단거리 알고리즘
 * Dijkstra 다익스트라 : 특정 1개의 정점~ 나머지 모든 정점까지의 최단거리를 구하는 알고리즘(가중치가 0이상의 양수일때만)
 * Bellman-ford 벨만포트: 특정 1개의 정점~ 나머지 모든 정점까지의 최단거리를 구하는 알고리즘 (가중치가 0 양수 음수 모두 구할수있음)
 * Floyd Warshall 플로이드 워샬 : 모든 정점들간의 최단 경로

 *=> 이문제는 다익스트라에 해당됨, 하지만 인접행렬은 메모리가 부족해서 사용불가
 *=> DFS는 가지치기가 많이 안되서 시간터짐
 *=> BFS에 다익스트라 아이디어를 넣어서 구해보자
 */
public class BOJ_4485_adv {
	
	private static int N;
	private static int[][] m;
	private static int[][] memo;
	private static int[] dr;
	private static int[] dc;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 1; ; i++) {
			N = Integer.parseInt(bf.readLine().trim());
			if(N == 0) break;
			m = new int[N][N];
			for (int j = 0; j < N; j++) {
				String str = bf.readLine().trim();
				for (int k = 0, index = 0; k < N; k++, index += 2) {
					m[i][j] = str.charAt(index)-'0';
					memo[i][j] = Integer.MAX_VALUE;
				}
			}
			memo = new int[N][N];
			
//			for (int j = 0; j < N; j++) {
//				for (int k = 0; k < N; k++) {
//					memo[i][j] = Integer.MAX_VALUE;
//				}
//			}
//			bfs
			dr = new int[] {-1,1,0,0};
			dc = new int[] {0,0,-1,1};
			//Queue<int[]> q = new LinkedList<int[]>();
			PriorityQueue<int[]> q = new PriorityQueue<int[]>(new Comparator<int[]>(){
			    
			    public int campare(int[] o1, int[] o2){
			        return o1[2] - o2[2]; //최단 거리의 작은 값을 우선으로 하자
			    }
			});
			memo[0][0] = m[0][0];
			q.offer(new int[] {0,0,memo[0][0]}); //r행 c열 경로의 최솟값
			while(!q.isEmpty()) {
				//큐에서 꺼내기
				int[] data = q.poll();
				int r = data[0];
				int c = data[1];
				int cost = data[2];
				//비용이 개선되었을 때 가지치기
				
				for (int j = 0; j < 4; j++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if(0<=nr && 0<=nc && nc<N && nr<N && memo[nr][nc]>cost + m[nr][nc]) {
						memo[nr][nc] = cost + m[nr][nc];
						q.offer(new int[] {nr,nc,cost + m[nr][nc]});
					}
				}
				//큐에서 꺼낸 정점의 모든 인접한 정점을 큐에 넣기
			}
			System.out.println(m[N-1][N-1]);
		}
	}
}
