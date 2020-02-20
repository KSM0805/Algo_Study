package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11724 {
	private static int M;
	private static int N;
	private static int[] par;
	private static int cnt;

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		par = new int[N];
		makeSet();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine().trim());
			union(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
		}
		cnt = 0;
		for (int i = 0; i < N; i++) {
			if(par[i]<0) cnt++;
		}
		System.out.println(cnt);
	}

	private static int findSet(int a) {
		if(par[a]<0) return a; //음수면 자신이 부모
		return findSet(par[a]);
	}
	private static void union(int num1, int num2) {
		//부모 노드 찾기
		int root1 = findSet(num1);
		int root2 = findSet(num2);
		
		if(root1 == root2) return;
		//부모 노드의 랭크 값 확인하기 (기본값 -1)
		int rank1 = par[root1];
		int rank2 = par[root2];
		if(rank1 < rank2) { //1이 더 깊다.
			par[root1] += par[root2];
			par[root2] = root1;
		}else {
			par[root2] += par[root1];
			par[root1] = root2;
		}
		
	}

	private static void makeSet() {
		for (int i = 0; i < N; i++) {
			par[i] = -1;
		}
		
	}
}
