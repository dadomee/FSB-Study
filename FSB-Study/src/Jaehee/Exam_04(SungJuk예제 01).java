//�ο����� �Է¹޾� �� �ο��� ��ŭ �̸�, ����, ���������� �Է¹ް� �̸�, ����, ������ ��½����ִ� ���α׷�
//(��, �̸�, ����, ����, ����, ������ �ϳ��� �ڷ������� ���� Ŭ������ �����, �� Ŭ������ �̿��Ͽ� ������ּ���.

//����ȭ �������
import java.util.Scanner;

class SungJuk{
	private String name;
	private int kor, eng, tot, rank;
	
	SungJuk(String name, int kor, int eng){
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		tot = kor+eng;
		rank=1; //���� �ʱⰪ 1��
	}
	
	public String getName() {return name;} //�缳��(set) ���� ����ʵ尪�� ������(get) 
	
	public void setKor() {
		this.kor = kor;}
	public int getKor() {return kor;}
	
	public void setEng() {
		this.eng = eng;}
	public int getEng() {return eng;}
	
	public int getTot() {return tot;} //�缳��(set) ���� ����ʵ尪�� ������(get) 
	public int getrank() {return rank;} //�缳��(set) ���� ����ʵ尪�� ������(get) 
	public void clearRank() { 
		rank = 1;
	}
	public void plusRank() { 
		rank++;
	}
	
	void disp() {
		System.out.println(name+"���� ������"+tot+"���̰�, ������"+rank+"���Դϴ�.");
	}
}

public class Exam_04 {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		
		System.out.print("�л����� �Է� : ");
		int inwon = in.nextInt();
		
		SungJuk[] sj = new SungJuk[inwon];
		
		for(int i = 0; i<inwon; ++i) {
			System.out.print("�̸��� �Է� : ");
			String name = in.next();
			System.out.print("�������� : ");
			int kor = in.nextInt();
			System.out.print("�������� : ");
			int eng = in.nextInt();
		sj[i] = new SungJuk(name, kor, eng);
		}
		for(int i=0; i<inwon; ++i) {
			for(int j=0; j<inwon; ++j) {
				if(sj[i].getTot() < sj[j].getTot()) {
					sj[i].plusRank();
				}
			}
		}
		for(int i=0; i<inwon; ++i) {
			sj[i].disp();
		}
	}
	}

/*
import java.util.Scanner;

class SungJuk{
	String name; //����ȭ�������� �޶���
	int kor, eng, tot, rank; //����ȭ�������� �޶���
	
	SungJuk(String name, int kor, int eng){
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		tot = kor+eng;
		rank=1; //���� �ʱⰪ 1��
	}
	void disp() {
		System.out.println(name+"���� ������"+tot+"���̰�, ������"+rank+"���Դϴ�.");
	}
}

public class Exam_04 {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		
		System.out.print("�л����� �Է� : ");
		int inwon = in.nextInt();
		
		SungJuk[] sj = new SungJuk[inwon];
		
		for(int i = 0; i<inwon; ++i) {
			System.out.print("�̸��� �Է� : ");
			String name = in.next();
			System.out.print("�������� : ");
			int kor = in.nextInt();
			System.out.print("�������� : ");
			int eng = in.nextInt();
		sj[i] = new SungJuk(name, kor, eng);
		}
		for(int i=0; i<inwon; ++i) {
			for(int j=0; j<inwon; ++j) {
				if(sj[i].tot < sj[j].tot) { //����ȭ�������� �޶���
					sj[i].rank++; //����ȭ�������� �޶���
				}
			}
		}
		for(int i=0; i<inwon; ++i) {
			sj[i].disp();
		}
	}
	}
*/