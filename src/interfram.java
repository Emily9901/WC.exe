package PJ;



import java.awt.BorderLayout;

import java.awt.Color;

import java.awt.Dimension;

import java.awt.Font;

import java.awt.GridLayout;

import java.awt.TextArea;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.File;

import java.io.IOException;



import javax.swing.*;

import javax.swing.filechooser.FileSystemView;



import PJ.Option;



public class interfram extends JFrame implements ActionListener{

        int[] command2=new int[6];

	int[] Enabled=new int[3];

	Font F1=new Font("黑体",0,16);

	Font F2=new Font("黑体",0,14);

	Font F3=new Font("楷体",0,17);

	Color C1=new Color(245,245,245);

	//下面是需要的组件

	JButton DFP=new JButton("选取文件夹/文件");

	JTextField dfp=new JTextField(25);

	

	JLabel Label1=new JLabel("后缀:");

	//这几个当不是选择文件夹的时候不能选，而且只能选其中一个

	ButtonGroup bg=new ButtonGroup();

	JRadioButton jrb1=new JRadioButton(".txt");

	JRadioButton jrb2=new JRadioButton(".c");

	JRadioButton jrb3=new JRadioButton(".java");

	JRadioButton jrb9=new JRadioButton("all");

	

	JLabel Label2=new JLabel("功能:");

	JRadioButton jrb4=new JRadioButton("字符数");

	JRadioButton jrb5=new JRadioButton("词数");

	JRadioButton jrb6=new JRadioButton("行数");

	JRadioButton jrb7=new JRadioButton("空行/注释行/代码行");

	

	JButton OK=new JButton("OK");

	TextArea Result=new TextArea();

	

	String fl_ph=null;

	String suffix1=null;

	

	int F=0;                                //标记选的是文件夹

	int f=0;                                //标记选的是文件

	

	Option option1=new Option();

		

	public interfram(){

		this.setTitle("Count");

		this.setLayout(new BorderLayout());      

		this.setSize(750,700);

		this.setResizable(false);

		this.setLocation(600, 170);

		

		JPanel p1=new JPanel(new GridLayout(4,1));

		p1.setPreferredSize(new Dimension(750,160));

		this.add(p1,BorderLayout.NORTH);

		

		JPanel pp1=new JPanel();        //界面第一行的设置

		DFP.setFont(F2);

		DFP.setFocusPainted(false);

		DFP.setBackground(C1);

		pp1.add(DFP);

		dfp.setPreferredSize(new Dimension(30,27));

		pp1.add(dfp);

		

		JPanel pp2=new JPanel();       //界面第二行的设置

	        Label1.setFont(F1);

	        jrb1.setFont(F3);

	        jrb2.setFont(F3);

	        jrb3.setFont(F3);

	        jrb9.setFont(F3);

	        bg.add(jrb1);

		bg.add(jrb2);

		bg.add(jrb3);

		bg.add(jrb9);

		pp2.add(Label1);

		pp2.add(jrb1);

		pp2.add(jrb2);

		pp2.add(jrb3);

		pp2.add(jrb9);

		

	        JPanel pp3=new JPanel();       //界面第三行的设置

		Label2.setFont(F1);

		jrb4.setFont(F1);

	        jrb5.setFont(F1);

	        jrb6.setFont(F1);

	        jrb7.setFont(F1);

	        pp3.add(Label2);

		pp3.add(jrb4);

		pp3.add(jrb5);

		pp3.add(jrb6);

		pp3.add(jrb7);

		

		JPanel pp4=new JPanel();       //界面第四行的设置

		OK.setFont(F2);

		OK.setFocusPainted(false);

		OK.setBackground(C1);

		OK.setPreferredSize(new Dimension(420,27));

		pp4.add(OK);

	    

		p1.add(pp1);

		p1.add(pp2);

		p1.add(pp3);

		p1.add(pp4);

		

		JPanel p2=new JPanel();         

		Result.setPreferredSize(new Dimension(700,470));

		Result.setFont(F1);

		Result.setBackground(Color.white);

		Result.setEditable(false);

		p2.add(Result);

		p2.setSize(750, 500);

		this.add(p2,BorderLayout.CENTER);

		

		DFP.addActionListener(this);     //为相应的组件设置监听

		jrb1.addActionListener(this);

		jrb2.addActionListener(this);

		jrb3.addActionListener(this);

		jrb4.addActionListener(this);

		jrb5.addActionListener(this);

		jrb6.addActionListener(this);

		jrb7.addActionListener(this);

		jrb9.addActionListener(this);

		

		OK.addActionListener(this);

		

		

	}

	

