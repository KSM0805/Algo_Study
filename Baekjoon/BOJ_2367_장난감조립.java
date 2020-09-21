package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_2367_장난감조립 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static int[] order;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim());		// 1 ~ N-1 : 부품 번호 , N : 완제품 
		M = Integer.parseInt(bf.readLine().trim());
		map = new int[N+1][N+1];
		order = new int[N+1];
		ArrayList<Integer>[] list = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int Z = Integer.parseInt(st.nextToken());
			// X를 만드는데 Y가 Z개 필요하다.
			order[X]++;
			list[Y].add(X);
			map[X][Y] = Z;
		}
		LinkedList<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if(order[i] == 0) {
				map[i][i] = 1;
				q.add(i);
			}
		}
		while(!q.isEmpty()) {
			// order가 0인 부품(기본부품으로만 구성되어있는)
			int cur = q.poll();
			// 이 부품이 필요한 중간부품!
			for (int i = 0; i < list[cur].size(); i++) {
				int nextPart = list[cur].get(i);
				int num = map[nextPart][cur];
				map[nextPart][cur] = 0;
				for (int j = 1; j <= N; j++) {
					// 기본부품의 갯수를 올린다.
					if(map[cur][j] != 0) {
						map[nextPart][j] += map[cur][j]*num;
					}
				}
				order[nextPart]--;
				if(order[nextPart] == 0) {
					q.add(nextPart);
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if(map[N][i] != 0) System.out.println(i + " " + map[N][i]);
		}
	}
}
