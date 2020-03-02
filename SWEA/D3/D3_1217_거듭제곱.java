package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_1217 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//		int T = Integer.parseInt(bf.readLine().trim());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			int test = Integer.parseInt(bf.readLine().trim());
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			int num = Integer.parseInt(st.nextToken());
			int multi = Integer.parseInt(st.nextToken());
			int result = multi(num,multi);
			System.out.println("#"+test+" "+result);
		}//for end
	}

	public static int multi(int num, int multi) {
		if(multi == 1) {
			return num;
		}
		else {
			return num*multi(num,multi-1);
		}
	}
}
