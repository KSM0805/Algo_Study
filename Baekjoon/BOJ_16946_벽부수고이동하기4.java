package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16946_벽부수고이동하기4 {
	private static int N;
	private static int M;
	private static int[][] arr;
	private static int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		StringBuilder sb;
		for (int i = 0; i < N; i++) {
			sb = new StringBuilder(bf.readLine());
			for (int j = 0; j < M; j++) {
				// 벽은 -1, 통과 가능한 위치는 -2
				arr[i][j] = sb.charAt(j) - '2';
			}
		}
		
		// 1. 기존의 통과 가능한 위치에서 최대 움직일 수 있는 공간을 체크한다. -> 그룹
		// 그룹별로 길이를 체크하기 위한 변수
		ArrayList<Integer> groupCount = new ArrayList<Integer>();
		
		// 그룹 번호 
		int groupNum = -1;
		// 그룹당 소속 갯수 
		int count = 0;
		
		LinkedList<int[]> q = new LinkedList<int[]>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 현재 위치가 벽이거나 방문한 곳이면 continue
				if (arr[i][j] != -2) continue;
				
				q.clear();
				groupNum++;
				count = 1;
				
				// 현재 위치에서 4방향 탐색
				arr[i][j] = groupNum;
				q.add(new int[] {i,j});
				
				while(!q.isEmpty()) {
					int[] cur = q.poll();
					
					// 현재 위치에서 4방향 탐색
					for(int d = 0; d < 4; d++) {
						int nr = cur[0] + dir[d][0];
						int nc = cur[1] + dir[d][1];

						if (nr > -1 && nr < N && nc > -1 && nc < M && arr[nr][nc] == -2) {
							q.add(new int[] {nr, nc});
							arr[nr][nc] = groupNum;
							// 움직일 수 있는 공간이 하나 추가 
							count = (count + 1) % 10;
						}
					}
				}
				
				groupCount.add(count);
			}
		}
		
		// 2. 기존의 벽에서 4방향으로 탐색하여 그룹별로 카운트를 합한 뒤 자신의 영역을 +1 한다.
		// 중복된 그룹을 세지 않기 위해서 pq를 사용한다.
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		int total = 0;
		sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] != -1) {
					sb.append(0);
					continue;
				}
				
				total = 0;
				// 4방향 탐색해서 돌아다닐 수 있는 그룹을 찾는다.
				for (int d = 0; d < 4; d++) {
					int nr = i + dir[d][0];
					int nc = j + dir[d][1];
					
					if (nr > -1 && nr < N && nc > -1 && nc < M && arr[nr][nc] != -1) pq.add(arr[nr][nc]);
				}
				
				// 탐색한 그룹에서 카운트를 더한다.
				// 중복된 그룹을 사용하지 않기 위한 전의 그룹 메모리 변수
				int prevGroup = -1;
				while(!pq.isEmpty()) {
					int curGroup = pq.poll();
					if (prevGroup == curGroup) continue;
					
					total = (total + groupCount.get(curGroup)) % 10;
					prevGroup = curGroup;
				}
				
				// 움직일 수 있는 총 영역 저장
				sb.append((total + 1) % 10);
			}
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
	}
}

//4 5
//10001
//00111
//00010
//11101