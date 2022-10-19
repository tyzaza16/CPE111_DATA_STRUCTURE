import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DictHash {
	static HashMap <String, Dnode> dict = new HashMap<String, Dnode> (); // reserve hashmap 
	public static int Read_File() { 
		int count1=0;
		String str;
		try {
			FileInputStream fcsv = new FileInputStream("C:\\Users\\LENOVO\\Desktop"
					+ "\\CPE-YEAR#1\\CPE SUPJECT\\CPE111 DATA STRUCTURE\\Assignment 6\\UTF8_Lexitron.csv"); // สร้างไฟล์มาเก็บที่ตัวแปรที่ระบุ Endcoding
			InputStreamReader utf = new InputStreamReader(fcsv,"UTF-8"); // ระบุ Endcoding 
			BufferedReader buff = new BufferedReader(utf); // ใช้ class bufferreader อ่านข้อมูล BOM ทิ้ง
			buff.read(); // อ่าน BOM ทิ้ง (BOM มีขนาดเท่ากับ char เลยใช้ .read() เหมือนอ่าน char )
			while((str = buff.readLine()) != null ) { // วนรอบอ่านไฟล์
				AddHashMap(str); // check node have been ? 
				count1++; // นับจำนวน
			}
			fcsv.close(); // ปิดไฟล์เมื่ออ่านเสร็จ
		}
		catch(Exception e){
			System.out.println("\t\t\tOperation Error!."); // if error 
		}
		return count1; // total read
	}
	public static boolean Compare_Mean(Dnode z,String mean) { // use to compare different mean 
		int i;
		boolean check = false;
		for(i=0;i<z.mean.size();i++) { // loop  
			if(z.mean.get(i).equalsIgnoreCase(mean)) // check every mean in that key
				check = true; // if mean have already 
		}
		return check; // then return value of check 
	}
	public static void AddHashMap(String str) {
		String key;
		str = str.trim().toLowerCase().replaceAll("\\s+"," "); // ***** important don't forget to convert String to lower or upper 
		String [] buff = str.split(","); // split มันจาก ,
		key = buff[0]; // first of buff is always word 
		Dnode x = new Dnode(buff); // reserve new Node to add mean 
		if(dict.containsKey(key)) { // check key ever in Hash ?
			Dnode z = dict.get(key); // create a new variable to compare and add mean 
			if(!Compare_Mean(z,x.mean.get(0))){ // if mean isn't ever in hash
				z.mean.add(x.mean.get(0)); // add mean into ArrayList of Node
			}
		}
		else { // if key isn't ever in hash
			dict.put(key,x); // put new Node in hash
		}
	}
	public static void Show_stats(int count) { 
		int max = 0,sum=0,i;
		String mostword = null;
		System.out.println("\t\t\tTotal Read = "+count+" records."); 
		System.out.printf("\t\t\tNumber of KeyWord : %d records.\n",dict.size());
		for(String itr : dict.keySet()) { // loop to find sum of mean & max keywords
			Dnode value = dict.get(itr);
			sum = sum + value.mean.size();
			if(max < value.mean.size()) {
				max = value.mean.size();
				mostword = itr;
			}
		}
		System.out.printf("\t\t\tNumber of Meaning : %d records.\n",sum);
		System.out.printf("\t\t\tMost Keywords is %s Found : %d records.\n",mostword,max);
		Dnode z = dict.get(mostword); // create new variable to show meaning in Node
		for(i=1;i<=max;i++) {
			System.out.printf("\t\t\t%d ) %s\n",i,z.mean.get(i-1));
		}
	}
	public static void Find_words(String str) { // Find Keywords in Hash
		int i;
		if(dict.containsKey(str)) { // check keyword is in hash?
			Dnode z = dict.get(str);
			for(i=1;i<=z.mean.size();i++) { // if keyword have been in hash
				System.out.printf("\t\t\t%d ) %s\n",i,z.mean.get(i-1)); // show all mean of that word
			}
		}
		else System.out.printf("\t\t\tDon't have %s in HashMap!.\n",str); // if keyword haven't been in hash 
	}
	public static void main(String[] args) {
		int count;
		String str = "";
		count = Read_File(); // ReadFile & count total Read
		Show_stats(count); // show every stats in testcase
		Scanner in = new Scanner(System.in);
		while(!str.equalsIgnoreCase("end")){ // loop to Read Keyword if doesn't match "end"
			System.out.print("Enter your words >  "); 
			str = in.nextLine(); // Read keywords
			str = str.trim().toLowerCase().replaceAll("\\s+"," "); //************* important don't forget to convert String to lower or upper 
			Find_words(str); // then put it in FindWord methodw
		}
		System.out.printf("\t\tEndProgram.\n");
		System.out.printf("\t\tThis program is writen by Sorathorn Kaewchotchuangkul 63070501067 CPE/1");
	}

}
