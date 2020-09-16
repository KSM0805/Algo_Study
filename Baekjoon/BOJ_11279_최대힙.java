package BOJ;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_11279_최대힙 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				return Integer.compare(arg1, arg0);
			}
		});
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int x = sc.nextInt();
			if(x==0) sb.append(pq.isEmpty() ? "0" : pq.poll()).append("\n");
			else pq.add(x);
		}
		System.out.println(sb);
	}
}
