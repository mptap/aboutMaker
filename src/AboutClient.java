package project2012;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class ServerData
{
 static String name;
 static int port_no;
 static PrintWriter out = null;
}

public class AboutClient
{
 public static void main(String args[])
 {
  Frame1 frame = new Frame1();
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setBounds(75,75,450,500);
  frame.setVisible(true);
 }
}

class Frame1 extends JFrame
{
 JLabel label2 = new JLabel("KHIRWADKAR INSTITUTE for TECHNOLOGICAL EXCELLENCE");
 JLabel label = new JLabel("PROJECT DATA INFORMATION");
 JLabel label1;
 FlowLayout f = new FlowLayout(FlowLayout.CENTER);
 Font f1 = new Font("TimesRoman", Font.BOLD,16); 
 Font f2 = new Font("TimesRoman", Font.BOLD,12); 
 JLabel l1,l2,l3,l4,l5;
 JTextField t1,t2,t3,t4,t5,t6;
 JComboBox pick;
 Box box1 = Box.createHorizontalBox();
 JButton b1 = new JButton("Submit");
 JButton b2 = new JButton("Contributors");
 GridLayout gl= new GridLayout(6,2,30,10);
 Socket socket = null;
 PrintWriter out = null;
 Panel p1 = new Panel();
 Panel p2 = new Panel();
 GridLayout gl1= new GridLayout(2,1,10,10);
 ImageIcon kite = new ImageIcon("kite.gif");
 int i1;
 String server, port, pName, pDateD, pDateY, contrib,contributor;
 Frame1 selfref;

 public Frame1()
 {
  super("Project information");
  Container cPane = getContentPane();
  selfref = this;
  l1 = new JLabel("Server Name: ");
  t1 = new JTextField(30);
  l2 = new JLabel("Port No: ");
  t2 = new JTextField(30);
  l3 = new JLabel("Project Name: ");
  t3 = new JTextField(30);
  l4 = new JLabel("Date of completion: ");
  t4 = new JTextField(2);
  t6 = new JTextField(4);
  
  l5 = new JLabel("Contributors No: ");
  t5 = new JTextField(30);
  MyActionListener listen1 = new MyActionListener();
  pick = new JComboBox();
 
  pick.addItem("Jan");
  pick.addItem("Feb");
  pick.addItem("Mar");
  pick.addItem("Apr");
  pick.addItem("May");
  pick.addItem("Jun");
  pick.addItem("Jul");
  pick.addItem("Aug");
  pick.addItem("Sep");
  pick.addItem("Oct");
  pick.addItem("Nov");
  pick.addItem("Dec");
  b1.addActionListener(listen1);
  b2.addActionListener(listen1);
  t1.addActionListener(listen1);
  t2.addActionListener(listen1);
  t3.addActionListener(listen1);
  t4.addActionListener(listen1);
  t5.addActionListener(listen1);
  t6.addActionListener(listen1);
  p2.setLayout(f);
  label1 = new JLabel(kite);
  p2.add(label1);
  label2.setFont(f2);
  p2.add(label2);
  label.setFont(f1);
  p2.add(label);
  p1.setLayout(gl);
  box1.add(t4);
  box1.add(Box.createRigidArea(new Dimension(15,0)));
  box1.add(pick);
  box1.add(Box.createRigidArea(new Dimension(15,0)));
  box1.add(t6);
  p1.add(l1);
  p1.add(t1);
  p1.add(l2);
  p1.add(t2);
  p1.add(l3);
  p1.add(t3);
  p1.add(l4);
  p1.add(box1);
  p1.add(l5);
  p1.add(t5);
  p1.add(b1);
  p1.add(b2);
  cPane.setLayout(gl1);
  cPane.add(p2);
  cPane.add(p1);
  t1.requestFocus();
 }
 
