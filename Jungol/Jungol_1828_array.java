package jungol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Jungol_1828_array {
	private static int cnt;
	private static int[][] list;
	private static boolean[] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine().trim());
		cnt = 0;
		list = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			list[i][0] = Integer.parseInt(st.nextToken()); //최저 
			list[i][1] = Integer.parseInt(st.nextToken()); //최고
		}
//		sort
		Stack<int[]> stack = new Stack<>();
		for (int i = 0; i < N-1; i++) {
			int min = list[i][1]; // 최고 온도
			int pos = i;
			for (int j = i+1; j < N; j++) {
				if(min>list[j][1]) {
					min = list[j][1];
					pos = j;
				}
			}
			if(i != pos) {
				stack.push(new int[] {list[i][0], list[i][1]});
				stack.push(new int[] {list[pos][0], list[pos][1]});
				list[i] = stack.pop();
				list[pos] = stack.pop();
			}
		}
//		필요한 냉장고 갯수
		visit = new boolean[N];
//		for (int i = 0; i < N; i++) {
//			if(!visit[i]) {
//				cnt++;
//				int min = list[i][1];s
//				visit[i] = true;
//				for (int j = 0; j < N; j++) {
//					if(min >= list[j][0] && !visit[j]) {
//						visit[j] = true;
//					}
//				}
//			}
//		}
		int start = 0;
		while(start<N) {
			int max = list[start][1];
			boolean ok = true;
			for (int i = start+1; i < N; i++) {
				if(max<list[i][0]) {
					ok = false;
					start = i;
					cnt++;
					break;
				}
			}
			if(ok) {
				cnt++;
				break;
			}
			
		}
		System.out.println(cnt);
	}
}
