class A10{ //����������ڷ���(����ü�� �Ǿտ��� �� �� ����)
	int a;
	int b;
	int c, d, e, f, g;
	//��� ����ʵ尪�� this.�� JVM�� ���ؼ� �ڵ����� ����
	A10(){		this.a=10;	this.b=20;
				this.c=30;	this.d=40;	this.e=50;
				this.f=60;	this.g=70;
	}
	A10(int a){
		this(); //�Ű����� ���� �����ڸ� ȣ��(=a���� �� ȣ��) - �������� ù��° �⿡�� ��� ����
		//��ü �����߿��� ������this�� �ٸ� ������ ȣ�� ����!
		this.a=a;}//����ʵ� a�� �̸��� ���ٸ�, ����ʵ� ������ �տ� this.�� ���̸� ����ʵ带 ����Ų��.
	//����ʵ�� �̸��� ������ this.�� JVM�� �ڵ� ���� �Ұ���
	A10(int x, int y){
		this(x); //�Ű����� �ϳ� �ִ� ������ ȣ��(=���� A10(int a){}���� ȣ��)
		this.b=y;}
	
	void disp() {
		System.out.println("a="+this.a+"b="+this.b);
	}
}
	
public class Exam_10 {
	public static void main(String[]args) {
		A10 ap = new A10();// A10()�����ڷ� ap��ü�� �����Ѵ�
		ap.disp();
		A10 bp = new A10(100); //A10(int x)�����ڸ� bp��ü�� �����Ѵ�
		bp.disp();
		A10 cp = new A10(100,200); //A10(int x, int y)�����ڸ� cp��ü�� �����Ѵ�
		cp.disp();
	}
}
