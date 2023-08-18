import java.util.*;
import java.io.*;

public class Exam_01 {
	public static void main(String[] args) throws IOException{ //read������ �ۼ�
		
		//��Ģ�����ڸ� �Է��Ͽ� �� ���� ����ϴ� ���α׷�
		
		int su1 = input();
		int su2 = input();
		
		
		//��Ģ���� �Է�
		System.out.print("��Ģ �����ڸ� �Է� : ");
		char op = (char)System.in.read(); //read�� �Ѱ��� �о���ڴٴ� ��
		//¦�� throws IOException < import java.io.*;
		int res = 0;

		//��Ģ���� (+-*/)
		switch(op) {
		case '+' : res = sum(su1, su2); break;
		case '-' : res = min(su1, su2); break;
		case '*' : res = mul(su1, su2); break;
		case '/' : res = div(su1, su2); break;
		default :
			System.out.println("�߸��Է��ϼ̽��ϴ�.");
			System.exit(0); //switch~case������ ����
		}
		System.out.printf("%d %c %d = %d\n", su1, op, su2, res); //d�� ����, f�� �Ǽ�, c�� ����, s�� ���ڿ�?????
}
		public static int sum(int a, int b) {
			return a+b;
		}
		public static int mul(int a, int b) {
			return a*b;
		}
		public static int min(int a, int b) {
			if(a>b) return a-b;
			return b-a; //else�� ���
		}
		public static int div(int a, int b)	{
			if(a==0) return b; //b�� res�� ��
			//if(a==0) {a=1; return b/a;}�� ����
			if(b==0) return a; //a�� res�� ��
			if(abs(a) > abs(b)) return a/b; //���밪 ū ���� �������� ������
			else return b/a;
		}
		//���밪 ���ϴ� �޼ҵ�(�ٽ�!!!)
		public static int abs(int a) { //���밪(abs) ���ϴ¸޼ҵ�
			if (a<0) return a*-1;
			return a;
		}
		
		//�Է� ���� ��
		public static int input() {
			Scanner in = new Scanner(System.in);
			System.out.print("���� �Է� : ");
			int su = in.nextInt();
			return su;
		}
	}

