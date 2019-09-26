package PJ;



import java.io.File;

import java.io.IOException;



import junit.framework.TestCase;



public class OptionTest extends TestCase {



	protected void setUp() throws Exception {

		super.setUp();

    }



	public void testBaseOption() throws IOException {

		File file=new File("D:\\yuanli\\Documents\\15.c");

		String s=file.getAbsolutePath();

		PJ.Option.BaseOption(s);

	}



	public void testExtendOption() throws IOException {

		File file=new File("D:\\yuanli\\Documents\\15.c");

		String s=file.getAbsolutePath();

		PJ.Option.ExtendOption(s);

	}



	public void testIsDirectory() throws IOException {

		int[] t={1,1,1,1,1,1};

		File file=new File("D:\\test");

		String s=file.getAbsolutePath();

		PJ.Option.isDirectory(t,s,"c");

	}



	public void testCommand() throws IOException {

		int[] t={1,1,1,1,1,1};

		File file=new File("D:\\yuanli\\Documents\\15.c");

		String s=file.getAbsolutePath();

		PJ.Option.Command(t,s);

	}



}
