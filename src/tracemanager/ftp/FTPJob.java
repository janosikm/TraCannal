package tracemanager.ftp;

import it.sauronsoftware.ftp4j.FTPFile;

public class FTPJob extends FTPFile {
	private	String jobID;
	private	String owner;
	private	String status;
	private	String jobClass;
	
	public String getJobID() {
		return jobID;
	}
	public void setJobID(String jobID) {
		this.jobID = jobID;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getJobClass() {
		return jobClass;
	}
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	

}
