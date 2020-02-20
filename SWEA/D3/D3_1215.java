package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_1215 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//		int T = Integer.parseInt(bf.readLine().trim());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			int size = Integer.parseInt(bf.readLine().trim());
			char[][] map = new char[8][8];
			for (int i = 0; i < 8; i++) {
				String str = bf.readLine().trim();
				for (int j = 0; j < 8; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			boolean pal = false;
			int cnt = 0;
			if(size == 1) {
				System.out.println("#"+test_case+" 64");
				continue;
			}
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j <= 8-size; j++) {
					pal = true;
					if(map[i][j] == map[i][j+size-1]) {
						for (int k = 1; k <(size)/2; k++) {
							if(map[i][j+k]!= map[i][j+size-1-k]) {
								pal = false;
								break;
							}
						}
						if(!pal) {
							continue;
						}
						else {
							cnt++;
						}
					}
				}
			}
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j <= 8-size; j++) {
					pal = true;
					if(map[j][i] == map[j+size-1][i]) {
						for (int k = 1; k <(size)/2; k++) {
							if(map[j+k][i] != map[j+size-1-k][i]) {
								pal = false;
								break;
							}
						}
						if(!pal) {
							continue;
						}
						else {
							cnt++;
						}
					}
				}
			}
			System.out.println("#"+test_case+" "+cnt);
			
		}//for end
	}

}
