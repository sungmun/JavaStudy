package Serversynchronization;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IP {
	private String localip;
	private String externalip;
	
	public IP() {
		// TODO Auto-generated constructor stub
		try {
			localip=InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getLocalip() {
		return localip;
	}

	public void setLocalip(String localip) {
		this.localip = localip;
	}

	public String getExternalip() {
		return externalip;
	}
	
	public void setExternalip(String externalip) {
		this.externalip = externalip;
	}
}
