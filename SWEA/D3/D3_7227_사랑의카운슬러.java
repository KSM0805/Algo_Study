package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_7227 {
	private static int N;
	private static int[][] arr;
	private static long min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim());
			arr = new int[N][2];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
				arr[i][0] = Integer.parseInt(st.nextToken()); //x 좌표
				arr[i][1] = Integer.parseInt(st.nextToken()); //y 좌표
			}
			//벡터 (x,y) -> (a,b) 이면 벡터는 a-x, b-y
			//a-x + ... 이게 제일 작아야한다.
			//
			min = Long.MAX_VALUE;
			int[] num = new int[4];
			for (int i = 0, size = 1<<N; i < size; i++) {
				int k = 0;
				Arrays.fill(num, 0);
				for (int j = 0; j < N; j++) {
					if( (i & 1<<j) == 0 ) {
						num[0] += arr[j][0];
						num[1] += arr[j][1];
						k++;
					}else {
						num[2] += arr[j][0];
						num[3] += arr[j][1];
					}
				}
				if(k==N/2) {
					long x = num[0] - num[2];
					long y = num[1] - num[3];
					long result = x*x + y*y;
					if(min>result) min = result;
				}
			}
			System.out.println("#"+test_case+" "+min);
		}//for end
	}
}
