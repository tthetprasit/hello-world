package au.com.marlo.sftp.loadtest;

import org.apache.camel.main.Main;

import au.com.marlo.sftp.loadtest.route.SftpRoute;

public final class SftpRouteTest {

	private SftpRouteTest(){}
	
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.addRouteBuilder(new SftpRoute());
		main.run();
	}

}
