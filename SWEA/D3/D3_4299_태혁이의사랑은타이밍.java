package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_4299 {
	private static int result;
	private static int pros;
	private static int day;
	private static int wait;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			pros = 11 + (11*60);
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			day = Integer.parseInt(st.nextToken());
			wait = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
			result = 0;
			if(day == 11) {
				result = (pros<=wait) ? wait-pros : -1;
			}else {
				wait += (day-11)*60*24;
				result = wait - pros;
			}
			System.out.println("#"+test_case+" "+result);
		}//for end
	}
}
