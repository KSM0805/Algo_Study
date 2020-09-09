package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class BOJ_1655_가운데를말해요 {

	private static int N;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();
		
		N = Integer.parseInt(bf.readLine().trim());
		PriorityQueue<Integer> small = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		PriorityQueue<Integer> big = new PriorityQueue<>();
		int mid = Integer.parseInt(bf.readLine().trim());
		result.append(mid + "\n");
		for (int i = 2; i <= N; i++) {
			int n = Integer.parseInt(bf.readLine().trim());
			if(small.size() == big.size()) {
				if(mid <= n) big.add(n);
				else {
					small.add(n);
					big.add(mid);
					mid = small.poll();
				}
			} else {
				if(mid >= n) small.add(n);
				else {
					big.add(n);
					small.add(mid);
					mid = big.poll();
				}
			}
			result.append(mid + "\n");
		}
		System.out.println(result);
	}
}
