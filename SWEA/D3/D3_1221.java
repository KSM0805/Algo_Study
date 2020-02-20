package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_1221 {
	private static int[] arr;
//	private static String[] no = {"ZRO", "ONE", "TWO", "THR","FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			String test = st.nextToken();
			int num = Integer.parseInt(st.nextToken());
			arr = new int[10];
			st = new StringTokenizer(bf.readLine().trim()," ");
			while(st.countTokens() != 0) {
				switch (st.nextToken()) {
					case "ZRO":
						arr[0]++;
						break;
					case "ONE":
						arr[1]++;
						break;
					case "TWO":
						arr[2]++;
						break;
					case "THR":
						arr[3]++;
						break;
					case "FOR":
						arr[4]++;
						break;
					case "FIV":
						arr[5]++;
						break;
					case "SIX":
						arr[6]++;
						break;
					case "SVN":
						arr[7]++;
						break;
					case "EGT":
						arr[8]++;
						break;
					case "NIN":
						arr[9]++;
						break;
				}
				
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < arr[0]; i++) {
				sb.append("ZRO ");
			}
			for (int i = 0; i < arr[1]; i++) {
				sb.append("ONE ");
			}
			for (int i = 0; i < arr[2]; i++) {
				sb.append("TWO ");
			}
			for (int i = 0; i < arr[3]; i++) {
				sb.append("THR ");
			}
			for (int i = 0; i < arr[4]; i++) {
				sb.append("FOR ");
			}
			for (int i = 0; i < arr[5]; i++) {
				sb.append("FIV ");
			}
			for (int i = 0; i < arr[6]; i++) {
				sb.append("SIX ");
			}
			for (int i = 0; i < arr[7]; i++) {
				sb.append("SVN ");
			}
			for (int i = 0; i < arr[8]; i++) {
				sb.append("EGT ");
			}
			for (int i = 0; i < arr[9]; i++) {
				sb.append("NIN ");
			}
			System.out.println(test);
			System.out.println(sb);
		}//for end
	}
}
