import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataArray {
	CSVnode [] data; //การจองตัวแปรอาร์เรย์ชื่อ data โดยใช้ object จาก class CSVnode
	int count;
	public DataArray(int max){ // constructor ของ class ต้องการค่า max เพื่อสร้างขนาดของอาร์เรย์ไว้
		data = new CSVnode[max]; // กำหนดตวามยาวของอาร์เรย์ data 
		count = 0; // ให้เป็น 0 เพราะยังไม่ได้อ่านไฟล์
	}
	public void add_data(int a,long b,String c,String d){ // method ในการเพิ่มข้อมุลจากที่อ่านได้ลงในตัวแปร data
		CSVnode x = new CSVnode(a,b,c,d); // สร้างตัวแปรประเภทเดียวกันมาเก็บเพราะ data ไม่สามารถเก็บได้โดยตรง
		data[count++] = x; // หลังจากนั้นถึงเอาค่าที่เก็บไว้ย้ายมาที่ data แล้วนับจำนวนเพื่ม
	}
	public void print_test() { // method ในการแสดงผลข้อมูลกำหนดจำนวนที่ต้องการแสดงผล
		data[0].print_data();
		data[49999].print_data();
		data[99999].print_data();
		}
	public void swapdata(int i,int j){ // method ในการสลับข้อมูล
		CSVnode x = new CSVnode(); // หาตัวแปรมาเพื่อจำค่าของข้อมูลอันเก่าไว้เพราะเมื่อ swap ข้อมูลจะเปลี่ยนไป
		x = data[j]; // ให้ x จำค่าของ data j
		data[j] = data[i]; // กำหนดค่าใหม่ค่า data j เพราะจำค่าอันเดิมไว้แล้ว 
		data[i] = x; // หลังจากนั้นให้ค่า data i = x ที่จำไว้ซึ่งก็คือค่า data j
	}
	public void QuicksortStr(int first, int last){ //quick sort ของ String แบบ Partition
		int i=first, j=last;
		if (first < last){ //ถ้าตัวแรกน้อยกว่าตัวท้าย
			do 
			{
				while((data[i].str2.compareToIgnoreCase(data[j].str2) <= 0) && (i < j))// ถ้าค่า string ของ data ตัวแรกน้อยกว่าหรือเท่ากับค่า string ของ data ตัวสุดท้ายและค่าของตำแหน่งแรกน้อยกว่าตำแหน่งสุดท้าย
					j--;// ลดค่า ตัวสุดท้ายมาเรื่อยๆ
				if (data[i].str2.compareToIgnoreCase(data[j].str2) > 0){// ถ้าค่าของ string มากกว่าตัวหลังที่เราลดลงมาจากบรรทัดด้านบน
					swapdata(i,j);// ให้สลับข้อมูลของสองตัวนั้น
					i++;// และเพิ่มค่าตำแหน่งที่ข้อมูลด้านซ้ายไปอีกหนึ่ง
				}
				while((data[i].str2.compareToIgnoreCase(data[j].str2) <= 0) && (i < j)) // ถ้าค่า string ของ data ตัวแรกน้อยกว่าหรือเท่ากับค่า string ของ data ตัวสุดท้ายและค่าของตำแหน่งแรกน้อยกว่าตำแหน่งสุดท้าย
						i++;// ลดค่า ตัวสุดแรกมาเรื่อยๆ
				if (data[i].str2.compareToIgnoreCase(data[j].str2) > 0){// ถ้าค่าของ string มากกว่าตัวหลังที่เราลดลงมาจากบรรทัดด้านบน
					swapdata(i,j); //ให้สลับข้อมูลของสองตัวนั้น
					j--; // ลดค่าตำแหน่งหรือเลื่อนตำแหน่งตัวสุดท้าย
				}
			} while(i<j);// ถ้าตำแหน่งของตัวแรกยังน้อยกกว่าตัวสุุดท้ายอยู่
			if (first < j-1) // เช็คเพื่อว่าถ้าตัวแรกยังน้อยกว่าตัวสุดท้ายเมื่อลดตำแหน่งลงมา 1 แล้ว
				QuicksortStr(first, j-1);// recursion โดยส่งค่านั้นไป
			if (i+1 < last)  // แต่ถ้า First + 1 แล้วน้อยกกว่า Last ให้ส่งค่านี้ไปแทน
				QuicksortStr(i+1, last); // recursion โดยส่งค่านั้นไป
		}
	}
	public void QuicksortNum(int first, int last){ //quick sort ของตัวเลขแบบ Partition
		int i=first, j=last;
		if (first < last){  //ถ้าตัวแรกน้อยกว่าตัวท้าย
			do 
			{
				while((data[i].num <= data[j].num) && (i < j)) // ถ้าค่าตัวเลขของ data ตัวแรกน้อยกว่าหรือเท่ากับค่าตัวเลขของ data ตัวสุดท้ายและค่าของตำแหน่งแรกน้อยกว่าตำแหน่งสุดท้าย
					j--; // ลดค่า ตัวสุดท้ายมาเรื่อยๆ
				if (data[i].num > data[j].num){ // ถ้าค่าของเลขตัวเลขมากกว่าตัวหลังที่เราลดลงมาจากบรรทัดด้านบน
					swapdata(i,j); // ให้สลับข้อมูลของสองตัวนั้น
					i++;// และเพิ่มค่าตำแหน่งที่ข้อมูลด้านซ้ายไปอีกหนึ่ง
				}
				while((data[i].num <= data[j].num) && (i < j)){  // ถ้าค่าตัวเลขของ data ตัวแรกน้อยกว่าหรือเท่ากับค่าตัวเลขของ data ตัวสุดท้ายและค่าของตำแหน่งแรกน้อยกว่าตำแหน่งสุดท้าย
						i++;} //ให้พิจาณาตำแหน่งถัดไป
				if (data[i].num > data[j].num){ // ถ้าค่าของเลขตัวเลขมากกว่าตัวหลังที่เราลดค่ามาแล้ว
					swapdata(i,j); // ให้สลับข้อมูลของสองตัวนั้น
					j--; // พิจารณาตำแหน่งก่อนหน้าค่าตัวหลังที่พิจารณาไปเมื่อบรรทัด 64
				}
			} while(i<j); // ถ้าตำแหน่ง i ยังน้อยกว่า j อยู่ก็ให้ Recursion วนไปเรื่อยๆจนกว่า first > last
			if (first < j-1) // เช็คเพื่อว่าถ้าตัวแรกยังน้อยกว่าตัวสุดท้ายเมื่อลดตำแหน่งลงมา 1 แล้ว
				QuicksortNum(first, j-1); // recursion โดยส่งค่านั้นไป
			if (i+1 < last) // แต่ถ้า First + 1 แล้วน้อยกกว่า Last ให้ส่งค่านี้ไปแทน
				QuicksortNum(i+1, last);
		}
	}
	public void mergeDatanum(int first,int last){// รับตำแหน่งของข้อมูลตัวแรกกับตัวสุดท้ายเข้ามา
		int mid, i, i1, i2;
		CSVnode[] temp = new CSVnode[last - first + 1]; // สร้าง temp ขึ้นมาเพื่อรองรับข้อมูลในการเรียง
		mid = (first + last) / 2;
		i1 = first;
		i2 = mid+1;  
		for (i=0;i<=last-first;i++){ //วนรอบเพื่อจัดเรียงข้อมูล
			temp[i] = new CSVnode();  // จอง Memory ให้ตัวแปรย่อยๆที่อยู่ใน Array
			if (i1<=mid && i2<=last){  // เช็คตำแหน่งเพื่อความแน่นอน
				if (data[i1].num < data[i2].num) // ถ้าค่าของตัวแรกน้อยกกว่าค่าของตัวถัดไปของตัวกลาง
					temp[i] = data[i1++]; // ให้เอาข้อมูลตัวแรกเป็นข้อมูลตัวแรกของ Data ที่อ่านมาได้เลย
				else 
					temp[i] = data[i2++];  // ถ้าไม่ก็ให้ตัวแรกของมันเท่ากับค่าของตัดถัดไปของตัวกลาง
				}
			else if (i1>mid) // เช็คตำแหน่งเพื่อความแน่นอน
				temp[i] = data[i2++]; // ให้ temp ตัวแรก เท่ากับ data ตัวถัดไปของตัวกลาง
			else if (i2>last) // เช็คตำแหน่งเพื่อความแน่นอน
				temp[i] = data[i1++]; // ให้ temp ตัวแรก เท่ากับ data ตัวแรก
			}
		for (i=0;i<=last-first;i++) // copy เมื่อเรียงลำดับเสร็จแล้ว
			data[first+i] = temp[i];
	}
	public void mergeDataString(int first,int last){ // รับตำแหน่งของข้อมูลตัวแรกกับตัวสุดท้ายเข้ามา
		int mid, i, i1, i2;
		CSVnode[] temp = new CSVnode[last - first + 1]; // สร้าง temp ขึ้นมาเพื่อรองรับข้อมูลในการเรียง
		mid = (first + last) / 2;// หาตำแหน่งกลาง
		i1 = first; 
		i2 = mid+1;  
		for (i=0;i<=last-first;i++){ //วนรอบเพื่อจัดเรียงข้อมูล
			temp[i] = new CSVnode(); // จอง Memory ให้ตัวแปรย่อยๆที่อยู่ใน Array
			if (i1<=mid && i2<=last){  // เช็คตำแหน่งเพื่อความแน่นอน
				if ((data[i1].str2.compareToIgnoreCase(data[i2].str2) < 0)) // ถ้าข้อความของตัวแรกน้อยกว่าตัวกลาง
					temp[i] = data[i1++]; // ให้ temp ตัวแรก เท่ากับ data ตัวแรก
				else 
					temp[i] = data[i2++]; // ถ้าไม่ให้เท่ากับตัวถัดไปจากตัวกลาง
				}
			else if (i1>mid) // เช็คตำแหน่งเพื่อความแน่นอน
				temp[i] = data[i2++]; // ให้ temp ตัวแรก เท่ากับ data ตัวถัดไปของตัวกลาง
			else if (i2>last) // เช็คตำแหน่งเพื่อความแน่นอน
				temp[i] = data[i1++];  // ให้ temp ตัวแรก เท่ากับ data ตัวแรก
			}
		for (i=0;i<=last-first;i++) // copy เมื่อเรียงลำดับเสร็จแล้ว
			data[first+i] = temp[i];
	}
	public void mergeSort(int first, int last){ 
		int mid;
		if (first < last){ // เช็คว่าตำแหน่งแรกน้อยกว่าตำแหน่งสุดท้าย
			mid = (first+last)/2; //หาตำแหน่งข้อมูลตรงกลาง
			mergeSort(first, mid);// แบ่งครึ่งข้อมูลโดยใช้การ Recursive ไปเรื่อยๆจนกว่าตำแหน่งแรกกับตำแหน่งสุดท้ายเป็นตำแหน่งเดียวกัน
			mergeSort(mid+1,last);// แบ่งครึ่งข้อมูลโดยใช้การ Recursive ไปเรื่อยๆจนกว่าตำแหน่งแรกกับตำแหน่งสุดท้ายเป็นตำแหน่งเดียวกัน
			mergeDatanum(first,last);// ส่งข้อมูลที่ต้องจัดเรียงไปให้ฟังก์ชั่นที่ใช้จัดเรียง
		}
	}
	public void mergeSortString(int first, int last){  // merge sort แบบสตริง
		int mid;
		if (first < last){  // เช็คว่าตำแหน่งแรกน้อยกว่าตำแหน่งสุดท้าย
			mid = (first+last)/2; //หาตำแหน่งข้อมูลตรงกลาง
			mergeSortString(first, mid); // แบ่งครึ่งข้อมูลโดยใช้การ Recursive ไปเรื่อยๆจนกว่าตำแหน่งแรกกับตำแหน่งสุดท้ายเป็นตำแหน่งเดียวกัน
			mergeSortString(mid+1,last); // แบ่งครึ่งข้อมูลโดยใช้การ Recursive ไปเรื่อยๆจนกว่าตำแหน่งแรกกับตำแหน่งสุดท้ายเป็นตำแหน่งเดียวกัน
			mergeDataString(first,last); // ส่งข้อมูลที่ต้องจัดเรียงไปให้ฟังก์ชั่นที่ใช้จัดเรียง
		}
	}
	public void Insertion_sort(int start,int stop,int order) { // รับค่าเริ่มต้นและสิ้นสุดในการเรียงลำดับและ order มา
		int i,j;
		CSVnode x = new CSVnode(); // สร้างตัวแปรมาเพื่อจำค่า ref. ไว้
		if(order == 0) { // ถ้า Order = 0 ให้เรียงจากน้อยไปมาก
			for(i=start+1;i<=stop;i++) { //เริ่มจากตัวแรก
				x = data[i]; // จำค่า
				for(j=i;j>start && (x.num < data[j-1].num);j--) // ดูเงื่อนไขถ้าตัวบนมากกว่าให้สลับ
					data[j] = data[j-1]; //สลับตัวที่ติดกันด้านบน
				data[j]= x;
			
			}
		}
		else { // ถ้าไม่เรียงจากมากไปน้อย
			for(i=start+1;i<=stop;i++) {
				x = data[i];
				for(j=i;j>start && (x.num > data[j-1].num);j--) // j and i is the same but use to compare value
					data[j] = data[j-1];
				data[j]= x;
			}
		}
			
	}
	public void Bubble_sort(int start,int stop,int order) { // รับค่าเริ่มต้นและสิ้นสุดในการเรียงลำดับและ order มา
		int i,j;
		boolean doswap = true; 
		if(order == 0) { // ถ้า order = 0 เรียงจากน้อยไปมาก
			for(i=start;i<stop && doswap ;i++) { // i เริ่มจากตัวแรกถึงตัวรองสุดท้ายแล้วต้องมีการ swap เมื่อเปรี่ยบเทียบขึ้นมาเรื่อยๆ
				doswap = false; // ถ้าไม่มีการ swap ให้หยุดเรียงลำดับทันที
				for(j=stop;j>i;j--) { // ค้นจากล่างมาบน
					if(data[j].num < data[j-1].num) { // เปรียบเทียบข้อมูลที่ติดกันถ้าตัวบนน้อยกว่าตัวล่างไหม
						swapdata(j,j-1);// น้อยกว่า swap 
						doswap = true; // ให้ swap = true เพราะมีการสลับ
					}
				}
			}
		}
		else { // ถ้าไม่ใช่เรียงจากมากไปน้อย
			for(i=start;i<stop && doswap ;i++) { 
				doswap = false;
				for(j=stop;j>i;j--) {
					if(data[j].num > data[j-1].num) { // ทำงานเหมือนด้านบนต่างที่เปรียบหาตัวมากกว่าถ้าตัวล่างมากกว่าถึงจะสลับ
						swapdata(j,j-1);
						doswap = true;
					}
				}
			}
		}
	}
	public void selection_sort(int start,int stop,int order) { // รับค่าเริ่มต้นและสิ้นสุดในการเรียงลำดับและ order มา
		int i,j,min;
		if(order == 0) {  // ถ้า order = 0 เรียงจากน้อยไปมาก
			for(i=start;i<stop;i++) { // เริ่มจากตัวแรกจนถึงตัวสุดรองสุดท้าย
				min = i; // จำตัวแหน่งของ i ไว้
				for(j=i+1;j<=stop-1;j++) { // j เป็นตัวถัดไปจาก i
					if(data[j].num < data[min].num) // ถ้าตัวถัดไปมันน้อยกว่าให้มันจะตำแหน่งที่มันควรอยู่ไว้
						min = j;								
				}
				swapdata(min,i); //เมื่อค้นหาตำแหน่งที่ควรอยู่เสร็จให้สลับกับตำแหน่งนั้น
			}
		}
		else { // ถ้าไม่ใช่เรียงจากมากไปน้อย
			for(i=start;i<stop;i++) {
				min = i;
				for(j=i+1;j<=stop-1;j++) {
					if(data[j].num > data[min].num) // การทำงานเหมือนกันแต่แค่หาตัวที่มากกว่า
						min = j;								
				}
				swapdata(min,i);
			}		
		}
	}
	public void scansort(int start,int stop,int order) { // รับค่าเริ่มต้นและสิ้นสุดในการเรียงลำดับและ order มา
		int i,j;
		if(order == 0) { // ถ้า order = 0 เรียงจากน้อยไปมาก
		for(i=start;i<stop;i++) //เริ่มจากตัวแรกจนถึงตัวรองสุดท้าย
			for(j=i+1;j<=stop;j++) // j คือตัวถัดจาก i จนถึงตัวสุดท้าย
				if(data[j].num < data[i].num) // ถ้าตัวถัดไปน้อยกว่า
					swapdata(i,j); // ให้สลับทันที
		}
		else { // ถ้าไม่ใช่เรียงจากมากไปน้อย
			for(i=start;i<stop;i++) 
				for(j=i+1;j<=stop;j++)
					if(data[j].num > data[i].num) // การทำงานเหมือนกันต่างกันที่ถ้าตัวถัดไปมากกว่าให้สลับ
						swapdata(i,j);
		}
	}
	public int Load_DataFile(String filename) { // รับชื่อไฟล์ที่ต้องการอ่านเข้ามา
		int count2 = 0, a;
		long b;
		String c,d;
		try { // ใช้เพื่อป้องกัน error ถ้าเจอจะไปดูที่ catch
			File fr = new File(filename); // สร้างตัวแปรไฟล์
			Scanner read = new Scanner(fr); // อ่านไฟล์
			read.useDelimiter(",|\n"); // อ่านจนเจอตัวคั่นคือ , and enter
			while(read.hasNextInt()) { // เช็คว่าถ้าตัวต่อไปอ่านเจอเป็น จนเต็มให้ทำใน while
				a = read.nextInt(); // อ่านจน. เต็ม
				b = read.nextLong(); // อ่านจน. เต็ม
				c = read.next(); //อ่านไปจนเจอ ,
				d = read.nextLine(); //อ่านจนหมดบรรทัด
				add_data(a,b,c,d); // เพิ่มข้อมูลจากที่อ่านได้ลงใน data
				count2++; // นับจำนวนเพิ่มเมื่ออ่านไฟล์ได้
			}
		}
		catch(FileNotFoundException e){ // ถ้าเจอ error จะทำใน catch ทันที
			System.out.println("Error ! can't read File "+filename); // แสดงผลเมื่ออ่านไม่ได้
			return 0; // ส่งค่า 0 กลับไปเมื่อไม่สามารถอ่านไฟล์ได้เลย
			
		}
		return count2; // ถ้าอ่านได้ให้ส่งจำนวนที่อ่านได้กลับไป
	}
}
