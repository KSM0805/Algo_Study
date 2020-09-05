package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_16236_아기상어_2 {
	private static int result;
	private static int N;
	private static int[][] map;
	private static boolean check;
	private static boolean[][] visit;
	private static int[][] dir = {{-1,0}, {0,-1}, {0,1}, {1,0}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		
		N = Integer.parseInt(bf.readLine().trim());
		map = new int[N][N];
		StringTokenizer st;
		BabyShark babyShark = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					babyShark = new BabyShark(i, j, 2, 0);
					map[i][j] = 0;
				}
			}
		}
		LinkedList<int[]> q= new LinkedList<>();
		visit = new boolean[N][N];
		check = true;
		while(check) {	// 먹이를 먹을 수 있는 동안 반복한다.
			check = false; // 먹이를 먹을 수 있다
			q.clear();
			for (boolean[] v : visit) {
				Arrays.fill(v, false);
			}
			q.add(new int[] {babyShark.r, babyShark.c, 0});
			visit[babyShark.r][babyShark.c] = true;
			int maxCnt = Integer.MAX_VALUE;
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				if(maxCnt < cur[2]) break;
				int nr, nc;
				for (int i = 0; i < 4; i++) {
					nr = cur[0] + dir[i][0];
					nc = cur[1] + dir[i][1];
					if(nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc]) continue;
					// 1. 먹을 수 있는 물고기면 먹는다
					if(map[nr][nc] != 0 && map[nr][nc] < babyShark.size) {
						check = true;
						if(maxCnt == cur[2]) {
							if(babyShark.r > nr) {
								babyShark.r = nr;
								babyShark.c = nc;
							} else if(babyShark.r == nr && babyShark.c > nc) {
								babyShark.r = nr;
								babyShark.c = nc;
							}
						} else {
							maxCnt = cur[2];
							babyShark.r = nr;
							babyShark.c = nc;
							babyShark.eat++;
						}
					// 2. 지나갈 수 있는 위치는 지나간다.
					}else if(map[nr][nc] == 0 || map[nr][nc] == babyShark.size) {
						visit[nr][nc] = true;
						q.add(new int[] {nr, nc, cur[2] + 1});
					}
				}
			}
			if(check) {
				result += maxCnt + 1;
				map[babyShark.r][babyShark.c] = 0;
				if(babyShark.eat == babyShark.size) {
					babyShark.size++;
					babyShark.eat = 0;
				}
			}
		}
		System.out.println(result);
	}
}

class BabyShark{
	int r,c;
	int size;
	int eat;
	
	public BabyShark(int r, int c, int size, int eat) {
		super();
		this.r = r;
		this.c = c;
		this.size = size;
		this.eat = eat;
	}

	@Override
	public String toString() {
		return "BabyShark [r=" + r + ", c=" + c + ", size=" + size + ", eat=" + eat + "]";
	}
	
	
	
}