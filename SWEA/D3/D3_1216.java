package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_1216 {
	static int max;
	static boolean result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			max = 1;
			int test = Integer.parseInt(bf.readLine().trim());
			
			char[][] map = new char[100][100];
			String str;
			
			for (int i = 0; i < 100; i++) { //map 채우기
				str = bf.readLine().trim();
				for (int j = 0; j < 100; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j <= 100-max; j++) {
					for (int k = j+max; k < 100; k++) {
						if(map[i][j] == map[i][k]) {
							result = true;
							for (int l = 1; l < (k-j); l++) {
								if(map[i][j+l] != map[i][k-l]) {
									result = false;
									break;
								}
							}
							if(result) {
								max = k-j+1;
							}
						}//같은거 비교 끝
					}
				} //한 행 검사 끝
			}// 모든 행 검사 끝
			
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j <= 100-max; j++) {
					for (int k = j+max; k < 100; k++) {
						if(map[j][i] == map[k][i]) {
							result = true;
							for (int l = 1; l < (k-j); l++) {
								if(map[j+l][i] != map[k-l][i]) {
									result = false;
									break;
								}
							}
							if(result) {
								max = k-j+1;
							}
						}//같은거 비교 끝
					}
				} //한 열 검사 끝
			}// 모든 열 검사 끝
			System.out.println("#"+test+" "+max);
		}//for end
	}
}
