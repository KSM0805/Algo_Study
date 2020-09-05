package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_3954_Brainfk인터프리터 {

	private static int tc;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(bf.readLine().trim());
		int Sm, Sc, Si, pos, pointer, cmdCnt;
		String command, input;
		int[] mem, loop;
		for (int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			Sm = Integer.parseInt(st.nextToken());
			mem = new int[Sm];	// 0으로 초기화
			Sc = Integer.parseInt(st.nextToken());
			Si = Integer.parseInt(st.nextToken());
			command = bf.readLine().trim();	//길이 : Sc
			input = bf.readLine().trim();	//길이 : Si
			HashMap<Integer, Integer> blanket = new HashMap<>();	//괄호 검사하기
			Stack<Integer> stack = new Stack<>();
			for (int i = 0; i < Sc; i++) {
				if(command.charAt(i) == '[') stack.add(i);
				else if(command.charAt(i) == ']') {
					int open = stack.pop();
					blanket.put(i, open);
					blanket.put(open, i);
				}
			}
			pos = 0;		// 명령어 위치
			pointer = 0;	// 포인터 위치
			//일단 명령을 수행한다.
			/*
			    -	포인터가 가리키는 수를 1 감소시킨다. (modulo 28)
				+	포인터가 가리키는 수를 1 증가시킨다. (modulo 28)
				<	포인터를 왼쪽으로 움직인다.(1 감소)
				>	포인터를 오른쪽으로 움직인다.(1 증가)
				[	만약 포인터가 가리키는 수가 0과 같다면, [와 짝을 이루는 ]로 점프한다.
				]	만약 포인터가 가리키는 수가 0이 아니라면, ]와 짝을 이루는 [로 점프한다.
				.	포인터가 가리키는 수를 출력한다.
				,	문자 하나를 읽고 포인터가 가리키는 곳에 저장한다. 입력의 마지막(EOF)인 경우에는 255를 저장한다.
			 */
			// 괄호 위치에서 포인터와 포인터에 해당하는 숫자가 같으면 loop!
			// 괄호위치 - 4096, 포인터 - 100000, 포인터 숫자 - 0~255
			cmdCnt = 0;
			loop = new int[Sc];
			while(pos < Sc && cmdCnt < 50000001) {
				cmdCnt++;
//				System.out.println(Arrays.toString(mem));
				switch (command.charAt(pos)) {
				case '-':
					mem[pointer] = mem[pointer] == 0 ? 255 : mem[pointer] - 1;
					break;
				case '+':
					mem[pointer] = mem[pointer] == 255 ? 0 : mem[pointer] + 1;
					break;
				case '<':
					pointer--;
					if(pointer < 0) pointer = Sm - 1;
					break;
				case '>':
					pointer++;
					if(pointer >= Sm) pointer = 0;
					break;
				case '[':
					if(mem[pointer] == 0) pos = blanket.get(pos) - 1;
					break;
				case ']':
					if(mem[pointer] != 0) {
						loop[pos]++;
						pos = blanket.get(pos) - 1;
					}
					break;
				case '.':
					break;
				case ',':
					if(input.length() > 0) {
						mem[pointer] = input.charAt(0);
						input = input.substring(1);
					} else {
						mem[pointer] = 255;
					}
					break;
				}
				pos++;
			}
			if(cmdCnt >= 50000001) {
				for (int i = Sc - 1; i > -1; i--) {
					if(loop[i] > 0) {
						System.out.println("Loops " + blanket.get(i) + " " + i);
						break;
					}
				}
//				System.out.println("Loop " + loop[0] + " " + loop[1]);
			}
			else System.out.println("Terminates");
		}
	}
}
