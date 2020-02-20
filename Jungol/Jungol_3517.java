package jungol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_3517 {
	static int N;
	static int Q;
	static int[] NUMBERS;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim());
		NUMBERS = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine().trim(), " ");
		for (int i = 0; i < N; i++) {
			NUMBERS[i] = Integer.parseInt(st.nextToken());
		}
		Q = Integer.parseInt(bf.readLine().trim());
		st = new StringTokenizer(bf.readLine().trim(), " ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0, size = st.countTokens(); i < size; i++) {
			sb.append(binarySearch(Integer.parseInt(st.nextToken()))+" ");
		}
		System.out.println(sb);
	}
	private static int binarySearch(int c) {
		int start = 0;
		int end = N;
		int mid;
		while(start<=end) {
			mid = (start+end) / 2;
			if(NUMBERS[mid] == c) {
				return mid;
			}
			else if( NUMBERS[mid] < c ) {
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}
		return -1;
	}

}
/*public class Jungol_3517 { //시간오바됨!
	static int N;
	static int Q;
	private static int[] NUMBERS;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim());
		
		NUMBERS = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine().trim(), " ");
		for (int i = 0; i < N; i++) {
			NUMBERS[i] = Integer.parseInt(st.nextToken());
		}
		Q = Integer.parseInt(bf.readLine().trim());
		
		st = new StringTokenizer(bf.readLine());
		
		for (int i = 0; i < Q; i++) {
			int find = Integer.parseInt(st.nextToken());
			int j = 0;
			for (; j < N; j++) {
				if(find == NUMBERS[j]) {
					System.out.print(j+" ");
					break;
				}
			}
			if(j>=N) {
				System.out.println(-1+" ");
			}
		}
	}
}*/
