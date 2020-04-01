package Project2.ver04;

import java.io.Serializable;

abstract class Account implements Serializable{
	
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

	abstract void deposit (int money); 
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acNumber == null) ? 0 : acNumber.hashCode());
		//result = prime * result + balance;
		//result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		Account ac = (Account)obj;
		
		if(ac.acNumber.equals(this.acNumber)) {
			return true;
		}
		else {
			return false;
		}
		
	}
}	
	
