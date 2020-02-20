package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17471 {
	private static int N;
	private static int[] People;
	private static int totalPeo1;
	private static int totalPeo2;
	private static ArrayList<Integer> area1;
	private static ArrayList<Integer> area2;
	private static boolean[][] map;
	private static int minPeople;
//	private static int[] map2;
	private static int[] par;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim()); //구역수
		People = new int[N];
		map = new boolean[N][N];
//		makeSet();
		area1 = new ArrayList<>();
		area2 = new ArrayList<>();
		totalPeo1 = 0;
		totalPeo2 = 0;
		minPeople = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		for (int i = 0; i < N; i++) {
			People[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim());
			for (int j = 0, end = Integer.parseInt(st.nextToken()); j < end; j++) {
				map[i][Integer.parseInt(st.nextToken())-1] = true;
			}
		}
//		System.out.println(Arrays.toString(map2));
		for (int i = 1, size = 1<<N; i < size-1; i++) {
			totalPeo1 = 0;
			totalPeo2 = 0;
			area1.clear();
			area2.clear();
			for (int j = 0; j < N; j++) {
				if((i & 1<<j) != 0) {
					area1.add(j);
					totalPeo1 += People[j];
				}
				else {
					area2.add(j);
					totalPeo2 += People[j];
				}
			}
			int x = Math.abs(totalPeo1-totalPeo2);
			System.out.println(area1.toString() + "그리고 두번째 구역은"+ area2.toString()+ " "+x);
			if(minPeople<x) continue;
			if(isPossible()) {
				minPeople = x;
			}
		}
		if(minPeople == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(minPeople);
		// 기준은 인구 수
		// 1. 구역의 갯수 1~10
		// 2. 구역 인구 수 1~100
		// 3. 배열을 10개니까 2개로 나누는거! 해봤자 100개의 케이스밖에 안됨
		// 4. 3. 하고 나서 연결이 가능한지 체크
		// 5. 4. 후에 연결이 가능하면 인구 수 체크
		
	}

	private static boolean isPossible() {
		if(DisJoin1()) {
			return DisJoin2();
		}
		return false;
	}
	private static boolean DisJoin1() {
		int size = area1.size();
		if(size == 1) return true;
		par = new int [size];
		for (int i = 0; i < size; i++) {
			par[i] = i;
		}
		for (int i = 0; i < size-1; i++) {
			for (int j = i+1; j < size; j++) {
				if(map[area1.get(i)][area1.get(j)]) union(i,j);
			}
		}
		int stand = findSet(0);
		for (int i = 1; i < size; i++) {
			if(stand != findSet(i)) return false;
		}
		return true;
	}
	private static boolean DisJoin2() {
		int size = area2.size();
		if(size == 1) return true;
		par = new int [size];
		for (int i = 0; i < size; i++) {
			par[i] = i;
		}
		for (int i = 0; i < size-1; i++) {
			for (int j = i+1; j < size; j++) {
				if(map[area2.get(i)][area2.get(j)]) union(i,j);
			}
		}
		int stand =  findSet(0);
		for (int i = 1; i < size; i++) {
			if(stand != findSet(i)) return false;
		}
		return true;
	}
	public static int findSet(int v) {
		if(v == par[v]) return v;
		else { //루트가 아니면 자식이니까 부모로 돌아간다.
			return findSet(par[v]);
		}
	}

	public static void union(int v, int u) {
		//각각의 노드들의 root 찾아서 결합
		par[findSet(u)] = findSet(v);
	}
}
