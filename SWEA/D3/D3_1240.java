package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_1240 {
	private static int N;
	private static int M;
	private static String[] no = {"0001101","0011001","0010011","0111101","0100011","0110001","0101111","0111011","0110111","0001011"};
//	private static String[] No = {"3211", "2221", "2122", "1411", "1132", "1231", "1114", "1312", "1213", "3112"};
	private static String[] str;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			str = new String[N];
			for (int i = 0; i < N; i++) {
				str[i] = bf.readLine().trim();
			}
			int idx = -1;
			int row = 0;
			while(idx<0) {
				idx = str[row].lastIndexOf('1');
				row++;
			}
			idx = idx-55; //56자릿수니까 0번째 자리부터
			row -= 1; //해당하는 행
			StringBuilder totalString = new StringBuilder();
			totalString.append(str[row].substring(idx, idx+56)); //56 이진수 저장
			int[] target = new int[8]; //암호코드 저장배열
			StringBuilder sb;
			//이진수를 그대로 문자열로 처리해서 비교했으면 훨씬 간단할것
			for (int i = 0; i < 8; i++) {
				sb = new StringBuilder(totalString.substring(0, 7)); //앞에서부터 7자릿수 저장
				totalString.replace(0, 7, ""); //앞에서부터 7자리 제거
				for (int k = 0; k < 10; k++) {
					if(no[k].contains(sb)) { //배열이 같은 경우를 찾아서 코드로 전환
						target[i]=k;
						break;
					}
				}
			}//암호코드 찾기 끝
			int odd = 0;
			int eve = 0;
			for (int i = 0; i <8; i++) {
				if((i+1) % 2 ==0) eve += target[i]; //짝수
				else odd += target[i]; //홀수
			}
			System.out.print("#"+test_case+" ");
			int result = odd*3 + eve;
			if(result % 10 == 0) {
				System.out.println(odd+eve);
			}
			else System.out.println("0");
		}//for end
	}
}
