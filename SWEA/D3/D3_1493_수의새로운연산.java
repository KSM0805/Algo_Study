package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_1493_수의새로운연산 {
	private static int result;
	private static int P;
	private static int Q;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		int[][] memNum = new int[45151][2];
		int[][] memPos = new int[301][301];
		int num = 1;
		int r = 1;
		int c = 1;
		while(num<45151) {
			int nr = r;
			int nc = c;
			for (int i = 0; i < r; i++) {
				memNum[num][0] = nr;
				memNum[num][1] = nc;
				memPos[nr][nc] = num;
				nr--;
				nc++;
				num++;
			}
			r++;
		}
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			P = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			int[] ar1 = {memNum[P][0], memNum[P][1]};
			int[] ar2 = {memNum[Q][0], memNum[Q][1]};
			int[] ar = {ar1[0]+ar2[0], ar1[1]+ar2[1]};
			result = memPos[ar[0]][ar[1]];
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end
}
