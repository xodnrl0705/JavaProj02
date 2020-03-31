package Project2.ver04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;


public class AccountManager {

	private HashSet<Account> accounts;

	public AccountManager() {
		
		try {
			
			ObjectInputStream in =
					new ObjectInputStream(
							new FileInputStream("src/Project2/ver04/AccountBook.obj"));
			accounts = (HashSet<Account>)in.readObject();
			in.close();
		}
		catch (Exception e) {
			System.out.println("파일을 찾을 수 없습니다.");
			accounts = new HashSet<Account>();
			
			//e.printStackTrace();
		}
		
	}
	// 메뉴출력
	public void showMenu() {

		while(true) {

			System.out.println("-----Menu------");
			System.out.println("1.계좌개설");
			System.out.println("2.입	금");
			System.out.println("3.출	금");
			System.out.println("4.계좌정보출력");
			System.out.println("5.프로그램종료");
			System.out.print("선택: "); 
			
			try {
				Scanner scan = new Scanner(System.in);
				int choice = scan.nextInt();
				scan.nextLine();
				if(!(choice>=1 && choice<=5)) {
					MenuSelectException ex = new MenuSelectException();
					throw ex;							
				}

				switch(choice) {

				case MenuChoice.MAKE:
					makeAccount();
					break;
				case MenuChoice.DEPOSIT:
					depositMoney();
					break;
				case MenuChoice.WITHDRAW:
					withdrawMoney();
					break;
				case MenuChoice.INQUIRE:
					showAccInfo();
					break;
				case MenuChoice.EXIT:
					saveInfo();
					System.out.println("프로그램을 종료합니다.");
					return;
				}
				
			}
			catch (InputMismatchException e) {
				System.out.println("문자를 입력하시면 안됩니다.");
			}
			catch (MenuSelectException e) {
				System.out.println("1~5까지의 정수를 입력해주세용~");
			}
			
		}
	} 
	