  class MyActionListener implements ActionListener
  {
   public void actionPerformed(ActionEvent ae)
   {
    Object source = ae.getSource();
      if(source instanceof JTextField)
     {
      if(source == t1)
       t2.requestFocus();
      if(source == t2)
       t3.requestFocus();
      if(source == t3)
       t4.requestFocus();
      if(source == t4)
       pick.requestFocus();
      if(source == t5)
       b1.requestFocus();
      if(source == t6)
       t5.requestFocus();
      return;
    }
    server = t1.getText();
    port = t2.getText();
    pName = t3.getText();
    pDateD = t4.getText();
    pDateY = t6.getText();
    contrib = t5.getText();
    if(source == b1)
    {
     if(server.equals("") || port.equals("") || pName.equals("") 
            || pDateD.equals("") || pDateY.equals("") || contrib.equals(""))
     {
	JOptionPane.showMessageDialog(null,
	"All fields are Mandatory\nPlease Re-enter. ",
	"Warning",JOptionPane.PLAIN_MESSAGE);
	return;
     }
    
      
      try
      {
        i1 = Integer.parseInt(t5.getText());
      }
      catch(Exception e)
      {
	JOptionPane.showMessageDialog(null,
	"Contributers No. must be integer data\nPlease Re-enter. ",
	"Warning",JOptionPane.PLAIN_MESSAGE);
	t5.setText("");
	return;
	                                     
      }
      
     ServerData.name = t1.getText();
     ServerData.port_no = Integer.parseInt(t2.getText());
     try
     {
      
       socket = new Socket(ServerData.name,ServerData.port_no);
       ServerData.out = new PrintWriter(socket.getOutputStream(),true);
       ServerData.out.println(""+t3.getText());
       ServerData.out.println(""+t4.getText()+"-"+pick.getSelectedItem()+"-"+t6.getText());
       ServerData.out.println(""+t5.getText());
     }
     catch(IOException e)
     { 
      System.out.println("Exception: couldn't create stream socket"+ e.getMessage());
      System.exit(1);
     }
    } 
   if(source == b2)
   {
     AboutFrame credits = new AboutFrame("AMfKP31-Mar-2012.dat");
     credits.setVisible(true);
     return;
   }
  
     String contributers = t5.getText();
     int nc = Integer.parseInt(contributers);
     AbtFrame1 frame = new AbtFrame1("Contributer's details: ", nc);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     PanelAbtCl pane = new PanelAbtCl(frame);
     frame.setContentPane(pane);
     frame.setBounds(200, 200, 500, 350);
     setVisible(false);
     frame.setVisible(true);

    
   }
  }
 
 public Insets getInsets()
 {
  return new Insets(30,20,25,30);
 }
}
 
/*    
public class AboutClient
{
	public static void main(String args[])
	{
	       AbtFrame1 frame = new AbtFrame1("Contributer's details: ");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       PanelAbtCl pane = new PanelAbtCl(frame);
	       frame.setContentPane(pane);
	       frame.setBounds(200, 200, 500, 350);
	       frame.setVisible(true);
	}
}
*/

class AbtFrame1 extends JFrame
{
	AbtFrame1 selfRef;
        int ctr;
        
        AbtFrame1(String title, int counter)
        {
         super(title);	
         ctr = counter; 
        }
}

	
                                                  	
