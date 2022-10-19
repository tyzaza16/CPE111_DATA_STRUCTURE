import java.util.Arrays;

public class Ass2 {

	public static void main(String[] args) {
		int max = 100010;
		double timescanR,timescanI,timescanD,timeInserR,timeInserI,timeInserD,timeselecR,timeselecI,timeselecD,timebubbleR
		,timebubbleI,timebubbleD;
		long starttime,stoptime;
		DataArray info = new DataArray(max); //สร้างตัวแปรอาร์เรย์มาเก็บข้อมูลที่อ่านจากไฟล์
		DataArray temp = new DataArray(20000); // สร้างตังแปรอาร์เรย์มาอีกเพื่อจะ copy ข้อมูลที่อ่านได้ในตอนแรกลงไปในตัวแปรนี้เพื่อประหยัดเวลาในการอ่านไฟล์ข้อมูลใหม่ทุุกครั้งในการเรียงลำดับ
		if(info.Load_DataFile("C:\\Users\\LENOVO\\Downloads\\testAss2.csv") > 0) {  //อ่านไฟล์แล้ว return ออกมามากกว่า 0 คืออ่านไฟล์เจอแล้วถึงจะเรียงลำดับได้
			System.out.println("System Can Read File = "+info.count); // แสดงจำนวนข้อมูลที่อ่านได้
			temp.data = Arrays.copyOf(info.data,19999); // copy ข้อมูลเพื่อใช้ในการเรียงในแบบต่างๆ
			System.out.println("Scansort testing time....."); 
			starttime = System.nanoTime(); // จับเวลาเริ่มต้น
			temp.scansort(0,10000,0); // ให้เรียงลำดับแบบ scansort จากข้อมูลที่ Random กันอยู่
			stoptime = System.nanoTime(); // จับเวลาเริ่มต้น
			timescanR = (stoptime - starttime)/1E6; // หาเวลาที่ใช้ในการเรียงลำดับแล้วหารด้วย 10^6 เพื่อทำเป็น milliseconds
			starttime = System.nanoTime();  // จับเวลาเริ่มต้น
			temp.scansort(0,10001,0); // ให้เรียงลำดับแบบ scansort แบบเพิ่มข้อมุลเข้าไป 1 ตัว
			stoptime = System.nanoTime(); // หยุดจับเวลา
			timescanI = (stoptime - starttime)/1E6; // หาเวลาที่ใช้ในการเรียงลำดับแล้วหารด้วย 10^6 เพื่อทำเป็น milliseconds
			starttime = System.nanoTime(); // จับเวลาเริ่มต้น
			temp.scansort(0,10001,1); // ให้เรียงลำดับแบบ scansort แบบย้อนกลับ
			stoptime = System.nanoTime(); // หยุดจับเวลา
			timescanD = (stoptime - starttime)/1E6; // หาเวลาที่ใช้ในการเรียงลำดับแล้วหารด้วย 10^6 เพื่อทำเป็น milliseconds
			System.out.println("End of Scansort testing.");
			temp.data = Arrays.copyOf(info.data,19999); // copy ข้อมูลเพื่อใช้ในการเรียงในแบบต่างๆใหม่อีกรอบเพิ่มจะเรีบงแบบ Random data
			System.out.println("Selectionsort testing time.....");
			starttime = System.nanoTime(); // จับเวลาเริ่มต้น
			temp.selection_sort(0,10000,0); // ให้เรียงลำดับแบบ selectionsort จากข้อมูลที่ Random กันอยู่
			stoptime = System.nanoTime(); // หยุดจับเวลา
			timeselecR = (stoptime - starttime)/1E6; // หาเวลาที่ใช้ในการเรียงลำดับแล้วหารด้วย 10^6 เพื่อทำเป็น milliseconds
			starttime = System.nanoTime(); // จับเวลาเริ่มต้น
			temp.selection_sort(0,10001,0);// ให้เรียงลำดับแบบ selectionsort แบบเพิ่มข้อมูลเข้าไป 1 ตัว
			stoptime = System.nanoTime(); // หยุดจับเวลา
			timeselecI = (stoptime - starttime)/1E6; // หาเวลาที่ใช้ในการเรียงลำดับแล้วหารด้วย 10^6 เพื่อทำเป็น milliseconds
			starttime = System.nanoTime(); // จับเวลาเริ่มต้น
			temp.selection_sort(0,10001,1);// ให้เรียงลำดับแบบ selectionsort แบบย้อนกลับ
			stoptime = System.nanoTime(); // หยุดจับเวลา
			timeselecD = (stoptime - starttime)/1E6; // หาเวลาที่ใช้ในการเรียงลำดับแล้วหารด้วย 10^6 เพื่อทำเป็น milliseconds
			System.out.println("End of Selectionsort testing.");
			temp.data = Arrays.copyOf(info.data,19999); // copy ข้อมูลเพื่อใช้ในการเรียงในแบบต่างๆใหม่อีกรอบเพิ่มจะเรีบงแบบ Random data
			System.out.println("Insertionsort testing time.....");
			starttime = System.nanoTime(); // จับเวลาเริ่มต้น
			temp.Insertion_sort(0,10000,0); // ให้เรียงลำดับแบบ insertion sort จากข้อมูลที่ Random กันอยู่
			stoptime = System.nanoTime(); // หยุดจับเวลา
			timeInserR = (stoptime - starttime)/1E6; // หาเวลาที่ใช้ในการเรียงลำดับแล้วหารด้วย 10^6 เพื่อทำเป็น milliseconds
			starttime = System.nanoTime(); // จับเวลาเริ่มต้น 
			temp.Insertion_sort(0,10001,0);// ให้เรียงลำดับแบบ insertion sort แบบเพิ่มข้อมูลเข้าไป 1 ตัว
			stoptime = System.nanoTime(); // หยุดจับเวลา
			timeInserI = (stoptime - starttime)/1E6; // หาเวลาที่ใช้ในการเรียงลำดับแล้วหารด้วย 10^6 เพื่อทำเป็น milliseconds
			starttime = System.nanoTime(); // จับเวลาเริ่มต้น
			temp.Insertion_sort(0,10001,1);// ให้เรียงลำดับแบบ insertion sort แบบย้อนกลับ
			stoptime = System.nanoTime(); // หยุดจับเวลา
			timeInserD = (stoptime - starttime)/1E6; // หาเวลาที่ใช้ในการเรียงลำดับแล้วหารด้วย 10^6 เพื่อทำเป็น milliseconds
			System.out.println("End of Insertionsort testing.");
			temp.data = Arrays.copyOf(info.data,19999); // copy ข้อมูลเพื่อใช้ในการเรียงในแบบต่างๆใหม่อีกรอบเพิ่มจะเรีบงแบบ Random data
			System.out.println("Bubblesort testing time.....");
			starttime = System.nanoTime(); // จับเวลาเริ่มต้น
			temp.Bubble_sort(0,10000,0); // ให้เรียงลำดับแบบ bubble sort แบบข้อมูลที่สุ่มกันอยู่
			stoptime = System.nanoTime(); // หยุดจับเวลา
			timebubbleR = (stoptime - starttime)/1E6;
			starttime = System.nanoTime(); // จับเวลาเริ่มต้น
			temp.Bubble_sort(0,10001,0); // ให้เรียงลำดับแบบ bubble sort แบบเพิ่มข้อมูลเข้าไป 1 ตัว
			stoptime = System.nanoTime(); // หยุดจับเวลา
			timebubbleI = (stoptime - starttime)/1E6;
			starttime = System.nanoTime(); // จับเวลาเริ่มต้น
			temp.Bubble_sort(0,10001,1); // ให้เรียงลำดับแบบ bubble sort แบบย้อนกลับ
			stoptime = System.nanoTime(); // หยุดจับเวลา
			timebubbleD = (stoptime - starttime)/1E6;
			System.out.println("End of Bubblesort testing."); // ตั้งแต่นี้ไปก็จะเป็นในส่วนของการแสดงผลเวลาของการเรียงลำดับแบบต่างโดยแสดงเป็นรูปแบบตาราง
			System.out.printf("\t\t+---------------------------------------------------------------------------------+\n\n");
			System.out.printf("\t\t|      Sort      |  Random data (n)  | Insert data(n+1)  |  Descending data(n+1)  |\n\n");
			System.out.printf("\t\t+---------------------------------------------------------------------------------+\n");
			System.out.printf("\t\t|    Scan sort   |      %10.6f ms|      %10.6f ms|           %10.6f ms|\n",timescanR,timescanI,timescanD);
			System.out.printf("\t\t| Selection sort |      %10.6f ms|      %10.6f ms|           %10.6f ms|\n",timeselecR,timeselecI,timeselecD);
			System.out.printf("\t\t| Insertion sort |      %10.6f ms|      %10.6f ms|           %10.6f ms|\n",timeInserR,timeInserI,timeInserD);        
			System.out.printf("\t\t|  Bubble sort   |      %10.6f ms|      %10.6f ms|           %10.6f ms|\n",timebubbleR,timebubbleI,timebubbleD);
			System.out.printf("\t\t+---------------------------------------------------------------------------------+");
			
		}
		else {
			System.out.println("Error! Read File = 0"); // ถ้าไม่สามารถอ่านไฟล์ได้จะให้แสดงผลว่า error
		}
		

	}

}
