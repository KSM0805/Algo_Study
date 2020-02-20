package bj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11660 {
	private static int N;
	private static int M;
	private static int[][] map;

	public static void main(String[] args) throws Exception{
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < N; j++) {
				if(j==0) map[i][0] = Integer.parseInt(st.nextToken());
				else map[i][j] = map[i][j-1] + Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine().trim());
			int startR = Integer.parseInt(st.nextToken()) - 1;
			int startC = Integer.parseInt(st.nextToken()) - 1;
			int endR = Integer.parseInt(st.nextToken()) - 1;
			int endC =Integer.parseInt(st.nextToken()) - 1;
			int result = 0;
			for (int j = startR; j <= endR; j++) {
				result += map[j][endC];
				if(startC > 0) {
					result -= map[j][startC-1];
				}
			}
			System.out.println(result);
		}
		System.out.println();
	}
}
