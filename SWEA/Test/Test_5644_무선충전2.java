package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test_5644_무선충전2 {
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
			//최대 충전값 구하기를 함수로 빼보기
			int sum = charging(0,0,-1) + charging(9,9,-1); // -1: 두 충전기가 같은지 비교하지 않을때
			dfs(0,0,9,9,-1,sum);
			System.out.println("#"+test_case+" "+Time[M-1]);
		}//testcase end
	}//main end

	private static int charging(int r, int c, int mode) {
		if(map[r][c].equals("")) return 0;
		int max = 0;
		for (int i = 0; i < map[r][c].length(); i++) {
			if(mode == map[r][c].charAt(i)-'0') continue;
			max = Math.max(max, BCInfo[map[r][c].charAt(i)-'0'][3]);
		}
		return max;
	}

	private static void dfs(int Ar, int Ac, int Br, int Bc, int time, int charge) {
		if(time == M-1) {
			return;
		}
		int nt = time +1;
		int nAr = Ar + dir[moveA.charAt(nt<<1)-'0'][0];
		int nAc = Ac + dir[moveA.charAt(nt<<1)-'0'][1];
		int nBr = Br + dir[moveB.charAt(nt<<1)-'0'][0];
		int nBc = Bc + dir[moveB.charAt(nt<<1)-'0'][1]; //두 개의 위치이동
		//최대로 들어갈 충전기를 선택
		int A = 0, B = 0, max = 0;
		if(!map[nAr][nAc].equals("")) { //A가 충전가능한 경우
			for (int i = 0; i < map[nAr][nAc].length(); i++) {
				A = BCInfo[map[nAr][nAc].charAt(i)-'0'][3];
				max = Math.max(max, A+charging(nBr, nBc, map[nAr][nAc].charAt(i)-'0'));
			}
		}else if(!map[nBr][nBc].equals("")) { //B가 충전 가능한 경우
			max = charging(nBr, nBc, -1);
		}
		if(Time[nt] == 0 || Time[nt] < charge+max) {
			Time[nt] = charge+max;
			dfs(nAr, nAc, nBr, nBc, nt, charge+max);
		}
	}
}
