package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_2098_외판원순회 {

	private static int result;
	private static int N;
	private static int[][] cost;
	private static int[][] mem;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 20000000;	// 최대 16*1,000,000
		N = Integer.parseInt(bf.readLine().trim());
		cost = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// *결국 사이클 -> 즉 어디서 출발해도 똑같다 !
		// 0번에서 N-1로 가자
		mem = new int[N][(1 << N) - 1];
		result = dfs(0, 1);
		System.out.println(result);
	}

	private static int dfs(int pos, int root) {
		if(root == (1 << N) - 1) return cost[pos][0] == 0 ? 20000000 : cost[pos][0];
		if(mem[pos][root] != 0) return mem[pos][root];
		// 가는방법을 모른다면 알아봐야한다.
		int min = 20000000;
		for (int i = 0; i < N; i++) {
			if(cost[pos][i] != 0 && (root & 1 << i) == 0) {
				min = Math.min(min, dfs(i, root | 1 << i) + cost[pos][i]);
			}
		}
		mem[pos][root] = min;
		return mem[pos][root];
	}
}
