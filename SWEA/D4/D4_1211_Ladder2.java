package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1211_Ladder2 {
	private static int result;
	private static boolean[][] map;
	private static int max;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			bf.readLine();
			map = new boolean[100][100];
			StringTokenizer st;
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < 100; j++) {
					if(st.nextToken().equals("1")) {
						map[i][j] = true;
					}
				}
			}
			max = Integer.MAX_VALUE;
			result = -1;
			for (int i = 0; i < 100; i++) {
				if(map[0][i]) {
					ladder(i);
				}
			}
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end

	private static void ladder(int col) {
		int c = col;
		int r = 0;
		int step = 0;
		while(r<100) {
			if(r==99) {
				if(max >= step) {
					max = step;
					result = col;
				}
				break;
			}
			r++;
			step++;
			if(c>0 && map[r][c-1]) { // 왼쪽에 사다리가 있는경우
				for (int i = c-1; i > -1; i--) {
					if(!map[r][i]) {
						c = i+1;
						break;
					}
					if(i==0) {
						c = 0;
					}
					step++;
				}
			}else if(c<99 && map[r][c+1]) { // 오른쪽에 사다리가 있는경우
				for (int i = c+1; i < 100; i++) {
					if(!map[r][i]) {
						c = i-1;
						break;
					}
					if(i==99) {
						c = 99;
					}
					step++;
				}
			}
		}
	}
}
