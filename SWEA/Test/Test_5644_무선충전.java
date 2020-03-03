package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test_5644_무선충전 {
	private static int result;
	private static int M;
	private static int BC;
	private static String moveA;
	private static String moveB;
	private static int[][] BCInfo;
	private static String[][] map;
	private static int[][] dir = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};
	private static int[] Time;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			M = Integer.parseInt(st.nextToken()); //20~100 총 이동시간
			BC = Integer.parseInt(st.nextToken()); //1~8 충전기의 개수
			moveA = bf.readLine(); //초기위치 0,0
			moveB = bf.readLine(); //초기 위치 9,9
			BCInfo = new int[BC][4]; //각 BC의 정보 , [0]: 행위치, [1]: 열위치, [2]: 충전범위, [3]: 성능
			for (int i = 0; i < BC; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				BCInfo[i][1] = Integer.parseInt(st.nextToken());
				BCInfo[i][0] = Integer.parseInt(st.nextToken());
				BCInfo[i][2] = Integer.parseInt(st.nextToken());
				BCInfo[i][3] = Integer.parseInt(st.nextToken());
			}
			map = new String[10][10];
			for (String[] string : map) {
				Arrays.fill(string, "");
			}
			for (int i = 0; i < BC; i++) {
				for (int j = BCInfo[i][0]-1-BCInfo[i][2],idx = 1; j <= BCInfo[i][0]-1+BCInfo[i][2]; j++) { //행 정보
					for (int k = BCInfo[i][1]-idx; k < BCInfo[i][1]-1+idx; k++) { //열정보
						if(j<0 || k<0 || j>9 || k>9) continue;
						else {
							map[j][k] = map[j][k] + "" + i;
						}
					}
					if(j<BCInfo[i][0]-1) idx++;
					else idx--;
				}
			}
			//dfs
			Time = new int[M];
			if(map[0][0] == null && map[9][9] == null) dfs(0,0,9,9,-1,0); //A의 위치, B의 위치, 현재시간 , 현재 충전값
			else { 
				int max = 0, sum = 0;
				if(!map[0][0].equals("")) {
					for (int i = 0; i < map[0][0].length(); i++) {
						max = Math.max(max, BCInfo[map[0][0].charAt(i)-'0'][3]);
					}
					sum += max;
				}
				max = 0;
				if(!map[9][9].equals("")) {
					for (int i = 0; i < map[9][9].length(); i++) {
						max = Math.max(max, BCInfo[map[9][9].charAt(i)-'0'][3]);
					}
					sum += max;
				}
				dfs(0,0,9,9,-1,sum);
			}
			System.out.println("#"+test_case+" "+Time[M-1]);
		}//testcase end
	}//main end

	private static void dfs(int Ar, int Ac, int Br, int Bc, int time, int charge) {
		if(time == M-1) {
			return;
		}
		int nt = time +1;
		int nAr = Ar + dir[moveA.charAt(nt*2)-'0'][0];
		int nAc = Ac + dir[moveA.charAt(nt*2)-'0'][1];
		int nBr = Br + dir[moveB.charAt(nt*2)-'0'][0];
		int nBc = Bc + dir[moveB.charAt(nt*2)-'0'][1]; //두 개의 위치이동
		//최대로 들어갈 충전기를 선택
		int max = 0, sum = 0;
		if(map[nAr][nAc].equals("")) {
			if(map[nBr][nBc].equals("")) { //충전기가 없는 경우
				if(Time[nt] == 0 || Time[nt] < charge) {
					Time[nt] = charge;
					dfs(nAr,nAc,nBr,nBc,nt,charge); //아무것도 하지않는다.
				}
			}
			else { //B만 가능한 충전기가 있는경우
				for (int i = 0; i < map[nBr][nBc].length(); i++) {
					max = Math.max(max, BCInfo[map[nBr][nBc].charAt(i)-'0'][3]);
				}
				if(Time[nt] == 0 || Time[nt] < charge+max) {
					Time[nt] = charge+max;
					dfs(nAr,nAc,nBr,nBc,nt,charge+max);
				}
			}
		}else { 
			if(map[nBr][nBc].equals("")) { //A만 충전 가능한 경우
				for (int i = 0; i < map[nAr][nAc].length(); i++) {
					max = Math.max(max, BCInfo[map[nAr][nAc].charAt(i)-'0'][3]);
				}
				if(Time[nt] == 0 || Time[nt] < charge+max) {
					Time[nt] = charge+max;
					dfs(nAr,nAc,nBr,nBc,nt,charge+max);
				}
			}else { //A, B모두 충전 가능한 경우
				for (int i = 0; i < map[nAr][nAc].length(); i++) {
					for (int j = 0; j <  map[nBr][nBc].length(); j++) {
						if(map[nAr][nAc].charAt(i) == map[nBr][nBc].charAt(j)) {//같은 충전기라면
							max = Math.max(max, BCInfo[map[nAr][nAc].charAt(i)-'0'][3]);
						}else {
							sum = BCInfo[map[nAr][nAc].charAt(i)-'0'][3] + BCInfo[map[nBr][nBc].charAt(j)-'0'][3];
							max = Math.max(max, sum);
						}
					}
				}
				if(Time[nt] == 0 || Time[nt] < charge+max) {
					Time[nt] = charge+max;
					dfs(nAr,nAc,nBr,nBc,nt,charge+max);
				}
			}
		}
	}
}
