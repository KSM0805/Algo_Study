package swea.d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class D4_4408_2 {
	private static int N;
	private static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			cnt = 0;
			N = Integer.parseInt(bf.readLine().trim());
			int rooms[] = new int[201];
			
			int max = 0;
			int temp;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
				int playRoom = Integer.parseInt(st.nextToken());
				int myRoom = Integer.parseInt(st.nextToken());
				
				if(playRoom > myRoom) {
					temp = playRoom;
					playRoom = myRoom;
					myRoom = temp;
				}
				//홀수 방 번호 짝수로 바꿔주기
				if(playRoom % 2 != 0) playRoom += 1;
				if(myRoom %2 != 0) myRoom += 1;
				//400개에서 200개 마주보는 짝으로
				playRoom /= 2;
				myRoom /= 2;
				
				for (int j = playRoom; j <= myRoom; j++) {
					rooms[j]++;
				}
			}
			for (int i = 0; i < rooms.length; i++) {
				if(max<rooms[i]) max = rooms[i];
			}
			System.out.println("#"+test_case+" " +max);
		}//for end
	}
}

