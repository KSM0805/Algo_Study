package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test_5644_무선충전3 {
	private static int result;
	private static int[][] person = new int[2][2];
	private static int M;
	private static int BC;
	private static int[][] step;
	private static int[][] BCInfo;
	private static ArrayList<Integer>[][] charge = new ArrayList[11][11];;
	private static int[][] dir = {{0,0}, {-1,0}, {0,1}, {1,0}, {0,-1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				charge[i][j] = new ArrayList<>();
			}
		}
		for (int test_case = 1; test_case <= T; test_case++) {
			result = 0;
			Arrays.fill(person[0], 1);
			Arrays.fill(person[1], 10);
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			M = Integer.parseInt(st.nextToken());
			BC = Integer.parseInt(st.nextToken());
			step = new int[2][M];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < M; j++) {
					step[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			BCInfo = new int[BC][4];
			for (int i = 0; i < BC; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < 4; j++) {
					BCInfo[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < 11; i++) {
				for (int j = 0; j < 11; j++) {
					charge[i][j].clear();
				}
			}
			for (int i = 0; i < BC; i++) {
				int c = BCInfo[i][0];
				int r = BCInfo[i][1];
				int cov = BCInfo[i][2];
				int cnt  = 1;
				for (int j = r - cov; j <= r + cov; j++) {
					for (int k = c - cnt + 1; k < c + cnt; k++) {
						if(j < 0 || k < 0 || j > 10 || k > 10) continue;
						charge[j][k].add(i);
					}
					if(j < r) cnt++;
					else cnt--;
				}
			}
			int time = 0;
			while(time <= M) {
				int ch = 0;
				// A만 충전 가능한 경우
				if(charge[person[0][0]][person[0][1]].size() > 0) {
					for (int a : charge[person[0][0]][person[0][1]]) {
					// B도 충전 가능한 경우
						if(charge[person[1][0]][person[1][1]].size() > 0) {
							for (int b : charge[person[1][0]][person[1][1]]) {
								if(a == b) ch = Math.max(ch, BCInfo[a][3]);
								else ch = Math.max(ch, BCInfo[a][3] + BCInfo[b][3]);
							}
						} else {
							ch = Math.max(ch, BCInfo[a][3]);
						}
					}
					// B만 충전 가능한 경우
				} else if(charge[person[1][0]][person[1][1]].size() > 0) {
					for (int b : charge[person[1][0]][person[1][1]]) {
						ch = Math.max(ch, BCInfo[b][3]);
					}
				}
				if(time < M) {
					person[0][0] += dir[step[0][time]][0];
					person[0][1] += dir[step[0][time]][1];
					person[1][0] += dir[step[1][time]][0];
					person[1][1] += dir[step[1][time]][1];
				}
				time++;
				result += ch;
			}
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end
}
