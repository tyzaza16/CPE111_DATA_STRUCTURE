
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
	public void print_data(){ // method ในการแสดงผลข้อมูลของ data แต่ละตัว
		System.out.printf("%6d %d %s %s\n",id,num,str1,str2);
	}
}
