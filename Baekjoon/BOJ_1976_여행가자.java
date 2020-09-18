package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1976_여행가자 {

	private static int[] parents;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine().trim());	// 도시의 수
		int M = Integer.parseInt(bf.readLine().trim());	// 여행가는 도시의 수
		StringTokenizer st;
		parents = new int[N];
		Arrays.fill(parents, -1);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if(a == 1) {
					join(i,j);
				}
			}
		}
		st = new StringTokenizer(bf.readLine().trim()," ");
		int base = find(Integer.parseInt(st.nextToken()) - 1);
		for (int i = 1; i < M; i++) {
			int x = find(Integer.parseInt(st.nextToken()) - 1);
			if(x != base) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

	private static void join(int i, int j) {
		int p1 = find(i);
		int p2 = find(j);
		if(p1 != p2) {
			if(parents[p1] < parents[p2]) {
				parents[p1] += parents[p2];
				parents[p2] = p1;
			} else {
				parents[p2] += parents[p1];
				parents[p1] = p2;
			}
		}
	}

	private static int find(int i) {
		if(parents[i] < 0) return i;
		parents[i] = find(parents[i]);
		return parents[i];
	}
}
