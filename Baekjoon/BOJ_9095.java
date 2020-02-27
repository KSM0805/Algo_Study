package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BOJ_9095 {
	private static int N;
	private static int cnt;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(bf.readLine().trim());
			cnt = 0;
			LinkedList<Integer> q = new LinkedList<>();
			q.offer(0);
			while(!q.isEmpty()) {
				int num = q.poll();
				if(num==N) {
					cnt++;
					continue;
				}
				if(num>N) {
					continue;
				}
				for (int j = 1; j < 4; j++) {
					q.offer(num+j);
				}
			}
			System.out.println(cnt);
		}
	}
}
