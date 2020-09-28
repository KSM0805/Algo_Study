package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17610_양팔저울 {

	private static int k;
	private static int[] gi;
	private static boolean[] possibleNum;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(bf.readLine().trim());
		
		gi = new int[k];
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		int max = 0;
		for (int i = 0; i < k; i++) {
			gi[i] = Integer.parseInt(st.nextToken());
			max += gi[i];
		}
		possibleNum = new boolean[max+1];
		for (int i = 1; i < (1 << k); i++) {	// 비트마스킹으로 1인 것만 사용한다고 가정
			dfs(i, -1, 0);
		}
		int result = 0;
		for (int i = 1; i <= max; i++) {
			if(!possibleNum[i]) {
				result++;
			}
		}
		System.out.println(result);
	}

	private static void dfs(int num, int pos, int sum) {
		for (int i = pos + 1; i < k; i++) {
			if((num & (1 << i)) != 0) {	// 사용 가능한 경우
				dfs(num, i, sum + gi[i]);
				dfs(num, i, sum - gi[i]);
				return;
			}
		}
		possibleNum[Math.abs(sum)] = true;
	}
}
