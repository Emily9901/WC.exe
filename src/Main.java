package PJ;



import java.util.Scanner;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import PJ.Option;   

import java.io.BufferedReader;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

import java.util.regex.Matcher;

import java.util.regex.Pattern;



import PJ.interfram;                                              



public class Main {

        int[] command1=new int[6];                      //用一个标记数组标记用户在命令行下选了哪些功能

	                                                //数组下标从大到小对应数值标记计算字符数，词数，行数，空行，注释行，代码行一共6个功能

	Pattern p1=Pattern.compile("-x");

	static Pattern p2=Pattern.compile("-s");

	Pattern p3=Pattern.compile("-c");

	Pattern p4=Pattern.compile("-w");

	Pattern p5=Pattern.compile("-l");

	Pattern p6=Pattern.compile("-a");

	Pattern p7=Pattern.compile("\\*.");

	

	String instru=null;                             //用于存储用户命令中的功能部分           

        String flph=null;                               //用于存储用户命令中的路径部分

	String ssflph=null;                             //用于存储目录路径

        String sssflph=null;                            //用于存储目录中指定文件的后缀

    

	Option option=new Option();                     

	static interfram inter=new interfram();         

	

	int tag=0;                                      //用于标记处理目录时有无指定具体的后缀形式

	

	public static void main(String[] args) throws IOException {

		// TODO Auto-generated method stub

		System.out.println("----------------------------命令格式----------------------------");

		System.out.println("-x                             //切换到图形界面");                

		System.out.println("-c [文件路径]                  //返回字符数");

		System.out.println("-w [文件路径]                  //返回词数");

		System.out.println("-l [文件路径]                  //返回行数");

		System.out.println("-a [文件路径]                  //返回空行数、注释行数和代码行数");

		System.out.println("-s [-c/-w/-l/-a] [目录路径]    //递归处理目录下符合条件的文件");

		System.out.println("----------------------------------------------------------------");

		System.out.println("示例:(1) -c -w D:\\test.java");    

		System.out.println("     (2) -s -c D:\\*.java");

		System.out.println("----------------------------------------------------------------");

		System.out.println("输入命令:");

		while(true){                                                               //实现从命令行不断接收用户命令

		Scanner in=new Scanner(System.in);

		String input=in.nextLine();

		if(input.matches("-x")){inter.setVisible(true);}                           //若输入-x，则切换到图形界面

		else{

		Main main1=new Main();

		if(main1.check(input)) main1.deal();}                                      //否则，先对输入的命令进行检查，格式正确再进行分析实现

		}			

	  }

	                                               

	public boolean check(String input) throws IOException{                             //用于检查命令行输入格式是否正确，并且对命令加以处理



		flph=null;                                                                 //初始化变量

		ssflph=null;                                                               

		sssflph=null;                                                             

		

		if(input.matches("-\\S\\s*")){                                             //处理输入只有单个功能的请况，eg:"-c"

			instru=input;

			flph=null;

		}else{                                                                     //处理输入的命令中有无文件路径的情况，eg:"-s -c -l"和"-s -c -l D:\\test"

		String[] ipu=input.split(" ");                                             //并且从输入中分离出命令的功能部分(instru)和路径部分(flph)

		flph=ipu[ipu.length-1];

		instru="";

		for(int i=0;i<ipu.length-1;i++){                                           

			instru=instru+ipu[i];

			if(i==ipu.length-2&&ipu[ipu.length-1].matches("-\\S")){

				instru=instru+ipu[i+1];

				flph=null;

			}

		}			     

		}

		if(flph==null){System.out.println("路径不存在");return false;}                           

		

		if(!instru.matches("(-[cwla]){1,}")&&!instru.matches("(-s)(-[cwla]){1,}")){ //判断输入的功能命令是否正确

			System.out.println("命令输入错误");

			return false;

		}

		if(p2.matcher(instru).find()){                                                    //命令中有-s，说明处理的对象是目录

			

			if(p7.matcher(flph).find()){                                              //-s的情况下有*.，表明有指定具体的文件后缀

				tag=0;

				Pattern p8=Pattern.compile("\\*.\\S{1,}");

				if(p8.matcher(flph).find()){                                      //利用*.分离出目录路径和指定的后缀

				String[] sflph=flph.split("\\*.");          

				ssflph=sflph[0];

				sssflph=sflph[1];

				}else {System.out.println("没有指定目标文件的属性");  return false;}

				

			if(ssflph==null||ssflph.matches("\\s*")){return false;}

			if(sssflph==null){return false;}

			File file=new File(ssflph);

			File[] files=file.listFiles();

			if(files==null){                                                          //判断目录不存在

				System.out.println("目录不存在");                          

				return false;

			}

			if(files.length==0){

				System.out.println("目录为空");                                   //判断目录为空

				return false;

			}

		  }else if(flph!=null){                                                          //没有*.的情况，说明没有指定具体的后缀

			  tag=1;

			  File fll=new File(flph);

			  if(fll.isFile()){System.out.println("错误！应为文件夹路径！"); return false;}

			  File flll[]=fll.listFiles();

			  if(flll==null){ 

				  System.out.println("目录不存在");                              //同样判断目录不存在

				  return false;

			  }

			  if(flll.length==0){

				  System.out.println("目录为空");                                //判断目录为空                           

				  return false;

			  }

		  }else {System.out.println("文件路径错误");return false;}                       //除了以上两种情况，其他情况表明输入的目录路径错误

			

		return true;

		}

		else{                                                                           //没有-s，表明处理的对象是单个文件

			if(flph==null){System.out.println("文件路径不存在");return false;}

			File file=new File(flph);

			if(!file.exists()){

				System.out.println("找不到指定文件");                               

				return false;

			}else if(!file.isFile()){

				System.out.println("文件路径错误");

				return false;

			}else return true;

			

		}			

	}

	

	public void deal() throws IOException{                                                //用于对正确命令的分析与实现

		for(int k=0;k<command1.length;k++){                                           //初始化标记数组

			command1[k]=0;

		}

		if(p3.matcher(instru).find()){command1[5]=1;}

		if(p4.matcher(instru).find()){command1[4]=1;}

		if(p5.matcher(instru).find()){command1[3]=1;}

		if(p6.matcher(instru).find()){command1[2]=1;command1[1]=1;command1[0]=1;}

		if(p2.matcher(instru).find()){

			if(tag==0){

				option.isDirectory(command1, ssflph, sssflph);                //目录中有指定具体的后缀

			}else if(tag==1){

				option.isDirectory(command1, flph, "\\S*");                   //无指定后缀

			}

			

		}else{

			option.Command(command1, flph);                                       //单个文件

		}

		

	}

}
