package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1715_카드정렬하기 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		
		for (int i = 0; i < n; i++) {
			pq.add(Long.parseLong(bf.readLine()));
		}
		
		long total = 0;
		
		while(!pq.isEmpty() && pq.size() != 1) {
			long sum = pq.poll() + pq.poll();
			total += sum;
			pq.add(sum);
		}
		System.out.println(total);
	}
}
