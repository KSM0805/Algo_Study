package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1194_달이차오른다가자 {
	private static int result;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static boolean[][][] visit;

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] pos = new int[2];
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = bf.readLine().trim();
			for (int j = 0; j < M; j++) {
				char ch = str.charAt(j);
				if(ch == '.') {
					map[i][j] = -1;
				} else if(ch == '#') {
					map[i][j] = -2;
				} else if(ch == '0') {
					map[i][j] = -1;
					pos[0] = i;
					pos[1] = j;
				} else if(ch == '1') {
					map[i][j] = -3;
				} else if(ch -'a' > -1 && ch - 'a' < 26) {
					map[i][j] = ch - 'a';
				} else {
					map[i][j] = ch - 'A' + 100;	//문
				}
			}
		}
		/*HashSet[][] visit2 = new HashSet[N][M];
		for (int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				visit2[i][j] = new HashSet<Integer>();
			}
		}*/
		visit = new boolean[N][M][64];
		LinkedList<Minsig> q = new LinkedList<>();
		q.add(new Minsig(pos[0], pos[1], 1, 0)); //[0]: 행위치, [1]: 열위치, [2]: 열쇠유무, [3]: 진행횟수
		visit[pos[0]][pos[1]][0] = true;
		while(!q.isEmpty()) {
			Minsig cur = q.poll();
//			System.out.println("위치 " + cur.r + " " + cur.c + " 열쇠" + Integer.toBinaryString(cur.keys) + " 스텝: " + cur.cnt);
			if(map[cur.r][cur.c] == -3) {
				result = cur.cnt - 1;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dir[i][0];
				int nc = cur.c + dir[i][1];
				if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
//				if(!visit2[nr][nc].contains(cur.keys)) {
//					visit2[nr][nc].add(cur.keys);
				if(!visit[nr][nc][cur.keys]) {
					visit[nr][nc][cur.keys] = true;
					// 1. 빈공간인경우
					if(map[nr][nc] == -1 || map[nr][nc] == -3) {
						q.add(new Minsig(nr, nc, cur.cnt + 1, cur.keys));
					}
					// 3. 문인경우
					else if(map[nr][nc] > 99 && ( (1<<(map[nr][nc] - 100)& cur.keys) != 0 )) {
						// 들어올 수 있는 문이다.
						q.add(new Minsig(nr, nc, cur.cnt + 1, cur.keys));
					}
					// 2. 열쇠인경우
					else if(map[nr][nc] > -1 && map[nr][nc] < 26 ) {
						q.add(new Minsig(nr, nc, cur.cnt + 1, cur.keys | (1<<map[nr][nc])));
					}
				}
			}
		}
		System.out.println(result == 0 ? "-1" : result);
	}
}

class Minsig{
	int r;
	int c;
	int cnt;
	int keys;
	
	public Minsig(int r, int c, int cnt, int keys) {
		super();
		this.r = r;
		this.c = c;
		this.cnt = cnt;
		this.keys = keys;
	}
}