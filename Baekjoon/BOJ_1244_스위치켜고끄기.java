package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1244_스위치켜고끄기 {

	private static int N;
	private static int[] switches;
	private static int studentNum;
	private static int[][] students;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim());	// 100 이하
		switches = new int[N + 1];
		switches[0] = -1;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		for (int i = 1; i <= N; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		studentNum = Integer.parseInt(bf.readLine().trim());	// 100이하
		students = new int[studentNum][2];
		for (int i = 0; i < studentNum; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			students[i][0] = Integer.parseInt(st.nextToken());
			students[i][1] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < studentNum; i++) {
			// 남학생
			if(students[i][0] == 1) {
				for (int j = students[i][1]; j <= N; j+=students[i][1]) {
					switches[j] = switches[j] == 1 ? 0 : 1;
				}
				//여학생
			} else {
				int pos = students[i][1];
				int cnt = -1;
				// 점차 넓어진다.
				int r = 1;
				while(pos - r > 0 && pos + r <= N && switches[pos-r] == switches[pos+r]) {
					r++;
				}
				if(cnt < r - 1) {
					cnt = r - 1;
				}
				if(pos > 0) {
					for (int k = pos - cnt; k <= pos + cnt; k++) {
						switches[k] = switches[k] == 1 ? 0 : 1;
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(switches[i] + " ");
			if(i % 20 == 0) {
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}