class PanelAbtCl extends JPanel
{
	JButton b1;
        AbtFrame1 frame1;
	JTextField tf1, tf2, tf3, tf4;
	JLabel l1, l2, l3, l4, l5, ltitle;
	JComboBox colg;
	GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
	public PanelAbtCl(AbtFrame1 frame)
	{
       
                frame1 = frame;
        	b1= new JButton("Submit");
                MyActionListener listen1 = new MyActionListener();
                b1.addActionListener(listen1); 

		tf1 = new JTextField(20); 
	        tf2 = new JTextField(20); 
	        tf3 = new JTextField(20); 
                tf4 = new JTextField(20);
                
		tf1.addActionListener(listen1); 
                tf2.addActionListener(listen1); 
                tf3.addActionListener(listen1); 
                tf4.addActionListener(listen1); 
                
                tf1.requestFocus();
		colg = new JComboBox();
		colg.addItem("VNIT(VRCE)");
		colg.addItem("SRCOEM");
		colg.addItem("YCCE");
		colg.addItem("GHRCE");
             	colg.addItem("PCE");
               	colg.addItem("NIT");
               	colg.addItem("S.B.JAIN INSTITUTE OF TECHNOLOGY");
               	colg.addItem("BCCOE");
               	colg.addItem("KDK COLLEGE OF ENGINEERING");
               	colg.addItem("SVPCOE");
               	colg.addItem("GHCOE");
               	colg.addItem("JIT");
               	colg.addItem("VIT");
               	colg.addItem("SVSS COLLEGE OF ENGINEERING");
               	colg.addItem("PIET");
               	colg.addItem("BCYRC UMRER College Of ENGINEERING ");

		
                MyItemListener listen2 = new MyItemListener();
                colg.addItemListener(listen2); 


		colg.setEditable(true);
		
		
                l1 = new JLabel("Name: ");
                l2 = new JLabel("Branch: ");
                l3 = new JLabel("Degree & Semester: ");
                l4 = new JLabel("College: ");
		l5 = new JLabel("Photo: ");
                ltitle = new JLabel("Contributer ");
                setLayout(gbl);
		
		gbc.fill = GridBagConstraints.NONE;
                gbc.anchor = GridBagConstraints.CENTER;
		                
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbl.setConstraints(ltitle, gbc);                 
                add(ltitle);

                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.weightx=30;
		gbc.weighty=10;
		gbl.setConstraints(l1, gbc);                 
                add(l1); 
	        
                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.weightx=0;
		gbc.weighty=10;
     	        gbl.setConstraints(l2, gbc);                 
                add(l2); 
     
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.weightx=0;
		gbc.weighty=10;
                gbl.setConstraints(l3, gbc);                 
                add(l3); 
     
                gbc.gridx = 0;
                gbc.gridy = 4;
                gbc.weightx=0;
		gbc.weighty=10;
                gbl.setConstraints(l4, gbc);                 
                add(l4); 

 		gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.weightx=0;
		gbc.weighty=10;
                gbl.setConstraints(l5, gbc);                 
                add(l5); 
                  
		gbc.gridx = 1;
                gbc.gridy = 1;
                gbc.weightx=30;
		gbc.weighty=10;
                gbl.setConstraints(tf1, gbc);                 
                add(tf1); 
	        
	        
                gbc.gridx = 1;
                gbc.gridy = 3;
                gbc.weightx=0;
		gbc.weighty=10;
                gbl.setConstraints(tf2, gbc);                 
                add(tf2); 
	        
	        
                gbc.gridx = 1;
                gbc.gridy = 2;
                gbc.weightx=0;
		gbc.weighty=10;
                gbl.setConstraints(tf3, gbc);                 
                add(tf3); 
	        
                
		gbc.gridx = 1;
                gbc.gridy = 4;
                gbc.weightx=0;
		gbc.weighty=10;
                gbl.setConstraints(colg, gbc);                 
                add(colg); 
                
                
		gbc.gridx = 1;
                gbc.gridy = 5;
                gbc.weightx=0;
		gbc.weighty=10;
                gbl.setConstraints(tf4, gbc);                 
                add(tf4); 

		gbc.gridx = 0;
                gbc.gridy = 6;
		gbc.gridwidth = 2;
                gbc.weightx=40;
		gbc.weighty=15;
                gbl.setConstraints(b1, gbc);                 
		add(b1);
	}
        
        String name, branch, course, picfile;
        String college;
       	class MyItemListener implements ItemListener
        { 
	
         	public void itemStateChanged(ItemEvent ie)
                {
                     
                       Object source = ie.getSource();
                       if(source == colg)
                         {  
                          Object newColg = ie.getItem();
                          college = newColg.toString();
	       		 }
                }
        } 
        class Frame3 extends JFrame
        {
  	 JLabel opn;
  	 TextArea block;
         JButton submit;
	 FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
  
 	 Frame3 (String title)
  	 { 
  		super(title);
	        opn = new JLabel("Enter operations:");
                submit = new JButton("Submit");
                MyActionListener3 listen3 = new MyActionListener3();
                submit.addActionListener(listen3);
                block = new TextArea(15, 55);
                block.requestFocus(); 
                setLayout(fl);
                add(opn);
                add(block);
                add(submit);
         }
         
        class MyActionListener3 implements ActionListener
        { 
          public void actionPerformed(ActionEvent ae3)
          {
            Object source = ae3.getSource();
            if(source == submit)
            {
	     try
	     {
            	 ServerData.out.println(""+ block.getText());
             } 
	     catch(Exception e)
             { 
                 System.out.println("Exception: couldn't create stream socket"+ 
                                       e.getMessage()); 
                 System.exit(1);
             }   
             setVisible(false);
             dispose();
             JOptionPane.showMessageDialog(null, "Data Saved Successfully!", 
                                           "",JOptionPane.PLAIN_MESSAGE);
             System.exit(0);
             
            }
          }
        }
       }
                  
	
       	class MyActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			Object source = ae.getSource();
		
                      
			if(source == b1)
			{
                        
                          try
                          {
                           ServerData.out.println(""+ tf1.getText());
                           ServerData.out.println(""+ tf2.getText());
                           ServerData.out.println(""+ tf3.getText());
                           ServerData.out.println(""+ colg.getSelectedItem().toString());
                           ServerData.out.println(""+ tf4.getText());
                          }
                          catch(Exception e)
                          { 
                           System.out.println("Exception: couldn't create stream socket"+ 
                                           e.getMessage()); 
                           System.exit(1);
                          }   
                          
			  frame1.ctr--;
                          if(frame1.ctr > 0)
                          {
                            AbtFrame1 frame2 = new AbtFrame1("Contributer's details: ", 
                                                               frame1.ctr);
                            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            PanelAbtCl pane2 = new PanelAbtCl(frame2);
                            frame2.setContentPane(pane2);
                            frame2.setBounds(200, 200, 500, 350);
		            frame1.setVisible(false);
			    frame1.dispose();
                            frame2.setVisible(true);
                          }
                          else if (frame1.ctr == 0)
                          {
     			   frame1.setVisible(false);
                           frame1.dispose();
			   Frame3 ThirdFrame = new Frame3("Operations");
   			   ThirdFrame.setBounds(200, 200, 500, 350);
    			   ThirdFrame.setVisible(true);
                           ThirdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                          }
                 }			
            }
       }  	
	
   }




