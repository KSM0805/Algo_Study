package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_7193 {
	private static int N;
	private static String str;
	private static int len;
	private static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			str = st.nextToken(); //타겟 숫자
			len = str.length() - 1; //자릿수 최대 천만자리수
			// 123456
			//1*9^len + 2*9^len-1
			//(9 % 8) *len
			result = 0;
			for (int i = 0; i <= len; i++) {
				result += str.charAt(i)-'0';
			}
			System.out.println(result%(N-1));
		}//for end
	}
}
