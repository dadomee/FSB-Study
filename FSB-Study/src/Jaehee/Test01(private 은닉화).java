package test;

public class Test01 {
	private int a;
	private int b;
	public Test01() {
		a=10;
		b=20;
	}

	
	//setter�޼ҵ� : ����ʵ� �缳��
	public void setA(int a){ //set+����ʵ� a�� ���ļ� �ռ����� setA��� �̸��� ���°Ŵ�.
				//�ռ���
		if(a%2 != 0) {
		this.a = a;
		}
	}
	public void setB(int b){
		if(a%2 != 0) {
		this.b = b;
		}
	}
	//getter�޼ҵ� : ����ʵ尪 ������
	public int getA(){ return a; } //get+����ʵ� a�� ���ļ� �ռ����� getA��� �̸��� ���°Ŵ�.
	public int getB(){ return b; }
	
	public void disp() {
		System.out.println("a="+a+"b="+b);
	}
}
