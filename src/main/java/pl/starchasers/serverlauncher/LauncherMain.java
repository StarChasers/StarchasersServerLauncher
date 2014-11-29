package pl.starchasers.serverlauncher;

import java.io.File;

import net.magik6k.jwwf.core.JwwfServer;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class LauncherMain {
    private static final String configFile = "serverlauncher.conf";
    public static final Config config;
	
    static{
    	config = ConfigFactory.parseFile(new File(configFile));
    }
    
	public static void main(String[] args){
		int port;
		try {
			port = new Integer( args[ 0 ] );
		} catch ( Exception e ) {
			System.out.println( "No port specified. Defaulting to 8888" );
			port = 8888;
		}
		JwwfServer server = new JwwfServer(port);
		try {
			String addr = args[1];
			if(addr != null){
				server.setApiUrl(addr);
			}
		} catch( Exception e ) {
			System.out.println( "No custom API address given, using automatic mode." );
		}
		JwwfServer.debugOutput(true);
		server.bindWebapp(LauncherClient.class).start();
		server.start();
	}
}