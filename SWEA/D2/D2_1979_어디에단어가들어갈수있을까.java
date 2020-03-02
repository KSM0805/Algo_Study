package swea.d2;

import java.util.Arrays;
import java.util.Scanner;

public class D2_1979 {
	public static void main(String[] args) { 
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int size = sc.nextInt();
			int wordSize = sc.nextInt();
			int total = 0; // 단어가 들어갈 수 있는 자리의 수
			int[][] arr = new int [size][size];
			for (int i = 0; i < size; i++) { //행
				for (int j = 0; j < size; j++) { //열
					arr[i][j] = sc.nextInt(); // 1또는 0 받기
				}
			}
			for (int i = 0; i < size; i++) { //가로(행)에서 1찾기
				int[] arrRow = new int[size];
				for (int j = 0; j < size; j++) { //열 이동
					if(arr[i][j] == 1) { // 1이 연속되면 누적
						if(j==0) {
							arrRow[j] = 1;
						}
						else {
							arrRow[j] = arrRow[j-1] + arr[i][j];
						}
						
					}
				}
				for (int k = 0; k < size; k++) { // 길이가 3으로 끝나는 가로 칸을 찾음
					if((arrRow[k] == wordSize) && ((k==size-1)||(arrRow[k+1] == 0))) {
						total++;
					}
				}
				
			}
			for (int i = 0; i < size; i++) { //세로(열)에서 1찾기
				int[] arrRow = new int[size];
				for (int j = 0; j < size; j++) { //행 이동
					if(arr[j][i] == 1) { // 1이 연속되면 누적
						if(j==0) {
							arrRow[j] = 1;
						}
						else {
							arrRow[j] = arrRow[j-1] + arr[j][i];
						}
						
					}
				}
				for (int k = 0; k < size; k++) { // 길이가 3으로 끝나는 가로 칸을 찾음
					if((arrRow[k] == wordSize) && ((k==size-1)||(arrRow[k+1] == 0))) {
						total++;
					}
				}
				
			}
			System.out.println("#"+test_case+" "+total);
//			for (int[] is : arr) {
//				System.out.println(Arrays.toString(is));
//			}
		}//for end
	}

}
