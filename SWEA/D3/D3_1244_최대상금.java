package swea.d3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class D3_1244 {
	
	public static void main(String[] args) throws FileNotFoundException { 
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int number = sc.nextInt();
			int change = sc.nextInt();
			String str = Integer.toString(number);
			String[] num = str.split(""); // 순서대로 숫자 한 글자씩 나누기
			String[] num2  = str.split("");
			
			int len = num.length;
			//1. 오름차 순으로 정렬
			Arrays.sort(num2);
			int start = 0;
			int pos = -1; // 숫자를 바꿀 자리
			String tmp; // 교환할 숫자
			boolean ch = false;
			//2. 횟수만큼 교환
			top:
			for (int i = change; i > 0; i--) {
				//3. 가장 중요한 건 높은 자릿수에 높은 숫자가 오는것
				ch = false;
				for (int j = start; j < len-1; j++) {
					//3-1. 높은 숫자부터 교환
					if(!(num2[len-1-j].equals(num[j]))) { // 숫자가 같지 않으면
						for (int k = j+1; k < len; k++) {
							if(num2[len-1-j].equals(num[k])) { //숫자 위치를 찾으면
								pos = k;
								if(num[j].equals(num2[len-1-k])) {
									break;
								}
							}
							if((num2[len-1-j].equals(num[k]))&&(num[j].equals(num[k]))){
								pos = k;
								break;
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
							if(num[a].equals(num[b])) break top;
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
			for (String string : num) {
				System.out.print(string);
			}
			System.out.println();
		}//for end
	}

}