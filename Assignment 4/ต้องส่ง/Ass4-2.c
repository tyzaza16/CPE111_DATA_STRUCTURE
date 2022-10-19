#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>
#include <math.h>
typedef struct node_type
{
	double info;
	struct node_type *next;    // การสร้าง Structure เพื่อเก็บข้อมูลของ Linkedlist ประกอบด้วยข้อมูล 2 ส่วน
								// คือข้อมูลและ Pointer ที่จะชี้ไปที่ตัวถัดไป
}linkedlist;
linkedlist *first =NULL,*last=NULL,*ptrglo; // จองตัวแปร pointer แบบ global เพราะทุกฟังก์ชั่นใช้เหมือนกันหมดเลยจองทีเดียว
linkedlist *create_node(double value){ // ฟังก์ชั่นในการสร้าง Node แต่ละอันของ linkedlist
	linkedlist *ptr; // จอง pointer มาตัวหนึ่ง
	ptr = (linkedlist *) malloc(sizeof(linkedlist)); // จองพื้นที่ของ Memory ที่จะเก็บ node นั้นๆ
	ptr->info = value;      // ใส่ค่าของข้อมูลลงในโหนด
	ptr->next = NULL; // ปรับให้มันชี้ไปที่ NULL
	return ptr; // ส่ง address กลับ
}
void add_node(double value){ // ฟังก์ชั่นเพิ่มข้อมูล linkedlist ที่ตัวสุดท้าย
	linkedlist *ptr; // จอง Pointer ขึ้นมาตัวหนึ่ง
	ptr = create_node(value); // ให้มันไปสร้างโหนด จอง memory มารอไว้
 	if(first == NULL){ // เช็คว่าข้อมูลใน Linkedlist มีหรือป่าว
		first = last = ptr; // ไม่มีให้ first last ชี้ไปที่ข้อมูลตัวที่จะเพิ่่มเข้ามา
	}
	else{
		last->next = ptr; // แต่ถ้ามีอยู่แล้วให้เพิ่มไปตัวสุดท้ายของ linkedlist
		last = ptr;
	}
}
void push_node(double value){ // สร้างโหนดและผลักมันเข้าไปใน Linkedlist ในตำแหน่งแรก
	linkedlist *ptr;
	ptr = create_node(value); // สร้างโหนด
	if(first == NULL) // เข็คว่ามีข้อมูลไหม
		first = last = ptr; // ไม่มี first last ชี้ไปที่ตัวนั้น
	else{
		ptr->next = first; // ถ้ามี
		first = ptr; // ก็เพิ่มไปตัวแรกให้มันชี้ไปที่ first ชี้และเปลี่ยน First เป็นตัวเอง
	}
}
int size_of_linkedlist(){ // หาขนาด linkedlist
	int count = 0;
	ptrglo = first; // Pointer ชี้ไปโหนดแรก
	while(ptrglo != NULL){ // วนรอบจนกว่าจะถึงตัวท้าย
		count++; // นับจำนวน
		ptrglo = ptrglo->next;// update pointer
	}
	return count; // ส่งจำนวนกลับ
}
void insert_node(double value,int pos){ // เพิ่มข้อมูลแบบเรียงลำดับให้ด้วย ต้องการ ตำแหน่งที่จะให้เพิ่มและค่าข้อมูล
	int i = 0;
	linkedlist *prev; // pointer เอาไว้ชี้ตัวก่อนหน้าที่จะ insert ข้อมูลเข้าไป
	prev = first; // กำหนดในมันชี้ไปที่ตัวแรกก่อนที่จะวนรอบเพื่อหาตำแหน่งของมัน
	if(pos == 0) // ถ้ามันเท่ากับตำแหน่งแรก
		push_node(value); // ใช้ Push ช่วย
	else if(pos == (size_of_linkedlist())) //สุดท้าย
		add_node(value); // ใช้ add ช่วย
	else{
		while(i != pos-1){ // อยู่ตรงกลาง Linkedlist หมายเหตุ สำคัญต้องเช็คเงื่อนไขและต้องดูว่า i เริ่มจากเท่าไหร่ถ้า i เริ่มจาก 0 != pos-1 แต่ถ้า i=1 จะได้ i != pos 
			prev = prev->next; // วนรอบหาตำแหน่งตัวก่อนหน้าที่จะเพิ่มข้อมูลเข้าไป
			i++; // นับ i 
		}
		ptrglo = create_node(value); // สร้างโหนดรอ
		ptrglo->next = prev->next; // เปลี่ยนทิศทาง Pointer ที่ชี้หลังจากเจอตำแหน่ง previous
		prev->next = ptrglo;
	}
}
void peek_node(int index){ // ดูข้อมูลที่ Index นั้นๆ
	int count = 0,i=0,size;
	size = size_of_linkedlist()-1;
	if(index == 0){  //ดูข้อมูลที่ตำแหน่งแรก
		ptrglo = first;
		printf("Ans : %g\n",ptrglo->info);
	}
	else if(index == -1 || (index == size_of_linkedlist()-1)){ // ดูข้อมุลตำแหย่งสุดท้าย
		ptrglo = first;
		for(i=0;i<size;i++){
			ptrglo = ptrglo->next;
		}
		printf("Ans : %g\n",ptrglo->info);
	}
	else{ // ดูข้อมูลที่อยู่ระหว่างกลาง
		ptrglo = first;
		while(i<index && ptrglo->next != NULL){ // ต้องวนรอบไปเรื่อยๆจนกว่าจะถึง Index ที่ต้องการดูข้อมูล
			ptrglo = ptrglo->next;
			i++;
		}
		if(ptrglo->next != NULL) // เช็คว่ามันวนไปเรื่อยๆแล้วเจอไหมในกรณีนี้คือเจอ
			printf("Ans : %g\n",ptrglo->info);
		else // อันนี้คือไม่เจอ แปลว่ามันค้นไปเลยตัวสุดท้ายแล้วก็ยังไม่ถึง index ที่อยาดูค่า 
			printf("Don't have index to peek,have only [0]-[%d]\n",size); // index เกิน
	}
}
void print_nodeall(){ // แสดงข้อมูลทั้งหมดใน linkedlist
	int i = 0; 
	ptrglo = first;
	while(ptrglo != NULL){ // วนรอบแล้วแสดงผลข้อมูลทีละตัวจนถึงตัวสุดท้าย
		printf("Data [%d] : %g",i,ptrglo->info);
		i++;
		ptrglo = ptrglo->next;
	}
}
linkedlist *pop_node(double *value){ // ดึงหรือลบข้อมูลตัวแรกสุดใน linkedlist
	linkedlist *ptr;
	ptr = first;
	*value = ptr->info;
	first = first->next;
	ptr->next = NULL;
	if(first == NULL) //มีข้อมูลตัวเดียวแล้วกำลังจะถูกดึงออก
		last = NULL;  // แสดงว่าเมื่อดึงออกแล้วจะไม่มีข้อมูลหรือใน Linkedlist
	return ptr; // ส่ง address กลับเพื่อนำไปใช้งานต่อ
}
int search_index(double value,int index[]){ // หา Index ของข้อมูลที่เราทราบค่าอยู่แล้ว
	int i=0,count=0;
	ptrglo = first;
	while(ptrglo != NULL){ // วนรอบไปเรื่อยๆจนกว่าจะหมด
		if(ptrglo->info == value){ // ถ้าเจอค่าที่ต้องการดู Index ให้เก็บค่า Index ที่เจอลงใน Array เพราะอาจมีมากกว่าตำแหน่งเดียว
			index[count++] = i; // พร้อมกับนับจำนวนด้วยจะได้ทราบค่าเจอกี่ที่
		}
		ptrglo = ptrglo->next; 
		i++; // ตัวบอก Index ที่เจอว่ามันเท่ากับเท่าไหร่
	}
	if(count==0) // ถ้ามัน = 0 คือไม่เจอเลย
		return -1; // บอกว่าไม่เจอ
	else // ถ้าไม่เท่ากับ 0 แสดงว่าเจอ
		return count; // ให้บอกว่าเจอกี่ตัว
}
void delete_position(int pos){ // Node ของ Linkedlist ตรง Index ที่ต้องการลบ
	linkedlist *ptr,*prev;
	int i=0;
	double value;
	ptr = prev = first;
	if(pos == 0){ //ลบตัวแรก
		first = ptr->next;
		ptr->next = NULL;
	}
	else if(pos == 0 && size_of_linkedlist() == 1) // มีข้อมูลตัวเดียว
		first = last = NULL;
	else{
		while(i != pos-1){
			prev = prev->next;
			i++;
		}
		if(pos == (size_of_linkedlist()-1)){ // ลบตัวสุดท้าย
			ptr = last;
			last = prev;
			prev->next = NULL;
		}
		else{ // ลบตัวกลาง
			ptr = prev->next;
			prev->next = ptr->next;
			ptr->next = NULL;
		}
	}
	free(ptr); // ลบหน่วยความจำที่ไม่ได้ใช้แล้วคืนให้ระบบ
}
int spilt(char *buff,char token[][20]) //ฟังก์ชั่นแยกแต่ละตัวเพื่อจะนำไปบอกว่าแต่ละตัวนั้นเป็นสตริงชนิดอะไร
{
	char *tok; // สร้าง pointer เพื่อชี้ตัวที่ต้องการแยก
	int count = 0; // สร้างตัวแปร จน.เต็มเพื่อเก็บค่าจำนวน
	tok = strtok(buff," "); // เป็นการให้ tok ชี้ไปที่ตำแหน่งเริ่มและเพิ่มไปเรื่อยๆโดยหา token ของสตริงจนกว่าจะเจอเว้นวรรคจึงหยุดโดยจะเติม \0 ให้อัตโนมัติ
	while(tok != NULL){ // ทำงานเมื่อ tok ไม่เท่ากับ \0
		strcpy(token[count++],tok); // copy ค่าสตริงที่อ่านมาได้มาเก็บไว้ใน token เพื่อจะเอาไปแสดงผลต่อ
		tok = strtok(NULL," "); //เช็คต่อจาก null ที่มันทำให้ก่อนหน้าเพราะ buff เพี้ยนไปแล้ว
	}
	return count; // รีเทิร์นค่าของ count กลับไปใน main เพิ่มนำไปวนรอบเช็คชนิดของสตริงต่อ
}
int check_operater(char *token){ // ฟังก์ชั่น check เครื่องหมายของสตริง
	if((strlen(token) == 1) && (strchr("+-/*()^",token[0]) != NULL)) //เงื่อนไขคือถ้าความยาวของมันเท่ากับ 1 มันจะไม่รวม \0 ก็คือมันต้องมี 1 ตัวและต้องเป็นเครื่องหมายที่เราต้องการด้วย
		return 1; // ถ้าใช่ รีเทิร์นค่าเท่ากับ 1
	else // ถ้าไม่ตรงกับเงื่อนไขด้านบน
		return 0; // รีเทิร์นค่าเท่ากับ 0
}
int check_number(char *token,double *number){ // ฟังก์ชั่นเช็คตัวเลข
	char ch; // สร้าง character มาเพื่ือหาค่าสตริงที่ต่อด้านหลัง
	if(sscanf(token,"%lf%c",number,&ch) == 1) // อ่านค่าจากสตริงที่ส่งมาต้องอ่านได้แค่ตัวแรกซึ่งเป็นเลขและต้องไม่เจอ character เลยสักตัว
		return 1; //ถ้าใช่รีเทิร์น 1
	else
		return 0; // ไม่ใช่รีเทิร์น 0

}
int Index_to_insert(double value){ // หา Index ที่จะเอาค่าใส่เข้าไปใน Linkedlist โดยวิธีการ insert เพราะต้องเพิ่มเข้าไปแบบเรียงข้อมูลด้วย
	linkedlist *ptr_i,*ptr_j;
	int i=0;
	for(ptr_i=first;ptr_i!=NULL && ptr_i->info < value;ptr_i=ptr_i->next) // หาตำแหน่งที่ถูกต้องให้กับข้อมูลในการเพิ่มข้อมูลเข้าไป
			i++; // นับไปเรื่อยๆจนกว่าจะเจอตำแหน่งที่ต้องการ
	if(i==0) // ถ้ามันควรอยู่ตำแหน่งแรก
		return 0;  // ให้ค่า 0 กลับ
	else return i; // แต่ถ้าเป็นตำแหน่งอื่นก็รีเทิร์นค่าตำแหน่งนั้น
}
int check_group(char *token){ // ฟังก์ชั่นเช็คว่าเป็นฟังก์ชั่นไหม
	char fname[19][10] = {"end","list","sort","pop","help","sqrt","rec","neg","pow","+","-","*","/","delete","search","peek","push","add","insert"}; // สร้าง array 2 มิติมาเก็บค่าสตริงที่เป็นฟังก์ชั่น
	int i=0,check =-1; // กำหนด ตัวแปรจำนวนเต็ม
	char buff[20]; //  สร้างสตริงเพื่อจะเอามาเปรียบเทียบ 
	strcpy(buff,token); // copy จากสตริงที่รับมาเปรียบไปอีกสตริงหนึ่ง
	strlwr(buff); // ให้ทุกตัวในสตริงเป็นตัวพิมเล็ก
	for(i=0;i<19;i++){ // สั่งวนรอบเพื่อเช็คกลุ่มของสตริงตัวแรกที่รับมา
		if(strcmp(fname[i],buff) == 0) 
			check = i; // ถ้าตรงกับตัวไหนให้รีเทิร์นค่าตำแหน่งตัวนั้นมา
	}
	if(check < 0) // ไม่ตรงสักอัน
		return 0;
	else if (check>=0 && check <=12) // อยู่ในกลุ่มที่ 1
		return 1;
	else if (check>=13 && check <=16) // อยู่ในกลุ่ม 2
		return 2;
	else return 3; // กลุ่ม 3 ตั้งแต่ตัวที่ 17 ไป
}
void scansort_linkedlist(){ // เรียงลำดับข้อมูลใน Linkedlist ใช้ pointer
	linkedlist *ptr_i,*ptr_j;
	double swap;
	for(ptr_i=first; ptr_i!=NULL ; ptr_i = ptr_i->next){
		for(ptr_j=ptr_i->next; ptr_j != NULL ; ptr_j = ptr_j->next){
			if(ptr_i->info > ptr_j->info){
				swap = ptr_i->info;                     // เรียงเหมือน scansort ปกติทุกอย่างต่างที่ว่าใช้ Address ในการวนรอบไปเรื่อยๆ
				ptr_i->info = ptr_j->info;              // แล้วดูข้อมูลใน node แล้วเอามาเปรียบเทียบเอา
				ptr_j->info = swap;

			}
		}
	}
}
int checksort_linkedlist(){ // ฟังก์ชั่นที่เอาไว้เช็คว่าข้อมูลใน linklist มันเรียงกันอยู่หรือป่าว
	linkedlist *ptr_i,*ptr_j; 
	int check=1;
	for(ptr_i=first; ptr_i!=NULL ; ptr_i = ptr_i->next){
		for(ptr_j=ptr_i->next; ptr_j != NULL ; ptr_j = ptr_j->next){
			if(ptr_i->info > ptr_j->info)
				check = 0;  // ถ้าไม่เรียง check = 0
		}
	}
	return check; // ถ้าเรียง check = 1
}
void show_command(){ // ฟังก์ชั่นแสดงคำสั่งที่่สามารถใช้งานได้
	printf("\t\t\tCommand : (don't need anything behind command): [end][list][help][sort][pop][sqrt][rec][neg][pow][+][-][*][/]\n");
	printf("\t\t\tCommand : (need only one number behind command): [peek n][search n][push n][delete n] \n");
	printf("\t\t\tCommand : (need at least one number behind command): [insert n][add n] \n");
	printf("\t\tNote! : n instead of number\n");
}
int check_num3(int count,char token[][20]){ // ฟังก์ชั่นนี้เอาไว้เช็คตัวเลขถ้าไปเช็คสตริงมาแล้วอยู่กลุ่มที่ 3
	int i,check = 0;
	double number;
	for(i=1,check = 1;i<count && check != 0;i++){ // วนรอบเช็คไปเรื่อยๆจนครบตามค่าที่รับมาแต่ถ้าเจอตัวที่ไม่ใช่ตัวเลขจะหยุดทำทันที
		check = check_number(token[i],&number); // ส่งไปเช็คว่าเป็นตัวเลขหรือป่าว
	}
	if(check != 0) // ถ้าทำจนครบแล้วมันเป็นตัวเลขค่า check จะไม่เท่ากับ 0 แล้วให้ค่า 1 กลีบไปแสดงว่ามันเป็นตัวเลขทั้งหมด
		return 1;
	else // ถ้าไม่เป็น
		return 0;
}
double string_to_num(char *token){ // แปลงจากสตริงเป็นตัวเลขเพื่อที่เอาไปใช้ต่อ
	double value=0; // สร้างตัวแปรที่จะเอามารับค่าหลังจากแปลงค่าแล้ว
	char *end; // สร้าง Pointer ขึ้นมาตัวหนึ่ง
	value = strtod(token,&end);// ใช้ฟังกฺ์ชั่นของ string แปลงโดยอาศัย pointer ที่สร้างในการแปลงค่า
	return value; // ส่งค่ากลับ
}
void monitor_delete(){ // หน้าจอการแสดงผลเมื่อลบค่าค่าใดไป จริงๆใช้ฟังก์ชั่น moniter ปกติด้านล่างก็ได้แต่ผู้เขียนอยากให้ตัวเองเห็นภาพชัดเลยสร้างขึ้นใหม่
	if(size_of_linkedlist() != 0){
		printf("List : ");
		ptrglo = first;
		while(ptrglo != NULL){
			printf(" %g",ptrglo->info);        // เมื่อลบแล้วมันจะแสดงข้อมูลที่เหลืออยู่ใน linkedlist ทันที
			ptrglo = ptrglo->next;
		}
		printf("\n");
	}
	else
		printf("List : NULL\n");
}
void Do_command_list1(char token[][20],int count){ // ฟังก์ชั่นในการทำงานของ Syntax กลุ่ม 1
	double value=0,value2=0;
	linkedlist *ptr;
	if(strcmp(token[0],"list") == 0){ // ถ้าสตริงเท่ากับ list
		if(size_of_linkedlist() > 0) // เช็คว่ามีข้อมูลใน Linkedlist หรือไม่
			print_nodeall(); // มีแสดงข้อมูลทั้งหมด
		else // ไม่มีบอกว่าไม่มีข้อมูล
			printf("No data now!.\n");
		}
	else if(strcmp(token[0],"sort") == 0){   // ถ้าสตริงเท่ากับ sort
		if(size_of_linkedlist() > 0){ // เช็คว่ามีข้อมูลใน Linkedlist หรือไม่
			scansort_linkedlist(); // มี
			printf("Ans : Sort success!.\n");
		}
		else
			printf("No data now!.\n"); // ไม่มีบอกไม่มีข้อมูล
		}
	else if(strcmp(token[0],"pop") == 0){    // ถ้าสตริงเท่ากับ pop
		if(size_of_linkedlist() > 0){// เช็คว่ามีข้อมูลใน Linkedlist หรือไม่
			ptrglo = pop_node(&value); // มีก็ให้ pop ออกมา
			printf("Ans : %g\n",value); // แสดงค่าที่ Pop
			free(ptrglo); // ลบ memmory ทิ้ง 
		}
		else
			printf("No data now!.\n"); // ไม่มีบอกไม่มีข้อมูล
		}
	else if(strcmp(token[0],"help") == 0){   // ถ้าสตริงเท่ากับ help
		show_command(); // แสดงผลคำสั่งที่ใช้งานได้
	}
	else if(strcmp(token[0],"sqrt") == 0){  // ถ้าสตริงเท่ากับ sqrt
		if(size_of_linkedlist() > 0){ // เช็คว่ามีข้อมูลใน Linkedlist หรือไม่
			ptr = pop_node(&value); //  มีก็ Pop ข้อมูลตัวแรกออกมา
			ptr->info = sqrt(value); // เอามาคำนวณ
			printf("Ans : %g\n",ptr->info); //แสดงคำตอบ
			push_node(ptr->info); // ใส่กลับ Linkedlist
			free(ptr); // คืนความจำให้ระบบ
		}
		else
			printf("No data now!.\n"); // ไม่มีบอกไม่มีข้อมูล
	}
	else if(strcmp(token[0],"rec") == 0){  // ถ้าสตริงเท่ากับ rec
		if(size_of_linkedlist() > 0){ // เช็คว่ามีข้อมูลใน Linkedlist หรือไม่
			ptr = pop_node(&value); //  มีก็ Pop ข้อมูลตัวแรกออกมา
			ptr->info = 1/value; // เอามาคำนวณ
			printf("Ans : %g\n",ptr->info); //แสดงคำตอบ
			push_node(ptr->info); // ใส่กลับ Linkedlist
			free(ptr); // คืนความจำให้ระบบ
		}
		else
			printf("No data now!.\n"); // ไม่มีบอกไม่มีข้อมูล
		}
	else if(strcmp(token[0],"neg") == 0){ // ถ้าสตริงเท่ากับ neg
		if(size_of_linkedlist() > 0){ // เช็คว่ามีข้อมูลใน Linkedlist หรือไม่
			ptr = pop_node(&value); //  มีก็ Pop ข้อมูลตัวแรกออกมา
			ptr->info = -value; // เอามาคำนวณ
			printf("Ans : %g\n",ptr->info); //แสดงคำตอบ
			push_node(ptr->info); // ใส่กลับ Linkedlist
			free(ptr); // คืนความจำให้ระบบ
		}
		else
			printf("No data now!.\n"); // ไม่มีบอกไม่มีข้อมูล
		}
	else if(strcmp(token[0],"pow") == 0){   // ถ้าสตริงเท่ากับ pow
		if(size_of_linkedlist() > 1) { // เช็คว่ามีข้อมูลใน Linkedlist มากกว่า 1 ตัวหรือไม่
		ptr = pop_node(&value);
		ptrglo = pop_node(&value2);   //  มีก็ Pop ข้อมูลออกมา 2 ครั้งเพราะต้องการ 2 ตัวเพื่อคำนวณ
		ptr->info = pow(value2,value); // เอามาคำนวณ
		printf("Ans : %g\n",ptr->info);  //แสดงคำตอบ
		push_node(ptr->info);  // ใส่กลับ Linkedlist
		free(ptr);free(ptrglo); // คืนความจำให้ระบบ
		}
		else if(size_of_linkedlist() == 1) // ถ้ามีแค่ 1 ตัวให้บอกต้องการข้อมูล 2 ตัว
			printf("Need 2 data to Calculate\n"); 
		else
			printf("No data now!.\n"); // ไม่มีบอกไม่มีข้อมูล
		}
	else if(strcmp(token[0],"+") == 0){  // ถ้าสตริงเท่ากับ +
		if(size_of_linkedlist() > 1){ // เช็คว่ามีข้อมูลใน Linkedlist มากกว่า 1 ตัวหรือไม่
		ptr = pop_node(&value);
		ptrglo = pop_node(&value2); //  มีก็ Pop ข้อมูลออกมา 2 ครั้งเพราะต้องการ 2 ตัวเพื่อคำนวณ
		ptr->info = value2 + value;  // เอามาคำนวณ
		printf("Ans : %g\n",ptr->info); //แสดงคำตอบ
		push_node(ptr->info);  // ใส่กลับ Linkedlist
		free(ptr);free(ptrglo);   // คืนความจำให้ระบบ
		}
		else if(size_of_linkedlist() == 1) // ถ้ามีแค่ 1 ตัวให้บอกต้องการข้อมูล 2 ตัว
			printf("Need 2 data to Calculate\n");
		else
			printf("No data now!.\n"); // ไม่มีบอกไม่มีข้อมูล

		}
	else if(strcmp(token[0],"-") == 0){ // ถ้าสตริงเท่ากับ -
		if(size_of_linkedlist() > 1){ // เช็คว่ามีข้อมูลใน Linkedlist มากกว่า 1 ตัวหรือไม่
		ptr = pop_node(&value);
		ptrglo = pop_node(&value2); //  มีก็ Pop ข้อมูลออกมา 2 ครั้งเพราะต้องการ 2 ตัวเพื่อคำนวณ
		ptr->info = value2 - value; // เอามาคำนวณ
		printf("Ans : %g\n",ptr->info);  //แสดงคำตอบ
		push_node(ptr->info);  // ใส่กลับ Linkedlist
		free(ptr);free(ptrglo); // คืนความจำให้ระบบ
		}
		else if(size_of_linkedlist() == 1) // ถ้ามีแค่ 1 ตัวให้บอกต้องการข้อมูล 2 ตัว
			printf("Need 2 data to Calculate\n");
		else
			printf("No data now!.\n"); // ไม่มีบอกไม่มีข้อมูล
		}
	else if(strcmp(token[0],"*") == 0){ // ถ้าสตริงเท่ากับ *
		if(size_of_linkedlist() > 1){ // เช็คว่ามีข้อมูลใน Linkedlist มากกว่า 1 ตัวหรือไม่
		ptr = pop_node(&value);
		ptrglo = pop_node(&value2); //  มีก็ Pop ข้อมูลออกมา 2 ครั้งเพราะต้องการ 2 ตัวเพื่อคำนวณ
		ptr->info = value2*value; // เอามาคำนวณ
		printf("Ans : %g\n",ptr->info); //แสดงคำตอบ
		push_node(ptr->info); // ใส่กลับ Linkedlist
		free(ptr);free(ptrglo); // คืนความจำให้ระบบ
		}
		else if(size_of_linkedlist() == 1) // ถ้ามีแค่ 1 ตัวให้บอกต้องการข้อมูล 2 ตัว
			printf("Need 2 data to Calculate\n");
		else
			printf("No data now!.\n"); // ไม่มีบอกไม่มีข้อมูล
		}
	else{ // ถ้าไม่ใช่เหลืออันเดียว /
		if(size_of_linkedlist() > 1){ // เช็คว่ามีข้อมูลใน Linkedlist มากกว่า 1 ตัวหรือไม่
		ptr = pop_node(&value);
		ptrglo = pop_node(&value2); //  มีก็ Pop ข้อมูลออกมา 2 ครั้งเพราะต้องการ 2 ตัวเพื่อคำนวณ
		ptr->info = value2/value; // เอามาคำนวณ
		printf("Ans : %g\n",ptr->info); //แสดงคำตอบ
		push_node(ptr->info); // ใส่กลับ Linkedlist
		free(ptr);free(ptrglo); // คืนความจำให้ระบบ
		}
		else if(size_of_linkedlist() == 1) // ถ้ามีแค่ 1 ตัวให้บอกต้องการข้อมูล 2 ตัว
			printf("Need 2 data to Calculate\n"); 
		else
			printf("No data now!.\n"); // ไม่มีบอกไม่มีข้อมูล
		}
}
void Do_command_list2(char token[][20],int count){
	int i=0,start=0,index[20],indexnum=0,j=0;
	double value;
	char ch;
	if(strcmp(token[0],"peek") == 0){ // ถ้าเป็นคำสั่ง Peek
		if(size_of_linkedlist() > 0){ // เช็คข้อมูลใน linkedlist ว่ามีไหม
			indexnum = (int)string_to_num(token[1]); // มีก็แปลงค่าเลขที่ตามมา 1 ตัว
			peek_node(indexnum); // ส่งค่านั้นไป peek
		}
		else printf("No data now!.\n"); // ไม่มีข้อมูล
	}
	else if(strcmp(token[0],"search") == 0){ // ถ้าเป็นคำสั่ง Search
		if(size_of_linkedlist() > 0){ // เช็คข้อมูลใน linkedlist ว่ามีไหม
			value = string_to_num(token[1]);  // มีก็แปลงค่าเลขที่ตามมา 1 ตัว
			indexnum = search_index(value,index); // ส่งค่านั้นไป หา Index 
			if(indexnum == -1) // ไม่มีค่านั้นใน Linkedlist
				printf("Not Found %g in Linkedlist.\n",value);
			else{ // มี
				printf("Found %g in Linkedlist : ",value); 
				for(i=0;i<indexnum;i++){ // วนรอบบอกว่าเจอที่ไหนบ้างเพราะอาจมีมากกว่า 1 ตำแหน่ง
					if(i < indexnum-1)
						printf("%d",index[i]);
					else
						printf("%d\n",index[i]);
				}
			}
		}
		else printf("No data now!.\n"); // ไม่มีข้อมูลใน Linkedlist
	}
	else if(strcmp(token[0],"push") == 0){ // คำสั่ง Push
		value = string_to_num(token[1]); // แปลงค่าสตริงเป็นเลข
		push_node(value); // push 
	}
	else{ //delete
		value = string_to_num(token[1]);// แปลงค่าสตริงเป็นเลข
		indexnum = search_index(value,index); //  เช็คค่านั้นว่ามีใน linkedlist ไหม
		if(indexnum != -1){ // มี
			while(indexnum != 0){ // วนรอบทำไปเรื่อยๆจนกว่าจะเท่ากับค่าว่าเจอกี่ตัว
				printf("Ans : Do you want to delete linklist [%d] (Y/N)  ",index[0]); // ถามว่าจะลบค่านั้นจริงๆไหม
				scanf(" %c",&ch);  // รับคำตอบ
				if(ch == 'y' || ch == 'Y'){ //ลบ
					delete_position(index[0]); // ส่งค่าไปลบ
					if(indexnum > 1) // เช็คว่าถ้าจำนวนครั้งที่จะต้องทำมากกว่า 1 
						monitor_delete(); // ให้อัพเดตข้อมูลใน linkedlist ด้วย
					indexnum -= 1; // ลบจำนวนครั้งที่ต้องทำไป 1 เพราะทำไปแล้ว
					for(i=1,j=0;i<=indexnum;i++) // อัพเดตข้อมูล index ที่ต้องลบ
						index[j++] = index[i]-1; // อัพเดตตำแหน่ง index เพราะมันถูกลบ
				}				
				else{ // ไม่ลบ
					printf("Ans : Cancel delete %g in Linkedlist[%d]\n",value,index[0]); // ไม่แสดงข้อความยกเลิก
					indexnum -= 1; // ลบจำนวนครั้งที่ต้องทำไป 1 เพราะทำไปแล้ว
					for(i=1,j=0;i<=indexnum;i++) // อัพเดตข้อมูล index ที่ต้องลบ
						index[j++] = index[i]; 
				}
				rewind(stdin); // ลบค่าที่ค้างในคีย์บอร์ด
			}
		}
		else if(indexnum == -1 && (size_of_linkedlist() == 0)) // ถ้าไม่เจอค่าที่ต้องการลบแล้วไม่มีข้อมูลใน Linkedlist 
			printf("Ans : No data to delete now.\n",value); // บอกไม่มีข้อมูล
		else // แต่ถ้ามีข้อมูลแต่หาตำอหน่งไม่เจอก็คือไม่มีข้อมูลนั้นใน likedlist
			printf("Ans : Not Found %g in Linkedlist.\n",value); 
	}
}
void Do_command_list3(char token[][20],int count){ // คำสั่ง group 3
	int i,check,pos=0;
	double value=0;
	if(strcmp(token[0],"add") == 0){ // คำสั่ง add
		for(i=1;i<count;i++){ // วนรอบ add ข้อมูลไปเรื่อยจนกว่าพารามิเตอร์ที่ตามหลังมาจะหมด
			value = string_to_num(token[i]); // แปลงค่าก่อน
			add_node(value);
		}
	}
	else{ // คำสั่ง insert
		check = checksort_linkedlist(); // check ว่าข้อมูลเรียงอยู่หรือไม่
		if(check == 1){ //เรียง
			for(i=1;i<count;i++){
				value = string_to_num(token[i]);  // แปลงค่าก่อน
				pos = Index_to_insert(value); // หาตำแหน่งที่ควรจะ Add ข้อมูลลงไป
				insert_node(value,pos); // ส่งตำแหน่งไปเพิ่ม
			}
		}
		else // ไม่เรียง
			printf("Can't Insert data,please sorted before.\n");

	}
}
void monitor(){ // แสดงผลข้อมูลใน Linkedlist
	if(size_of_linkedlist() != 0){ // มีข้อมูล
		printf("List : ");
		ptrglo = first;
		while(ptrglo != NULL){
			printf(" %g",ptrglo->info);
			ptrglo = ptrglo->next;
		}
		printf("\n");
	}
	else //ไม่มีข้อมูล
		printf("List : NULL\n");
	printf("Command : "); // สั่งให้แสดงผลข้อความ
}
int main(){
	char str[255],buff[255],token[40][20]; // สร้างสตริงเพื่อเก็บค่าของข้อความ
	int count,i = 0,group = 0; // สร้างตัวแปร จน.เต็ม
	double number; // สร้างตัวแปร จน.จริงprintf("Parametor Error!\n")
	do{
	monitor();
	gets(str); // รับค่าสตริง EX. "ABC...." 
	if(strcmp(str,"end") != 0){ // เปรียบเทียบค่าสตริง กับคำว่า end ถ้ามันเหมือนกันจะไม่ทำคำสั่งใน if จะข้ามไปจบโปรแกรมเลย
		strcpy(buff,str); //copy ค่าสตริงจาก str มาไว้ใน buff
		strlwr(buff); // ให้สตริงใน buff ตัวเลขหมด
		count = spilt(buff,token); // ให้ทำฟังก์ชั่น spilt แยกแต่ละตัวออกจากกันเพื่อจะทำไประบุชนิดสตริงและรีเทิร์นค่าจำนวนที่แยกออกมาเสร็จสิ้นให้ count
		group = check_group(token[0]); // เช็คกลุ่ม
		if(group == 1 && count == 1) // อยู่กลุ่ม 1 และต้องมีสตริงที่รับมาแค่ตัวเดียว
			Do_command_list1(token,count); // ทำคำสั่งในกลุ่ม 1
		else if(group == 2 && count == 2 && (check_number(token[1],&number) == 1))// อยู่กลุ่ม 2 และต้องมีสตริงที่รับมาแค่ 2 ตัวและตัวที่ 2 ต้องเป็นตัวเลข
			Do_command_list2(token,count);// ทำคำสั่งในกลุ่ม 2
		else if(group == 3 && count >=2 && (check_num3(count,token) == 1))// อยู่กลุ่ม 3 และต้องมีสตริงที่รับมาอย่างน้อย 2 ตัวและตัวที่ 2 เป็นต้นไปต้องเป็นตัวเลข
			Do_command_list3(token,count);// ทำคำสั่งในกลุ่ม 3
		else if(group == 0) // อยู่กลุ่ม 0 อยู่ในกลุ่มใดเลย
			printf("Syntax Error!,please try again.\n");
		else // อยู่ใน 3 กลุ่มแต่จำนวนไม่ตามที่ต้องการ
			printf("Parametor Error!\n");
		}
	}while(strcmp(str,"end") != 0);// จะทำซ้ำเมื่อสตริง str กับ end ไม่เท่ากัน
	printf("Answer : END\n");
	printf("This program is written by Sorathorn Kaewchotchuangkul 63070501067 CPE REGULAR");
	return 0; 
}