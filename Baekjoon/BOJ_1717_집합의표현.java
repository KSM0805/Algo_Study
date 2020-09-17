package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1717_집합의표현 {

	private static int[] parents;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parents = new int[n+1];
		Arrays.fill(parents, -1);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			int mode = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(mode == 0) {
				join(a,b);
			} else {
				if(find(a) == find(b)) {
					 System.out.println("YES");
				} else System.out.println("NO");
			}
		}
	}

	private static void join(int a, int b) {
		int p1 = find(a);
		int p2 = find(b);
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

	private static int find(int n) {
		if(parents[n] < 0) return n;
		parents[n] = find(parents[n]);
		return parents[n];
	}
	
}
