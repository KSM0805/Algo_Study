package d5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class D5_1798_범준이의제주도여행계획 {
	private static int result;
	private static int M;
	private static int N;
	private static int[][] disTime;
	private static int airport;
	private static ArrayList<Integer> hotels;
	private static ArrayList<int[]> tours;
	private static boolean[] visit;
	private static String route;
	private static boolean start;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = 0;
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());	//3~35 지점 개수 
			M = Integer.parseInt(st.nextToken());	//1~5 휴가기간
			disTime = new int[N][N];
			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = i + 1; j < N; j++) {
					disTime[i][j] = Integer.parseInt(st.nextToken());
					disTime[j][i] = disTime[i][j];
				}
			}
			airport = 0;
			hotels = new ArrayList<>();
			tours = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				String str = st.nextToken();
				if(str.equals("P")) {	// 관광지
					tours.add(new int[] {i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
										//[0] : 인덱스, [1]: 놀이시간, [2]: 만족도
				} else if(str.equals("H")) {	// 호텔
					hotels.add(i);
				} else {	// 공항
					airport = i;
				}
			}
			visit = new boolean[tours.size()];
			route = "";
			start = false;
			dfs(0, 1, 0, 0, "");		//[0]: 현재 위치, [1]: 날짜, [2]: 소요시간, [3]: 만족도, [4]: 경로
			System.out.print("#"+test_case+" ");
			if(result != 0) {
				System.out.println(result + " " + route);
			} else {
				System.out.println("0");
			}
		}//testcase end
	}//main end

	private static void dfs(int pos, int day, int runningTime, int satis, String str) {
		if(start && day == M && pos == airport) {
			if(result < satis) {
				result = satis;
				route = str;
			}
			return;
		}
		start = true;
		// 1. 호텔을 간다 -> 마지막 날인 경우에는 공항
		int time = 0;
		if(day == M) {
			time = runningTime + disTime[pos][airport];
			if(time <= 540) {
				dfs(airport, day, time, satis, str + (airport + 1));
			}
		} else {
			for (int i = 0; i < hotels.size(); i++) {
				time = runningTime + disTime[pos][hotels.get(i)];
				if(time <= 540) {
					dfs(hotels.get(i), day + 1, 0, satis, str + (hotels.get(i) + 1) + " ");
				}
			}
		}
		// 2. 관광지를 간다.
		for (int i = 0; i < tours.size(); i++) {
			if(!visit[i]) {
//				System.out.println("pos :" + pos + " tour : " + tours.get(i)[0] + " 이동거리 : " + disTime[pos][tours.get(i)[0]] + " 놀이 시간 "+tours.get(i)[1]);
				time = runningTime + disTime[pos][tours.get(i)[0]] + tours.get(i)[1];
				if(time <= 540) {
					visit[i] = true;
					dfs(tours.get(i)[0], day, time, satis + tours.get(i)[2], str + ((tours.get(i)[0] + 1) + " "));
					visit[i] = false;
				}
			}
		}
	}
}
