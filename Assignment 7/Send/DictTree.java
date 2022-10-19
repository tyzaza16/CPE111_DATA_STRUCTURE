import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

public class DictTree {
	static TreeSet<Dnode> dict = new TreeSet<Dnode> (); // สร้างตัวแปร Array list แบบ global มาเก็บข้อมูลที่อ่านมา
	static int count1=0;
	public static void Read_File() { // ฟังก์ชั่นในการอ่านไฟล์
		String str;
		try { // ใช้ try catch เพื่อเกิด error!
			FileInputStream fcsv = new FileInputStream("C:\\Users\\LENOVO\\Desktop"
					+ "\\CPE-YEAR#1\\CPE SUPJECT\\CPE111 DATA STRUCTURE\\Assignment 6\\UTF8_Lexitron.csv"); // สร้างไฟล์มาเก็บที่ตัวแปรที่ระบุ Endcoding
			InputStreamReader utf = new InputStreamReader(fcsv,"UTF-8"); // ระบุ Endcoding 
			BufferedReader buff = new BufferedReader(utf); // ใช้ class bufferreader อ่านข้อมูล BOM ทิ้ง
			buff.read(); // อ่าน BOM ทิ้ง (BOM มีขนาดเท่ากับ char เลยใช้ .read() เหมือนอ่าน char )
			while((str = buff.readLine()) != null ) { // วนรอบอ่านไฟล์
				AddTreeNode(str); // check node have been ? 
				count1++; // นับจำนวน
			}
			fcsv.close(); // ปิดไฟล์เมื่ออ่านเสร็จ
		}
		catch(Exception e){ // ถ้าเกิด Error!
			System.out.printf("Error! Can't Read File %n"); // แสดงข้อความ
		}
	} 
	public static void AddTreeNode(String str){
		Dnode x = new Dnode(str);
		if(dict.contains(x)) { // use compareTo function to contains word only
			TreeSet<Dnode> z = (TreeSet<Dnode>) dict.subSet(x, true, x, true);// search from x to x and get x too (mean just 1 node)
			if(!z.first().mean.contains(x.mean.get(0))) // check repeat meaning 
				z.first().mean.add(x.mean.get(0)); // add new meaning
		}
		else // if didn't have x node in tree before add x to new node
			dict.add(x); 
	}
	public static void print_stats() { // show every stats of tutorial
		int sum = 0,max = 0,i=0;
		Dnode x = new Dnode(); 
		System.out.printf("KeyWord in TreeSet : %d \n",dict.size()); // show keyword
		for(Dnode itr : dict) { // loop to count meaning 
			sum += itr.mean.size(); // sum every meaning 
			if(max < itr.mean.size()) { // find maximum meaning in Tree 
				max = itr.mean.size(); // maximum
				x = itr; // remember node that have maximum meaning
			}
		}
		System.out.println("Meaning in TreeSet :  "+ sum); // show amount of meaning
		System.out.println("Most Keyword Found : "+x.word+" \ncount : "+max); // show most keyword
		for(String show : x.mean) { // loop to printf different meaning
			System.out.printf("\t\t%d) %s " + show + " \n",++i,x.word); 
		}
	}
	public static void Find_print_keyword(String str) { // find keyword that users needs
		Dnode x = new Dnode(); 
		int i=0;
		x.word = str; // put string from users in Node to search node in Tree
		if(dict.contains(x)) { // check String is in Tree?
			TreeSet<Dnode> z = (TreeSet<Dnode>) dict.subSet(x,true,x,true); // if string is in Tree
			System.out.printf("Ans > Found %s in BinaryTree & Have %d meaning\n",str,z.first().mean.size()); // show users
			for(String print : z.first().mean) { // loop to print different meaning
				System.out.printf("\t\t%d) %s %s \n",++i,str,print);
			}
		}
		else System.out.printf("Not Found %s in BinaryTree !!\n",str); // if string isn't in in tree
	}
	public static void main(String[] args) { // class use to run
		String str; 
		Scanner in = new Scanner(System.in); 
		Read_File(); // อ่านข้อมูล
		System.out.println("Total Read = "+count1+" records."); // show total read of File
		print_stats(); // show every stats 
		do { // loop to find keywords that users need
			System.out.printf("Enter token > "); 
			str = in.nextLine(); // อ่านข้อมูล
			str = str.trim().replaceAll("\\s+"," "); // เช็คเว้นวรรคทั้งหมดแล้วแทนด้วยเว้นวรรคตัวเดียว
			Find_print_keyword(str); // send string to search in tree
		}while(!str.equalsIgnoreCase("end")); // ทำซ้ำถ้า str ไม่ใช่ end 
		System.out.println("End Program.");
		System.out.printf("This program is writen by Sorathorn Kaewchotchuangkul 63070501067 CPE/1");
	}

}
