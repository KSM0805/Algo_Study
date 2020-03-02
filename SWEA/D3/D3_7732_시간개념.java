package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class D3_7732 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			String curTime = bf.readLine().trim();
			String time = bf.readLine().trim();
			int curHour = Integer.parseInt(curTime.substring(0, 2));
			int curMin = Integer.parseInt(curTime.substring(3, 5));
			int curSec = Integer.parseInt(curTime.substring(6, 8));
			int proHour = Integer.parseInt(time.substring(0, 2));
			int proMin = Integer.parseInt(time.substring(3, 5));
			int proSec = Integer.parseInt(time.substring(6, 8));
			if( curHour>proHour || ((curHour==proHour) && (curMin>proMin)) || ((curHour==proHour) && (curMin==proMin) && (curMin>proMin))) {
				//약속이 다음 날인 경우
				proHour += 24;
			}
			int cur = curSec + curMin*60 + curHour*60*60; //초로 변환
			int next = proSec + proMin*60 + proHour*60*60;
			int result = next - cur;
			System.out.print("#"+test_case+" ");
			int num = result/3600;
			System.out.print( (num>9 ? num : "0"+num) + ":");
			result = result % 3600;
			num = result / 60;
			System.out.print( (num>9 ? num : "0"+num) + ":");
			num = result % 60;
			System.out.println((num>9 ? num : "0"+num));
		}//for end
	}
}
