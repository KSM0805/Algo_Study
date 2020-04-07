package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_9760_PokerGame {
	private static String numSort = "A23456789TJQK";
	private static String shapeSort = "SDHC";
	private static String[] ans = {" ", "Straight Flush", "Four of a Kind", "Full House", "Flush"
								   , "Straight", "Three of a kind", "Two pair", "One pair"};
	private static int[] num;
	private static int[] shape;
	private static int result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			shape = new int[4];
			num = new int[13];
			String str;
			for (int i = 0; i < 5; i++) {
				str = st.nextToken();
				shape[shapeSort.indexOf(str.charAt(0))]++;
				num[numSort.indexOf(str.charAt(1))]++;
			}
			result = Integer.MAX_VALUE;
			/*
			 * 1. Straight Flush : 모두 동일한 슈트에 랭크(값)가 모두 연속적이다(여기서는 로얄 플러쉬를 포함한다. 로얄 플러쉬는 모두 동일한 슈트에 T, J, Q, K, A를 갖는다).
			 * 2. Four of a Kind : 5장 중 4개의 랭크(값)가 모두 같다.
			 * 3. Full House : 3장의 동일한 랭크(값)와 다른 랭크(값)의 동일한 2장으로 구성된다.
			 * 4. Flush : 5장이 모두 동일한 슈트다.
			 * 5. Straight : 다른 슈트가 섞여있으며 랭크(값)가 모두 연속적이다.
			 * 6. Three of a kind : 동일한 랭크(값)가 3장이다(1,2,3,3,3).
			 * 7. Two pair : 동일한 랭크(값) 2장씩 두개의 랭크다(2,6,6,3,3).
			 * 8. One pair : 동일한 랭크가 2장이다(2,4,5,5,7).
			 * 9. High card : 1~8번에 해당하지 않는다.  
			 */
			for (int i = 0; i < 4; i++) {
				if(shape[i] == 5) {
					if(num[0] == 1 && num[9] == 1) { // 로얄 스트레이트 플러쉬
						for (int j = 10; j < 13; j++) {
							if(num[j] != 1) break;
							if(j == 12) result = 1;
						}
					}else{
						for (int j = 0; j < 9; j++) {
							if(num[j] == 1) {
								for (int k = j+1; k < j+5; k++) {
									if(num[k] != 1) break;
									if(k == j+4) result = 1;
								}
							}
						}
					}
					if(result != 1) result = 4;
					break;
				}
			}
			if(result != 1) {
				for (int i = 0; i < 13; i++) {
					if(num[i] == 4) {
						result = Math.min(2, result);
						break;
					}
					if(num[i] == 3) {
						for (int j = i+1; j < 13; j++) {
							if(num[j] == 2) {
								result = Math.min(3, result);
								break;
							}
						}
						result = Math.min(6, result);
						break;
					}
					if(num[i] == 2) {
						for (int j = i+1; j < 13; j++) {
							if(num[j] == 3) {
								result = Math.min(3, result);
								break;
							}
							if(num[j] == 2) {
								result = Math.min(7, result);
								break;
							}
						}
						result = Math.min(8, result);
						break;
					}
					if(num[i] == 1 && i < 9) {
						if(i == 0) {
							for (int j = 9; j < 13; j++) {
								if(num[j] != 1) break;
								if(j == 12) result = Math.min(5, result);
							}
						}
						for (int j = i + 1; j < i + 5; j++) {
							if(num[j] != 1) break;
							if(j == i+4) result = Math.min(5, result);
						}
					}
				}
			}
			System.out.print("#"+test_case+" ");
			if(result == Integer.MAX_VALUE) System.out.println("High card");
			else System.out.println(ans[result]);
		}//testcase end
	}//main end
}

