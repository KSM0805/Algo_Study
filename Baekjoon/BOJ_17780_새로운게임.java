package BOJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17780_새로운게임 {
	private static int result;
	private static int N;
	private static int K;
	private static int[][] map;
	private static ArrayList<Integer>[][] horseMap;
	private static int[][] dir = {{0,1}, {0,-1}, {-1,0}, {1,0}};
	private static Horse[] horses;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0; 
		// N*N크기의 체스판
		StringTokenizer st = new StringTokenizer(bf.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());	// 4-12
		K = Integer.parseInt(st.nextToken());	// 4-10
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim(), " ");
			for (int j = 0; j < N; j++) {
				// 0 : 흰색, 1 : 빨간색, 2 : 파란색
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 말은 각자의 번호와 방향을 갖고있다.
		// 좌 우 위 아래
		horseMap = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				horseMap[i][j] = new ArrayList<Integer>();
			}
		}
		horses = new Horse[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			horses[i] = new Horse(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 1);
			horseMap[horses[i].r][horses[i].c].add(i);
		}
		top:
		while(result < 1001) {
			for (int i = 0; i < K; i++) {
				if(horses[i].rank > 3) break top;
			}
			for (int i = 0; i < K; i++) {
				if(horses[i].rank == 1) {
					int nr = horses[i].r + dir[horses[i].d][0];
					int nc = horses[i].c + dir[horses[i].d][1];
					if(nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == 2) {
						horses[i].d = horses[i].d % 2 == 0 ? horses[i].d + 1 : horses[i].d - 1;
						nr = horses[i].r + dir[horses[i].d][0];
						nc = horses[i].c + dir[horses[i].d][1];
						if(nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == 2) continue;
						// 방향을 틀었는데 파란색이 아닌 경우
						else{
							int r = horses[i].r;
							int c = horses[i].c;
							//흰색
							if(map[nr][nc] == 0) {
								for (int j = 0, size = horseMap[r][c].size(); j < size; j++) {
									int target = horseMap[r][c].get(0);
									horseMap[nr][nc].add(target);
									horses[target].r = nr;
									horses[target].c = nc;
									horses[target].rank = horseMap[nr][nc].size();
									horseMap[r][c].remove(0);
								}
							} else {
								//빨강
								for (int j = horseMap[r][c].size() - 1; j > -1; j--) {
									int target = horseMap[r][c].get(j);
									horseMap[nr][nc].add(target);
									horses[target].r = nr;
									horses[target].c = nc;
									horses[target].rank = horseMap[nr][nc].size();
									horseMap[r][c].remove(j);
								}
							}
						}
						// 흰색인 경우
					} else if(map[nr][nc] == 0) {
						int r = horses[i].r;
						int c = horses[i].c;
						for (int j = 0, size = horseMap[r][c].size(); j < size; j++) {
							int target = horseMap[r][c].get(0);
							horseMap[nr][nc].add(target);
							horses[target].r = nr;
							horses[target].c = nc;
//							System.out.println(target);
							horses[target].rank = horseMap[nr][nc].size();
							horseMap[r][c].remove(0);
						}
						// 빨강
					} else {
						int r = horses[i].r;
						int c = horses[i].c;
						for (int j = horseMap[r][c].size() - 1; j > -1; j--) {
							int target = horseMap[r][c].get(j);
							horseMap[nr][nc].add(target);
							horses[target].r = nr;
							horses[target].c = nc;
							horses[target].rank = horseMap[nr][nc].size();
							horseMap[r][c].remove(j);
						}
					}
				}
//				System.out.println(i + ": " + horses[i]);
			}
			result++;
		}
		System.out.println(result > 1000 ? "-1" : result);
	}
}
class Horse {
	int r;
	int c;
	int d;
	int rank;
	public Horse(int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.d = d;
	}
	public Horse(int r, int c, int d, int rank) {
		this.r = r;
		this.c = c;
		this.d = d;
		this.rank = rank;
	}
	@Override
	public String toString() {
		return "Horse [r=" + r + ", c=" + c + ", d=" + d + ", rank=" + rank + "]";
	}
	
}
