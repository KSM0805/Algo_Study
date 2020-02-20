package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_3975 {

		public static void main(String[] args) throws NumberFormatException, IOException {
			System.setIn(new FileInputStream("input.txt"));
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			int T = Integer.parseInt(bf.readLine().trim());
			
			for (int test_case = 1; test_case <= T; test_case++) {
				StringTokenizer st = new StringTokenizer(bf.readLine().trim());
				double Alice = Double.parseDouble(st.nextToken()) /  Double.parseDouble(st.nextToken());
				double Bob = Double.parseDouble(st.nextToken()) /  Double.parseDouble(st.nextToken());
				System.out.print("#"+test_case+" ");
				if(Alice == Bob) {
					System.out.println("DRAW");
				}else if(Alice > Bob) System.out.println("ALICE");
				else System.out.println("BOB");
			}//for end
		}

}