	public void overlapInput(boolean a, Account ac) {
		Scanner scan = new Scanner(System.in);
		
		if(a == false) {
			System.out.println("같은이름이 있습니다. 덮어쓰기[0] | 메뉴로돌아가기[1] : ");
			int overlap = scan.nextInt();
			
			if(overlap == 0) {
				accounts.remove(ac);
				accounts.add(ac);
				System.out.println("===데이터 입력이 완료되었습니다.===");
			}
		}
		else {
			System.out.println("===데이터 입력이 완료되었습니다.===");
		}
	}
	
	
	// 계좌개설을 위한 함수
	public void makeAccount() {
		String aNumber,aName, aGrade;
		int aBalance, aRate;
		Scanner scan = new Scanner(System.in);
		try {
			
			System.out.println("***신규계좌개설***");
			System.out.println("-----계좌선택------");
			System.out.println("1.보통계좌");
			System.out.println("2.신용신뢰계좌");
			System.out.print("선택: "); 
			
			int choice = scan.nextInt();
			scan.nextLine();
			
			if(!(choice==1 || choice==2)) {
				MenuSelectException ex = new MenuSelectException();
				throw ex;							
			}
			
			if(choice == 1) {
				
				System.out.println("계좌번호: "); aNumber = scan.nextLine();
				System.out.println("고객이름: "); aName = scan.nextLine();
				System.out.println("잔고: "); aBalance = scan.nextInt();
				scan.nextLine();
				System.out.println("기본이자%(정수형태로입력): ");aRate = scan.nextInt();
				scan.nextLine();
				
				Account nac = new NormalAccount(aNumber,aName,aBalance,aRate);
				boolean a = accounts.add(nac);
				
				overlapInput(a,nac);
				
			}
			
			else if(choice == 2) {
				
				System.out.println("계좌번호: "); aNumber = scan.nextLine();
				System.out.println("고객이름: "); aName = scan.nextLine();
				System.out.println("잔고: "); aBalance = scan.nextInt();
				scan.nextLine();
				System.out.println("기본이자%(정수형태로입력): ");aRate = scan.nextInt();
				scan.nextLine();
				System.out.println("신용등급(A,B,C등급): "); aGrade = scan.nextLine();
				
				Account hac = new HighCreditAccount(aNumber,aName,aBalance,aRate,aGrade);
				boolean a = accounts.add(hac);
				
				overlapInput(a, hac);
				
			}
			System.out.println("계좌계설이 완료되었습니다."); 
			System.out.println();
		}
		catch (InputMismatchException e) {
			System.out.println("문자를 입력하시면 안됩니다.");
			makeAccount();
		}
		catch (MenuSelectException e) {
			System.out.println("1 또는 2로 입력해주세용~");
			makeAccount();
		}


	}    
	// 입    금
	public void depositMoney() {

		Scanner scan = new Scanner(System.in);
		String iAcnum;
		int money;
		
		System.out.println("***입   금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.println("계좌번호: "); iAcnum = scan.nextLine();
		
		try {
			Iterator<Account> itr = accounts.iterator();
			while(itr.hasNext()){
				Account ac = itr.next();
				if(iAcnum.equals(ac.acNumber)) {

					System.out.println("입금액: "); 
					money = scan.nextInt();
					scan.nextLine();
					
					if(money<0) {
						System.out.println("음수는 입금할 수 없어요!!");
						depositMoney();						
					}
					else if(!(money%500 == 0)) {
						System.out.println("입금액은 500단위로 가능합니다.");
						System.out.println("Ex) 1000, 1500원 입금가능, 1600원 입금불가.");
						depositMoney();
					}
					else {
						ac.deposit(money);
						System.out.println("입금이 완료되었습니다.");
						break;
					}
				}
			}
		}
		catch (InputMismatchException e) {
			System.out.println("문자를 입력하시면 안됩니다.");
			depositMoney();
		}
	}  
	// 출    금
	public void withdrawMoney() {

		Scanner scan = new Scanner(System.in);
		String iAcnum;
		int money;

		System.out.println("***출   금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.println("계좌번호: "); iAcnum = scan.nextLine();

		try {
			Iterator<Account> itr = accounts.iterator();
			while(itr.hasNext()){
				Account ac = itr.next();
				if(iAcnum.equals(ac.acNumber)) {

					System.out.println("출금액: "); 
					money = scan.nextInt();
					scan.nextLine();
					
					if(money<0) {
						System.out.println("음수는 출금할 수 없어요!!");
						withdrawMoney();						
					}
					else if(!(money%1000==0)) {
						System.out.println("출금액은 1000단위로 가능합니다.");
						System.out.println("Ex)2000원 출금가능, 1100원을 출금불가.");
						withdrawMoney();
					}
					else if(ac.balance < money) {
						System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
						System.out.println("[1] Yes|[2] NO : ");
						int choice = scan.nextInt();
						scan.nextLine();
						if(!(choice ==1||choice==2)) {
							MenuSelectException e = new MenuSelectException();
							throw e;
						}
						if(choice == 1) {
							ac.balance = 0;
							System.out.println("출금이 완료되었습니다.");
							break;
						}
						else if(choice == 2) {
							System.out.println("출금요청을 취소하셨습니다.");
							System.out.println("메뉴로 돌아갑니다.");
							break;
						}
						
					}
					else {
						ac.balance = ac.balance - money;
						System.out.println("출금이 완료되었습니다.");
						break;
					}
				}
			}
		}
		catch (InputMismatchException e) {
			System.out.println("문자를 입력하시면 안됩니다.");
			withdrawMoney();
		}
		catch (MenuSelectException e) {
			System.out.println("1 또는 2로 입력해주세용~");
			withdrawMoney();
		}
	}
		

	// 전체계좌정보출력
	public void showAccInfo() {

		System.out.println("***계좌정보출력***");
		
		Iterator<Account> itr = accounts.iterator();
		for(Account ac : accounts) {
			System.out.println("-------------");
			ac.showInfo();
			System.out.println("-------------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");

	}
	
	public void saveInfo() {
		try {
			
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("src/Project2/ver04/AccountBook.obj")
					);
			//myFriends 객체배열에 저장된 친구의 정보만큼 반복
			out.writeObject(accounts);
	
			out.close();
			
		}
		catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}

}
