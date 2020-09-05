package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2580_스도쿠 {

	private static int[][] map;
	private static int zero;
	private static ArrayList<int[]> empty;
	private static int[][] nums;
	private static boolean result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = false;
		// hashSet을 9+9+9 => 27개
		nums = new int [3][9];	//[0]: 가로,  [1]: 세로, [2]: 굵은 선
		map = new int [9][9];
		StringTokenizer st;
		zero = 0;
		empty = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 가로줄추가
				nums[0][i] |= 1 << map[i][j];
				// 세로줄 추가
				nums[1][j] |= 1 << map[i][j];
				// 굵은 선
				nums[2][i/3 * 3 + j/3] |= 1 << map[i][j];
				if(map[i][j] == 0) {
					zero++;
					empty.add(new int[] {i, j});
				}
			}
		}
		solve(0);
	}

	private static void solve(int idx) {
		if(result) return;
		if(idx == zero) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			result = true;
			return;
		}
		int[] cur = empty.get(idx);
		for (int i = 1; i < 10; i++) {
			if((nums[0][cur[0]] & (1 << i)) == 0 && (nums[1][cur[1]] & (1 << i)) == 0 && (nums[2][cur[0]/3 * 3 + cur[1]/3] & (1 << i)) == 0) {
				map[cur[0]][cur[1]] = i;
				int[] tmp = new int[3];
				tmp[0] = nums[0][cur[0]];
				tmp[1] = nums[1][cur[1]];
				tmp[2] = nums[2][cur[0]/3 * 3 + cur[1]/3];
				nums[0][cur[0]] |= 1 << i;
				nums[1][cur[1]] |= 1 << i;
				nums[2][cur[0]/3 * 3 + cur[1]/3] |= 1 << i;
				solve(idx + 1);
				map[cur[0]][cur[1]] = 0;
				nums[0][cur[0]] = tmp[0];
				nums[1][cur[1]] = tmp[1];
				nums[2][cur[0]/3 * 3 + cur[1]/3] = tmp[2];
			}
		}
	}
}
