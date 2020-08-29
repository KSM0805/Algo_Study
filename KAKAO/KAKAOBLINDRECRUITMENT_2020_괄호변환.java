package kakao;


public class KAKAOBLINDRECRUITMENT_2020_괄호변환 {
	public static void main(String[] args) {
//		String s = "(()())()";
		String s = "()))((()";
		System.out.println(solution(s));
	}
	private static String solution(String s) {
		if(s.equals("")) return s;
		// 1. 균형잡힌 괄호 문자열로 분리한다.
		int open = 0;
		int close = 0;
		String u = "", v = "";
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '(') {
				open++;
			} else {
				close++;
			}
			if(open == close) {
				u = s.substring(0, i + 1);
				v = solution(s.substring(i + 1, s.length()));
				break;
			}
		}
		// 2.올바른 괄호 문자열인가?
		open = 0;
		boolean correct = true;
		for (int i = 0; i < u.length(); i++) {
			if(u.charAt(i) == '(') open++;
			else {
				if(open > 0) open--;
				else {
					correct = false;
					break;
				}
			}
		}
		// 올바르지 않다면
		if(!correct) {
//			4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다. 
			StringBuilder sb = new StringBuilder();
			sb.append('(');
//			  4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다. 
			sb.append(v);
//			  4-3. ')'를 다시 붙입니다. 
			sb.append(')');
//			  4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. 
			for (int i = 1; i < u.length() - 1; i++) {
				sb.append(u.charAt(i) == '(' ? ')' : '(');
			}
//			  4-5. 생성된 문자열을 반환합니다.
			return sb.toString();
		} else {
			return u + v;
		}
	}
}
