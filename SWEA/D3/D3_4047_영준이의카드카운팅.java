package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class D3_4047 {
	private static boolean[] S;
	private static boolean[] D;
	private static boolean[] H;
	private static boolean[] C;
	private static boolean check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			S = new boolean[14];
			D = new boolean[14];
			H = new boolean[14];
			C = new boolean[14];
			int numS = 13;
			int numD = 13;
			int numH = 13;
			int numC = 13;
			String str = bf.readLine().trim();
			check = true;
			int sub;
			top:
			for (int i = 0; i < str.length(); i+=3) {
				sub = Integer.parseInt(str.substring(i+1, i+3));
				
				switch (str.charAt(i)) {
					case 'S':
						if(S[sub]) {
							check = false;
							break top;
						}else {
							S[sub] = true;
							numS--;
						}
						break;
					case 'D':
						if(D[sub]) {
							check = false;
							break top;
						}else {
							D[sub] = true;
							numD--;
						}
						break;
					case 'H':
						if(H[sub]) {
							check = false;
							break top;
						}else {
							H[sub] = true;
							numH--;
						}
						break;
					case 'C':
						if(C[sub]) {
							check = false;
							break top;
						}else {
							C[sub] = true;
							numC--;
						}
						break;
				}
			}
			System.out.print("#"+test_case+" ");
			if(!check) System.out.println("ERROR");
			else {
				System.out.println(numS+" "+numD+" "+numH+" "+numC);
			}
		}//for end
	}
}
