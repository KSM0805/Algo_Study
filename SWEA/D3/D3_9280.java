package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class D3_9280 {
	private static int N;
	private static int M;
	private static int[] Ri;
	private static int[] Wi;
	private static int[] parking;
	private static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			Ri = new int[N];
			for (int i = 0; i < N; i++) {
				Ri[i] = Integer.parseInt(bf.readLine().trim());
			}
			Wi = new int[M];
			for (int i = 0; i < M; i++) {
				Wi[i] = Integer.parseInt(bf.readLine().trim());
			}
			parking = new int[N];
//			Stack<Integer> stack = new Stack<>();
			LinkedList<Integer> q = new LinkedList<>();
			cnt = 0;
			for (int i = 0; i < 2*M; i++) {
				int cur = Integer.parseInt(bf.readLine().trim());
				if(cur<0) {
					int absCur = Math.abs(cur);
					for (int j = 0; j < N; j++) {
						if(parking[j]== absCur) {
							parking[j] = 0;
							cnt += Wi[absCur-1]*Ri[j];
							break;
						}
					}
				}else if(!q.isEmpty()) q.offer(cur);
				else{
					boolean seat = false;
					for (int j = 0; j < N; j++) {
						if(parking[j]== 0) {
							parking[j] = cur;
							seat = true;
							break;
						}
					}
					if(!seat) {
						q.offer(cur);
					}
				}
				if(!q.isEmpty()) {
					int pos = q.peek();
					boolean seat = false;
					for (int j = 0; j < N; j++) {
						if(parking[j]== 0) {
							parking[j] = pos;
							seat = true;
						}
					}
					if(seat) q.poll();
				}
			}
			System.out.println("#"+test_case+" "+cnt);
		}//for end
	}
}
