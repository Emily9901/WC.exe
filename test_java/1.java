package one;
import java.awt.*;
import javax.swing.*;

public class GUILayout extends JFrame {
    JButton jb[]=new JButton[6];
    JPanel jp1,jp2;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUILayout L=new GUILayout();
	}
    public GUILayout()
    {   
    	for(int i=0;i<6;i++)
    	{
    		jb[i]=new JButton(String.valueOf(i+1));
    	}
    	jp1=new JPanel();//JPanel本身默认是流式布局，FlowLayout,就不需要认为去设定了，这里默认居中对齐；
    	jp2=new JPanel();

    	jp1.add(jb[0]);//这里要一个一个加，不能一次性加，比如jp1.add(jb[0],jb[1]);
    	jp1.add(jb[1]);

    	
    	jp2.add(jb[5]);


    	this.add(jp1,BorderLayout.NORTH);
    	this.add(jp2, BorderLayout.SOUTH);
    	
 /*   	this.add(jb[2], BorderLayout.CENTER);
    	this.setTitle("Layout");
    	
    	this.setSize(500, 500);*/
    	
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//一定要添加这一步，避免浪费内存；
    	this.setVisible(true);

    }
}
