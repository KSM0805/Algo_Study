package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test_4013 {
	private static int result;
	private static int K;
	private static int[][] mag;
	private static int[] pos;
	private static int[] chpos;
	private static boolean[] visit;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			K = Integer.parseInt(bf.readLine().trim()); //회전 횟수
			mag = new int[4][8];
			StringTokenizer st;
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				// 0 0 1 0 ... 0: N극 , 1: S극
				for (int j = 0; j < 8; j++) {
					mag[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			pos = new int[8];
			for (int i = 0; i < 8; i+=2) {
				pos[i] = 6;
				pos[i+1] = 2;
			} //초기에 맞닿아 있는 날의 위치
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				chpos = Arrays.copyOf(pos, 8);
				visit = new boolean[4];
				int num = Integer.parseInt(st.nextToken())-1;
				visit[num] = true;
				spin(num, Integer.parseInt(st.nextToken()));
				pos =  Arrays.copyOf(chpos, 8);
			}
			result = 0;
			for (int i = 0, idx=0; i < 4; i++, idx+=2) {
				int a = pos[idx] + 2;
				if(a>7) a -= 8;
				else if(a<0) a += 8;
				if(mag[i][a]==1) {
					result += Math.pow(2, i);
				}
			}
			System.out.println("#"+test_case+" "+result);
		}//for end
	}

	private static void spin(int magNum, int dir) {
		chpos[magNum<<1] += -dir; //방향으로 한번 회전
		chpos[(magNum<<1)+1] += -dir; //방향으로 한번 회전
		if(chpos[magNum<<1]<0) chpos[magNum<<1] += 8;
		else if(chpos[magNum<<1]>7) chpos[magNum<<1] -= 8;
		if(chpos[(magNum<<1)+1]<0) chpos[(magNum<<1)+1] += 8;
		else if(chpos[(magNum<<1)+1]>7) chpos[(magNum<<1)+1] -= 8;
		if(magNum+1 < 4 && !visit[magNum+1] && mag[magNum][pos[(magNum<<1)+1]] != mag[magNum+1][pos[(magNum+1)<<1]]) { //오른쪽에 자석이 있고 반대극이 붙어있는경우
			visit[magNum+1] = true;
			spin(magNum+1,-dir);
		}
		if(magNum-1 > -1 && !visit[magNum-1] && mag[magNum][pos[(magNum<<1)]] != mag[magNum-1][pos[((magNum-1)<<1)+1]]) { //왼쪽에 자석이 있고 반대극이 붙어있는경우
			visit[magNum-1] = true;
			spin(magNum-1,-dir);
		}
	}
}
