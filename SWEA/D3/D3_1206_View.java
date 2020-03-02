package swea.d3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D3_1206 {
	public static void main(String[] args) throws FileNotFoundException { 
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
//		int T;
//		T=sc.nextInt();

		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int rowSize = sc.nextInt();
			int[] arr = new int [rowSize];
			long result = 0;
			for (int i = 0; i <rowSize; i++) {
				arr[i] = sc.nextInt();
			}
			int min; //조망권이 확보된 건물의 층 갯수
			for (int i = 2; i < rowSize-2; i++) { 
				min = 256;
				for (int j = 1; j < 3; j++) { // 양 옆으로 두 칸 검색
					if((arr[i]-arr[i-j] > 0) && (arr[i]-arr[i+j] > 0)) {
						min = Math.min(min, arr[i]-arr[i-j]);
						min = Math.min(min, arr[i]-arr[i+j]);
					}
					else {
						min = 0;
						break;
					}
				}
				result += min;
			}
			System.out.println("#"+test_case+" "+result);
		}//for end
	}

}
