package BOJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_16236_아기상어 {

	private static int result;
	private static int N;
	private static int[][] map;
	private static int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		N = Integer.parseInt(bf.readLine().trim());
		map = new int[N][N];
		Shark babyShark = new Shark();
		babyShark.size = 2;
		babyShark.eat = 0;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					babyShark.r = i;
					babyShark.c = j;
					map[i][j] = 0;
				}
			}
		}
		boolean possible = true;
		LinkedList<int[]> q = new LinkedList<>();
		// 물고기가 없거나 자기보다 큰 물고기만 남으면 out
		while(possible) {
			q.clear();
			q.add(new int[] {babyShark.r, babyShark.c, 0});
			// 잡아 먹은 물고기가 있다면
			int[] fish = {-1,-1,-1};
			// 한 칸씩 움직인다.
			boolean[][] visit = new boolean[N][N];
			visit[babyShark.r][babyShark.c] = true;
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				// 잡아 먹은 물고기가 있다면
				if(fish[0] != -1) {
					// 먼것만 나오면
					if(cur[2] > fish[2]) break;
					// 물고기를 먹을 수 있다면 멀지 않고 더 위이거나 더 왼쪽일때 업그레이드
					if ((map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] < babyShark.size) && (cur[0] < fish[0] || (cur[0] == fish[0] && cur[1] < fish[1]))) {
						fish[0] = cur[0];
						fish[1] = cur[1];
						fish[2] = cur[2];
					} 
					// 잡아 먹으려고 움직인다.
				} else {
					// 물고기를 잡아 먹을 수 있나?
					if((map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] < babyShark.size)) {
						fish[0] = cur[0];
						fish[1] = cur[1];
						fish[2] = cur[2];
					} else {
						for (int i = 0; i < 4; i++) {
							int nr = cur[0] + dir[i][0];
							int nc = cur[1] + dir[i][1];
							if(nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc] || map[nr][nc] > babyShark.size) continue;
							visit[nr][nc] = true;
							q.add(new int[] {nr, nc, cur[2] + 1});
						}
					}
				}
			}
			// 먹은 물고기가 있으면
			if(fish[0] != -1) {
				// 공간 초기화
				map[fish[0]][fish[1]] = 0;
				babyShark.eat++;
				if(babyShark.eat == babyShark.size) {
					babyShark.size++;
					babyShark.eat = 0;
				}
				babyShark.r = fish[0];
				babyShark.c = fish[1];
				result += fish[2];
			} else {
				possible = false;
			}
		}
		System.out.println(result);
	}
}
class Shark {
	int r;
	int c;
	int size;
	int eat;
}
