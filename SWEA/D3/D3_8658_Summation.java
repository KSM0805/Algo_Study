package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_8658 {
	private static int[] num;
	private static int[] sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			num = new int[10];
			sum = new int[10];
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			for (int i = 0; i < 10; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(num);
			while(num[9]>=10) {
				for (int i = 0; i < 10; i++) {
					sum[i] += num[i] % 10;
					num[i] /= 10;
				}
			}
			for (int i = 0; i < 10; i++) {
				sum[i] += num[i];
			}
			Arrays.sort(sum);
			System.out.println("#"+test_case+" "+sum[9]+" "+sum[0]);
		}//for end
	}
}
