package algo_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17825_�ֻ���_������ {
	static int max = 0;
	static int[] moves = new int[10];
	static Horse[] horses = new Horse[4];

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim(), " ");
		for (int i = 0; i < 10; i++) {
			moves[i] = Integer.parseInt(st.nextToken());
		}
		// Map �����
		// ���� ���
		Node startNode = new Node(0, 0);
		Node endNode = new Node(0, -1);
		Node lastNode = new Node(0, 40);
		Node beforeNode = startNode;
		// �Ķ��� ��常 ���� ��Ƶα�
		Node[] blueNodes = new Node[3];
		// 2 ~ 38�� ���
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
		// 38�� ���� 40�� ��� �ձ�
		beforeNode.nextNode = lastNode;
		
		// 10�� ��忡�� �Ķ��� ������ 13�� ���
		Node node13 = new Node(0, 13);
		blueNodes[0].blueNode = node13;
		Node node16 = new Node(0, 16);
		node13.nextNode = node16;
		Node node19 = new Node(0, 19);
		node16.nextNode = node19;
		Node midNode = new Node(0, 25);
		node19.nextNode = midNode;
		
		// 20�� ��忡�� �Ķ��� ������ 22�� ���
		Node node22 = new Node(0, 22);
		blueNodes[1].blueNode = node22;
		Node node24 = new Node(0, 24);
		node22.nextNode = node24;
		node24.nextNode = midNode;
		
		// 30�� ��忡�� �Ķ��� ������ 28�� ���
		Node node28 = new Node(0, 28);
		blueNodes[2].blueNode = node28;
		Node node27 = new Node(0, 27);
		node28.nextNode = node27;
		Node node26 = new Node(0, 26);
		node27.nextNode = node26;
		node26.nextNode = midNode;
		
		// midNode - 25������ 40������
		Node node30 = new Node(0, 30);
		midNode.nextNode = node30;
		Node node35 = new Node(0, 35);
		node30.nextNode = node35;
		node35.nextNode = lastNode;
		
		// 40������ ����
		lastNode.nextNode = endNode;
		// 4���� ���� ���� ��ġ����
		for(int i = 0; i < 4; i++) {
			horses[i] = new Horse();
			horses[i].curNode = startNode;
		}
		
		// ù��° ���� �̵��Ѵ�.
		moveHorse(0, 0, 0);
		
		System.out.println(max);
	}

	//[0]: ���� ������ ��, [1]: ���� �� ��, [2]: ���� ����
	private static void moveHorse(int idx, int turn, int score) {
//		System.out.println("##start " + );
		// �� �����̱�
		// ���� ���� ���� ĭ�̸� ������ �� ����.
		if (horses[idx].curNode.value == -1) {
			max = Math.max(max, score);
			return;
		}
		// �����δ�.
		Node curNode = horses[idx].curNode;
		int curColor = horses[idx].curNode.color;
		for (int i = 0; i < moves[turn]; i++) {
			Node nextNode;
			// ����ĭ�� �Ķ����̰� �Ķ������� �����ߴٸ�
			if (horses[idx].curNode.color == 1 && curColor == 1) {
				nextNode = horses[idx].curNode.blueNode;
			} else {
				nextNode = horses[idx].curNode.nextNode;
			}
			
			horses[idx].curNode = nextNode;
			// ����ĭ�� ����ĭ�̸� ����
			if (horses[idx].curNode.value == -1) {
				break;
			}
		}
		
		// ������ ���Ŀ� �ش� ��忡 ���� �ִ��� Ȯ���ϱ�
		boolean isPossible = true;
		for (Horse h : horses) {
			if (h != horses[idx]) {
				// ���� ĭ�� �ƴѵ� ��ģ ���
				if (h.curNode.value != -1 && h.curNode == horses[idx].curNode) {
					isPossible = false;
				}
			}
		}
		// �ش� ��忡 ���� �ִٸ�
		if (!isPossible) {
			// ������ �� �� ��ġ
			horses[idx].curNode = curNode;
			return;
		}
		
		// ����ĭ�� �ƴϸ� ���� �߰�
		int maxScore = score + (horses[idx].curNode.value != -1 ? horses[idx].curNode.value : 0);
		// ��� ���� ��������
		if (turn == 9) {
			max = Math.max(max, maxScore);
		} else { // �� ���������� ���� ��
			for (int j = 0; j < 4; j++) {
				moveHorse(j, turn + 1, maxScore);
			}
		}
		// ���� �� ����ġ
		horses[idx].curNode = curNode;
	}
}

class Node {
	int color;	// ĭ�� ���� 0: �Ķ��� ��, 1: �Ķ��� 
	int value;	// ���� 0: ����, -1: ��
	Node nextNode;	// ���� �̾����� ���
	Node blueNode;	// �Ķ������� �̾����� ���
	
	Node() {}
	Node(int color, int value) {
		this.color = color;
		this.value = value;
	}
}

class Horse {
	Node curNode;	// ���� ��ġ
}