import java.util.ArrayList;

public class Dnode {
	ArrayList<String> mean; // Node of mean & type
	public Dnode() { // don't have data
		mean = new ArrayList<String>();
		mean.add(""); 
	}
	public Dnode(String [] buff) { // have data 
		String meaning;
		if(buff.length == 1) // only words 
			meaning = "";
		else if(buff.length == 2) { // have meaning 
			meaning = buff[1];
		}
		else // have meaning & type 
			meaning = buff[1] + "("+ buff[2] +")";
		mean = new ArrayList<String>(); // reserve memor 
		mean.add(meaning); // add meaning into ArrayList
	}
}
