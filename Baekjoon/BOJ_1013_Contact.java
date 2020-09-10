package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_1013_Contact {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		StringBuilder sb;
		for (int i = 0; i < T; i++) {
			sb = new StringBuilder(bf.readLine().trim());
			while(sb.length() > 0) {
				System.out.println(sb);
				if(sb.length() > 3 && sb.substring(0,3).equals("100")) {
					int one = sb.indexOf("1", 1);
					if(one == -1) break;	// 1이 없으면 100+1+가 아니므로 탈락
					int end = sb.indexOf("0", one + 1) == -1 ? sb.length() : sb.indexOf("0", one + 1);	// 연속된 1이 끝나는지점
					if(one != end -1 && end + 2 <= sb.length() && sb.substring(end - 1, end + 2).equals("100")) end--;
					sb.delete(0, end);
					continue;
				}
				if(sb.length() > 1 && sb.substring(0, 2).equals("01")) {
					sb.delete(0, 2);
					continue;
				}
				break;
			}
			System.out.println(sb.length() == 0 ? "YES" : "NO");
		}
	}
}
