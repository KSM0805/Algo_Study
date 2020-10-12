package kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class KAKAOBLINDRECRUITMENT_2019_오픈채팅방 {
	public static void main(String[] args) {
		String[] str = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		solution(str);
		
	}

	private static void solution(String[] record) {
		StringTokenizer st;
		HashMap<String, String> user = new HashMap<>();
		ArrayList<String> str = new ArrayList<>();
		for (int i = 0; i < record.length; i++) {
			st = new StringTokenizer(record[i]," ");
			String mode = st.nextToken();
			String uid = st.nextToken();
			String nickname;
			switch (mode) {
			case "Enter":
				nickname = st.nextToken();
				user.put(uid, nickname);
				str.add("add " + uid);
				break;
			case "Leave":
				str.add("leave " + uid);
				break;
			case "Change":
				nickname = st.nextToken();
				user.put(uid, nickname);
				break;
			}
		}
		String[] answer = new String[str.size()];
		for (int i = 0; i < str.size(); i++) {
			st = new StringTokenizer(str.get(i));
			switch (st.nextToken()) {
			case "add":
				answer[i] = user.get(st.nextToken()) + "님이 들어왔습니다.";
				break;
			case "leave":
				answer[i] = user.get(st.nextElement()) + "님이 나갔습니다.";
				break;
			}
		}
		System.out.println(Arrays.toString(answer));
	}
}
