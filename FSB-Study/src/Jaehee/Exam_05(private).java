import test.*; //test��Ű������ Test01Ŭ������ �����´ٴ� ��!

class A05 {
	private int a; //private�� Ŭ���� ���ο����� ���� ������ �����������ڡ�
	private int b;
	void A05(){
		a = 10;
		b = 20;
	}
	void disp() {
		System.out.println("a="+a+"b="+b);
	}
}


public class Exam_05 {
	public static void main(String[]args) {
		Test01 t1 = new Test01();
		//Test01�� import�� �Ἥ test��Ű������ ������ �� �ִ�.
		
		/*
		A05 ap = new A05();
		ap.a = 100; //a�� �����ϴ� ��ü ap
		ap.b = 200;
		ap.disp();
		*/
	}
}
