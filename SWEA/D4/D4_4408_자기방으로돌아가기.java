package swea.d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class D4_4408 {
	private static int N;
	private static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			cnt = 0;
			N = Integer.parseInt(bf.readLine().trim());
			LinkedList<Room> list = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
				list.add(new Room(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			Collections.sort(list);
			Stack<Integer> stack = new Stack<>();
			while(!list.isEmpty()) {
				int max = list.poll().maxR;
//				System.out.println("max : "+max);
				for (int i = 0; i < list.size(); i++) {
					if(max % 2 == 1) {
						if(max + 1 < list.get(i).minR) {
						System.out.println("홀수 의 경우 minR : "+list.get(i).minR);
							stack.push(i);
							max = list.get(i).maxR;
						}
						
					}else {
						if(max < list.get(i).minR) {
							System.out.println("minR : "+list.get(i).minR);
							stack.push(i);
							max = list.get(i).maxR;
						}
					}
				}
				while(!stack.isEmpty()) {
					int re = stack.pop();
					list.remove(re);
				}
				cnt++;
			}
			System.out.println("#"+test_case+" " +cnt);
		}//for end
	}
}

class Room implements Comparable<Room>{
	int minR;
	int maxR;
	Room(){}
	public Room(int a, int b) {
		super();
		if(a>b) {
			minR = b;
			maxR = a;
		}
		else {
			minR = a;
			maxR = b;
		}
	}

	@Override
	public int compareTo(Room o) {
		if(minR < o.minR) return -1;
		else if(minR == o.minR) {
			if(maxR < o.maxR) return -1;
			else return 0;
		}
		return 1;
	}
	
}
