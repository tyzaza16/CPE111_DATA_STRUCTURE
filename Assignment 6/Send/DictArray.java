import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class DictArray {
	static ArrayList<Dnode> dict = new ArrayList<Dnode> (); // สร้างตัวแปร Array list แบบ global มาเก็บข้อมูลที่อ่านมา
	static int count1=0;
	static String x = null; // เอามารับ String ที่มากที่สุดที่จะหาตอนแรก
	public static void Read_File() { // ฟังก์ชั่นในการอ่านไฟล์
		String str;
		try { // ใช้ try catch เพื่อเกิด error!
			FileInputStream fcsv = new FileInputStream("C:\\Users\\LENOVO\\Desktop"
					+ "\\CPE-YEAR#1\\CPE SUPJECT\\CPE111 DATA STRUCTURE\\Assignment 6\\UTF8_Lexitron.csv"); // สร้างไฟล์มาเก็บที่ตัวแปรที่ระบุ Endcoding
			InputStreamReader utf = new InputStreamReader(fcsv,"UTF-8"); // ระบุ Endcoding 
			BufferedReader buff = new BufferedReader(utf); // ใช้ class bufferreader อ่านข้อมูล BOM ทิ้ง
			buff.read(); // อ่าน BOM ทิ้ง (BOM มีขนาดเท่ากับ char เลยใช้ .read() เหมือนอ่าน char )
			while((str = buff.readLine()) != null ) { // วนรอบอ่านไฟล์
				Dnode x = new Dnode(str); // จอง memories มาเก็บข้อมูลที่อ่านมาเป็น Structure
				dict.add(x); //  เพิ่มข้อมูล (ใช้ Library ArrayList)
				count1++; // นับจำนวน
			}
			fcsv.close(); // ปิดไฟล์เมื่ออ่านเสร็จ
		}
		catch(Exception e){ // ถ้าเกิด Error!
			System.out.printf("Error! Can't Read File %n"); // แสดงข้อความ
		}
	}
	public static int Delete_RepeatWord(){ // method ลบคำซ้ำ
		int i,j = 0; 
		for(i=0;i<dict.size()-1;i++) { // วนรอบทุกตัวหาตัวซ้ำ
			if(dict.get(i).CompareRepeatWord(dict.get(i+1))) { // ใช้ CompareRepeateWord ใน  Class Dnode เพเพื่อเช็คว่าเหมือนกันไหม
				dict.remove(i+1); // เหมือนลบทิ้ง
				j++; // นับว่าลบไปกี่ตัว
			}				
		}
		count1 = count1-j; // อัพเดต count
		return j; // รีเทิร์นจำนวนที่ลบ
	}
	public static int count_keyword() { // method นำคำที่มี word เหมือนกันแต่ type และ meaning ต่างกัน
		int i,j=1,count=0; // j เริ่มที่ 1 เพราะนับตัวเปรียบเทียบด้วยถ้า = 0 มันจะไม่นับตัวตั้งต้น
		for(i=0;i<dict.size()-1;i++) { // วนรอบเพื่อเช็ค
			if(dict.get(i).CompareKeyword(dict.get(i+1))) { // ใช้ CompareKeyWord ใน  Class Dnode (เพราะ get ข้อมูลออกมาแล้วมันเป็น Dnode)
				j++; // นับจำนวนถ้ามันมี Keyword เหมือนกัน
			}
			else { // แต่ถ้ามันไม่เหมือนกัน
				if(count < j) { // เช็คก่อนว่า count หรีอจำนวนของตัวก่อนหน้านั้นมากกว่าตัวนี้หรือป่าวเพราะเราต้องการตัวที่ซ้ำมากที่สุด
					count = j; // จำค่า j  
					x = dict.get(i).word; // จำ keyword
					}
				j=1; // รีเสร็จ j เมื่อเจอตัวที่ keyword ไม่เหมือนกันแล้ว
			}
		}
		return count; // ให้ค่ากลับว่ามีกี่ตัว
	}
	public static void Find_print_keyword(String str) { // methods ในการหา keyword ที่ต้องการ
		int index;
		Dnode x = new Dnode(); // สร้าง memories ของ Dnode มาหนึ่งตัว
		x.word = str; // ให้ word ของตัวนั้นเป็นตัวที่เราต้องการเปรียบเทียบ
		//System.out.printf("Your add : %s\n",x.word); 
		index = Collections.binarySearch(dict,x); // ใช้ Library Binary search ในการค้นหา word นั้นแล้วให้ Index ออกมา
		//System.out.printf("Index Found : %d\n",index);
		
		if(index > 0) { // ถ้าไม่ได้เจอตัวแรก
			while(index >= 0 && dict.get(index).word.equalsIgnoreCase(str)){ // ให้วนหาตัวแรกของ word นั้นเพราะ Index ที่ได้อาจไม่ใช่ตัวแรก
					index--; 
			}
			if(index < 0)// เจอ index ที่ตำแหน่งแรก index = 0 -> index-- = -1 หลังหลุดจาก Loop
				print_token(0,str); // จะได้ตำแหน่งแรกคือ 0
			else// ไม่ใช่ตำแหน่งแรกมันจะหลุดออกมาก่อนตำแหน่งแรก
				print_token(index+1,str); //ต้องบวกไป 1 ตำแหน่งจะได้ตำแหน่งแรก
		}
		else if(index == 0) // ถ้า Index เป็นตำแหน่งแรกตั้งแต่ Search ครั้งแรก
			print_token(0,str); // ส่งไปปริ้นเลย
		else // น้อยกว่า 0 หรือไม่เจอ word นั้น
			System.out.printf("Not Found token "+str+" in Dictonary.%n");
	}
	public static void print_token(int FirstIndex,String str) { // methods ในการปริ้นข้อมูล
		int count=0,i,last;
		i = FirstIndex; // กำหนด i เป็น Index ตัวแรกที่จะปริ้น
		//System.out.printf("Dict size : %d",dict.size());
		while(i < dict.size() && dict.get(i).word.equalsIgnoreCase(str)) {// วนรอบจนถึงตัวท้ายต้องมาก่อนไม่งั้นมันจะเอาไป Compare ก่อนเมื่อมันเลยตัวสุดท้ายไป
				count++; //นับ 
				i++; // เลื่อนปริ้นไปเรื่อยๆ
		}
		last = FirstIndex+count-1; // หาตำแหน่งสุดท้าย
		System.out.printf("\t\tFound "+count+" token at "+FirstIndex+" - %d %n",last);
		for(i=1;i<=count;i++) { // วนรอบแสดงข้อมูล
			System.out.printf("\t\t\t%d) %s %s(%s)%n",i,dict.get(FirstIndex).word,dict.get(FirstIndex).mean,dict.get(FirstIndex).type);
			FirstIndex++;
		}
	}
	public static void main(String[] args) { 
		int i,repeatwords = 0,keywords = 0;
		String str;
		Scanner in = new Scanner(System.in); 
		Read_File(); // อ่าน
		System.out.println("Total Read = "+count1+" records."); // แสดงผลตอนแรก
		Collections.sort(dict); // เรียงลำดับ
		repeatwords = Delete_RepeatWord(); // หาตัวซ้ำ + ลบตัวซ้ำ
		System.out.println("Repeat Words= "+repeatwords+" records."); // แสดงจำนวนตัวซ้ำ
		System.out.println("Total Read Now = "+count1+" records."); // แสดงจำนวนที่เหลือหลังจากลบไป
		keywords = count_keyword(); // หาจำนวนที่มี word ซ้ำมากที่สุด(type meaning ต่างกัน)
		System.out.println("Maximum meaning : "+ x +" have " +keywords+ " meaning.");// แสดงจำนวนที่ word เหมือนกันแต่ความหมายต่างกัน
		Find_print_keyword(x); // ปริ้นIndex และ ข้อมูลของตั้วนั้นออกมา
		do {
			System.out.printf("Enter token > ");
			str = in.nextLine(); // อ่านข้อมูล
			//System.out.println("Your token : "+str+"");
			str = str.trim().replaceAll("\\s+"," "); // เช็คเว้นวรรคทั้งหมดแล้วแทนด้วยเว้นวรรคตัวเดียว
			//System.out.println("Your token after trim : "+str+"");
			Find_print_keyword(str); // ส่ง token ที่รับไปหา Index และปริ้นออกมา
		}while(!str.equalsIgnoreCase("end")); // ทำซ้ำถ้า str ไม่ใช่ end
		System.out.println("End Program.");
		System.out.printf("This program is writen by Sorathorn Kaewchotchuangkul 63070501067 CPE/1");
	}

}
