package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16987_DFS {
	static int N;
	static int[][] EGGS;
	static int[] breaks;
	private static int[] s;
	private static int[] w;
	private static int MAX;
	/**
	 * 입력 방법 : Scanner => BufferedReader
	 * 쪼개는 방법 : String.split() => StringTokenizer 
	 * 쪼개지 않는 경우 : String.charAt()
	 * 
	 * 출력 방법
	 * String => StringBuffer => StringBuilder 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//		int T = Integer.parseInt(bf.readLine().trim());
		int T = 1;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			
			N = Integer.parseInt(bf.readLine());
			
			// 이차원 배열보다는 일차원 두 개가 나음
			// 자바에서는 클래스를 생성해서 하는 것도 좋지만 알고리즘에서는 되도록 사용 x
			s = new int[N]; //내구도
			w = new int[N]; //무게
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
				s[i] = Integer.parseInt(st.nextToken()); //내구도 <= 300 integer 타입
				w[i] = Integer.parseInt(st.nextToken());
			}
			MAX = 0;
			dfs(0, 0);
			System.out.println(MAX);
		}//for end
	}//main end
	/**
	 * 
	 * @param index 현재 들고 있는 계란 위치 -> 깨지지 않은 계란을 타격한다.
	 * @param cnt	깨진 계란의 갯수
	 */
	private static void dfs(int index, int cnt) {
		if(index == N || cnt >= N-1) { //종료 파트, 남은 계란이 1~0개일 때 끝 혹은 마지막 계란일 때
			if(MAX<cnt) {
				MAX = cnt;
			}
			return;
		}
		if(s[index] <= 0) {
			dfs(index+1, cnt);
			return;
		}
		for (int i = 0; i < N; i++) {
			if(index == i) continue; // 본인 계란은 깰 수 없다.
			if(s[i] <= 0) { //이미 깨진 계란인 경우
				continue;
			}
			//다른 계란을 내리치고 (내구도 감소)
			s[i] -= w[index];
			s[index] -= w[i]; // 이 두 가지가 바뀌어서 저장된 상태
			
			int tempCnt = 0; //현재 작업으로 꺠진 계란의 수를 체크
			if(s[i] <= 0) tempCnt++;
			if(s[index] <= 0) tempCnt++;
			dfs(index+1, cnt + tempCnt);
			
			//다른 계란을 안깬 상태로 복구 (내구도 다시 복구)
			s[i] += w[index];
			s[index] += w[i]; // 이 두 가지를 다시 복구
		}
//		}else {	//재귀 파트 =>if에 return이 있으면 else 안해도 됨
//			
//		}
		
	}
	

}