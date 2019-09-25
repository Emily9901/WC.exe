package PJ;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class wowow {
	private int charnum=0;         
	private int wordnum=0;
	private int linenum=0;
	private static int notenum=0;
	private static int codenum=0;
	private static int empnum=0;
	/*
	 hjkljkl
	 
	 */
	private String filepath;
	private String s1;
	private static String s2;  
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file=new File("D:\\yuanli\\Documents\\9.c");
		  String sss=file.getAbsolutePath();
		  ExtendOption(sss);
		  System.out.println(codenum);
		  System.out.println(empnum);
		  System.out.println(notenum);
		  if("-x".matches("-x")) System.out.println("111");
	}
	public static void ExtendOption(String filepath) throws IOException{
		
	}
}
