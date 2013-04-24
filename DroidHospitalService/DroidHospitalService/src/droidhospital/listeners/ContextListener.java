package droidhospital.listeners;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import droidhospital.service.DroidHospitalIPServer;

public class ContextListener implements ServletContextListener {

	private DroidHospitalIPServer ipServer;

	public void contextInitialized( ServletContextEvent arg0 ) {

		try {
			
			ipServer = new DroidHospitalIPServer();
			ipServer.start();
			
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed( ServletContextEvent arg0 ) {

		if( ipServer != null ) {
			ipServer.finaliza();
		}
	}
}