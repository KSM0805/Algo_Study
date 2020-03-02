package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class D4_1868_파핑파핑지뢰찾기 {
	private static int result;
	private static int N;
	private static int[][] map;
	private static boolean[][] visit;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim());
			map = new int[N][N]; // 1~300
			String str;
			int trap = 0;
			for (int i = 0; i < N; i++) {
				str = bf.readLine();
				for (int j = 0; j < N; j++) {
					if(str.charAt(j)=='*') {
						trap++;
						map[i][j] = -1;
						//mem
						for (int k = 0; k < 8; k++) {
							int nr = i + dir[k][0];
							int nc = j + dir[k][1];
							if(nr>-1 && nc>-1 && nr<N && nc<N && map[nr][nc] != -1) {
								map[nr][nc]++;
							}
						}
					}
				}
			}
			// 0을 모두 클릭하여 연속적으로 없앨 수 있는 칸을 모두 카운트! -> 트랩을 제외한 전체칸에서 카운트를 뺀만큼은 한번씩 클릭해야 한다
			visit = new boolean[N][N];
			result = 0;
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j]==0 && !visit[i][j]) {
						result++; //0을 클릭한 횟수
						sum += click(i,j); //클릭하여서 총 없어진 칸
					}
				}
			}
			result = result + (N*N - (trap+sum)); //클릭한것 + (전체칸에서 트랩과 클릭해서 없어진 칸 제외한 나머지)
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end
	
	private static int click(int r, int c) {
		int check = 1; //본인것
		visit[r][c] = true;
		for (int i = 0; i < 8; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			if(nr>-1 && nc>-1 && nr<N && nc<N && !visit[nr][nc]) {
				visit[nr][nc] = true;
				if(map[nr][nc]==0) {
					check += click(nr,nc);
				}
				else{
					check++;
				}
			}
		}
		return check;
	}
}
