package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class D3_1244_2 {
	private static int Change;
	private static String N;
	private static int[] num;
	private static int len;
	private static int[] targetNum;

	public static void main(String[] args) throws Exception { 
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(bf.readLine().trim());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = st.nextToken();
			Change = Integer.parseInt(st.nextToken());
			len = N.length();
			
			num = new int[len];
			
			int number = Integer.parseInt(N);
			
			for (int i = len-1; i >=0; i--) {
				num[i] = number % 10;
				number = number / 10;
			}
			targetNum = Arrays.copyOf(num, len);
			//1. 오름차 순으로 정렬
			Arrays.sort(targetNum); //원하는 상금

			int start = 0;
			int pos = -1,posSub = -1; // 숫자를 바꿀 자리
			int tmp; // 교환할 숫자
			boolean ch = false;
			//2. 횟수만큼 교환
			top:
			for (int i = Change; i > 0; i--) {
				//3. 가장 중요한 건 높은 자릿수에 높은 숫자가 오는것
				ch = false;
				for (int j = start; j < len-1; j++) {
					//3-1. 높은 숫자부터 교환
					int max = targetNum[len-1-j];
					if( max!= num[j] ) { // 숫자가 같지 않으면
						posSub = -1;
						for (int k = j+1; k < len; k++) {
							if(max == num[k]) { //숫자 위치를 찾으면
								pos = k;
								if(num[j] == targetNum[len-1-k]) {
									posSub = k;
							}
								//같은 위치라도 변경횟수가 없으면 작은 수가 오른쪽으로 가는게 유리
								if(posSub!= -1 && posSub > pos-i) {
									pos = posSub;
								}
							}
						}
						tmp = num[j]; //숫자 위치 바꾸기
						num[j] = num[pos];
						num[pos] = tmp;
						start = j+1; //시작 위치 + 1;
						ch = true;
						break;
					}
				}
				//4. 만약 정렬과 같은데 횟수가 남았다면 뒤에서부터 교환
				if(!ch) { // 배열이 동일하고 교환 횟수가 남으면
					for (int a = 0; a < len-1; a++) {//동일 숫자가 있을때
						for (int b = a+1; b < len; b++) {
							if(num[a]==num[b]) break top;
						}
					}
					for (int j = i; j > 0; j--) { //동일숫자가 없을때
						tmp = num[len-2];
						num[len-2] = num[len-1];
						num[len-1] = tmp;
					}
					break;
				}
			}
			System.out.print("#"+test_case+" ");
			for (int a: num) {
				System.out.print(a);
			}
			System.out.println();
		}//for end
	}

}