
public class TokenAnalysis {
	public static boolean isNumber(String token) { // method เช็คว่าเป็น number ไหม
		boolean check = true; 
		try {
			Double.parseDouble(token); // ให้ลองแปลงเป็น จำนวนจริง
		}
		catch(Exception e){ // ถ้าแปลงไม่ได้
			check = false; 
		}
		return check; // ส่งค่ากลับว่าแปลงได้ไหม TRUE FALSE
	}
	public static int isConstant(String token){ // method เป็นค่าคงที่หรือไม่
		String [] myfunc = {"pi","e","ans"}; 
		int i,ans=-1; 
		for(i=0;i < myfunc.length;i++) { // วนรอบเปรียบเทียบกับ String ใน Myfunc 
			if(myfunc[i].equalsIgnoreCase(token)) 
				ans = i;
		}
		return ans; // ถ้าตรงจะให้ค่าตำแหน่งกลับซึ่ง >= 0 แต่ถ้าไม่ก็จะให้ค่า -1
	}
	public static int isFuntion(String token) { // method เช็คว่าเป็น Function ไหม
		int i,ans=-1; 
		String [] myfunc = {"sin","cos","tan","asin","acos","atan","sqrt","log","exp","abs"};
		for(i=0;i<myfunc.length;i++) { // วนรอบเปรียบเทียบกับ String ใน Myfunc 
			if(myfunc[i].equalsIgnoreCase(token))
				ans = i;
		}
		return ans; // ถ้าตรงจะให้ค่าตำแหน่งกลับซึ่ง >= 0 แต่ถ้าไม่ก็จะให้ค่า -1
	}
	public static int checkgroup(String token) { // method เช็คกลุ่มของสตริง
		if(isNumber(token)) // เป็นเลข
			return 1;
		else if(isConstant(token) >= 0) // เป็นค่าคงที่
			return 1;
		else if(token.matches("[+-]"))// เป็น + -
			return 2;
		else if(token.matches("[*/]"))// เป็นเลข * /
			return 3;
		else if(token.equals("^"))// เป็นยกกำลัง
			return 4;
		else if(token.equals("!"))// เป็นนิเสธ
			return 5;
		else if(isFuntion(token)>=0)// เป็น function
			return 6;
		else if(token.equals("("))// เป็นวงเล็บเปิด
			return 7;
		else if(token.equals(")"))// เป็นวงเล็บปิด
			return 8;
		else // ไม่ตรงกับด้านบนเลย
			return 0;
	}
	public static String Transfrom(String token) { // method add space หน้าหลัง operator
		token = token.replace("+"," + "); // ใช้ .replace โดยถ้ามีตัวที่เราต้องการให้แทนที่ใหม่มันก็จะแทนค่าที่เราให้แทนใหม่ลงไป
		token = token.replace("-"," - ");
		token = token.replace("*"," * ");
		token = token.replace("/"," / ");
		token = token.replace("^"," ^ ");
		token = token.replace("("," ( ");
		token = token.replace(")"," ) ");
		return token;
	}
	public static void change_sign_operater(String [] token) { // method เปลี่ยนเครื่องของ - แบบ unary ให้เป็น ! 
		int i;
		if(token[0].equals("-")) // ถ้ามันอยู่ตัวแรกเลย
			token[0] = "!"; // เปลี่ยนเพราะเป็น unary แน่นอน
		for(i=0;i<token.length - 1; i++) { // วนรอบเช็คไปจนตัวรองสุดท้าย
			if(token[i+1].equals("-") && (token[i].matches("[+-/*^()]"))) { // ถ้าตัวถัดไปเป็น - แล้วตัวก่อนหน้าเป็นเครื่องหมายอยู่แล้ว
				token[i+1] = "!";  // แสดงว่า - ตัวนั้นเป็น Unary แน่นอน
			}
		}
	}
	public static int check_state(String[] token) { // method เช็ค state 
		int i,next=0,state = 0,pair=0;
		for(i=0;i<token.length && state != -1;i++) { // วนรอบเช็คทุกตัวใน token
			state = next; // ให้ state เท่ากับตัวที่คิดได้ก่อนหน้าในแต่ละรอบ
			next = checkgroup(token[i]); // เช็คกลุ่มของ token ที่กำลังเช็ค
			if(next == 0) // ถ้า next เป็น 0
				state = -1; 
			else {
				if(next >= 2 && next <= 4) 
					next = 2; // รวมกรณีเครื่องหมายให้เป็นอันเดียวกันก่อนจะทำเพื่อความจะได้สะดวกขึ้น
				if(next == 7) 
					pair++;       // นับเพื่อเช็คเผื่อวงเล็บปิดจะมาก่อนวงเล็บเปิด
				if(next == 8)
					pair--;
				if(pair < 0 )  // ถ้ามัน < 0 แสดงว่าวงเล็บปิดมาก่อน
					state = -1;
				else if(state == 0 && (next == 2 || next == 8)) // ถ้ามันขึ้นต้นด้วย Operator กับวงเล็บปิด
					state = -1;
				else if(state == 1 && (next == 1 || next == 6 || next == 7 || next == 5)) // ถ้าเป็นตัวเลขแล้วตัวถัดไปต้องเป็น Operator กับวงเล็บปิด 
					state = -1;
				else if(state == 2 && (next == 2 || next == 8)) // ถ้าเป็น operator ตัวต่อไปต้องไม่ใช่ Operator กับวงเล็บปิด 
					state = -1;
				else if(state == 5 && (next == 2 || next == 8)) // ถ้าเป็น ! ตัวต่อไปต้องไม่ใช่ Operator กับวงเล็บปิด 
					state = -1;
				else if(state == 6 && (next != 7)) // ถ้าเป็น Function ตัวต่อไปต้องเป็นวงเล็บเปิด
					state = -1;
				else if(state == 7 && (next == 2 || next == 8)) // ถ้าเป็นวงเล็บเปิดตัวต่อไปต้องไม่ใช่ Operator กับวงเล็บปิด 
					state = -1;
				}
			}
		if(pair != 0 || state == -1) // ถ้าวงเล็บเปิด ปิด ไม่ครบคู่หรือ state = -1 อันใดอันหนึ่ง
			return -1; // ไม่สามารถคำนวณได้
		else { // ถ้าไม่ใช่
			if(next == 1 || next == 8) // เช็คว่าตัวท้ายต้องเป็นวงเล็บปิดเท่านั้นหรือไม่ก็ตัวเลข
				return 1; // คำนวณได้
			else // ถ้าไม่ใช่
				return -1; // ไม่สามารถคำนวณได้
			}
		
	}
}
