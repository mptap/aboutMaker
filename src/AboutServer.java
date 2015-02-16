package project2012.server;
import java.io.*;
import java.net.*;
import java.util.*;

public class AboutServer
{
 public static void main(String args[])
 {
  
  ServerSocket serversocket = null;
  Socket clientsocket = null;
  try
  {
   serversocket = new ServerSocket(1200);
   System.out.println("Project distributor server up and running...");
   InetAddress a= InetAddress.getLocalHost();
   System.out.println("On host: " + a.getHostName());
   System.out.println("On port : 1200");
   System.out.println("Waiting for clients...");
  }
  catch(IOException e)
  {
   System.err.println("Exception: couldn't create socket");
   System.exit(1);
  }


  
  // Starting server
  while(true)
  {
   // Wait for client to connect
   try
   {
    clientsocket = serversocket.accept();
   }
   catch(IOException e)
   {
    System.err.println("Exception: couldn't create socket");
    System.exit(1);  
   }

   Server1 server = new Server1(clientsocket);
   server.start();
  }
 }
}

class Server1 extends Thread
{
 Socket clientsocket;

 public Server1(Socket cs)
 {
  clientsocket = cs;
 }

 public void run()
 {
  // Receiving data from the client
  try
  {
   InputStreamReader isr = new InputStreamReader(clientsocket.getInputStream());
   BufferedReader is = new BufferedReader(isr);
   BufferedOutputStream osr = new BufferedOutputStream(clientsocket.getOutputStream());
   PrintWriter os = new PrintWriter(osr,false);
   String outLine,inLine;
   String projectName="", pDate="", nContributors="";
   String name="",branch="",degree="",college="",image="",operation="";
   int b=0;

   // File handling
   FileWriter file = null;
   BufferedWriter buff = null;
   
   projectName = is.readLine();              // receiving data for file
   pDate = is.readLine();
   nContributors = is.readLine();
   b = Integer.parseInt(nContributors);   

   System.out.println(projectName);
   System.out.println(pDate);
   System.out.println(nContributors);
   
   // forming a unique name of a file

   int a,l,m=0;
   l = projectName.length();
   a = projectName.lastIndexOf(' '); 
   String str,c,c1; 
   c1 = projectName.substring(0,1);
   str = ""+c1;
   m  = projectName.indexOf(' ',m+1);
   while(m>0)
   {
    c = projectName.substring(m+1,m+2);
    m  = projectName.indexOf(' ',m+1);
    str = str+c;
   }
 
   str = str + pDate + ".dat";                           // unique name of a file is formed
 
    file = new FileWriter(str);
    buff = new BufferedWriter(file);
    buff.write(projectName);
    buff.newLine();
    buff.write(pDate);
    buff.newLine();
    buff.write(nContributors);
    buff.newLine();
   
   for(int j=0;j<b;++j)                          // writing contributor's data 1-by-1
   {
    name = is.readLine();   
     buff.write(name);
     buff.newLine();
    degree = is.readLine();
     buff.write(degree);
     buff.newLine();
    branch = is.readLine();
     buff.write(branch);
     buff.newLine();
    college = is.readLine();
     buff.write(college);
     buff.newLine();
    image = is.readLine();
     buff.write(image);
     buff.newLine();
   
    System.out.println(name);
    System.out.println(degree);
    System.out.println(branch);
    System.out.println(college);
    System.out.println(image);
   }
  
    while((operation = is.readLine()) != null)
    {
     buff.write(operation);
     buff.newLine();
     System.out.println(operation);
    }
    buff.close();
  
    os.close();
    is.close();
    clientsocket.close();
    System.out.println("Data written to file " + str);
    System.out.println("Waiting for next client...");
   
  }  
  catch(Exception e)
  {
   System.err.println("Exception: " + e);
   e.printStackTrace();
  } 		                                                                                  
 }
}

