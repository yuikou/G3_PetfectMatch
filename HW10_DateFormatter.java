package homeWork9;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class HW10_DateFormatter {

	public static void main(String[] args) throws ParseException {
		//使用者輸入日期(年月日) ex. 20110131
		int gitVer = 1;
		Scanner sc = new Scanner(System.in);
		String regex = "^\\d{8}$";
		DateFormat df = new SimpleDateFormat("yyyyMMdd"); //把數字都轉成合法日期格式
		
		while(true) {
			System.out.println("請輸入日期(年月日) ex. 20110131");
			String userDate = sc.nextLine();			
			//檢查是否輸入非指定數字格式
			if(userDate.matches(regex)) {
				//轉換取得的數字成為時間表示資料
				Date date = df.parse(userDate);			
				//格式轉換(1)年/月/日(2)月/日/年(3)日/月/年三選一
				System.out.println("要格式化成:(1)年/月/日(2)月/日/年(3)日/月/年");
				int option = sc.nextInt(); 
				
				switch (option) {
				case 1:
					df = new SimpleDateFormat("yyyy/MM/dd");
					break;
				case 2:
					df = new SimpleDateFormat("MM/dd/yyyy");
					break;
				case 3:
					df = new SimpleDateFormat("dd/MM/yyyy");
					break;
				default:
					System.out.println("選擇格式不正確, 請重來");
				}
				System.out.println(df.format(date));
				break; //離開迴圈
			} else {
				System.out.println("日期格式不正確, 請再輸入一次");
			}
		}
		sc.close();
	}

}
