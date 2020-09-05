package boj;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_2636_치즈 {
	private static int result;
	private static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < M; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) map[i][j] = true;
				else map[i][j] = false;
			}
		}
		boolean[][] visit = new boolean[N][M]; 
		LinkedList<int[]> q = new LinkedList<>();
		ArrayList<Integer> count = new ArrayList<>();
		int cnt = 0;
		boolean check = true;
		while(check) {
			result++;
			count.add(cnt);
			cnt = 0;
			check = false;
			for (boolean[] is : visit) {
				Arrays.fill(is, false);
			}
			q.add(new int[] {0,0});
			visit[0][0] = true;
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				for (int i = 0; i < 4; i++) {
					int nr = cur[0] + dir[i][0];
					int nc = cur[1] + dir[i][1];
					if(nr < 0 || nc < 0 || nr >= N || nc >= M || visit[nr][nc]) continue;
					if(map[nr][nc]) {
						visit[nr][nc] = true;
						map[nr][nc] = false;
						check = true;
						cnt++;
					} else {
						visit[nr][nc] = true;
						q.add(new int[] {nr, nc});
					}
				}
			}
		}
		System.out.println(result - 1);
		System.out.println(count.get(count.size() - 1));
	}
}
