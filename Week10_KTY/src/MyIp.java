import java.net.InetAddress;

import java.net.UnknownHostException;

public class MyIp {
	public static void main(String[] args) {
		try {
			InetAddress local = InetAddress.getLocalHost();
			System.out.println("내 컴퓨터 ip주소:"+ local.getHostAddress());
			
		}catch(UnknownHostException e){
			e.printStackTrace();
		}
	}

}
