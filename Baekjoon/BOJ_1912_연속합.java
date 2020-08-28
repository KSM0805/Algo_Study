package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1912_연속합 {

	private static int result;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine().trim());
		int[][] num = new int[n][2];
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		for (int i = 0; i < n; i++) {
			num[i][0] = Integer.parseInt(st.nextToken());
			num[i][1] = num[i][0];
		}
		result = num[0][1];
		for (int i = 1; i < n; i++) {
			if(num[i][1] < num[i][0] + num[i-1][1]) {
				num[i][1] = num[i][0] + num[i-1][1];
			}
			result = Math.max(result, num[i][1]);
		}
		System.out.println(result);
	}
}
