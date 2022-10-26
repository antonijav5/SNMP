package routers;


import java.io.IOException;
import java.time.LocalTime;

import javax.swing.JLabel;

import com.ireasoning.protocol.snmp.SnmpConst;
import com.ireasoning.protocol.snmp.SnmpPdu;
import com.ireasoning.protocol.snmp.SnmpSession;

import gui.mainWindow;
public class RouterTracking extends Thread{
	public static SnmpSession session1;
	public static SnmpSession session2;
	public static SnmpSession session3;
	public static SnmpSession[] sessionsarray=new SnmpSession[3];
	private mainWindow owner;
	public String numIn="";
	public String numSet="";
	public String numGet="";
	public  String numOut="";
	public  String numBadCom="";
	public String numTraps="";
public RouterTracking(mainWindow own) {
owner=own;
try {
	session1 = new SnmpSession("192.168.10.1", 161,"si2019", "si2019", SnmpConst.SNMPV2);
	
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
try {
 session2 = new SnmpSession("192.168.20.1", 161,"si2019", "si2019", SnmpConst.SNMPV2);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
try {
 session3 = new SnmpSession("192.168.30.1", 161,"si2019", "si2019", SnmpConst.SNMPV2);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

sessionsarray[0]=session1;
sessionsarray[1]=session2;
sessionsarray[2]=session3;
}

public static final String inpckts = ".1.3.6.1.2.1.11.1.0";
public static final String outpckts = ".1.3.6.1.2.1.11.2.0";
public static final String getreq = ".1.3.6.1.2.1.11.15.0";
public static final String setreq = ".1.3.6.1.2.1.11.17.0";
public static final String gentrap = ".1.3.6.1.2.1.11.29.0";
public static final String badcomun = ".1.3.6.1.2.1.11.4.0";


public void startTracking(){
start();
}
public void finishTracking(){
interrupt();
}

@Override
public void run() {
	if (owner.clicked) owner.start.setEnabled(false);
while (!this.isInterrupted()) {
	
	
	            	try
	                { 	
	     for (int i=0;i<3;i++) {       	
	                SnmpPdu pd1=null;
	                try {
	               
	          		pd1=sessionsarray[i].snmpGetRequest(inpckts);
	          	} catch (IOException e1) {
	          		e1.printStackTrace();
	          	}
	                SnmpPdu pd2=null;
	                try {
	          		pd2=sessionsarray[i].snmpGetRequest(outpckts);
	          	} catch (IOException e) {
	          		e.printStackTrace();
	          	}
	                SnmpPdu pd3=null;
	                try {
	          		pd3=sessionsarray[i].snmpGetRequest(getreq);
	          	} catch (IOException e) {
	          	
	          		e.printStackTrace();
	          	}
	                SnmpPdu pd4=null;
	                try {
	          		pd4=sessionsarray[i].snmpGetRequest(setreq);
	          	} catch (IOException e) {
	          	
	          		e.printStackTrace();
	          	}
	              
	                SnmpPdu pd5=null;
	                try {
	          		pd5=sessionsarray[i].snmpGetRequest(gentrap);
	          	} catch (IOException e) {
	          	
	          		e.printStackTrace();
	          	}
	              
	                SnmpPdu pd6=null;
	                try {
	          		pd6=sessionsarray[i].snmpGetRequest(badcomun);
	          	} catch (IOException e) {
	          	
	          		e.printStackTrace();
	          	}
	              
	               
	                  numIn=pd1.toString().split("\n")[2].split(" ")[2];
	                   numOut=pd2.toString().split("\n")[2].split(" ")[2];
	                   numGet=pd3.toString().split("\n")[2].split(" ")[2];
	                   numSet=pd4.toString().split("\n")[2].split(" ")[2];
	                   numTraps=pd5.toString().split("\n")[2].split(" ")[2];
	                   numBadCom=pd6.toString().split("\n")[2].split(" ")[2];
	                   JLabel lab1=(JLabel) owner.routers[i].getComponent(2);
	                   JLabel lab2=(JLabel) owner.routers[i].getComponent(3);
	                   JLabel lab3=(JLabel) owner.routers[i].getComponent(4);
	                   JLabel lab4=(JLabel) owner.routers[i].getComponent(5);
	                   
	                   JLabel lab5=(JLabel) owner.routers[i].getComponent(6);
	                   JLabel lab6=(JLabel) owner.routers[i].getComponent(7);
	                   
	            
	                  lab1.setText("Number of received packets: "+numIn);
	                   lab2.setText("Number of sent packets: "+numOut);
	                 lab3.setText("Number of get requests: "+numGet);
	                 lab4.setText("Number of set requests: "+numSet);
	                  lab5.setText("Number of traps set: "+numTraps);
	                   lab6.setText("Number of bad community names set: "+numBadCom);
	     }
	                   try {
	          			sleep(10000);
	          		} catch (InterruptedException e) {
	          			owner.end.setEnabled(false);
	          			this.interrupt();
	          		}
	     
	          }	
	                 
	              
	                
	                catch(Exception e)
	                {
	                    System.out.println(e);
	                    e.printStackTrace();
	                }
	            }
}

}
