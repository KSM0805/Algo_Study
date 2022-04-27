package algo_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17825_주사위_윷놀이 {
	static int max = 0;
	static int[] moves = new int[10];
	static Horse[] horses = new Horse[4];

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim(), " ");
		for (int i = 0; i < 10; i++) {
			moves[i] = Integer.parseInt(st.nextToken());
		}
		// Map 만들기
		// 시작 노드
		Node startNode = new Node(0, 0);
		Node endNode = new Node(0, -1);
		Node lastNode = new Node(0, 40);
		Node beforeNode = startNode;
		// 파란색 노드만 따로 담아두기
		Node[] blueNodes = new Node[3];
		// 2 ~ 38번 노드
		for(int i = 2, blueIndex = 0; i <= 38; i += 2) {
			Node node = new Node(0, i);
			if(i == 10 || i == 20 || i == 30) {
				node.color = 1;
				blueNodes[blueIndex] = node;
				blueIndex++;
			}
			beforeNode.nextNode = node;
			beforeNode = node;
		}
		// 38번 노드와 40번 노드 잇기
		beforeNode.nextNode = lastNode;
		
		// 10점 노드에서 파란색 방향의 13점 노드
		Node node13 = new Node(0, 13);
		blueNodes[0].blueNode = node13;
		Node node16 = new Node(0, 16);
		node13.nextNode = node16;
		Node node19 = new Node(0, 19);
		node16.nextNode = node19;
		Node midNode = new Node(0, 25);
		node19.nextNode = midNode;
		
		// 20점 노드에서 파란색 방향의 22점 노드
		Node node22 = new Node(0, 22);
		blueNodes[1].blueNode = node22;
		Node node24 = new Node(0, 24);
		node22.nextNode = node24;
		node24.nextNode = midNode;
		
		// 30점 노드에서 파란색 방향의 28점 노드
		Node node28 = new Node(0, 28);
		blueNodes[2].blueNode = node28;
		Node node27 = new Node(0, 27);
		node28.nextNode = node27;
		Node node26 = new Node(0, 26);
		node27.nextNode = node26;
		node26.nextNode = midNode;
		
		// midNode - 25점에서 40점까지
		Node node30 = new Node(0, 30);
		midNode.nextNode = node30;
		Node node35 = new Node(0, 35);
		node30.nextNode = node35;
		node35.nextNode = lastNode;
		
		// 40점에서 도착
		lastNode.nextNode = endNode;
		// 4개의 말은 시작 위치에서
		for(int i = 0; i < 4; i++) {
			horses[i] = new Horse();
			horses[i].curNode = startNode;
		}
		
		// 첫번째 말이 이동한다.
		moveHorse(0, 0, 0);
		
		System.out.println(max);
	}

	//[0]: 현재 움직일 말, [1]: 현재 턴 수, [2]: 현재 점수
	private static void moveHorse(int idx, int turn, int score) {
//		System.out.println("##start " + );
		// 말 움직이기
		// 현재 말이 도착 칸이면 움직일 수 없다.
		if (horses[idx].curNode.value == -1) {
			max = Math.max(max, score);
			return;
		}
		// 움직인다.
		Node curNode = horses[idx].curNode;
		int curColor = horses[idx].curNode.color;
		for (int i = 0; i < moves[turn]; i++) {
			Node nextNode;
			// 현재칸이 파란색이고 파란색에서 시작했다면
			if (horses[idx].curNode.color == 1 && curColor == 1) {
				nextNode = horses[idx].curNode.blueNode;
			} else {
				nextNode = horses[idx].curNode.nextNode;
			}
			
			horses[idx].curNode = nextNode;
			// 다음칸이 도착칸이면 종료
			if (horses[idx].curNode.value == -1) {
				break;
			}
		}
		
		// 움직인 이후에 해당 노드에 말이 있는지 확인하기
		boolean isPossible = true;
		for (Horse h : horses) {
			if (h != horses[idx]) {
				// 도착 칸이 아닌데 겹친 경우
				if (h.curNode.value != -1 && h.curNode == horses[idx].curNode) {
					isPossible = false;
				}
			}
		}
		// 해당 노드에 말이 있다면
		if (!isPossible) {
			// 움직인 말 원 위치
			horses[idx].curNode = curNode;
			return;
		}
		
		// 도착칸이 아니면 점수 추가
		int maxScore = score + (horses[idx].curNode.value != -1 ? horses[idx].curNode.value : 0);
		// 모든 턴을 돌았으면
		if (turn == 9) {
			max = Math.max(max, maxScore);
		} else { // 다 못돌았으면 다음 턴
			for (int j = 0; j < 4; j++) {
				moveHorse(j, turn + 1, maxScore);
			}
		}
		// 현재 말 원위치
		horses[idx].curNode = curNode;
	}
}

class Node {
	int color;	// 칸의 색깔 0: 파란색 외, 1: 파란색 
	int value;	// 점수 0: 시작, -1: 끝
	Node nextNode;	// 다음 이어지는 노드
	Node blueNode;	// 파란색으로 이어지는 노드
	
	Node() {}
	Node(int color, int value) {
		this.color = color;
		this.value = value;
	}
}

class Horse {
	Node curNode;	// 현재 위치
}