package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
//bfs
public class BOJ_1463 {
	private static int N;
	private static int[] map;
	private static int min;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim());
		
		LinkedList<int[]> q = new LinkedList<>();
		map = new int[N];
		q.offer(new int[] {N,0}); // N: 현재 숫자, 0: 연산 횟수
		min = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int num = arr[0];
			int cnt = arr[1];
			if(num==1) {
				min = Math.min(min, cnt);
				continue;
			}
			if(min<cnt) continue;
			if(num%3==0 && (map[num/3] > cnt+1 || map[num/3]==0)) {
				map[num/3] = cnt + 1;
				q.offer(new int[] {num/3, cnt+1});
			}
			if(num%2==0 && (map[num/2] > cnt+1 || map[num/2]==0)) {
				map[num/2] = cnt + 1;
				q.offer(new int[] {num/2, cnt+1});
			}
			if(map[num-1] > cnt+1 || map[num-1]==0) {
				map[num-1] = cnt + 1;
				q.offer(new int[] {num-1, cnt+1});
			}
		}
		System.out.println(min);
	}
}
