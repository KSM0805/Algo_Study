package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class D3_1225 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<Integer> q = new LinkedList<>();
		for (int test_case = 1; test_case <= 10; test_case++) {
			q.clear();
			int test = Integer.parseInt(bf.readLine().trim());
			String str = bf.readLine().trim();
			String[] a = str.split(" ");
			for (int i = 0; i < a.length; i++) {
				q.offer(Integer.parseInt(a[i]));
			}
			int front;
			top:
			while(true) {
				for (int i = 1; i <=5; i++) { //왜 for문이 한번 더 들어갔는데 빠를까
					front = q.poll();
					front -= i;
					if(front<=0) {
						q.offer(0);
						break top;
					}
					q.offer(front);
				}
			}
			System.out.print("#"+test_case+" ");
			for (int j = 0; j < 8; j++) {
				System.out.print(q.poll()+" ");
			}
			System.out.println();
			
		}
	}

}
