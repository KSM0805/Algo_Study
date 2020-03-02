package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test_2383_점심식사시간 {
	private static int result;
	private static int N;
	private static int[][] map;
	private static ArrayList<int[]> people;
	private static int[][] stairs;
	private static ArrayList<Integer> stair;
	private static int size;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim());
			map = new int[N][N];
			people = new ArrayList<>(); // 사람이 들어갈 배열
			stairs = new int[2][3]; // 계단이 들어갈 배열
			StringTokenizer st;
			int pos = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==1) {
						people.add(new int[] {i,j}); //사람의 위치
					}else if(map[i][j]>1) {
						stairs[pos][0] = i; //위치와 소요시간
						stairs[pos][1] = j;
						stairs[pos][2] = map[i][j];
						pos++;
					}
				}
			}
			size = people.size();
			result = Integer.MAX_VALUE;
			//1. 각자 어디를 들어갈건지 선택 -> choose()
			//2. 그다음 시간을 고려 -> checkTime()
			choose();
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end

	private static void choose() {
		stair = new ArrayList<>();
		for (int i = 0, max = 1<<size; i < max; i++) {
			stair.clear();
			for (int j = 0; j < size; j++) {
				if( (1<<j & i) != 0 ) {
					stair.add(j);
				}
			}
			checkTime();
		}
	}

	private static void checkTime() {
		int[] wait1 = new int[stair.size()]; // 선택된 사람
		int[] wait2 = new int[size-stair.size()]; //선택되지않은 사람
		int pos1 = 0;
		int pos2 = 0;
		int max = -1;
		for (int i = 0; i < size; i++) {
			if(stair.contains(i)) {
				wait1[pos1] = Math.abs(people.get(i)[0] - stairs[0][0]) + Math.abs(people.get(i)[1] - stairs[0][1]);
				max = Math.max(max, wait1[pos1]);
				pos1++;
			}else {
				wait2[pos2] = Math.abs(people.get(i)[0] - stairs[1][0]) + Math.abs(people.get(i)[1] - stairs[1][1]);
				max = Math.max(max, wait2[pos2]);
				pos2++;
			}
		}
		if(result<max) { //이미 최대시간이 최소보다 크면 탐색하지 않음
			return;
		}
		Arrays.sort(wait1);
		Arrays.sort(wait2); //계단까지 걸리는 시간이 낮은 순서대로 정렬
		int[] notIn = {wait1.length, wait2.length}; // 아직계단에 들어가지 못한 사람수
		int[] in = {0,0}; //계단에 있는 사람 수
		int[] out = {0,0}; //계단에서 빠져나온 사람 수
		//시간을 고려 time = 1부터 올라가고 최대로 가득차면 뒤에사람들 시간 +1
		int time = 1;
		while(out[0]+out[1] <size) { //모두 빠져나오면 out
			//들어온 시간이 6이고 계단이 한칸이면 7에 나간다.
			for (int i = out[0]; i < in[0]+out[0]; i++) { //계단에서 사람이 빠져나오는지 체크
				if(wait1[i]+stairs[0][2] == time) { 
					in[0]--;
					out[0]++;
				}
			}
			for (int i = out[1]; i < in[1]+out[1]; i++) {
				if(wait2[i]+stairs[1][2] == time) { 
					in[1]--;
					out[1]++;
				}
			}
			
			for (int i = in[0]+out[0]; i < wait1.length; i++) {
				if(wait1[i] == time) {
					if(in[0]<3) { // 안이 가득차있지 않은경우
						in[0]++;
						notIn[0]--;
					}else { //안이 가득 차있는 경우
						for (int j = i; j <  wait1.length; j++) { //현재시간에 있는 사람들 1분 대기
							if(time == wait1[j]) {
								wait1[j]++;
							}else break;
						}
					}
				}else break;
			}
			for (int i = in[1]+out[1]; i < wait2.length; i++) {
				if(wait2[i] == time) {
					if(in[1]<3) {
						in[1]++;
						notIn[1]--;
					}else { //안이 가득 차있는 경우
						for (int j = i; j <  wait2.length; j++) {
							if(time == wait2[j]) {
								wait2[j]++;
							}else break;
						}
					}
				}else break;
			}
			time++;
		}
		result = Math.min(result, time);
	}
}
