package test;

public class test02 {
	public static void main(String[] args) {
		Test01 t1 = new Test01(); //���������� private������ ������ �� �������� ����ȭ(setter�޼ҵ�,getter�޼ҵ�)�Ͽ� ���� ����!
		
		//t1.a = 100;//a�� �����ϰ�ʹٴ� ��
		//t1.b = 200;// ����ȭ�ϱ������� error�߻�!! ����ʵ忡 private�� �־� ���� �Ұ���!!
		
		t1.setA(11); //����ʵ� �缳���� setA�� �����ϰ�ʹٴ� ��
		
		System.out.println("t1.a = " + t1.getA()*10); //t1.a=11*10
	}
}
