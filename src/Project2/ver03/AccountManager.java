package Project2.ver03;

import java.util.InputMismatchException;
import java.util.Scanner;


public class AccountManager {

	private Account[] accounts;
	private int numOfAccount;

	public AccountManager(int num) {
		accounts = new Account[num];
		numOfAccount = 0;
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
	// 계좌개설을 위한 함수
	public void makeAccount() {

		String aNumber,aName, aGrade;
		int aBalance, aRate;
		Scanner scan = new Scanner(System.in);

		System.out.println("***신규계좌개설***");
		System.out.println("-----계좌선택------");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.print("선택: "); 

		int choice = scan.nextInt();
		scan.nextLine();
		if(choice == 1) {
			
			System.out.println("계좌번호: "); aNumber = scan.nextLine();
			System.out.println("고객이름: "); aName = scan.nextLine();
			System.out.println("잔고: "); aBalance = scan.nextInt();
			scan.nextLine();
			System.out.println("기본이자%(정수형태로입력): ");aRate = scan.nextInt();
			scan.nextLine();
			
			NormalAccount ac = new NormalAccount(aNumber,aName,aBalance,aRate);
			accounts[numOfAccount++] = ac;
			
		}
		else if(choice == 2) {
			
			System.out.println("계좌번호: "); aNumber = scan.nextLine();
			System.out.println("고객이름: "); aName = scan.nextLine();
			System.out.println("잔고: "); aBalance = scan.nextInt();
			scan.nextLine();
			System.out.println("기본이자%(정수형태로입력): ");aRate = scan.nextInt();
			scan.nextLine();
			System.out.println("신용등급(A,B,C등급): "); aGrade = scan.nextLine();
			
			HighCreditAccount ac = new HighCreditAccount(aNumber,aName,aBalance,aRate,aGrade);
			accounts[numOfAccount++] = ac;
			
		}
		System.out.println("계좌계설이 완료되었습니다."); 
		System.out.println();

	}    
	// 입    금
	public void depositMoney() {

		String iAcnum;
		int money;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("***입   금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.println("계좌번호: "); iAcnum = scan.nextLine();
		try {
			for(int i=0; i<numOfAccount; i++) {
				if(iAcnum.compareTo(accounts[i].acNumber)==0){
					
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
						accounts[i].deposit(money);
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

		String iAcnum;
		int money;
		Scanner scan = new Scanner(System.in);

		System.out.println("***출   금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.println("계좌번호: "); iAcnum = scan.nextLine();

		try {
			for(int i=0; i<numOfAccount; i++) {
				if(iAcnum.compareTo(accounts[i].acNumber)==0){

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
					else if(accounts[i].balance < money) {
						System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
						System.out.println("[1] Yes|[2] NO : ");
						int choice = scan.nextInt();
						scan.nextLine();
						if(!(choice ==1||choice==2)) {
							MenuSelectException e = new MenuSelectException();
							throw e;
						}
						if(choice == 1) {
							accounts[i].balance = 0;
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
						accounts[i].balance = accounts[i].balance - money;
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
		for(int i=0; i<numOfAccount; i++) {
			System.out.println("-------------");
			accounts[i].showInfo();
			System.out.println("-------------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");

	}  

}
