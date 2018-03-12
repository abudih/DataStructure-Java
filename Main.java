/**
 * 
 * 
 */
import java.io.*;
import java.util.*;

public class Main {
	
	public static LinkedList readValues(File file) throws FileNotFoundException{
		
		LinkedList list = new LinkedList();
		Scanner sc = new Scanner(file);
		
		while(sc.hasNextInt())
		{
			list.append(sc.nextInt());
		}
		
		return list;
	}
	
	public static void displayList(LinkedList list){
		System.out.println(list.toString());
	}
	
	public static LinkedNode resultBubbleSort(LinkedList list){
		
		return Sorting.bubbleSort(list.getHead());
	}
	
	public static LinkedNode resultShellSort(LinkedList list){
		return Sorting.shellSort(list.getHead());
	}
	
	public static void main(String args[]) throws FileNotFoundException{
		
		LinkedList list = new LinkedList();
		File file1 = new File("random100.txt");
		File file2 = new File("random1000.txt");
		File file3 = new File("random10000.txt");
		File file4 = new File("reverse100.txt");
		File file5 = new File("reverse1000.txt");
		File file6 = new File("reverse10000.txt");
		File file7 = new File("inorder100.txt");
		File file8 = new File("inorder1000.txt");
		File file9 = new File("inorder10000.txt");
		
		File file = new File("output.txt");
		
		long fileSize;
		long startTime; 
		long endTime ;
		long duration ;
		
		
		list = readValues(file1);
		fileSize=file1.length();
		
		startTime = System.currentTimeMillis();
		resultBubbleSort(list);
		resultShellSort(list);
		endTime = System.currentTimeMillis();
		duration = endTime - startTime ;
		
		displayList(list);
		
		PrintWriter out = new PrintWriter(file.getAbsoluteFile());
		
		int _cmp = Sorting.cmp;
		int _exch = Sorting.exch;
		int sum1 = _cmp;
		int sum2 = _exch;
		
		out.println("k      "+"pass     "+" cmp    " + "  exch  ");
		out.println("-------------------------");  
        out.println(fileSize +"    "+duration +"     " + _cmp + "        " + _exch);
        
//        out.close();
       
		
		list = readValues(file2);
		fileSize=file2.length();
		
		startTime = System.currentTimeMillis();
		resultBubbleSort(list);
		resultShellSort(list);
		endTime = System.currentTimeMillis();
		duration = endTime - startTime;
		
		displayList(list);
		
		_cmp = Sorting.cmp;
		_exch = Sorting.exch;
		
		sum1+=_cmp;
		sum2+=_exch;
		
        out.println(fileSize +"   "+duration +"    " +_cmp + "       " + _exch);
		
		list = readValues(file3);
		fileSize=file3.length();
		
		startTime = System.currentTimeMillis();
		resultBubbleSort(list);
		resultShellSort(list);
		endTime = System.currentTimeMillis();
		duration = endTime - startTime;
		
		displayList(list);
		
		_cmp = Sorting.cmp;
		_exch = Sorting.exch;
		
		sum1+=_cmp;
		sum2+=_exch;
		
		out.println(fileSize +"  "+duration +"   " +_cmp + "      " + _exch);
		
		list = readValues(file4);
		fileSize=file4.length();
		
		startTime = System.currentTimeMillis();
		resultBubbleSort(list);
		resultShellSort(list);
		endTime = System.currentTimeMillis();
		duration = endTime - startTime;
		
		displayList(list);
		
		_cmp = Sorting.cmp;
		_exch = Sorting.exch;
		
		sum1+=_cmp;
		sum2+=_exch;
		
		out.println(fileSize +"    "+duration +"     " +_cmp + "      " + _exch);
		
		list = readValues(file5);
		fileSize=file5.length();
		
		startTime = System.currentTimeMillis();
		resultBubbleSort(list);
		resultShellSort(list);
		endTime = System.currentTimeMillis();
		duration = endTime - startTime;

		displayList(list);
		
		_cmp = Sorting.cmp;
		_exch = Sorting.exch;
		
		sum1+=_cmp;
		sum2+=_exch;
		
		out.println(fileSize +"   "+duration +"     " +_cmp + "      " + _exch);
		
		list = readValues(file6);
		fileSize=file6.length();
		
		startTime = System.currentTimeMillis();;	
		resultBubbleSort(list);
		resultShellSort(list);
		endTime = System.currentTimeMillis();;
		duration = endTime - startTime;
		
		displayList(list);
		
		_cmp = Sorting.cmp;
		_exch = Sorting.exch;
		
		sum1+=_cmp;
		sum2+=_exch;
		
		out.println(fileSize +"  "+duration +"     " +_cmp + "     " + _exch);
		
		list = readValues(file7);
		fileSize=file7.length();
		
		startTime = System.currentTimeMillis();;	
		resultBubbleSort(list);
		resultShellSort(list);
		endTime = System.currentTimeMillis();
		duration = endTime - startTime;
		
		displayList(list);
		
		_cmp = Sorting.cmp;
		_exch = Sorting.exch;
		
		sum1+=_cmp;
		sum2+=_exch;
		
		out.println(fileSize +"    "+duration +"     " +_cmp + "     " + _exch);
		
		list = readValues(file8);
		fileSize=file8.length();
		
		startTime = System.currentTimeMillis();
		resultBubbleSort(list);
		resultShellSort(list);
		endTime = System.currentTimeMillis();
		duration = endTime - startTime;
		
		displayList(list);
		
		_cmp = Sorting.cmp;
		_exch = Sorting.exch;
		
		sum1+=_cmp;
		sum2+=_exch;
		
		out.println(fileSize +"   "+duration +"     " +_cmp + "     " + _exch);
		
		list = readValues(file9);
		fileSize=file9.length();
		
		startTime = System.currentTimeMillis();	
		resultBubbleSort(list);
		resultShellSort(list);
		endTime = System.currentTimeMillis();
		duration = endTime - startTime;
		
		displayList(list);
		
		_cmp = Sorting.cmp;
		_exch = Sorting.exch;
		
		sum1+=_cmp;
		sum2+=_exch;
		
		out.println(fileSize +"  "+duration +"   " +_cmp + "     " + _exch);
        
        out.println("-------------------------------");
        
        out.println("Total          " + sum1 + "    " + sum2);
        
        out.close();
		
		System.out.println(Sorting.exch);
		System.out.println(Sorting.cmp);
	}

}
