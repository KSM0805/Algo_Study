package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_12852_1로만들기2 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine().trim());
		int[][] num = new int[N+1][2];	//[0]: count횟수, [1]: 전의 숫자
		num[1][0] = 0;
		num[1][1] = 0;
		int[] min = {Integer.MAX_VALUE, -1};
		for (int i = 2; i <= N; i++) {
			min[0] = Integer.MAX_VALUE;
			if(i % 3 == 0) {
				min[0] = num[i/3][0];
				min[1] = i/3;
			}
			if(i % 2 == 0) {
				if(min[0] > num[i/2][0]) {
					min[0] = num[i/2][0];
					min[1] = i/2;
				}
			}
			if(min[0] > num[i-1][0]) {
				min[0] = num[i-1][0];
				min[1] = i-1;
			}
			num[i][0] = min[0] + 1;
			num[i][1] = min[1];
		}
		StringBuilder result = new StringBuilder();
		int pos = N;
		result.append(num[N][0] + "\n");
		result.append(N + " ");
		while(num[pos][1] != 0) {
			result.append(num[pos][1] + " ");
			pos = num[pos][1];
		}
		System.out.println(result);
	}
}
