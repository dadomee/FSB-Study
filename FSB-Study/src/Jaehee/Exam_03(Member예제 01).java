import java.util.*;
import java.io.*;

class Member{
	private String name;
	private String tel;
	
	Member(String name, String tel){
		this.name = name;
		this.tel = tel;
	}
	
	public String getName() {
		return name;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTel() {
		return tel;
	}
	
	void disp() {
		System.out.println(name +"���� ��ȭ��ȣ�� " + tel +"�� �Դϴ�.");
	}
}
class MemberMg{
	Scanner in;
	Member[] mb;
	int increment;	//�迭���� �ʰ��Ǹ� �迭�� ũ�⸦ �ø��� ����(ó���� 5���� ��������� 3�� �� �ø��Ŵ�)
	int po; //�迭�� ��ġ
	
	MemberMg(){
		in = new Scanner(System.in);
		mb = new Member[5];
		increment = 3; 
		//����ġ 3
		//mb2(8ĭ¥��)�� ���� mb�� �� �����Ͽ� ����
		po = 0;
	}
	
	void input() {
		System.out.print("�̸��� �Է� : ");
		String name = in.next();
		System.out.print("��ȭ��ȣ�� �Է� : ");
		String tel = in.next();
		mb[po] = new Member(name, tel);
		//mb[po] = input; //input���� ���� �����ϴ� name�� tel�ּҰ��� �������
		po++; //���� �ڸ��� �Է�
		if(po == mb.length) { //po�� 5�� �Ǹ�
		Member[] mb2 = new Member[mb.length+increment];
		for(int i = 0; i<mb.length; ++i) {
			mb2[i] = mb[i];
		}
		mb = mb2;
		}
	}
	void view() {
		for(int i=0; i<po; ++i) {
			mb[i].disp(); //����ϴ� �޼ҵ� ȣ��
		}
	}
	void edit() {
		System.out.print("������ ȸ���� �̸� : ");
		String name = in.next();
		for(int i=0; i<po; ++i) {
			if (name.equals(mb[i].getName())) {
				System.out.println("���� ��ȭ��ȣ�� " + mb[i].getTel() +"�� �Դϴ�.");
				System.out.print("������ ��ȭ��ȣ : ");
				mb[i].setTel(in.next());
				System.out.println(name+"���� ��ȭ��ȣ�� �����Ͽ����ϴ�.");
				return;
			}
		}
		System.out.println(name+"���� ���� ȸ���� �ƴմϴ�.");
	}
	void delete() { //�����ڸ� ���� ������ ���ܿ���
		System.out.print("������ ȸ���� �̸� : ");
		String name = in.next();
		for(int i=0; i<po; ++i) {
		if(name.equals(mb[i].getName())) { //�Է¹��� name�� ��ġ
		for(int j=1; j<po-1; ++j) { //j�� 1�϶����� �� //�� ������ �����ʹ� �������ʾƾ���
			mb[j] = mb[j+1]; //�����ڸ� ����(name�� tel) �տ� �ֱ�
		}
		po--; //������ ���ܰ���
		System.out.println(name + "ȸ������ �����Ͽ����ϴ�.");
		return;
	}
	}
		System.out.println(name+"���� ���� ȸ���� �ƴմϴ�.");
	}
	void exit() {
		System.out.println("���α׷��� �����մϴ�.");
		System.exit(0);	//���α׷��� ���������� �����Ų��.
	}
	
}

public class E {
	public static void main(String[] args) throws IOException{
		MemberMg member = new MemberMg();
		
		while(true) {
			System.out.print("1.�Է� 2.��� 3.���� 4.���� 5.���� : ");
			int select = System.in.read() - 48;
			System.in.skip(5);
			switch(select) {
			case 1 :	member.input(); break;
			case 2 :	member.view(); break;
			case 3 :	member.edit(); break;
			case 4 :	member.delete(); break;
			case 5 :	member.exit(); break;
			default :	System.out.println("�߸��Է��ϼ̽��ϴ�. �ٽ� �Է��� �ּ���!!");
			}
		}
	}
}