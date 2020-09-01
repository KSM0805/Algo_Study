package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_15685_드래곤커브 {

	private static int result;
	private static int[][] dir  = {{1,0},{0,-1},{-1,0},{0,1}};
	private static int N;
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		N = Integer.parseInt(bf.readLine().trim());
		LinkedList<Integer> dragonDir = new LinkedList<>();
		StringTokenizer st;
		map = new int[102][102];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			int nr = Integer.parseInt(st.nextToken());
			int nc = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			dragonDir.add(d);
			for (int j = 0; j < g; j++) {
				for (int k = dragonDir.size() - 1; k >= 0; k--) {
					dragonDir.add((dragonDir.get(k) + 1) % 4);
				}
			}
			// 시작점에서 하고가기
			map[nc][nr] |= 1;
			if(nr > 0) map[nc][nr - 1] |= 2;
			if(nc > 0) map[nc - 1][nr] |= 8;
			if(nr > 0 && nc > 0) map[nc - 1][nr - 1] |= 4;
			for (int j = 0, end = (int)Math.pow(2, g); j < end; j++) {	
				d = dragonDir.poll();
				nr += dir[d][0];
				nc += dir[d][1];
				map[nc][nr] |= 1;
				if(nr > 0) map[nc][nr - 1] |= 2;
				if(nc > 0) map[nc - 1][nr] |= 8;
				if(nr > 0 && nc > 0) map[nc - 1][nr - 1] |= 4;
			}
		}
		for (int i = 0; i < 102; i++) {
			for (int j = 0; j < 102; j++) {
				if(map[i][j] == 15) result++;
			}
		}
		System.out.println(result);
	}
}
