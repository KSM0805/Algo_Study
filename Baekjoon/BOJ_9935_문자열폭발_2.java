package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class BOJ_9935_문자열폭발_2 {

	private static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder(bf.readLine().trim());
		String bomb = bf.readLine().trim();
		int bombSize = bomb.length();
		char[] arr = new char[sb.length()];
		// 한 글자씩 넣으면서 bomb가 맞으면 그만큼 감소!!
		int idx = 0;
		for (int i = 0; i < sb.length(); i++) {
			arr[idx] = sb.charAt(i);
			// 한 글자 넣고 폭발 문자열보다 크기가 크고 마지막 문자열이 폭발문자열 끝이면!
			if(idx + 1 >= bombSize && arr[idx] == bomb.charAt(bombSize - 1)) {
				boolean check = true;	// 폭발 가능한지 체크
				for (int j = 0; j < bombSize; j++) {
					if(arr[idx - j] != bomb.charAt(bombSize - 1 - j)) {
						check = false;
						break;
					}
				}
				if(check) idx -= bombSize;
			}
			idx++;
		}
		StringBuilder result = new StringBuilder();
		if(idx == 0) result.append("FRULA");
		for (int i = 0; i < idx; i++) {
			result.append(arr[i]);
		}
		System.out.println(result);
	}
}
