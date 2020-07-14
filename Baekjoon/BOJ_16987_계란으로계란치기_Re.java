package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16987_Re {
	static int N;
//	static int[][] EGGS;
	static int max;
//	static int[] breaks;
	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = 1;
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim());
//			breaks = new int[N];
//			for (int i = 0; i < N; i++) {
//				breaks[i] = -1;
//			}
//			EGGS = new int[N][2];
			Eggs eggs = new Eggs(N);
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
				eggs.add(new Egg(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()), true));
			}// 0번째는 내구도, 1번째는 무게
			max = 0;
			BreakEgg(0,0);
			//1. 처음 계란을 든다.
			//2. 다른 계란과 부딪힌다.
			//2.1 처음계란이 깨진다. -> 두번째 계란을 든다. ->나머지 계란을 깰 수 있다.
			//2.2 처음계란이 안깨진다. -> 두번째 계란으로 처음과 나머지를 깰 수 있다.
			
			System.out.println(max);
		}//for end
	}//main end
	
	
	private static void BreakEgg(int i, int j) {
	// TODO Auto-generated method stub
	}



	// queue 사용하기
	
	
//	private static void BreakEgg(int pos, int idx) {
//		if(pos==N-1) {
//			if(max<idx) max = idx;
//		}
//		else{
//			top:
//			for (int i = 0; i < N; i++) {
//				boolean possible = true;
//				for (int j = 0; j < idx; j++) {
//					if(breaks[j]==i) {
//						possible = false;
//						break;
//					}
//				}
//				if(i != pos && possible) {
//					BreakEgg(pos+1, crack(pos, i, idx));
//				}
//			}
//		}
//	}//breakegg end
//	
//	private static int crack(int myEgg, int otherEgg, int idx) {
//		for (int j = 0; j < idx; j++) {
//			if(breaks[j]==myEgg) {
//				return idx;
//			}
//		}
//		int my = EGGS[myEgg][0]-EGGS[otherEgg][1];
//		int other = EGGS[otherEgg][0]-EGGS[myEgg][1];
//		if(my<=0) {
//			breaks[idx] = myEgg;
//			idx++;
//		}
//		else {
//			EGGS[myEgg][0] = my;
//		}
//		if(other<=0) {
//			breaks[idx] = otherEgg;
//			idx++;
//		}
//		else {
//			EGGS[otherEgg][0] = other;
//		}
//		return idx;
//	}
	
}
class Egg{
	boolean crack;
	int strong;
	int mass;
	public Egg(int strong, int mass, boolean crack) {
		this.strong = strong;
		this.mass = mass;
		this.crack = crack;
	}
}
class Eggs{
	Egg[] egg;
	int idx;
	
	public Eggs(int num) {
		egg = new Egg[num];
		idx = 0;
	}
	
	public void add(Egg e) {
		egg[idx] = e;
		idx++;
	}
}