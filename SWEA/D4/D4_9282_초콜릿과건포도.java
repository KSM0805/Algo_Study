package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_9282_초콜릿과건포도 {
	private static int result;
	private static int N;
	private static int M;
	private static int[][] choco;
	private static int[][][][] mem;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			choco = new int[N][M];
			mem = new int[N][M][N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < M; j++) {
					choco[i][j] = Integer.parseInt(st.nextToken());
				}
			}
//			mem[0][0][N-1][M-1] //여기까지 자를 때의 최솟값을 저장!
			//자르기 구현
			result = cut(0,0,N-1,M-1); //시작행, 시작열, 끝행, 끝열, 초기의건포도갯수
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end

	/**
	 * @param sR 시작행
	 * @param sC 시작열
	 * @param eR 끝행
	 * @param eC 끝열
	 * @return 크기만큼 자를때의 최솟값 저장 배열
	 */
	private static int cut(int sR, int sC, int eR, int eC) {
		if(sR == eR && sC == eC) return 0;
		if(mem[sR][sC][eR][eC] != 0) {
			return mem[sR][sC][eR][eC];
		}
		//최솟값 구하는 방법
		// 1. 우선 현재 크기의 건포도를 더한다.
		int sum = 0;
		for (int i = sR; i <= eR; i++) {
			for (int j = sC; j <= eC; j++) {
				sum += choco[i][j];
			}
		}
		// 2. 자르기
		// 2-1. 가로로
		int min = Integer.MAX_VALUE; //최솟값을 담을 배열
		for (int i = sR; i < eR; i++) {
			//위 초콜릿 -> sR,sC,i,eC
			if(mem[sR][sC][i][eC] == 0) { //저장된 값이 없을때만
				mem[sR][sC][i][eC] = cut(sR,sC,i,eC); //잘라서 구하기
			}
			//아래 초콜릿 -> i+1,sC,eR,eC
			if(mem[i+1][sC][eR][eC] == 0) {
				mem[i+1][sC][eR][eC] = cut(i+1,sC,eR,eC);
			}
			min = Math.min(min, mem[sR][sC][i][eC] + mem[i+1][sC][eR][eC]);
		}
		// 2-2. 세로로
		for (int i = sC; i < eC; i++) {
			//왼쪽 초콜릿 -> sR,sC,eR,i
			if(mem[sR][sC][eR][i] == 0) {
				mem[sR][sC][eR][i] = cut(sR,sC,eR,i);
			}
			//오른쪽 초콜릿 -> sR,i+1,eR,eC
			if(mem[sR][i+1][eR][eC] == 0) {
				mem[sR][i+1][eR][eC] = cut(sR,i+1,eR,eC);
			}
			min = Math.min(min, mem[sR][sC][eR][i] + mem[sR][i+1][eR][eC]);
		}
		mem[sR][sC][eR][eC] = sum + min;
		return mem[sR][sC][eR][eC];
	}
}
