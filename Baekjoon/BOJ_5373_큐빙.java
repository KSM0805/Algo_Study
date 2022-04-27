package algo_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5373_큐빙 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int testCount = Integer.parseInt(bf.readLine().trim());
		char[][] rubics = new char[6][9];
		// 위 아래 앞 뒤 왼 오 - U D F B L R
		char[] order = {'U', 'D', 'F', 'B', 'L', 'R'};
		char[] colors = {'w', 'y', 'r', 'o', 'g', 'b'};
		/*
		 일단 돌리는 방법은 같다! > 모든 면이 돌리는 방법은 같다! > 맞닿은 면만 다를 뿐이다.
		 그럼 시계 방향과 반시계 방향으로 돌리는 법만 다르다.
		 1. 시계방향으로 돌릴 때
			1 2 3     7 4 1
			4 5 6  >  8 5 2
			7 8 9     9 6 3
			
		 2. 반시계 방향
		 	1 2 3     3 6 9
		 	4 5 6  >  2 5 8
		 	7 8 9     1 4 7 
			
		그럼 다시 생각해보자 우선 케이스에따라 나열해보기
		1. U+
			B : 1 2 3 > L 1 2 3
			L : 1 2 3 > F 1 2 3
			F : 1 2 3 > R 1 2 3
			R : 1 2 3 > B 1 2 3

		    
		반시계 방향은 시계방향의 반대로만 하면됨
		
		2. D+
			F : 7 8 9 > L 7 8 9
			L : 7 8 9 > B 7 8 9
			B : 7 8 9 > R 7 8 9
			R : 7 8 9 > F 7 8 9
			
		3. F+
			U : 7 8 9 > L 9 6 3
			L : 9 6 3 > D 3 2 1
			D : 3 2 1 > R 1 4 7
			R : 1 4 7 > U 7 8 9
			
		4. B+
			U : 1 2 3 > R 3 6 9
			R : 3 6 9 > D 9 8 7
			D : 9 8 7 > L 7 4 1
			L : 7 4 1 > U 1 2 3 
			
		5. L+
			U : 1 4 7 > B 9 6 3
			B : 9 6 3 > D 1 4 7
			D : 1 4 7 > F 1 4 7
			F : 1 4 7 > U 1 4 7
			
		6. R+
			U : 9 6 3 > F 9 6 3
			F : 9 6 3 > D 9 6 3
			D : 9 6 3 > B 1 4 7
			B : 1 4 7 > U 9 6 3
		*/
		// 변하는 순서
		
		int[][] up = {{3, 0, 1, 2}
						, {4, 0, 1, 2}
						, {2, 0, 1, 2}
						, {5, 0, 1, 2}};
		int[][] down = {{2, 6, 7, 8}
						, {4, 6, 7, 8}
						, {3, 6, 7, 8}
						, {5, 6, 7, 8}};
		int[][] front = {{0, 6, 7, 8}
						, {4, 8, 5, 2}
						, {1, 2, 1, 0}
						, {5, 0, 3, 6}};
		int[][] back = {{0, 0, 1, 2}
						, {5, 2, 5, 8}
						, {1, 8, 7, 6}
						, {4, 6, 3, 0}};
		int[][] left = {{0, 0, 3, 6}
						, {3, 8, 5, 2}
						, {1, 0, 3, 6}
						, {2, 0, 3, 6}};
		int[][] right = {{0, 8, 5, 2}
						, {2, 8, 5, 2}
						, {1, 8, 5, 2}
						, {3, 0, 3, 6}};
		int[][][] changeOrder = {up, down, front, back, left, right};
		
		// 결과 출력용
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 0; tc < testCount; tc++) {
			// 루빅스 초기화
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 9; j++) {
					rubics[i][j] = colors[i];
				}
			}
			
			// 돌리는 횟수
			int n = Integer.parseInt(bf.readLine().trim());
			
			StringTokenizer st = new StringTokenizer(bf.readLine().trim(), " ");
			while(st.hasMoreTokens()) {
				String str = st.nextToken();
				int target = 0;
				for (int i = 0; i < order.length; i++) {
					if (str.charAt(0) == order[i]) {
						target = i;
						break;
					}
				}
				char[] arr = rubics[target];
				char tmp;
				if (str.charAt(1) == '+') {
					// 돌릴 면을 먼저 돌린다.
					tmp = arr[0];
					arr[0] = arr[6];
					arr[6] = arr[8];
					arr[8] = arr[2];
					arr[2] = tmp;
					tmp = arr[1];
					arr[1] = arr[3];
					arr[3] = arr[7];
					arr[7] = arr[5];
					arr[5] = tmp;
					
					// 나머지 따라 바뀐 면도 돌린다.
					int[][] co = changeOrder[target];
					char[] temp = {rubics[co[0][0]][co[0][1]], rubics[co[0][0]][co[0][2]], rubics[co[0][0]][co[0][3]]};
					for (int i = 1; i < 4; i++) {
						for (int k = 1; k < 4; k++) {
							rubics[co[i-1][0]][co[i-1][k]] = rubics[co[i][0]][co[i][k]];
						}
					}
					// 마지막 면을 첫 면으로 채우기
					for (int i = 0; i < 3; i++) {
						rubics[co[3][0]][co[3][i + 1]] = temp[i];
					}
				} else {
					tmp = arr[0];
					arr[0] = arr[2];
					arr[2] = arr[8];
					arr[8] = arr[6];
					arr[6] = tmp;
					tmp = arr[1];
					arr[1] = arr[5];
					arr[5] = arr[7];
					arr[7] = arr[3];
					arr[3] = tmp;
					
					// 나머지 따라 바뀐 면도 돌린다.
					int[][] co = changeOrder[target];
					char[] temp = {rubics[co[3][0]][co[3][1]], rubics[co[3][0]][co[3][2]], rubics[co[3][0]][co[3][3]]};
					for (int i = 2; i >= 0; i--) {
						for (int k = 1; k < 4; k++) {
							rubics[co[i+1][0]][co[i+1][k]] = rubics[co[i][0]][co[i][k]];
						}
					}
					// 마지막 면을 첫 면으로 채우기
					for (int i = 0; i < 3; i++) {
						rubics[co[0][0]][co[0][i + 1]] = temp[i];
					}
				}
			}
			
			for (int i = 0; i < 9; i++) {
				sb.append(rubics[0][i]);
				if (i % 3 == 2) {
					sb.append("\n");
				}
			}
		}
		System.out.println(sb.toString().trim());
	}
}
