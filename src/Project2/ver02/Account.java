package Project2.ver02;


public class Account {
	
	String acNumber; //계좌번호
	String name;	//이름
	int balance; //잔액
	
	
	public Account(String acn, String n, int b) {
		acNumber = acn;
		name = n;
		balance = b;
	}
	
	public void showInfo() {
		System.out.println("계좌번호: "+ acNumber);
		System.out.println("고객이름: "+ name);
		System.out.println("잔고: "+ balance);
	}
	public void deposit(int money) {
		
	}
}	
	
