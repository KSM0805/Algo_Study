package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class D3_5549 {
	private static int result;
	private static int len;

		public static void main(String[] args) throws NumberFormatException, IOException {
			System.setIn(new FileInputStream("input.txt"));
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			int T = Integer.parseInt(bf.readLine().trim());
			
			for (int test_case = 1; test_case <= T; test_case++) {
				String str = bf.readLine().trim();
				len = str.length() - 1;
				char a = str.charAt(len);
				System.out.print("#"+test_case+" ");
				if((a-'0') % 2 == 1) System.out.println("Odd");
				else System.out.println("Even");
			}//for end
		}

}
