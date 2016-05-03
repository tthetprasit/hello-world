package au.com.marlo.sftp.loadtest.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;


public class SftpRoute extends RouteBuilder {

	private final static String SFTP_ENDPOINT = "{{sftp.server}}";
	private final static String FILE_DESTINATION = "{{file.destination}}";
	
	@Override
    public void configure() throws Exception {
        // configure properties component
        PropertiesComponent pc = getContext().getComponent("properties", PropertiesComponent.class);
        pc.setLocation("classpath:sftp.properties");

        // lets shutdown faster in case of in-flight messages stack up
        getContext().getShutdownStrategy().setTimeout(10);

        from(SFTP_ENDPOINT)
            .log("Downloading file ${file:name}")
            .to(FILE_DESTINATION)
            .log("Downloaded file ${file:name} complete.");

        // use system out so it stand out
        System.out.println("*********************************************************************************");
        System.out.println("Camel will route files from target/upload directory to the FTP server: "
                + getContext().resolvePropertyPlaceholders("{{sftp.server}}"));
        System.out.println("You can configure the location of the ftp server in the src/main/resources/ftp.properties file.");
        System.out.println("If the file upload fails, then the file is moved to the target/error directory.");
        System.out.println("Use ctrl + c to stop this application.");
        System.out.println("*********************************************************************************");
    }
}
