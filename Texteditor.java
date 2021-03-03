import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Texteditor extends JFrame implements ActionListener
{
	MenuBar mbar;
    Menu file,edit,format,font,font1,font2;
    MenuItem item1,item2,item3,item4;
    MenuItem item5,item6,item7,item8,item9,item10;
	MenuItem item11,item12,item13;
    MenuItem fname1,fname2,fname3,fname4;
    MenuItem fstyle1,fstyle2,fstyle3,fstyle4;
    MenuItem fsize1,fsize2,fsize3,fsize4;
	JPanel mainpanel;
	TextArea text;
	Font f;
	String months[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	GregorianCalendar gcalendar;
	String command=" ";
	Stack<String> stk= new Stack<>();
	String str=" ";
	int len1;
	int i=0;
	int pos1;
	int len;
	public Texteditor(String str)
	{
		super(str);
		mainpanel=new JPanel();
		mainpanel=(JPanel)getContentPane();
		mainpanel.setLayout(new FlowLayout());
		mbar=new MenuBar();
		setMenuBar(mbar);
		file=new Menu("File");
		edit=new Menu("Edit");
		format=new Menu("Format");
		font=new Menu("Font");
		font1=new Menu("Font Style");
		font2=new Menu("Size");
		file.add(item1=new MenuItem("New..."));
		file.add(item2=new MenuItem("Open"));
		file.add(item3=new MenuItem("Save As..."));
		file.add(item4=new MenuItem("Exit"));
		mbar.add(file);
		edit.add(item5=new MenuItem("Cut (Ctrl+X)"));
		edit.add(item6=new MenuItem("Copy (Ctrl+C)"));
		edit.add(item7=new MenuItem("Paste (Ctrl+V)"));
		edit.add(item8=new MenuItem("Delete"));
		edit.add(item10=new MenuItem("Select All (Ctrl+A)"));
		edit.add(item9=new MenuItem("Time/Date"));
		edit.add(item11=new MenuItem("Copy to Stack"));
		edit.add(item13=new MenuItem("Cut to Stack"));
		edit.add(item12=new MenuItem("Paste from Stack"));
		mbar.add(edit);
		format.add(font);
		format.add(font1);
		format.add(font2);
		font.add(fname1=new MenuItem("Courier"));
		font.add(fname2=new MenuItem("Sans Serif"));
		font.add(fname3=new MenuItem("Monospaced"));
		font.add(fname4=new MenuItem("Symbol"));
		font1.add(fstyle1=new MenuItem("Regular"));
		font1.add(fstyle2=new MenuItem("Bold"));
		font1.add(fstyle3=new MenuItem("Italic"));
		font1.add(fstyle4=new MenuItem("Bold Italic"));
		font2.add(fsize1=new MenuItem("12"));
		font2.add(fsize2=new MenuItem("14"));
		font2.add(fsize3=new MenuItem("18"));
		font2.add(fsize4=new MenuItem("20"));
		mbar.add(format);
		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);
		item5.addActionListener(this);
		item6.addActionListener(this);
		item7.addActionListener(this);
		item8.addActionListener(this);
		item9.addActionListener(this);
		item10.addActionListener(this);
		item11.addActionListener(this);
		item12.addActionListener(this);
		fname1.addActionListener(this);
		fname2.addActionListener(this);
		fname3.addActionListener(this);
		fname4.addActionListener(this);
		fstyle1.addActionListener(this);
		fstyle2.addActionListener(this);
		fstyle3.addActionListener(this);
		fstyle4.addActionListener(this);
		fsize1.addActionListener(this);
		fsize2.addActionListener(this);
		fsize3.addActionListener(this);
		fsize4.addActionListener(this);
		text=new TextArea(25,25);
		mainpanel.add(text);
		f=new Font("Monotype Corsiva",Font.PLAIN,15);
		text.setFont(f);
	}
	public void actionPerformed(ActionEvent ae)
	{
		command=(String)ae.getActionCommand();
		if(command.equals("New..."))
		{
			dispose();
			Texteditor note = new Texteditor("Untitled-Notepad");
			note.setSize(640,480);
			note.setVisible(true);
		}
		try
		{

			if(command.equals("Open"))
			{

				String addr=" ";
				FileDialog dialog=new FileDialog(this,"Open");
				dialog.setVisible(true);
				File f=new File((dialog.getDirectory()+dialog.getFile()));
				FileInputStream fobj=new FileInputStream(f);
				len=(int)f.length();
				for(int j=0;j<len;j++)
				{
					addr=addr + (char)fobj.read();
				}
				text.setText(addr);
			}
		}
		catch(IOException e)
		{
		
		}
		try
		{
			String filename="";

			if(command.equals("Save As..."))
			{
				FileDialog dialog1=new FileDialog(this,"Save As",FileDialog.SAVE);
				dialog1.setVisible(true);
				filename=dialog1.getFile();
				String textstr=text.getText();
				int length=textstr.length();
				byte buf[]=textstr.getBytes();
				File f1=new File((dialog1.getDirectory()+dialog1.getFile()));
				FileOutputStream fobj1=new FileOutputStream(f1);
				for(int k=0;k<len1;k++)
				{
					fobj1.write(buf[k]);
				}
				fobj1.close();
			}

			this.setTitle(filename);

		}
		catch(IOException e)
		{
			
		}
		if(command.equals("Exit"))
		{
			System.exit(0);
		}
		if(command.equals("Cut (Ctrl+X)"))
		{
			str=text.getSelectedText();
			i=text.getText().indexOf(str);
			text.replaceRange(" ",i,i+str.length());
		}
		if(command.equals("Cut to Stack"))
		{
			stk.push(text.getSelectedText());
			i=text.getText().indexOf(str);
			text.replaceRange(" ",i,i+str.length());
		}
		if(command.equals("Copy (Ctrl+C)"))
		{
			str=text.getSelectedText();
		}
		if(command.equals("Copy to Stack"))
		{
			stk.push(text.getSelectedText());
		}
		if(command.equals("Paste (Ctrl+V)"))
		{
			pos1=text.getCaretPosition();
			text.insert(str,pos1);
		}
		if(command.equals("Paste from Stack"))
		{
			pos1=text.getCaretPosition();
			try
			{
				text.insert((String)stk.pop(),pos1);
			}
			catch (EmptyStackException e)
			{
				text.insert("",pos1);
			}			
		}
		
		if(command.equals("Delete"))
		{
			String msg=text.getSelectedText();
			i=text.getText().indexOf(msg);
			text.replaceRange(" ",i,i+msg.length());
		}
		if(command.equals("Time/Date"))
		{
			gcalendar=new GregorianCalendar();
			String h=String.valueOf(gcalendar.get(Calendar.HOUR));
			String m=String.valueOf(gcalendar.get(Calendar.MINUTE));
			String s=String.valueOf(gcalendar.get(Calendar.SECOND));
			String date=String.valueOf(gcalendar.get(Calendar.DATE));
			String mon=months[gcalendar.get(Calendar.MONTH)];
			String year=String.valueOf(gcalendar.get(Calendar.YEAR));
			String hms="Time"+" - "+h+":"+m+":"+s+" Date"+" - "+date+" "+mon+" "+year;
			int loc=text.getCaretPosition();
			text.insert(hms,loc);
		}
		if(command.equals("Courier"))
		{

			String fontName=f.getName();
			String fontFamily=f.getFamily();
			int fontSize=f.getSize();
			int fontStyle=f.getStyle();

			f=new Font("Courier",fontStyle,fontSize);
			text.setFont(f);
		}
		if(command.equals("Sans Serif"))
		{
			String fontName=f.getName();
			String fontFamily=f.getFamily();
			int fontSize=f.getSize();
			int fontStyle=f.getStyle();

			f=new Font("Sans Serif",fontStyle,fontSize);
			text.setFont(f);
		}
		if(command.equals("Monospaced"))
		{
			String fontName=f.getName();
			String fontFamily=f.getFamily();
			int fontSize=f.getSize();
			int fontStyle=f.getStyle();

			f=new Font("Monospaced",fontStyle,fontSize);
			text.setFont(f);
		}

		if(command.equals("Symbol"))
		{
			String fontName=f.getName();
			String fontFamily=f.getFamily();
			int fontSize=f.getSize();
			int fontStyle=f.getStyle();

			f=new Font("Symbol",fontStyle,fontSize);
			text.setFont(f);
			System.out.println(f.getFamily());
		}
		if(command.equals("Regular"))
		{
			String fontName=f.getName();
			String fontFamily=f.getFamily();
			int fontSize=f.getSize();
			int fontStyle=f.getStyle();

			f=new Font(fontName,Font.PLAIN,fontSize);
			text.setFont(f);
		}
		if(command.equals("Bold"))
		{
			String fontName=f.getName();
			String fontFamily=f.getFamily();
			int fontSize=f.getSize();
			int fontStyle=f.getStyle();

			f=new Font(fontName,Font.BOLD,fontSize);
			text.setFont(f);
		}
		if(command.equals("Italic"))
		{
			String fontName=f.getName();
			String fontFamily=f.getFamily();
			int fontSize=f.getSize();
			int fontStyle=f.getStyle();

			f=new Font(fontName,Font.ITALIC,fontSize);
			text.setFont(f);
		}
		if(command.equals("Bold Italic"))
		{
			String fontName=f.getName();
			String fontFamily=f.getFamily();
			int fontSize=f.getSize();
			int fontStyle=f.getStyle();

			f=new Font(fontName,Font.BOLD|Font.ITALIC,fontSize);
			text.setFont(f);
		}
		if(command.equals("12"))
		{
			String fontName=f.getName();
			String fontFamily=f.getFamily();
			int fontSize=f.getSize();
			int fontStyle=f.getStyle();

			f=new Font(fontName,fontStyle,12);
			text.setFont(f);
		}
		if(command.equals("14"))
		{
			String fontName=f.getName();
			String fontFamily=f.getFamily();
			int fontSize=f.getSize();
			int fontStyle=f.getStyle();

			f=new Font(fontName,fontStyle,14);
			text.setFont(f);
		}
		if(command.equals("18"))
		{
			String fontName=f.getName();
			String fontFamily=f.getFamily();
			int fontSize=f.getSize();
			int fontStyle=f.getStyle();

			f=new Font(fontName,fontStyle,18);
			text.setFont(f);
		}
		if(command.equals("20"))
		{
			String fontName=f.getName();
			String fontFamily=f.getFamily();
			int fontSize=f.getSize();
			int fontStyle=f.getStyle();

			f=new Font(fontName,fontStyle,20);
			text.setFont(f);
		}
		if(command.equals("Select All (Ctrl+A)"))
		{
			String strText=text.getText();
			int strLen=strText.length();
			text.select(0,strLen);
		}
	}
	public static void main(String args[])
	{
		Texteditor note = new Texteditor("Untitled-Notepad");
		note.setSize(500,500);
		note.setVisible(true);
	}
}