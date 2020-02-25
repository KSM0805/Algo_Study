package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test_4014 {
	private static int result;
	private static int N;
	private static int X;
	private static int[][] map;
	private static boolean[] up;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken()); // 배열크기
			X = Integer.parseInt(st.nextToken()); // 경사로 길이
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = 0;
			boolean check = false;
			for (int i = 0; i < N; i++) { //가로 경사로 검사
				up = new boolean[N];
				int col = 0; //검사하는 위치
				check = true;
				top:
				while(col<N-1 && check) {
					int num = map[i][col];
					if(num == map[i][col + 1]) {
						col++;
					}else if(num-1 == map[i][col+1]){ // 내려가는 경사로
						int height = map[i][col+1];
						for (int j = 0; j < X; j++) {
							//범위를 벗어나거나 높이가 다르거나 이미 경사로가 설치된 경우
							if(col+1+j > N-1 || height != map[i][col + 1 +j] || up[col+1+j]) {
								check = false;
								break top;
							}
						}
						for (int j = 0; j < X; j++) {
							up[col+1+j] = true;
						}
						col += X;
					}else if( num + 1 == map[i][col+1]) { // 올라가는 경사로
						for (int j = 0; j < X; j++) {
							if(col-j < 0 || map[i][col-j] != num || up[col-j]) {
								check = false;
								break top;
							}
						}
						for (int j = 0; j < X; j++) {
							up[col-j] = true;
						}
						col++;
					}else {
						check = false;
						break top;
					}
				}
				if(check) {
					result++;
				}
			}
			for (int i = 0; i < N; i++) { //세로 경사로 검사
				up = new boolean[N];
				check = true;
				int row = 0; //검사하는 위치
				top:
				while(row<N-1 && check) {
					int num = map[row][i];
					if(num == map[row+1][i]) {
						row++;
					}else if(num-1 ==  map[row+1][i]){
						int height = map[row+1][i];
						for (int j = 0; j < X; j++) {
							if(row+1+j > N-1 || height != map[row + 1 +j][i] || up[row+1+j]) {
								check = false;
								break top;
							}
						}
						for (int j = 0; j < X; j++) {
							up[row+1+j] = true;
						}
						row += X;
					}else if( num + 1 == map[row+1][i]) {
						for (int j = 0; j < X; j++) {
							if(row-j < 0 || map[row-j][i] != num || up[row-j]) {
								check = false;
								break top;
							}
						}
						for (int j = 0; j < X; j++) {
							up[row-j] = true;
						}
						row++;
					}else {
						check = false;
						break top;
					}

				}
				if(check) {
					result++;
				}
			}
			System.out.println("#"+test_case+" "+result);
		}//for end
	}
}
