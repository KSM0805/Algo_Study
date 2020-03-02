package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class D3_3750 {
	private static int result;
	private static String num;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			num = bf.readLine().trim();
			while(num.length() != 1) {
				char[] ch = num.toCharArray();
				result = 0;
				for (int i = 0; i < ch.length; i++) {
					result += ch[i]-'0';
				}
				num = Integer.toString(result);
			}
			System.out.println("#"+test_case+" "+num);
		}//for end
	}
}
