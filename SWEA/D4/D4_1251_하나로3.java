package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class D4_1251_하나로3 {
	private static long result;
	private static int N;
	private static long[] posY;
	private static long[] posX;
	private static double E;
	private static int[] parents;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = 0;
			N = Integer.parseInt(bf.readLine().trim());	//1~1000
			posX = new long[N];	//0~1,000,000
			posY = new long[N];
			parents = new int[N];
			StringTokenizer st;
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < N; j++) {
					if(i == 0) posX[j] = Long.parseLong(st.nextToken());
					else posY[j] = Long.parseLong(st.nextToken());
				}
			}
			E = Double.parseDouble(bf.readLine().trim());
			PriorityQueue<long[]> q = new PriorityQueue<>(new Comparator<long[]>() {

				@Override
				public int compare(long[] o1, long[] o2) {
					return Long.compare(o1[2], o2[2]);
				}
			});
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					long len = (posX[i] - posX[j]) * (posX[i] - posX[j])
					+ (posY[i] - posY[j]) * (posY[i] - posY[j]);
					q.offer(new long[] {i, j, len});
				}
			}
			makeSet();
			while(!q.isEmpty()) {
				long[] cur = q.poll();
				if(find((int)cur[0]) == find((int)cur[1])) continue;
				union((int)cur[0], (int)cur[1]);
				result += cur[2];
			}
			result = Math.round(E * result);
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end

	private static void union(int i, int j) {
		int rooti = find(i);
		int rootj = find(j);
		if(rooti == rootj) return;
		parents[rooti] = rootj;
		
	}

	private static int find(int x) {
		if(parents[x] == x) return x;
		else return find(parents[x]);
	}

	private static void makeSet() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
}
