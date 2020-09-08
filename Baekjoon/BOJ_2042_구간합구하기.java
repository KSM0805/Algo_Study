package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2042_구간합구하기 {

	private static int N;
	private static int M;
	private static int K;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());	// 1-100만개 : 수의 갯수
		M = Integer.parseInt(st.nextToken());	// 1-10000 : 수의 변경이 일어나는 횟수
		K = Integer.parseInt(st.nextToken());	// 1-10000 : 구간의 합을 구하는 횟수
		long[] nums = new long[N + 1];				// 구간의 수를 담는 배열
		long[] sum = new long[N + 1];	// 구간의 합을 담는 배열
		nums[0] = 0;
		sum[0] = 0;
		HashMap<Integer, Long> change = new HashMap<>();
		int[] index = new int[M];
		for (int i = 1; i <= N; i++) {
			nums[i] = Long.parseLong(bf.readLine()); 
			sum[i] = nums[i] + sum[i - 1];
		}
		long gap = 0;
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < M+K; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if(a == 1) {
				// b번째 수를 c로 바꾼다.
				if(!change.containsKey(b)) {
					index[0] = b;
					Arrays.sort(index);
				}
				change.put(b, c - nums[b]);
			} else {
				// b번째부터 c번째 까지의 구간합
				gap = 0;
				int idx = Arrays.binarySearch(index, b);
				// ???어떻게 찾을것인가
				if(idx < 0) idx = -(idx + 1);
				for (int j = idx; j < M; j++) {
					if(index[j] > c) break;
					gap += change.get(index[j]);
				}
				sb.append(sum[(int)c]-sum[b-1] + gap).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}
