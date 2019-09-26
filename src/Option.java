package PJ;



import java.io.BufferedReader;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

import java.util.regex.Matcher;

import java.util.regex.Pattern;



public class Option {           

	

  public static int charnum=0;         

  public static int wordnum=0;

  public static int linenum=0;           

  public static int notenum=0;

  public static int codenum=0;

  public static int empnum=0;

  public String filepath;

  public static String s1;

  public static String s2;            

  boolean notetag=false;       



  public static void BaseOption(String filepath) throws IOException{                   //实现基本功能

	  charnum=0;wordnum=0;linenum=0;                                               //初始化

	  BufferedReader br=new BufferedReader(new FileReader(filepath));

	  while((s1=br.readLine())!=null){

		  linenum++;                                                           //计算行数

		  int l=s1.length();

		  for(int i=0;i<l;i++){

			Character c=s1.charAt(i);

			if(!(c==' '||c=='\n'||c=='\t'||c=='\r')){

				charnum++;                                             //计算字符数

				if(is(c)){

					if(i==0 || !is(s1.charAt(i-1)))

						wordnum++;                             //计算词数

				}

			}

		  }

	  }

  }

  

  

  public  static void ExtendOption(String filepath) throws IOException{                //实现扩展功能

	  empnum=0;notenum=0;codenum=0;                                                //初始化

	  BufferedReader br1=new BufferedReader(new FileReader(filepath));

	  Boolean notetag=false;                                                       //用于标记以/*  */标注的注释行

	  while((s2=br1.readLine())!=null){

		  Pattern p=Pattern.compile("\\*/");

		  Matcher m=p.matcher(s2);

		  int is_char=0;                                                       //用于计算非空字符

		  int l2=s2.length();

		  int x=0,y=0;

		  for(int k=0;k<l2;k++){

				Character c2=s2.charAt(k);

				if(!(c2==' '||c2=='\n'||c2=='\t'||c2=='\r')){

					is_char++; 

					if(c2=='/'){

						x=1;                                            						

						if(is_char>2){                          //如果在每行中的第一个"/"前面已有2个或2个以上的字符，则代码行数目加1

							codenum++;                                                 

							if(k+1<l2){

								if(s2.charAt(k+1)=='*'){ //如果该行中有"/*"且无"*/"，则标志着注释的开始，此时notetag=true

									if(!m.find()){

									notetag=true;notenum=notenum-1;

									}

								}

							}

							}

						else if(k+1<l2){                                            //如果每一行中第一个"/"前面有不多于一个字符                                              

 

							if(s2.charAt(k+1)=='/'){notenum+=1;is_char+=1;}     //而且"/"后面是字符"/"，则注释行数目加1

							if(s2.charAt(k+1)=='*'){                            //如果该行存在"/*"

								is_char+=1;

								if(m.find()){notenum++;}                    //且该行存在"*/"，则注释行数目加1

								else{notetag=true;}                         //否则标志着注释的开始，此时notetag=true

							}

						}

					}

					if(c2=='*'){                                                        //如果在这一行中最先找到的"*"而不是"/"

						

						if(k+1<l2){

							int i=0;

							

							if(s2.charAt(k+1)=='/'){                            //如果下一个字符是"/"

								y=1;

								is_char+=1;

								int b;

								for(b=k+1;b<l2;b++){

									if(!(s2.charAt(b)==' '||s2.charAt(b)=='\n'||s2.charAt(b)=='\t'||s2.charAt(b)=='\r'))i++;

								}

								if(i>2) {codenum++;notetag=false;}           //如果"*/"后面的非空字符多于1个，则为注释行，codenum++

								else{notenum+=1;notetag=false;}              //如果小于等于1个，则标志着注释结束("/* */")，此时notetag=false

							}

						}

					}					  					

				

				}

				if(x==1||y==1)break;

			  }  

		   if(notetag==true){                      

				

				notenum+=1;

			 			

			}

		   if(is_char<=1&&notetag==false){ empnum++;}                       //如果该行的非空字符小于等于1而且不属于注释行，则为空行，此时empnum++                        

		   if(notetag==false&&is_char>1&&x==0&&y==0){codenum++;}            //表示除了上面的所有情况外剩下的都属于代码行

		   

		   



	  }

  }

  

  public static boolean is(char ch){

	  if(Character.toString(ch).matches("[\\w]")||Character.toString(ch).matches("[\u4e00-\u9fa5]"))

		  return true;  

	  else return false;

  }

                                                        

  public static void isDirectory(int[] command,String filepath,String suffix) throws IOException{      //用于递归处理文件夹中指定的文件

	  File file=new File(filepath);

	  File[] files=file.listFiles();

	  for(File f:files){

		if(f.isDirectory())  

			isDirectory(command,f.getAbsolutePath(),suffix);                                        

		else if(f.isFile()){

			String fipa=f.getAbsolutePath();

			String filename=f.getName();

			String fs=filename.substring(filename.lastIndexOf(".")+1);                     //获得目录中某一文件的后缀        

			if(fs.matches(suffix)){                                                        //与指定的后缀相比较

				Command(command,fipa);                                                 //若相同，则表明是我们要找的文件

			}

		}

	  }

  }                                                  

                                                     

                                                        

  public static void Command(int[] command,String filepath) throws IOException{                       //处理单个文件，实现对应的功能

	  BaseOption(filepath);                                                                       //调用基本功能函数

	  ExtendOption(filepath);                                                                     //调用扩展功能函数

	  String[] sfp=filepath.split("\\\\");

	  String sfpp=sfp[sfp.length-1];                                                              //获得对应的文件名

	  System.out.println("文件名:"+sfpp);

	  if(command[5]==1)

		  System.out.println("字符数："+charnum);

	  if(command[4]==1)                              

		  System.out.println("词数："+wordnum); 

	  if(command[3]==1)

		  System.out.println("行数："+linenum);

	  if(command[2]==1)

		  System.out.println("空行："+empnum);

	  if(command[1]==1)

		  System.out.println("代码行："+codenum);

	  if(command[0]==1)                               

		  System.out.println("注释行："+notenum);	                                                      

	  System.out.println(" ");

	  

  }



}
