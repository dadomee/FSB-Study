import java.util.Scanner;

public class Exam_01 {
	public static void main(String[] args) {
		
		/*
		�ſ� ū ���� ����(�ڸ��� �����ؾ���) 
		ù��° �� �Է� : 1234560123456 <<<������ ������ �ȵǰ� ���ڿ��� �޾ƾ���
		�ι�° �� �Է� : 1
		ù��° �� : 1234560123456
		�ι�° �� : 0000000000001
		��� : 1234560123455
		*/
		
		//���ڿ��� ���
		String su1 = input("ù��°");
		String su2 = input("�ι�°");
		
		//
		if (su1.length() > su2.length()) su2 = po(su1, su2); //po();�޼ҵ�
		else su1 = po(su2, su1);
		
		int po = 0; //���ڸ����� ���� �������� 1, �ƴϸ� 0
		
		String res = ""; //�����(res)�� �ʱⰪ�� ����(0)
		
		for(int i=su1.length()-1; i>=0; --i) {
			char f1 = (char)(su1.charAt(i) - po); //���� ���ڸ����� ���� �����;��ϱ⶧���� <<< ����)100-1=099
			char f2 = su2.charAt(i);
			if (f1<f2) {
				po = 1; //���ڸ����� ���� �������� 1, �ƴϸ� 0
				f1 += 10;
				if (i==0 && po==1) {
					//����
				}
			}else {
				po = 0;
			}
			res = (f1-f2) + res; //�����(res) �տ� (f1-f2) ���ٺ��̱�
			// f1�� f2�� char���̱⶧���� �ƽ�Ű�ڵ�� ���� ����
		}
		System.out.println("ù��°�� : " + su1);
		System.out.println("�ι�°�� : "+ su2);
		System.out.println("���       : " + res);
	}
	
	public static String po(String big, String small) {
		String str = small;
		for(int i=0; i<big.length()-small.length(); ++i) {
			str = "0" + str; //������(su2) ���ڸ��� �������� ä��
		}
		return str;
	}
	
	public static String input(String str) {
		Scanner in = new Scanner(System.in);
		System.out.print(str+" ���� �Է� : ");
		String su = in.next();
		return su;
	}
}

