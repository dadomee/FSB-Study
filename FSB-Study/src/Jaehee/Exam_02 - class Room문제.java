//class room Ȱ���ؼ� ��湮�� Ǯ��� 
//Room����>����(��üȭ�Ͽ� ��ɸ����)>�� �޼ҵ� ����

import java.io.*;
import java.util.*;

//Room���Ǩ�
class Room{
	int roomSu;		//���� ���� �Է�
	boolean room[];	//������ŭ �迭�����
	//false�϶��� ���, true�϶��� �����
	int roomNum;	//�Խǰ� ��ǽ� �Է¹��� ���ȣ
	Scanner in;
	
	Room(){
		in = new Scanner(System.in);
		System.out.print("���� ������ �Է� : ");
		roomSu = in.nextInt();
		room = new boolean[roomSu];
	}
	
	//�� �޼ҵ� ������
	void input() {
		do {
			System.out.print("�Խ��Ͻ� ���� ��ȣ : ");
			roomNum = in.nextInt();
		}while(roomNum < 1 || roomNum > roomSu);
		if (room[roomNum-1]) {//true���
			System.out.println(roomNum+"ȣ���� ������Դϴ�.");
		}else {//false��� true�ؼ� ��������� ������ְ� �ԽǾȳ�
			room[roomNum-1] = true;
			System.out.println(roomNum+"ȣ�ǿ� �Խ��ϼ̽��ϴ�.");
		}
	}
	void output() {
		do {
			System.out.print("����Ͻ� ���� ��ȣ : ");
			roomNum = in.nextInt();
		}while(roomNum < 1 || roomNum > roomSu);
		if (!room[roomNum-1]) {
			System.out.println(roomNum+"ȣ���� ����Դϴ�.");
		}else {
			room[roomNum-1] = false;
			System.out.println(roomNum+"ȣ�ǿ��� ����ϼ̽��ϴ�.");
		}
	}
	void view() {
		for(int i=0; i<roomSu; ++i) {
			if (room[i]) {
				System.out.printf("%dȣ���� �����\n", i+1);
			}else {
				System.out.printf("%dȣ���� ���\n", i+1);
			}
		}
	}
	void exit() {
		System.out.println("���α׷��� �����մϴ�.");
		System.exit(0);	//���α׷��� ���������� �����Ų��.
	}
}

public class Exam_02 {
	public static void main(String[] args) throws IOException {
		Room room = new Room(); //Room�޼ҵ� ��ü ������
		
		while(true) {
			System.out.print("1.�Խ� 2.��� 3.���� 4.���� : ");
			int select = System.in.read() - 48; //ScannerŬ������ ��������ʾƼ� �ƽ�Ű�ڵ�� �ԷµǴϱ�!
			System.in.skip(5);
			switch(select) {
			case 1 :	room.input(); break;
			case 2 :	room.output(); break;
			case 3 :	room.view(); break;
			case 4 :	room.exit(); break;
			default :	System.out.println("�߸������̽��ϴ�. �ٽ� �Է��� �ּ���");
			}
		}
	}
}

