package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_8741 {
	private static int result;

	//비트 마스크해서 검색할 것  
		public static void main(String[] args) throws NumberFormatException, IOException {
			System.setIn(new FileInputStream("input.txt"));
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			int T = Integer.parseInt(bf.readLine().trim());
			
			for (int test_case = 1; test_case <= T; test_case++) {
				StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
				String str = Character.toString( st.nextToken().charAt(0)) + Character.toString( st.nextToken().charAt(0)) + Character.toString( st.nextToken().charAt(0));
				System.out.println("#"+test_case+" "+str.toUpperCase());
			}//for end
		}

}
