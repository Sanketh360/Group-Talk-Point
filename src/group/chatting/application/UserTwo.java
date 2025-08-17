
package Group.chatting.application;


import javax.swing.*;//to set frams and javax because it comes from java extended package
import javax.swing.border.*;//for padding of the message
import java.awt.*;
import java.awt.event.*;//to perform action
//for message time
import java.util.*;
import java.text.*;
import java.net.*;//for socket programming
import java.io.*;//to recive message

 public class UserTwo   implements ActionListener,Runnable {//jfram for fram and to perform action and runnable for multithreading
    
   JTextField text;//can be used in constructor and other function also
     JPanel a1;//we are displaying the message in the text field
     static Box vertical = Box.createVerticalBox();//verticaly assigning the messsage (line afer the line)
     static JFrame f = new JFrame();//creating the object of the class
      static DataOutputStream dout;
      
      BufferedReader reader;
      
       BufferedWriter writer;
     String name = "Likith"; 
      
      
     
     
    UserTwo(){//all fram codinng inside constructor
        f.setLayout(null);
        JPanel p1 = new JPanel();//image ,headet,name 
        
        p1.setBackground(new Color(7,94,84));//set green color   
        p1.setBounds(0,0,450,70);//set the color place
        p1.setLayout(null);//to correctly display image
        
        f.add(p1);//place at the top
        
        //back button
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));//to get image
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
         ImageIcon i3 = new ImageIcon(i2);//image is in i1 and ddesign is in i2 so we are we are creating new object i3 and putting image icone i2 in it
        JLabel back = new JLabel(i3);//to set the image in frame
        back.setBounds(5,20,25,25);//image ppositioing
        p1.add(back);//put the image in the panel(panel da mit)
        
        //click event on mouse to off the system 
        back.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent as){
               System.exit(0);
            }
        });
        //profile
         ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/bhav.png"));//to get image
        Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
         ImageIcon i6 = new ImageIcon(i5);//image is in i1 and ddesign is in i2 so we are we are creating new object i3 and putting image icone i2 in it
        JLabel profile = new JLabel(i6);//to set the image in frame
        profile.setBounds(40,5,60,60);//image ppositioing
        p1.add(profile);
        
        //video
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));//to get image
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
         ImageIcon i9 = new ImageIcon(i8);//image is in i1 and ddesign is in i2 so we are we are creating new object i3 and putting image icone i2 in it
        JLabel video = new JLabel(i9);//to set the image in frame
        video.setBounds(300,20,30,30);//image ppositioing
        p1.add(video);
        
        
        //phone
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));//to get image
        Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
         ImageIcon i12 = new ImageIcon(i11);//image is in i1 and ddesign is in i2 so we are we are creating new object i3 and putting image icone i2 in it
        JLabel phone = new JLabel(i12);//to set the image in frame
        phone.setBounds(360,20,35,30);//image ppositioing
        p1.add(phone);
        
        //3icon
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));//to get image
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
         ImageIcon i15 = new ImageIcon(i14);//image is in i1 and ddesign is in i2 so we are we are creating new object i3 and putting image icone i2 in it
        JLabel morevert = new JLabel(i15);//to set the image in frame
        morevert.setBounds(420,20,10,25);//image ppositioing
        p1.add(morevert);
        
        //name
        JLabel name = new JLabel ("COURSE_PROJECT");//set name
        name.setBounds(110 ,15,160,18);//get name
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        p1.add(name);//set name in panel
        
        JLabel Status = new JLabel ("Bhavith,Vithin,Likith,Sanketh,Anirudha,Varun");//set name
        Status.setBounds(110 ,35,160,18);//get name
        Status.setForeground(Color.WHITE);
        Status.setFont(new Font("SAN_SERIF",Font.BOLD,12));
        p1.add(Status);//set name in panel
        
        
        //creating panel fro text area
         a1 = new JPanel();
        a1.setBounds(5,75,440,570);
                a1.setBackground(Color.WHITE);

       f.add(a1);//ad text area in frame not in panel
        
        //user writing text in text field
         text = new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(text);
        
        //send button
        JButton send = new JButton("Send");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground( Color.white);
        //actiom on send
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(send);
        
        
        
        
                
                
        f.setSize(450,700);//set fram size
         f.setLocation(490,50);
         f.setUndecorated(true);//remove min max and x fron top
         f.getContentPane().setBackground(Color.white);//take all the fram and color
        
         f.setVisible(true);//visible fram
         
         //connect the frams with the server
         try{
              
             Socket socket = new Socket("localhost",2003);
            
             writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));//to brodcast to the other clint
             reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));//messege sent by user come thrung socket
             
             
         }catch(Exception e){
             e.printStackTrace();
         }
         
         
    }
    @Override
    public void actionPerformed(ActionEvent ae){
    try{
   String out = "<html><p>" + name + "</p><p>" + text.getText() + "</P></html>";//to get the value inside the text
  //we can not pass string in the panel in line  right.add(p2,BorderLayout.LINE_END) os we made p2 
  
   JPanel p2 = formatLable(out);
   
   
   a1.setLayout(new BorderLayout());//placing the message in the text field center ,rigth,left,bottom
   //set message in right
   JPanel right = new JPanel(new BorderLayout());
        right.setBackground(Color.WHITE);

   right.add(p2,BorderLayout.LINE_END);//set panel at right and display the message at the right most line end
   vertical.add(right);//message in vertical line by line
   
   vertical.add(Box.createVerticalStrut(15));//box size and gap between each message 15
   a1.add(vertical,BorderLayout.PAGE_START);//message in panel
   //sending the message
  try{
      writer.write(out);//output the message
      
      writer.write("\r\n");
      writer.flush();
      
      
  }catch (Exception e)
  {
      e.printStackTrace();
  }   
   
   //empty the text field after the message is sent
   text.setText("");
   
   
    f.repaint();//RELOADING THE PAGE
   f.invalidate();
    f.validate();
   
    }catch (Exception e){
        e.printStackTrace();
    }
    }
    //displaying the massage in desingd box
     public static JPanel formatLable(String out){
         JPanel panel = new JPanel();
                  panel.setBackground(Color.WHITE);

         panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));//display in panel in thre direction of y axix
         
         JLabel output = new JLabel("<html><p style=\"width:150px\">"+out+"</p></html>");//using the html for adjesting the messege
         output.setFont(new Font("Tahoma",Font.PLAIN,16));//set message text font styl and size
         output.setBackground(new Color(204,255,204));
         output.setOpaque(true);//display all the style
         output.setBorder(new EmptyBorder(0,15,0,50));
         
         
         panel.add(output);
         
         //display the time
       Calendar cal = Calendar.getInstance();
         SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
         
         JLabel time = new JLabel();//if we want to write any thing in panel the jlable is usede
         time.setText(sdf.format(cal.getTime()));//get real time
         panel.add(time);//add to panel
         
         
         return panel;
     }
     
     public void run(){
         //reading and displaying the msg from server
         try{
             String msg ="";
             while(true){
                 msg = reader.readLine();
                 if(msg.contains(name)){
                     continue;
                 }
                 
                 
                 
                 
                  //set the msg in panel
                  JPanel panel = formatLable(msg);
                  JPanel left =new JPanel(new BorderLayout());
                  left.add(panel,BorderLayout.LINE_START);
                  vertical.add(left);
                  
                  a1.add(vertical,BorderLayout.PAGE_START);
                 f.repaint();
                 f.invalidate();
                  f.validate();  
                  
             }
             
             
             
             
         }catch(Exception e){
             e.printStackTrace();
         }
     }
         
    
    
    
    public static void main(String[] args){
       UserTwo two=  new UserTwo();// unknow object
        //creating server
     Thread t1 = new Thread(two);
     t1.start();
     
       
         
    }
}
