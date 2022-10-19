import java.util.Comparator;

public class CSVnode {
	int id; // class นี้เหมือนเป็น structure ในภาษา c มีตัวแปรต่างๆใน class เพื่อทำการเก็บข้อมูลที่อ่านมาได้
	long num;
	String str1, str2;
	public CSVnode(){ // constructor แบบของตัวแปรแล้วไม่ต้องกำหนดค่าให้
		
	}
	public CSVnode(int a,long b,String c,String d){ // constructor แบบของตัวแปรแล้วต้องกำหนดค่าใน parameter ให้
		id = a;
		num = b;
		str1 = c;
		str2 = d;
	}
	public int compareTo(CSVnode x) {
		if(num - x.num > 0)
			return 1;
		else if (num - x.num == 0)
			return 0;
		else
			return -1;
	}
	public void print_data(){ // method ในการแสดงผลข้อมูลของ data แต่ละตัว
		System.out.printf("%6d %d %s %s\n",id,num,str1,str2);
	}
}
class Arraynum implements Comparator <CSVnode>{ // สร้าง class ขึ้นมาเพื่อเรียกใช้ Library sort 
	public int compare(CSVnode x,CSVnode y) { //สร้าง method ที่ต้องการเรียงและส่งค่าให้ตัว library ไปพิจารณาว่าเราต้องการจากน้อยไปมาก หรือ มากไปน้อย
		if(x.num == y.num)
			return 0;
		else if (x.num > y.num)
			return 1;
		else
			return -1;
	}
}
class ArrayStr2 implements Comparator <CSVnode>{ // สร้าง class ขึ้นมาเพื่อเรียกใช้ Library sort
	public int compare(CSVnode x,CSVnode y) { //สร้าง method ที่ต้องการเรียงและส่งค่าให้ตัว library ไปพิจารณาว่าเราต้องการจากน้อยไปมาก หรือ มากไปน้อย
		return (int)x.str2.compareToIgnoreCase(y.str2);
	}
}

