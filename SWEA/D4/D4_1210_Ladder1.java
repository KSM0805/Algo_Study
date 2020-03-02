package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class D4_1210 {
	private static int result;
	private static String[] str;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int tc = Integer.parseInt(bf.readLine().trim());
			str = new String[100];
			for (int i = 0; i < 100; i++) {
				str[i] = bf.readLine().trim();
			}
			result = 0;
			int r = 98, c = -1; // 도착 지점을 가질 변수, 마지막 행이 99에서 무조건 한 칸은 올라온다.
			for (int i = 0; i < 100; i++) {
				if(str[99].charAt(i<<1) == '2') {
					c = i;
					break;
				}
			}
			while(r != 0 ) {
				if(c<99 && str[r].charAt((c+1) << 1) == '1') { // 오른쪽에 다리가 있는 경우
					c = (str[r].indexOf('0', (c+1)<<1) - 2) / 2;
					if(c==-1) c = 99;
					r--;
				}else if(c>1 && str[r].charAt((c-1) << 1) == '1') { // 왼쪽에 다리가 있는 경우
					c = (str[r].lastIndexOf('0', (c-1) << 1) + 2) / 2;
					if(c==-1) c = 0;
					r--;
				}else { // 어느쪽에도 다리가 없는 경우
					r--;
				}
			}
			result = c;
			System.out.println("#"+tc+" "+result);
		}//for end
	}
}
