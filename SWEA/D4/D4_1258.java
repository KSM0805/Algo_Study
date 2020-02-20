package swea.d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class D4_1258 {
	private static int N;
	private static String[] str;
	private static int[][] map;
	private static LinkedList<Node> list = new LinkedList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim());
			map = new int[N][N];
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			top:
			while(true) {
				ar:
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if(map[i][j] != 0) {
							find(i,j);
							break ar;
						}
					}
					if(i>=N-1) break top;
				}
			}
			System.out.print("#"+test_case+" "+list.size()+" ");
			Collections.sort(list);
			while(!list.isEmpty()) {
				Node a = list.poll();
				System.out.print(a.r+" "+a.c+" ");
			}
			System.out.println();
		}//for end
	}

	private static void find(int row, int col) {
		int r = -1;
		int c = -1;
		for (int i = col; i < N; i++) { //가로 길이 찾기
			if(map[row][i] == 0) {
				c = i;
				break;
			}
		}
		for (int i = row; i < N; i++) { //세로 길이 찾기
			if(map[i][col] == 0) {
				r = i;
				break;
			}
		}
		for (int i = row; i < r; i++) { // 찾는 화학물질은 0으로 만들기 
			for (int j = col; j < c; j++) {
				map[i][j] = 0;
			}
		}
		list.add(new Node(r-row,c-col));
	}
}

class Node implements Comparable<Node>{
	int r,c;
	Node(){}

	public Node(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}

	@Override
	public int compareTo(Node o) {
		if( r*c < o.r*o.c) return -1;
		else if(r*c == o.r*o.c) {
			if(r < o.r) return -1;
		}
		return 0;
	}	
}