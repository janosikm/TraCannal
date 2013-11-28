package tracemanager.ftp;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import it.sauronsoftware.ftp4j.*;

import tracemanager.ftp.MFJobListParser;

public class FTPwrap {
	public FTPwrap () {};
	public LinkedList getJobs(String mask) {
		FTPClient client = new FTPClient();
		MFJobListParser parser = new MFJobListParser();
		FTPJob[] jobarray;
		LinkedList joblist = new LinkedList();
		
		try {
			client.connect("usilca31.ca.com", 21);
			client.login("melmi08", "Roci1212");
			FTPReply reply = client.sendSiteCommand("filetype=jes jesjobname=" + mask + " jesowner=*");
			client.setPassive(false);
			client.addListParser(parser);
			jobarray = (FTPJob[])client.list();

		}
		catch (Exception e) {

			return null;
		}
		
		for (int i = 0;i < jobarray.length; i++) {
			joblist.add(jobarray[i]);
		}
		
		return joblist;
		
	}
	
	public LinkedList getSteps(FTPJob job,FTPClient client) {

		MFJobStepsParser parser = new MFJobStepsParser();
		FTPJobStep[] jobsteparray;
		LinkedList steplist = new LinkedList();
		
		try {
			if(client==null) {
				client = new FTPClient();			
				client.connect("usilca31.ca.com", 21);
				client.login("melmi08", "Roci1212");
			}
			FTPReply reply = client.sendSiteCommand("filetype=jes jesjobname=" + job.getName() + " jesowner=*");
			client.setPassive(false);
			client.addListParser(parser);
			jobsteparray = (FTPJobStep[])client.list(job.getJobID());

		}
		catch (Exception e) {

			return null;
		}
		
		for (int i = 0;i < jobsteparray.length; i++) {
			steplist.add(jobsteparray[i]);
		}
		
		return steplist;		

	}
	
	public boolean loadTraces(FTPJob job) {
		FTPClient client = new FTPClient();		
		try {
			client.connect("usilca31.ca.com", 21);
			client.login("melmi08", "Roci1212");
			FTPReply reply = client.sendSiteCommand("filetype=jes jesjobname=" + job.getName() + " jesowner=*");

		}
		catch (Exception e) {		}		
		LinkedList joblist = getSteps(job,client);
		Iterator iter = joblist.iterator();
		
		boolean foundTraces = false;
		
		try {
			
			while (iter.hasNext()) {
				FTPJobStep jobstep = (FTPJobStep)iter.next();
				if(jobstep.getDdname().equals("EN$$$MOD")) {
					File traceFile = new File("C:\\Temp\\" + job.getJobID() + jobstep.getName() + "_1.txt");
					client.setType(FTPClient.TYPE_TEXTUAL);
					client.download(job.getJobID() + "." + jobstep.getId(), traceFile);
					foundTraces = true;
				}
			}
		} catch (Exception e) {}	
		
		return foundTraces;
	}
}