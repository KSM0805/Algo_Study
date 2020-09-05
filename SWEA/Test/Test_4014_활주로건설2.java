package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test_4014_활주로건설2 {
	private static int result;
	private static int N;
	private static int X;
	private static int[][] map;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = 0;
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());	// 6 ~ 20
			X = Integer.parseInt(st.nextToken());	// 2 ~ 4
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
//			System.out.println("r");
			// 가로
			int[] tmp = {-1,-1};
			for (int r = 0; r < N; r++) {
				// 1. 우선 현재 위치의 숫자가 얼마나 반복됏는지 갖고잇어야함!
				tmp[0] = map[r][0];
				tmp[1] = 1;
				for (int i = 1; i < N; i++) {
					// 2. 다른 높이를 마주할 경우
					if(tmp[0] != map[r][i]) {
						// 2칸 이상 차이나면 실패
						if(Math.abs(tmp[0] - map[r][i]) > 1) break;
						// 2-1. 한 칸 높은 경우
						if(tmp[0] + 1 == map[r][i]) {
							if(tmp[1] < X) break;	// 경사로 보다 짧으면 실패
							tmp[0] = map[r][i];
							tmp[1] = 1;
							// 2-2. 한 칸 낮은 경우
						} else {
							tmp[0] = map[r][i];
							tmp[1] = 1;
							for (int j = i + 1; j < N; j++) {
								if(tmp[0] != map[r][j]) {
									i = j - 1;
									break;
								}
								tmp[1]++;
							}
							if(tmp[1] < X) break;
							tmp[1] -= X;
						}
					} else tmp[1]++;
					if(i == N - 1) {
//						System.out.println(r);
						result++;
					}
				}
			}
//			System.out.println("c");
			// 세로
			for (int c = 0; c < N; c++) {
				tmp[0] = map[0][c];
				tmp[1] = 1;
				for (int i = 1; i < N; i++) {
					if(tmp[0] != map[i][c]) {
						if(Math.abs(tmp[0] - map[i][c]) > 1) break;
						if(tmp[0] + 1 == map[i][c]) {
							if(tmp[1] < X) break;	// 경사로 보다 짧으면 실패
							tmp[0] = map[i][c];
							tmp[1] = 1;
						} else {
							tmp[0] = map[i][c];
							tmp[1] = 1;
							for (int j = i + 1; j < N; j++) {
								if(tmp[0] != map[j][c]) {
									i = j - 1;
									break;
								}
								tmp[1]++;
							}
							if(tmp[1] < X) break;
							tmp[1] -= X;
						}
					} else tmp[1]++;
					if(i == N - 1) {
//						System.out.println(c);
						result++;
					}
				}
			}
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end	
}