	public void is_Directory(int[] command,String filepath,String suffix)throws IOException{     //用于递归处理文件夹中指定的文件

		File file=new File(filepath);

		File[] files=file.listFiles();

		if(files.length==0){                                                                 //判断所选的文件夹是否为空

		JOptionPane.showMessageDialog(this, "该文件夹为空！");

		}

		else{

			for(File f:files){

				if(f.isDirectory())

					is_Directory(command,f.getAbsolutePath(),suffix);

				else if(f.isFile()){

					String fipa=f.getAbsolutePath();

					String filename=f.getName();

					String fs=filename.substring(filename.lastIndexOf(".")+1);

					if(fs.matches(suffix)){

						Command(command,fipa);

					}

				}

				

			}

		}

	}

	

	public void Command(int[] command,String filepath) throws IOException{                       //处理单个文件，实现对应的功能

		option1.BaseOption(filepath);

		option1.ExtendOption(filepath);

		String[] sfp=filepath.split("\\\\");

		String sfpp=sfp[sfp.length-1];

		Result.append("文件名:"+sfpp+"\n");

		if(command2[5]==1){

			Result.append("字符数:"+option1.charnum+"\n");

		}

		if(command2[4]==1){

			Result.append("词数:"+option1.wordnum+"\n");

		}

		if(command2[3]==1){

			Result.append("行数:"+option1.linenum+"\n");

		}

		if(command2[2]==1){

			Result.append("空行:"+option1.empnum+"\n");

		}

		if(command2[1]==1){

			Result.append("代码行:"+option1.codenum+"\n");

		}

		if(command2[0]==1){

			Result.append("注释行:"+option1.notenum+"\n");

		}

		Result.append("\n");

	}

	

	@Override

	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub

		if(e.getSource()==DFP){                                                  //对应选择某个文件夹或文件

			JFileChooser jfc=new JFileChooser();

			FileSystemView fsv=FileSystemView.getFileSystemView();

			jfc.setCurrentDirectory(fsv.getHomeDirectory());

			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

			if(jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){

				Enabled[0]=1;

				File file=jfc.getSelectedFile();

				String file_path=file.getAbsolutePath();

				dfp.setText(file_path);

				if(file.isDirectory()){                                     

					jrb1.setEnabled(true);

					jrb2.setEnabled(true);

					jrb3.setEnabled(true);

					jrb9.setEnabled(true);

					F=1;f=0;                                        //处理文件夹，标记F=1，f=0

				}else if(file.isFile()){

					jrb1.setEnabled(false);                         //如果选择处理单个文件，界面中的第二行不能进行选择

					jrb2.setEnabled(false);

					jrb3.setEnabled(false);

					jrb9.setEnabled(false);

					Enabled[1]=1;

					F=0;f=1;                                        //处理文件，标记f=1，F=0

				}

				fl_ph=file_path;

			}

		}

		

		if(e.getSource()==jrb1){                                  

			suffix1="txt";

			Enabled[1]=1;

		}

		

		if(e.getSource()==jrb2){

			suffix1="c";

			Enabled[1]=1;

		}

		

		if(e.getSource()==jrb3){

			suffix1="java";

			Enabled[1]=1;

		}

		

		if(e.getSource()==jrb9){

			suffix1="\\S*";

			Enabled[1]=1;

		}

		

		if(e.getSource()==jrb4){

			if(jrb4.isSelected()){

				command2[5]=1;

				Enabled[2]=1;

			}

		}

		

		if(e.getSource()==jrb5){                                              //Command2数组对要执行的功能进行标记

			if(jrb5.isSelected()){

				command2[4]=1;

				Enabled[2]=1;

			}

		}

		

		if(e.getSource()==jrb6){

			if(jrb6.isSelected()){

				command2[3]=1;

				Enabled[2]=1;

			}

		}

		

		if(e.getSource()==jrb7){

			if(jrb7.isSelected()){

				command2[2]=1;

				command2[1]=1;

				command2[0]=1;

				Enabled[2]=1;

			}

		}

		

		if(e.getSource()==OK){

			if(Enabled[0]==1&&Enabled[1]==1&&Enabled[2]==1){                   //只有界面中上面的三行都选择完毕后，OK键才有效

				Result.setText(null);

				if(F==1){

					                                          

					try {

						is_Directory(command2,fl_ph,suffix1);      //处理文件夹

					} catch (IOException e1) {

						// TODO Auto-generated catch block

						e1.printStackTrace();

					}

				}else if(f==1){

					try {

						Command(command2,fl_ph);                   //处理单个文件

					} catch (IOException e1) {

						// TODO Auto-generated catch block

						e1.printStackTrace();

					}

				}

			}else{

				JOptionPane.showMessageDialog(this, "输入信息不完整！");

			}

		}				

	}



}
