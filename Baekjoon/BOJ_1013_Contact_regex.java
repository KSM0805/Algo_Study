package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_1013_Contact_regex {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int i = 0; i < T; i++) {
			System.out.println(bf.readLine().trim().matches("(100+1+|01)+") ? "YES" : "NO");
		}
	}
}
