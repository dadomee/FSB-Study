
public class Exam_09 {
	public static void main(String[] args) {
		star();
		System.out.println("aaaaaaaaaa");
		star();
		System.out.println("bbbbbbbbbb");
		star();
		star(4.5); //�޼ҵ� �����ε�(�Ű������� �޴� �ڷ����� ���� �ʴٸ� ������
		star(6); //�޼ҵ� �����ε�(�Ű������� �ϳ� �޴� �޼ҵ尡 ���ٸ� ������
		star();
		System.out.println("cccccccccc");
		star();
	}
	public static void star(double a) { //�̸��� ���� ȣ���ϴ°� �ƴ϶� �Ű������� �ٸ��� �ٸ� �޼ҵ�� �����ϰڴ�.
		for(int i = 1; i<=a; ++i) {
			System.out.print("*");
		}
		System.out.println();
	}
	public static void star(int a) { //�̸��� ���� ȣ���ϴ°� �ƴ϶� �Ű������� �ٸ��� �ٸ� �޼ҵ�� �����ϰڴ�.
		for(int i = 1; i<=a; ++i) {
			System.out.print("*");
		}
		System.out.println();
	}
	
	public static void star() {
		System.out.println("**********");
	}
}
