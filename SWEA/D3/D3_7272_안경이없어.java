package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_7272 {
	static String one = "ADOPQR"; // 6개
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			boolean ok = true;
			if(str1.length() != str2.length()) ok = false;
			if(ok) {
				for (int i = 0, size = str1.length(); i < size; i++) {
					if(one.contains(Character.toString(str1.charAt(i)))){
						if(!one.contains(Character.toString(str2.charAt(i)))){
							ok = false;
							break;
						}
					}else if(one.contains(Character.toString(str2.charAt(i)))){
						if(!one.contains(Character.toString(str1.charAt(i)))){
							ok = false;
							break;
						}
					}else if(str1.charAt(i)=='B') {
						if(str2.charAt(i)!= 'B') {
							ok = false;
							break;
						}
					}else if(str2.charAt(i)=='B') {
						if(str1.charAt(i)!= 'B') {
							ok = false;
							break;
						}
					}
				}
			}
			if(ok) System.out.println("#"+test_case+" SAME");
			else System.out.println("#"+test_case+" DIFF");
		}//for end
	}

}