/* class TestFrame
 {
  public static void main(String args[])
  {
    Frame3 ThirdFrame = new Frame3("Operations");
    ThirdFrame.setBounds(200, 200, 500, 350);
    ThirdFrame.setVisible(true);
    ThirdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
 }
*/ 

class MyData
{
  static String projectname,date;
  static int num;
  static String name[];
  static String branch[];
  static String yr[];
  static String college[];
  static String photoid[];
  static String steps;
}

//NPanel class

class Npanel extends JPanel
 {
  JLabel a1= new JLabel(MyData.projectname,JLabel.CENTER);
  JLabel a2= new JLabel("Project Done At : K.I.T.E",JLabel.CENTER);
  JLabel gg= new JLabel("Project Guided By: Mr.Sanjay Khirwadkar",JLabel.CENTER);
  JLabel a3= new JLabel("Project Completion Date :"+MyData.date,JLabel.CENTER);
  JLabel a4= new JLabel("Number of Candidate:"+MyData.num,JLabel.CENTER);
  
  //JLabel d[]= new JLabel[MyData.num];
  ImageIcon i1[]= new ImageIcon[MyData.num];
  ImagePanel i2[]= new ImagePanel[MyData.num];
  
  TextArea t1= new TextArea(MyData.steps,7, 50);

  JPanel p1 = new JPanel();
  JPanel p2 = new JPanel();
 // Box p2box;
  JPanel p3 = new JPanel();
  JPanel p4 = new JPanel();
  JPanel p5 = new JPanel();
  GridLayout g1= new GridLayout(5,1);
 // GridLayout g2= new GridLayout((MyData.num+1)/2,2,20,20);
  FlowLayout g2= new FlowLayout();
 //GridLayout g3= new GridLayout(1,1,20,20);
  Box box1[] = new Box[MyData.num];
  
  //GridLayout f1 =new GridLayout(3,1);
  Font fn =new Font("TimesRoman",Font.BOLD,20);
  Font fr =new Font("Helvetica",Font.ITALIC,15);  
  public Npanel()
  {
    p1.setLayout(g1);
  
    a1.setFont(fn);
    p1.add(a1);
    a2.setFont(fr);
    a3.setFont(fr);
    a4.setFont(fr);
    gg.setFont(fr);
    p1.add(a2);
    p1.add(gg);
    p1.add(a3);
    p1.add(a4);
  
    p2.setPreferredSize(new Dimension(660, 140));
    //p2.setLayout(g2);
    p3.setPreferredSize(new Dimension(660, 140));
    p4.setPreferredSize(new Dimension(660, 45));
    p5.setPreferredSize(new Dimension(660, 45));
    //p3.setLayout(g2);
   // p2box = Box.createHorizontalBox();
    
   for(int i=0;i<MyData.num;i++)
   {
      i1[i]= new ImageIcon(MyData.photoid[i]);
      i2[i]= new ImagePanel(i1[i], MyData.name[i]);
      //d[i]= new JLabel(MyData.name[i],JLabel.CENTER);
   }
   for(int i=0;i<MyData.num;i++)
   {
       i2[i].setPreferredSize(new Dimension(150, 130));
       i2[i].setToolTipText("<HTML>"+MyData.name[i]+"<BR>"+MyData.yr[i]+"<BR>"+MyData.branch[i]
                             +"<BR>"+MyData.college[i]+"</HTML>");
        box1[i] = Box.createVerticalBox();
    
       box1[i].add(i2[i]);
       //box1[i].add(d[i]);
     //  p2box.add(box1[i]);
   }
    
    for(int i=0;i<MyData.num;i++)
    {
      if(i<4)
        p2.add(box1[i]);
      else
        p3.add(box1[i]);
    }
    //p3.setLayout(g3);
    //p3.add(t1);
    t1.setEditable(false);
    //setLayout(f1);
    add(p1);
    if(MyData.num < 5)
      add(p4);
    add(p2);
    if(MyData.num > 4)
      add(p3);
    else
      add(p5);
    add(t1);
    
  }
 }

