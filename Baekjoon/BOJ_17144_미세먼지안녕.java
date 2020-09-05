package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지안녕 {

	private static int result;
	private static int R;
	private static int C;
	private static int T;
	private static int[][] map;
	private static int[][] dir1 = {{0,1},{-1,0},{0,-1}, {1,0}};
	private static int[][] dir2 = {{0,1},{1,0},{0,-1}, {-1,0}};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		R = Integer.parseInt(st.nextToken());	// 6 ~ 50 : 행 갯수
		C = Integer.parseInt(st.nextToken());	// 6 ~ 50 : 열 갯수
		T = Integer.parseInt(st.nextToken());	// 1 ~ 1000 : 시간
		map = new int[R][C];
		int air1 = -1;	// 공기 청정기 위
		int air2 = -1;	// 공기 청정기 아래
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					if(air1 == -1) air1 = i;
					else air2 = i;
				}
			}
		}
		LinkedList<int[]> spread = new LinkedList<>();
		for (int i = 0; i < T; i++) {
			
			// 미세먼지 확산
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					if(map[j][k] > 0) {
						int cnt = 0;
						for (int l = 0; l < 4; l++) {
							int nr = j + dir1[l][0];
							int nc = k + dir1[l][1];
							if(nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == -1) continue;
							spread.add(new int[] {nr, nc, map[j][k]/5});
							cnt++;
						}
						if(cnt > 0) map[j][k] -= map[j][k] / 5 * cnt;
					}
				}
			}
			// 확산된거 적용
			while(!spread.isEmpty()) {
				int[] cur = spread.poll();
				map[cur[0]][cur[1]] += cur[2];
			}
			// 공기 청정기 작동
			// 위에꺼부터
			int pos = 0;
			int r = air1;
			int c = 0;
			int before = 0;	// 전 미세먼지 양 저장
			while(pos < 4) {
				int nr = r + dir1[pos][0];
				int nc = c + dir1[pos][1];
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == -1) {
					pos++;
					continue;
				}
				// 전 미세먼지를 저장한다.
				int temp = map[nr][nc];
				map[nr][nc] = before;
				before = temp;
				r = nr;
				c = nc;
			}
			// 아래꺼
			pos = 0;
			r = air2;
			c = 0;
			before = 0;	// 전 미세먼지 양 저장
			while(pos < 4) {
				int nr = r + dir2[pos][0];
				int nc = c + dir2[pos][1];
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == -1) {
					pos++;
					continue;
				}
				// 전 미세먼지를 저장한다.
				int temp = map[nr][nc];
				map[nr][nc] = before;
				before = temp;
				r = nr;
				c = nc;
			}
		}
		for (int i = 0; i < R; i++) {
			
			for (int l = 0; l < C; l++) {
				result += map[i][l];
			}
		}
		System.out.println(result + 2);
	}
}
