package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.sound.sampled.LineListener;

public class D3_7985 {

	private static int K;
	private static int[] num;
	private static int len;

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			K = Integer.parseInt(bf.readLine().trim());
			StringTokenizer st = new StringTokenizer(bf.readLine().trim());
			len = (int) Math.pow(2, K) - 1;
			num = new int[len];
			for (int i = 0; i < len; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			//1. 2^K -1 /2 = p
			//2. p - (p/2) / p + p/2
			// 분할정복
			System.out.print("#"+test_case+" ");
			divide(0,len-1); // bfs
			System.out.println();
		}//for end
	}

	private static void divide(int start, int end) {
		LinkedList<int[]> q = new LinkedList<>();
		q.offer(new int[] {start, end});
		while(!q.isEmpty()) {
			int[] ar = q.poll();
			int s = ar[0];
			int e = ar[1];
			int p = (s + e)/2;
			if(s==len-1 && e==len-1) {
				System.out.print(num[p]+" ");
				return;
			}if(s==0 && e!=len-1) System.out.println();
			if(s == e) System.out.print(num[p]+" ");
			else {
				System.out.print(num[p]+" ");
				q.offer(new int[] {s, p-1});
				q.offer(new int[] {p+1, e});
			}
		}
	}
}
