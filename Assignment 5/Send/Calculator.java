import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {
	static ArrayList <String> postfix = new ArrayList<String>(); // จองตัวแปรที่จะเก็บ Postfix
	static Stack <Double> numstack = new Stack<Double>(); // จองตัวแปรที่จะเก็บเฉพาะตัวเลขในตอนคำนวณ
	static Stack <String> oprstack = new Stack<String>(); // จองตัวแปรเก็บเครื่องหมายตอนคำนวณ
	static double ans =0; // สร้างตัวแปร ANS ไว้เก็บคำตอบ
	public static void Change_infix_to_postfix(String [] token) { // method เปลี่ยน Infix -> postfix
		int group,cur,prior=0,i;
		String buff;
		postfix.clear(); // เคลียร์พวกข้อมูลที่อยู่ใน postfix
		oprstack.clear(); // เคลียร์พวกข้อมูลที่อยู่ใน operater stack
		for(i=0;i<token.length;i++) { // วนรอบเพื่ออ่านค่าของ token ที่ split มา
			group = TokenAnalysis.checkgroup(token[i]); // หากลุ่มของ token ตัวนั้น
			if(group == 1) { // เป็นเลข
				postfix.add(token[i]); //ให้เพิ่มเข้าไปใน Array list เลย
			}
			else if(group >= 2 && group <= 6) {// เป็น function and operater
				do { 
					buff = oprstack.peek(); //ให้ดูเครื่องหมายของตัวล่าสุดใน operater stack ก่อน
					prior = TokenAnalysis.checkgroup(buff); // เช็คความสำคัญของเครื่องหมายที่ peek ออกมาดู
					cur = TokenAnalysis.checkgroup(token[i]); // เช็คความสำคัญของเครื่องหมายที่เรากำลังจะเพิ่ม
					if(prior >= cur && prior <= 6) { // ถ้าตัวเรา peek ออกมาดูมันสำคัญกว่าตัวที่กำลังจะเพิ่มเข้าไป
						buff = oprstack.pop(); // ให้ pop ออกมาจากตัวที่เก็บเครื่องหมาย
						postfix.add(buff); // แล้วเพิ่มมันเข้าไปใน Postfix Arraylist
					}
				}while(prior >= cur && prior <= 6); // ทำไปเรื่อยๆถ้าเครื่องหมายใน operator stack ยังสำคัญกว่าตัวที่จะเพิ่มเข้ามา
				oprstack.push(token[i]); // ทำเสร็จแล้วจึงค่อยเพิ่มตัวล่าสุดเข้าไปใน Operator stack
			}
			else if(group == 7) // ถ้าเป็น "(" 
				oprstack.push(token[i]); // ให้เพิ่มเข้าไปใน operator stack เลย
			else if(group == 8) { // ถ้าเป็น ")"
				do { 
					buff = oprstack.pop(); // ดึงข้อมูลใน Operator stack ออกมา
					if(!buff.equals("(")) // ถ้าไม่ใช่ "("
						postfix.add(buff); // เพิ่มข้อมูลตัวนั้นลงใน Postfix เลย (ก็คือทำไปจนกว่าจะเจอคู่นั้นก็คือวงเล็บเปิด)
				}while(!buff.equals("(")); // ถ้าเจอวงเล็บเปิดแล้วก็หยุดทำ
			}
			else // ถ้าไม่เข้าในกรณีด้านบนเลย
				System.out.println("answer > Postfix Error!.");  // บอก Postfix Error!
		}
	}
	public double Convert_String(String token) { // method ในการแปลง String
		if(token.equalsIgnoreCase("E")) // ถ้าเจอ E
			return Math.E; // ให้ค่า E กลับ
		else if(token.equalsIgnoreCase("PI")) // ถ้าเจอ pi 
			return Math.PI; // ให้ค่า pi กลับ
		else if(token.equalsIgnoreCase("ans")) // ถ้าเจอ ans
			return ans; // ให้ค่า ans กลับ
		else
			return Double.parseDouble(token); // ถ้าไม่ตรงกลับด้านบนให้แปลงค่ามันเป็นตัวเลขจำนวนจริง
	}
	public double Calculate() { // method คำนวณค่าของเครื่องคิดเลข
		int i,group; 
		double num,num1,num2;
		String token = new String("");
		for(i=0;i<postfix.size();i++) { // วนรอบเพื่อเอาค่าที่เราแปลงจาก infix -> postfix แล้วมาคิดให้ครบทุกตัว
			token = postfix.get(i); // ดึงข้อมูลจาก postfix ออกมาทีละตัว
			group = TokenAnalysis.checkgroup(token); // แล้วเอามาเช็กกลุ่ม
			if(group == 1) { // อยู่กลุ่ม 1 
				num = Convert_String(token); // ไปแปลง String เป็นเลขก่อน
				numstack.push(num); // ใส่เข้าไปใน numstack
			}
			else if(group >= 2 && group <= 4) { // เป็นเครื่องหมาย
				num1 = numstack.pop(); // pop ข้อมูลออกมารอคำนวณ 2 ตัวเลย
				num2 = numstack.pop();
				if (group == 2 && token.equals("+")) { // เป็น +
					num = num1 + num2; // เอาข้อมูลที่ Pop ออกมามาบวก
					numstack.push(num); // ใส่เข้าไปใน numstack
				}
				else if (group == 2 && token.equals("-")) { // เป็น -
					num = num2 - num1;// เอาข้อมูลที่ Pop ออกมามา -
					numstack.push(num);// ใส่เข้าไปใน numstack
				}
				else if (group == 3 && token.equals("*")) { // เป็น *
					num = num2 * num1;// เอาข้อมูลที่ Pop ออกมามาคูณ
					numstack.push(num);// ใส่เข้าไปใน numstack
				}
				else if (group == 3 && token.equals("/")) { // เป็น "/"
					num = num2 / num1;// เอาข้อมูลที่ Pop ออกมามาหาร
					numstack.push(num);// ใส่เข้าไปใน numstack
				}
				else if (group == 4 ) { // เป็น ^
				num = Math.pow(num2,num1); // เอามายกกำลัง 
				numstack.push(num); // ใส่เข้าไปใน numstack 
				}
			}
			else if (group == 5) { // ! 
				num1 = numstack.pop(); // เอาข้อมูลออกมาตัวเดียวพอ
				num = -num1; // ใส่ลบเข้าไป
				numstack.push(num); // ใส่เข้าไปใน numstack 
			}
			else if (group == 6) { // เป็นพวก Function 
				num1 = numstack.pop(); // ดึงข้อมูลออกมาแค่ตัวเดียว
				if(token.equalsIgnoreCase("sin")) { // เป็น sin
 					num = Math.sin(num1 * (Math.PI / 180)); // เอาข้อมูลที่ Pop ใส่ลงไปใน sin (ต้องทำเป็น Radian ด้วย)
					numstack.push(num); // คำนวณแล้วเพิ่มเข้าไปใน numstack
				} 
				else if(token.equalsIgnoreCase("cos")) { // เป็น cos
					num = Math.cos(num1*(Math.PI / 180)); // เอาข้อมูลที่ Pop ใส่ลงไปใน sin (ต้องทำเป็น Radian ด้วย)
					numstack.push(num); // คำนวณแล้วเพิ่มเข้าไปใน numstack
				}
				else if (token.equalsIgnoreCase("tan")) { // เป็น tan
					num = Math.tan(num1*(Math.PI / 180)); // เอาข้อมูลที่ Pop ใส่ลงไปใน sin (ต้องทำเป็น Radian ด้วย)
					numstack.push(num); // คำนวณแล้วเพิ่มเข้าไปใน numstack
				}
				else if(token.equalsIgnoreCase("asin")) { // เป็น asin
					num = Math.asin(num1)*(180 /Math.PI); // เอาข้อมูลที่ Pop ใส่ลงไปใน sin (ต้องทำเป็น Degree ด้วย)
					numstack.push(num);	// คำนวณแล้วเพิ่มเข้าไปใน numstack
				}
				else if(token.equalsIgnoreCase("acos")) { // เป็น acos
					num = Math.acos(num1)*(180 /Math.PI); // เอาข้อมูลที่ Pop ใส่ลงไปใน sin (ต้องทำเป็น Degree ด้วย)
					numstack.push(num); // คำนวณแล้วเพิ่มเข้าไปใน numstack
				}
				else if(token.equalsIgnoreCase("atan")) { // เป็น atan
					num = Math.atan(num1)*(180 /Math.PI); // เอาข้อมูลที่ Pop ใส่ลงไปใน sin (ต้องทำเป็น Degree ด้วย)
					numstack.push(num); // คำนวณแล้วเพิ่มเข้าไปใน numstack
				}
				else if(token.equalsIgnoreCase("sqrt")) { // เป็น sqrt
					num = Math.sqrt(num1); // เอาเลขที่ Pop ไว้ก่อนหน้าใส่เข้าไปในสแควร์รูท
					numstack.push(num); // คำนวณแล้วเพิ่มเข้าไปใน numstack
				}
				else if(token.equalsIgnoreCase("exp")) { // เป็น exp
					num = Math.exp(num1); // เอาเลขที่ Pop ไว้ก่อนหน้าใส่เข้าไปใน exponential
					numstack.push(num);	// คำนวณแล้วเพิ่มเข้าไปใน numstack		
				}
				else if(token.equalsIgnoreCase("log")) { // เป็น log
					num = Math.log10(num1); // เอาเลขที่ Pop ไว้ก่อนหน้าใส่เข้าไปใน Log ฐาน 10
					numstack.push(num); // คำนวณแล้วเพิ่มเข้าไปใน numstack
				}
				else if(token.equalsIgnoreCase("abs")){ // เป็น abs
					num = Math.abs(num1); // เอาเลขที่ Pop ไว้ก่อนหน้าใส่เข้าไปใน absolute
					numstack.push(num); // คำนวณแล้วเพิ่มเข้าไปใน numstack
				}
			}
		}
		ans = numstack.pop(); // ทำครบทุกตัวจะได้คำตอบอยู่ใน Numstack แล้วก็ดึงออกมาใส่ Ans 
		return ans; // ส่งค่ากลับ
	}
	public static void main(String[] args) { 
		Calculator cal = new Calculator();          // จอง instance cal เป็นตัวคำนวณ , in เป็น Scanner
		Scanner in = new Scanner(System.in); 
		String str; // จอง String 
		int state,i; 
		do { 
			System.out.printf("expression > "); 
			str = in.nextLine(); // อ่าน String จนหมดบรรทัด
			if(str.equalsIgnoreCase("help")) // ถ้าเจอคำว่า help
				System.out.println("sin, cos, tan, asin, acos, atan, sqrt, log, exp, abs +, -, *, /, ^, (, ), pi, ans");
			else { // ถ้าไม่ใช่ help
				if(!str.equalsIgnoreCase("end")) { // และไม่ใช่ end  
					str = "(" + str + ")"; // ใส่วงเล็บเพิ่มหน้าหลังของ String
					str = TokenAnalysis.Transfrom(str); // เติมช่องว่าง
					//System.out.println(""+str+"");  แสดงข้อความหลังจาก Transform
					String [] token = str.trim().split("\\s+"); // แบ่ง String ตามช่องว่าง
					int count = token.length; // นับจำนวนที่ Spilt ได้
					TokenAnalysis.change_sign_operater(token); // แปลง - แบบ unary ให้เป็น ! เพื่อง่ายต่อการแยกแยะ
					state = TokenAnalysis.check_state(token); // ส่งไปเช็ค State ว่าสามารถคำนวณชุดเลขที่ผู้ใช้ป้อนเข้ามาได้หรือไม่
					if(state != -1) { // ถ้าคำนวณได้
						cal.Change_infix_to_postfix(token); // เปลี่ยนเป็น Postfix ก่อนคำนวณ
						System.out.println("Postfix (Queue) = " + postfix); // แสดง  postfix หลังจากแปลงแล้ว
 						ans = cal.Calculate(); // คำนวณค่า
						System.out.println("answer > " + ans);  // แสดงคำตอบ
					}
					else
						System.out.print("answer > Syntax Error!\n"); // ถ้าไม่สามารถคำนวณได้ให้แสดงข้อความบอก
				}
			}
		}while(!str.equalsIgnoreCase("end")); // ทำไปเรื่อยๆถ้ายังไม่ได้รับคำว่า end
		System.out.print("End Program!."); // แสเงข้อความจบโปรแกรม
		System.out.print("This Program wrote by Sorathorn Kaewchotchuangkul 63070501067 CPEREGULAR.");
	}

}
