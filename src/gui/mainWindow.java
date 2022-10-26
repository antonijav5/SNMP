package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.ireasoning.protocol.snmp.SnmpPdu;

import routers.RouterTracking;

public class mainWindow extends JFrame {
	
	public RouterTracking tracker=new RouterTracking(this);
	public static String numIn="";
	public static String numOut="";
	public static String numBadCom="";
	public static String numTraps="";
public static boolean clicked=false;
//public static LocalTime time=new LocalTime(0,0,0,0);
public Button start;
public Button end;


	public JLabel[] array=new JLabel[18];
public	JPanel router1=new JPanel(new GridLayout(8,1));
	public JPanel router2=new JPanel(new GridLayout(8,1));
	public JPanel router3=new JPanel(new GridLayout(8,1));
	public JPanel[] routers=new JPanel[3];
	
	
public mainWindow() {
	for (int i=0;i<18;i=i+6) {
	array[i]=new JLabel("Number of received packets: ");
	array[i+1]=new JLabel("Number of sent packets: ");
	array[i+2]=new JLabel("Number of get requests: ");
	array[i+3]=new JLabel("Number of set requests: ");
	array[i+4]=new JLabel("Number of traps set: ");
	array[i+5]=new JLabel("Number of bad community names set: ");
 }

	routers[0]=router1;
	routers[1]=router2;
	routers[2]=router3;
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
setBounds(600,200,410,400);
setResizable(false);
setSize(900,400);
setTitle("SNMP");
 setVisible(true);
 populateWindow();
}

public void populateWindow() {
	JPanel mainPanel=new JPanel();
	JPanel routerPanel=new JPanel();
	JPanel buttonPanel=new JPanel();
	buttonPanel.setBackground(Color.LIGHT_GRAY);
	JLabel ip1=new JLabel("IP Address: 192.168.10.1");
	JLabel ip2=new JLabel("IP Address: 192.168.20.1");
	JLabel ip3=new JLabel("IP Address: 192.168.30.1");
	JLabel id1=new JLabel("Router R1");
	JLabel id2=new JLabel("Router R2");
	JLabel id3=new JLabel("Router R3");

		 start=new Button("Start viewing SNMP statistics");
	end=new Button("Quit viewing SNMP statistics");
	end.setEnabled(false);
	start.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//setEnabled(false);
			clicked=true;
			end.setEnabled(true);
			tracker.start();
			
		}
	}
	);


			
			end.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					tracker.interrupt();
					
				}});
	router1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 4));
	router2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 4));
	router3.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 4));
		router1.setBackground(new Color(197,180,227));
		router2.setBackground(new Color(147,180,227));
		router3.setBackground(new Color(17,180,227));
		id1.setHorizontalAlignment(JLabel.CENTER);
		id1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		id1.setBackground(Color.LIGHT_GRAY);
		router1.add(id1);
		router1.add(ip1);
		router1.add(array[0],BorderLayout.CENTER);
		router1.add(array[1]);
		router1.add(array[2]);
		router1.add(array[3]);
		router1.add(array[4]);
		router1.add(array[5]);
		id2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		id2.setHorizontalAlignment(JLabel.CENTER);
		router2.add(id2);
		router2.add(ip2);
		router2.add(array[6]);
		router2.add(array[7]);
		router2.add(array[8]);
		router2.add(array[9]);
		router2.add(array[10]);
		router2.add(array[11]);
		id3.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		id3.setHorizontalAlignment(JLabel.CENTER);
		router3.add(id3);
		router3.add(ip3);
		router3.add(array[12]);
		router3.add(array[13]);
		router3.add(array[14]);
		router3.add(array[15]);
		router3.add(array[16]);
		router3.add(array[17]);
		
	buttonPanel.add(start);
	buttonPanel.add(end);
	mainPanel.setLayout(new BorderLayout());
	routerPanel.setLayout(new GridLayout(1,3));

	routerPanel.add(router1);
	routerPanel.add(router2);
	routerPanel.add(router3);
	
	mainPanel.add(routerPanel,BorderLayout.CENTER);
	mainPanel.add(buttonPanel,BorderLayout.SOUTH);
	add(mainPanel);

}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
}
