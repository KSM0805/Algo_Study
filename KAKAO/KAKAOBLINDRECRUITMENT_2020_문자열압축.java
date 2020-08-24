package kakao;

public class KAKAOBLINDRECRUITMENT_2020_문자열압축 {
	public static void main(String[] args) {
		String s = "A";
//		String s = "aabbaccc";
		System.out.println(solution(s));
	}

	private static int solution(String s) {
		int result = Integer.MAX_VALUE;
		for (int i = 1; i <= s.length() + 1 / 2; i++) {
			// i 는 자르는 단위야
			// 1. 기준점이 어디까지 반복되나?
			int len = i;
			int pos = i;	// 비교하는 시작 지점
			String str = s.substring(0,i);	// 처음 비교하는 문자열
			while(pos + i <= s.length()) {
				if(len > result) break;
				String str2 = s.substring(pos, pos+i);	// 그 다음 문자열
				int repeat = 1;
//				System.out.println("new - str : " + str +" str2 : " + str2);
				while(str.equals(str2)) {
					// 같다는 전재조건
					// 같으니까 반복수 +1
					repeat++;
					// 다음 문자열도 같을까?
					pos += i;	// 다음 비교시작 지점
					if(pos + i > s.length()) break;	// 다음 지점이 없으면
					str2 = s.substring(pos, pos + i);
				}
				// 2. 반복되지 않을 때는 반복되지 않은 지점이 기준이 된다!
				// 일단 현재 기준 문자열의 길이와 반복한 숫자의 길이를 더한다.
//				System.out.println("repeat : " + repeat + " str: " + str + " str2 : " + str2);
				if(repeat != 1) len += Integer.toString(repeat).length();
				// 문자열의 길이를 초과한 경우
				System.out.println("repeat : " + repeat + " str : " + str + " i : " + i + " len : " + len + " pos : " + pos);
				if(pos + i <= s.length()) {
					len += i;
					str = s.substring(pos, pos+i);
					pos += i;
				}
			}
			// 3. 그리고 비교할 대상이 없는 만큼 또 추가한다.
			len += s.length() - pos;
//			System.out.println(len);
			result = Math.min(result, len);
		}
		return result;
	}
}
