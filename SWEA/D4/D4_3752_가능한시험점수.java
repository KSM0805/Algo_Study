package swea.d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_3752 {
	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			int size = Integer.parseInt(bf.readLine().trim());
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			int maxScore = 0;
			int[] scores = new int[size];
			for (int i = 0; i < size; i++) {
				scores[i] = Integer.parseInt(st.nextToken());
				maxScore += scores[i];
			}
			
			boolean[] scoreCase = new boolean[maxScore+1];
			scoreCase[0] = true;
			for (int i = 0; i < size; i++) {
				for (int j = maxScore; j >= 0; j--) {
					if(scoreCase[j]) {
						scoreCase[j+scores[i]] = true;
					}
				}
			}
			int cnt = 0;
			for (int i = 0; i <= maxScore; i++) {
				if(scoreCase[i]) cnt++;
			}
			System.out.println("#"+test_case+" "+cnt);
		}//for end
	}
}
