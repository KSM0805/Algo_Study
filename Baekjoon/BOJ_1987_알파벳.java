package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_1987_알파벳 {

	private static int result;
	private static HashSet alphabet;
	private static int R;
	private static int C;
	private static char[][] map;
	private static int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = Integer.MIN_VALUE;
		alphabet = new HashSet<Character>();
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		StringBuilder sb;
		for (int i = 0; i < R; i++) {
			sb = new StringBuilder(bf.readLine().trim());
			for (int j = 0; j < C; j++) {
				map[i][j] = sb.charAt(j);
			}
		}
		alphabet.add(map[0][0]);
		dfs(0,0,1);
		System.out.println(result);
	}

	private static void dfs(int r, int c, int cnt) {
		if(result < cnt) result = cnt;
		for (int i = 0; i < 4; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			if(nr < 0 || nc < 0 || nr >= R || nc >= C || alphabet.contains(map[nr][nc])) continue;
			// 갈 수 있는 경우
			alphabet.add(map[nr][nc]);
			dfs(nr, nc, cnt + 1);
			alphabet.remove(map[nr][nc]);
		}
	}
}
