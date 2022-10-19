import java.util.ArrayList;
import java.util.Collections;

public class Dnode implements Comparable<Dnode>{
	String word;
	String mean;
	String type; 
	public Dnode() { // constructor แบบ node เปล่า
		word = "";
		mean = "";
		type = "";
	}
	public int compareTo(Dnode x) { // Comparable ของ Sort และ binary search
		return (int) this.word.compareToIgnoreCase(x.word); // รีเทิร์นเป้น int
	}
	public Dnode(String buff) { // constructor แบบมีข้อมูล
		buff = buff.trim().replaceAll("\\s+"," "); // ลบ white space หน้า หลัง ตรงกลางที่เป็น white space ยาวๆก็ให้เหลืออันเดียว
		String [] str = buff.split(","); // split มันจาก ,
		word = str[0];
		mean = str[1];
		type = str[2];
	}
	boolean CompareRepeatWord(Dnode x) { // methods เปรียบเทียบคำที่ซ้ำกันทุกอย่าง
		if(this.word.equalsIgnoreCase(x.word) && this.mean.equalsIgnoreCase(x.mean) && this.type.equalsIgnoreCase(x.type))
			return true;
		else
			return false;
	}
	boolean CompareKeyword(Dnode x) { //  methods เปรียบเทียบคำที่ซ้ำกันเฉพาะ word
		if(this.word.equalsIgnoreCase(x.word) && !(this.mean.equalsIgnoreCase(x.mean)))
			return true;
		else return false;
	}
}