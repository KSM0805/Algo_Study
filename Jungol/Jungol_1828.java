package jungol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Jungol_1828 {
	private static LinkedList<Node> list;
	private static int cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine().trim());
		cnt = 0;
		list = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);
		
//		Stack<Integer> stack = new Stack<>();
		
		while(!list.isEmpty()) {
//			int max = list.get(0).up;
			int max = list.poll().up;
			
//			for (int i = 1; i < list.size(); i++) {
//				if(max>=list.get(i).down) {
//					stack.push(i);
//				}
//			}
//			while(!stack.isEmpty()) {
//				int a = stack.pop();
//				list.remove(a);
//			}
//			list.remove(0);
//			cnt++;
			
			
			if(list.isEmpty()) {
				cnt++;
			}else {
				while(list.get(0).down <= max) {
					list.remove(0);
				}
				cnt++;
			}
			
//			if(list.size() == 1) {
//				list.remove(0);
//				cnt++;
//			}else if(list.get(1).down <= max) {
//				list.remove(1);
//			}else {
//				cnt++;
//				list.remove(0);
//			}
		}
		System.out.println(cnt);
	}
	private static class Node implements Comparable<Node>{
		int up, down;

		public Node(int down, int up) {
			super();
			this.up = up;
			this.down = down;
		}

		@Override
		public int compareTo(Node o) {
			if(this.up<o.up)	return -1;
			else if(this.up==o.up)	return 0;
			else 	return 1;
		}
		
	}
}
