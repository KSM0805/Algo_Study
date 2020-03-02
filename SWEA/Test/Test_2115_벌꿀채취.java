package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test_2115 {
	private static int result;
	private static int C;
	private static int M;
	private static int N;
	private static int[][] map;
	private static boolean[][] visit;
	private static int[][] pos;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = 0;
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken()); //벌통크기 3~10
			M = Integer.parseInt(st.nextToken()); //선택하는 벌통의 개수 1~5(<=N)
			C = Integer.parseInt(st.nextToken()); //꿀채취 최대양 1~30
			// 한 칸당 꿀의 양은 1~9
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visit = new boolean[N][N];
			pos = new int[2][2];
			//꿀벌 위치 선택하기
			bee(0,0);
			System.out.println("#"+test_case+" "+result);
		}//for end
	}

	private static void bee(int r, int num) { // 선택할 꿀벌의 갯수
		if(num==2) {
			crop();
		}else {
			for (int i = r; i < N; i++) {
				//r번째 행에서는 열이 0번째 부터가 아니지만 같이 묶어서 처리
				for (int j = 0; j < N; j++) {
					if(check(i,j)) {
						for (int k = j; k < j+M; k++) {
							visit[i][k] = true;
						}
						pos[num][0] = i;
						pos[num][1] = j;
						bee(i,num+1);
						for (int k = j; k < j+M; k++) {
							visit[i][k] = false;
						}
						pos[num][0] = 0;
						pos[num][1] = 0;
					}
				}
			}
		}
	}

	private static void crop() {
		//최댓값을 만들집합을 찾아야함.
		int total = 0;
		total += max(pos[0]);
		total += max(pos[1]);
		result = Math.max(total, result);
	}

	private static int max(int[] bee) {
		int max = 0;
		int sum=0, pow = 0;
		for (int i = bee[1]; i < bee[1]+M; i++) {
			sum += map[bee[0]][i];
			pow += map[bee[0]][i] * map[bee[0]][i];
		}
		if(sum<=C) {
			return pow;
		}
		for (int i = 0, size = 1<<M; i < size; i++) {
			sum = 0;
			pow = 0;
			for (int j = 0; j < M; j++) {
				if((i & 1<<j) != 0 ) {
					sum += map[bee[0]][bee[1]+j];
					pow += map[bee[0]][bee[1]+j] * map[bee[0]][bee[1]+j];
				}
			}
			if(sum<=C) {
				max = Math.max(max, pow);
			}
		}
		return max;
	}

	private static boolean check(int r, int c) {
		if(c+M > N) return false;
		for (int i = c; i < c+M; i++) {
			if(visit[r][i]) return false;
		}
		return true;
	}
}