//Frame method

class AboutFrame extends JFrame
 {
   Npanel pane;
   public AboutFrame(String datafilename)
   {
     super("INFO ABOUT PROJECT");
       Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
       setBounds((screenSize.width-700)/2, (screenSize.height-600)/2,700,600);
     try
     {
     FileReader file =new FileReader(datafilename);
     BufferedReader buff =new BufferedReader(file);
     String line;
     int i=1,j=0,x=0,y=0,z=0,u=0,w=0,c=0;
     MyData.steps="Following are the steps to execute the program :\n";
     while((line=buff.readLine())!=null)
      {
         if(i==1)
           MyData.projectname = line;
	 if(i==2)
	   MyData.date = line;
	 if(i==3)
	  {
	    MyData.num = Integer.parseInt(line);
	    MyData.name= new String[MyData.num];
            MyData.branch = new String[MyData.num];
            MyData.yr=new String[MyData.num];
            MyData.college=new String[MyData.num];
            MyData.photoid= new String[MyData.num];
         } 
	if(j<MyData.num)
	{
	 if((i==(4+x))&&((x+4)<(MyData.num*5+4)))
	  {
	    MyData.name[j]= line;
	    x=x+5;c++;
	  }
	 if((i==(5+y))&&((y+5)<(MyData.num*5+4)))
	  {
	    MyData.branch[j]= line;
	    y=y+5;c++;
	  }
	 if((i==(6+z))&&((z+6)<(MyData.num*5+4)))
	  {
	    MyData.yr[j]=line;
	    z=z+5;c++;
	  }
	 if((i==(7+u))&&((u+7)<(MyData.num*5+4)))
	  {
	    MyData.college[j]=line;
	    u=u+5;c++;
	  }
	 if((i==(8+w))&&((w+8)<(MyData.num*5+4)))
	  {
	    MyData.photoid[j]=line;
	    File f=new File(MyData.photoid[j]);
	      if(!f.exists())
	        MyData.photoid[j]="noPhoto.jpg";
	    w=w+5;c++;
	  }
	  if(c==5)
	  {
	   j++;c=0;
	  }
       }
	  if(i>=(MyData.num*5+4))
	   MyData.steps=MyData.steps+"\n"+line;
         i++;
	 
      }
     buff.close();
     }
     catch(IOException e)
     {
       System.out.println("Error: "+e);
     }

       pane=new Npanel();
       setContentPane(pane);

     //test on console remove after use
      /*( System.out.println("projname "+MyData.projectname);
       System.out.println("Date: "+MyData.date);
       System.out.println("number of Candidate: "+MyData.num);
      for(int k=0;k<MyData.num;k++)
      {
       System.out.println("Name: "+MyData.name[k]);
       System.out.println("Branch: "+MyData.branch[k]);
       System.out.println("year: "+MyData.yr[k]);
       System.out.println("College: "+MyData.college[k]);
       System.out.println("Photo-id: "+MyData.photoid[k]);
       
      }
       System.out.println("steps: "+MyData.steps);
   */
     WindowListener list1 = new WindowAdapter()
     {
       public void windowClosing(WindowEvent we)
        {
	  setVisible(false);
	  dispose();
//	  System.exit(0);
	}
     };
     addWindowListener(list1);
   }
 }

class ImagePanel extends JPanel
{
  Image img;
  String nm;
  Font f;
  FontMetrics fm;
  ImagePanel(ImageIcon ii, String name)
  {
    img = ii.getImage();
    nm = name;
    f = new Font("Courier", Font.PLAIN, 11);
    fm = getFontMetrics(f);
  }
  protected void paintComponent(Graphics g)
  {
    //g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    g.setFont(f);
    int x1 = (getWidth() - 80) / 2;
    int x2 = (getWidth() - fm.stringWidth(nm)) / 2;
    g.drawImage(img, x1, 0, 80, 100, this);
    g.drawString(nm, x2, 115);
  }
}


	


















