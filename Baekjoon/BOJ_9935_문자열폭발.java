package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;

public class BOJ_9935_문자열폭발 {

	private static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder(bf.readLine().trim());
		String bomb = bf.readLine().trim();
		// Stack?
		// 한단어씩 스택에 집어넣는다 이때 bomb와 얼마나 일치하는지 체크한다.
		// 그 순서대로 LinkedList에 Integer로 저장
		// 들어오다가 5개가 완성되면 poll! => 내가 몇번째 까지 넣었는지 체크
		int cnt = 0;
		Stack<Character> st = new Stack<>();
		LinkedList<Integer> count = new LinkedList<>();
		for (int i = 0; i < sb.length(); i++) {
			// 일단 첫번째 단어를 넣는다.
			char cur = sb.charAt(i);
			// 1. cnt에 이어지는지 체크
			st.add(cur);
			if(bomb.charAt(cnt) == cur) {
				cnt++;
			} else {
				count.add(cnt);
				if(bomb.charAt(0) != cur) count.add(0);
				cnt = bomb.charAt(0) == cur ? 1 : 0;
			}
			// 2. cnt가 꽉 채워졌다면!
			if(cnt == bomb.length()) {
				for (int j = 0; j < cnt; j++) {
					st.pop();
				}
				if(count.isEmpty() || count.peekLast() == 0) cnt = 0; 
				else cnt = count.pollLast();
			}
		}
		StringBuilder result = new StringBuilder();
		while(!st.isEmpty()) {
			result.append(st.pop());
		}
		result.reverse();
		System.out.println(result.length() == 0 ? "FRULA" : result);
	}
}
