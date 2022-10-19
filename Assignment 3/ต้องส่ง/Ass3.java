import java.util.*;

public class Ass3 {

	public static void main(String[] args) {
		double timenumlibrary,timestrlibrary,timenummerge,timestrmerge,timenumquick
		,timestrquick; //สร้างตัวแปรเพื่อจะเก็บเวลาของ sort ต่างๆ
		long starttime,stoptime;
		DataArray test = new DataArray(100010); //สร้างตัวแปรที่จะอ่านข้อมูล
		DataArray temp = new DataArray(100010);  //ตัวแปรที่ copy ข้อมูลเพื่อควบคุมสภาพแวดล้อมให้เหมือนกัน
		if(test.Load_DataFile("C:\\Users\\LENOVO\\Downloads\\testAss3.csv") > 0) { // อ่านไฟล์ถ้าอ่านเจอถึงจะให้ทำในเงื่อนไข
			System.out.println("System Can Read File = "+test.count);// แสดงจำนวนข้อมูลที่อ่านได้
			//No sort 
			System.out.println("No sorting.....");
			test.print_test();// แสดงข้อมูลที่ยังไม่ได้จัดเรียง
			
			// Arrays sort num
			
			temp.data = Arrays.copyOf(test.data,test.count); // copy ข้อมูลเพื่อใช้ในการเรียงในแบบต่างๆ
			temp.count = test.count; // copy จำนวนของข้อมูลที่อ่านได้จากไฟล์
			System.out.println("Run Arrays sort number.....");
			starttime = System.nanoTime(); // จับเวลา
			Arrays.sort(temp.data, new Arraynum()); //จัดเรียงข้อมูลตาม Sort
			stoptime = System.nanoTime(); // จำเวลาเมื่อจัดเรียงเสร็จ
			timenumlibrary = (stoptime-starttime) / 1E6; //หาเวลาที่ใช้ในการจัดเรียง
			temp.print_test(); // แสดงผลข้อมูลเมื่อจัดเรียงแล้ว
			
			// Arrays sort string
			
			temp.data = Arrays.copyOf(test.data,test.count); // copy ข้อมูลเพื่อใช้ในการเรียงในแบบต่างๆ
			temp.count = test.count; // copy จำนวนของข้อมูลที่อ่านได้จากไฟล์
			System.out.println("Run Arrays sort Strings.....");
			starttime = System.nanoTime();// จับเวลา
			Arrays.sort(temp.data,new ArrayStr2());  //จัดเรียงข้อมูลตาม Sort
			stoptime = System.nanoTime();// จำเวลาเมื่อจัดเรียงเสร็จ
			timestrlibrary = (stoptime-starttime) / 1E6;//หาเวลาที่ใช้ในการจัดเรียง
			temp.print_test(); // แสดงผลข้อมูลเมื่อจัดเรียงแล้ว
			
			//Merge Sort num
			
			temp.data = Arrays.copyOf(test.data,test.count); // copy ข้อมูลเพื่อใช้ในการเรียงในแบบต่างๆ
			temp.count = test.count; // copy จำนวนของข้อมูลที่อ่านได้จากไฟล์
			System.out.println("Run Merge sort number.....");
			starttime = System.nanoTime(); // จับเวลา
			temp.mergeSort(0,temp.count-1);//จัดเรียงข้อมูลตาม Sort
			stoptime = System.nanoTime();// จำเวลาเมื่อจัดเรียงเสร็จ
			timenummerge = (stoptime-starttime) / 1E6; //หาเวลาที่ใช้ในการจัดเรียง
			temp.print_test(); // แสดงผลข้อมูลเมื่อจัดเรียงแล้ว
			
			// Merge Sort String
			
			temp.data = Arrays.copyOf(test.data,test.count); // copy ข้อมูลเพื่อใช้ในการเรียงในแบบต่างๆ
			temp.count = test.count; // copy จำนวนของข้อมูลที่อ่านได้จากไฟล์
			System.out.println("Run Merge sort String.....");
			starttime = System.nanoTime();// จับเวลา
			temp.mergeSortString(0,temp.count-1);//จัดเรียงข้อมูลตาม Sort
			stoptime = System.nanoTime(); // จำเวลาเมื่อจัดเรียงเสร็จ
			timestrmerge = (stoptime-starttime) / 1E6; //หาเวลาที่ใช้ในการจัดเรียง
			temp.print_test();// แสดงผลข้อมูลเมื่อจัดเรียงแล้ว
			
			// Quicksort num
			
			temp.data = Arrays.copyOf(test.data,test.count); // copy ข้อมูลเพื่อใช้ในการเรียงในแบบต่างๆ
			temp.count = test.count; // copy จำนวนของข้อมูลที่อ่านได้จากไฟล์
			System.out.println("Run Quick sort Number.....");
			starttime = System.nanoTime(); // จับเวลา 
			temp.QuicksortNum(0,temp.count-1);//จัดเรียงข้อมูลตาม Sort
			stoptime = System.nanoTime();// จำเวลาเมื่อจัดเรียงเสร็จ
			timenumquick = (stoptime-starttime) / 1E6; //หาเวลาที่ใช้ในการจัดเรียง
			temp.print_test(); // แสดงผลข้อมูลเมื่อจัดเรียงแล้ว
			
			//Quicksort string
			
			temp.data = Arrays.copyOf(test.data,test.count); // copy ข้อมูลเพื่อใช้ในการเรียงในแบบต่างๆ
			temp.count = test.count; // copy จำนวนของข้อมูลที่อ่านได้จากไฟล์
			System.out.println("Run Quick sort String.....");
			starttime = System.nanoTime();// จับเวลา 
			temp.QuicksortStr(0,temp.count-1); //จัดเรียงข้อมูลตาม Sort
			stoptime = System.nanoTime(); // จำเวลาเมื่อจัดเรียงเสร็จ
			timestrquick = (stoptime-starttime) / 1E6; //หาเวลาที่ใช้ในการจัดเรียง
			temp.print_test(); // แสดงผลข้อมูลเมื่อจัดเรียงแล้ว
			
			System.out.println("End of testing."); // ตั้งแต่นี้ไปก็จะเป็นในส่วนของการแสดงผลเวลาของการเรียงลำดับแบบต่างโดยแสดงเป็นรูปแบบตาราง
			System.out.printf("\t\t+-----------------------------------------------------------+\n\n");
			System.out.printf("\t\t|       Sort       |   time Number (ms)  | time String (ms) |\n\n");
			System.out.printf("\t\t+-----------------------------------------------------------+\n");
			System.out.printf("\t\t|   Library sort   |           %10.6f|     %10.6f ms|\n",timenumlibrary,timestrlibrary);
			System.out.printf("\t\t|    Merge sort    |           %10.6f|     %10.6f ms|\n",timenummerge,timestrmerge);
			System.out.printf("\t\t|    Quick sort    |           %10.6f|     %10.6f ms|\n",timenumquick,timestrquick);        
			System.out.printf("\t\t+-----------------------------------------------------------+\n");
		}
		else 
			System.out.printf("Can't Read File"); //ถ้าอ่านไฟล์ไม่ได้ก็จะแสดงข้อความว่าไม่สามารถอ่านไฟล์ได้
		System.out.printf("This Program Written By Sorathorn Kaewchotchuangkul 63070501067 CPEREGULAR"); 
	}


}
