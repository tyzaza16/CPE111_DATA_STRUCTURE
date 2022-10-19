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
	public void print_test(int start,int stop) { // method ในการแสดงผลข้อมูลกำหนดจำนวนที่ต้องการแสดงผล
		int i;
		for(i=start;i<stop;i++) // วนรอบเพื่อแสดงผลข้อมูล
			data[i].print_data(); // เรียกใช้ method จาก class CSVnode ที่มีอยู่แสดงผลข้อมูล
	}
	public void swapdata(int i,int j){ // method ในการสลับข้อมูล
		CSVnode x = new CSVnode(); // หาตัวแปรมาเพื่อจำค่าของข้อมูลอันเก่าไว้เพราะเมื่อ swap ข้อมูลจะเปลี่ยนไป
		x = data[j]; // ให้ x จำค่าของ data j
		data[j] = data[i]; // กำหนดค่าใหม่ค่า data j เพราะจำค่าอันเดิมไว้แล้ว 
		data[i] = x; // หลังจากนั้นให้ค่า data i = x ที่จำไว้ซึ่งก็คือค่า data j
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
			var read = new Scanner(fr); // อ่านไฟล์
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
