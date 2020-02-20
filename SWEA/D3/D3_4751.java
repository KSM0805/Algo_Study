package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class D3_4751 {

		private static int size;

		public static void main(String[] args) throws NumberFormatException, IOException {
			System.setIn(new FileInputStream("input.txt"));
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			int T = Integer.parseInt(bf.readLine().trim());
			
			for (int test_case = 1; test_case <= T; test_case++) {
				String str = bf.readLine().trim();
				size = str.length();
				StringBuilder sb = new StringBuilder();
				sb.append("..#..");
				for (int i = 1; i < size; i++) { // 첫째줄
					sb.append(".#..");
				}
				sb.append("\n.#.#.");
				for (int i = 1; i < size; i++) { // 둘째줄
					sb.append("#.#.");
				}
				sb.append("\n#.").append(str.charAt(0)).append(".#");
				for (int i = 1; i < size; i++) { // 셋째줄
					sb.append(".").append(str.charAt(i)).append(".#");
				}
				sb.append("\n.#.#.");
				for (int i = 1; i < size; i++) { // 넷째줄
					sb.append("#.#.");
				}
				sb.append("\n..#..");
				for (int i = 1; i < size; i++) { // 다섯째줄
					sb.append(".#..");
				}
				
				System.out.println(sb);
			}//for end
		}

}
