package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Jungol_1141_stack {
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> st = new Stack<Integer>();
		//예외 처리는 필수다.
//		String s = bf.readLine(); //String
		int num = Integer.parseInt(bf.readLine().trim()); // 문자열을 정수로 변환,trim() 공백 제거
		
		long sum = 0;
		//나를 볼 수 있는 소를 체크
		// 1. 스택에 마지막에 있는 아이가 나를 볼 수 있다면 push
		// 2. 못 보는 애라면 나를 볼 수 있을 때까지 pop
		
		int cow = Integer.parseInt(bf.readLine().trim());
		st.push(cow);
		for (int i = 1; i < num; i++) {
			cow = Integer.parseInt(bf.readLine().trim());
			
			while(!st.isEmpty() && cow>=st.peek()) {
				st.pop();
			}
			sum += st.size();
			st.push(cow);
		}
		System.out.println(sum);
	}

}
