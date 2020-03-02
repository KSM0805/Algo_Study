package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class D4_1218 {
	private static String str = "(){}[]<>";
	private static int N;
	private static int[] blank;
	private static boolean check;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine());
			String s = bf.readLine().trim();
			blank = new int[8]; // 괄호의 갯수를 담을 배열
			for (int i = 0; i < N; i++) {
				blank[str.indexOf(s.charAt(i))]++;
			}
			check = true;
			for (int i = 0; i < 8; i+=2) {
				if(blank[i] != blank[i+1]) {
					check = false;
					break;
				}
			}
			System.out.println("#"+test_case+" " + (check ? "1":"0"));
		}//for end
	}
}
