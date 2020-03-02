package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;

public class D3_5986_새샘이와세소수 {
	private static int result;
	private static int N;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim()); //소수의 집합으로 나타낼 숫자 7~999
			//1. 먼저 소수구하기
			//2. 소수들을 순서대로 더하면서 N이 되는 경우의 수 합하기
			HashSet<Integer> prime = new HashSet<>();
			for (int i = 2; i < N; i++) {
				prime.add(i); // 2~N까지의 수를 넣는다.
			}
			for (int i = 2; i < (N/2); i++) {
				int num = i*2;
				while(num<N) {
					if(prime.contains(num)) {
						prime.remove(num);
					}
					num +=i;
				}
			}
			Object[] primeNumbers = prime.toArray();
			int size = primeNumbers.length;
			result = 0;
			LinkedList<int[]> q = new LinkedList<>();
			for (int i = 0; i < size; i++) {
				q.offer(new int[] {i, (int) primeNumbers[i] , 1}); //현재 위치와 총합, cnt
			}
			while(!q.isEmpty()) {
				int[] arr = q.poll();
				if(arr[2] == 2) {
					if(N-arr[1]>(int)primeNumbers[arr[0]] && prime.contains(N-arr[1])) {
						result++;
					}
					continue;
				}
				for (int i = arr[0]; i < size; i++) {
					if(arr[1] + (int)primeNumbers[i] <= N) {
						q.offer(new int[] {i,arr[1] + (int)primeNumbers[i], arr[2]+1});
					}
				}
			}
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end
}
