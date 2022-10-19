import java.util.ArrayList;
import java.util.Collections;

public class Dnode implements Comparable<Dnode>{
	String word;
	ArrayList<String> mean;
	public Dnode() { // constructor แบบ node เปล่า
		word = "";
		mean = new ArrayList<String>();
		mean.add("");
	}
	public int compareTo(Dnode x) { // Comparable ของ Sort และ binary search
		return (int) this.word.compareToIgnoreCase(x.word); // รีเทิร์นเป็น int
	}
	public Dnode(String buff) { // constructor แบบมีข้อมูล
		String meanscan;
		buff = buff.trim().replaceAll("\\s+"," "); // ลบ white space หน้า หลัง ตรงกลางที่เป็น white space ยาวๆก็ให้เหลืออันเดียว
		String [] str = buff.split(","); // split มันจาก ,
		word = str[0];
		meanscan = str[1] + "(" + str[2] + ")"; // gather meaning and type
		mean = new ArrayList<String>(); // reserve a memory of mean
		mean.add(meanscan); // add mean + type in ArrayList
	}
} 