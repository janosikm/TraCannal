package tracemanager.ftp;

import it.sauronsoftware.ftp4j.FTPFile;

public class FTPJobStep extends FTPFile {
	
	private int id;
	private String procstep;
	private String stepclass;
	private String ddname;
	private long bytecount;
	
	public FTPJobStep () {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProcstep() {
		return procstep;
	}

	public void setProcstep(String procstep) {
		this.procstep = procstep;
	}

	public String getStepclass() {
		return stepclass;
	}

	public void setStepclass(String stepclass) {
		this.stepclass = stepclass;
	}

	public String getDdname() {
		return ddname;
	}

	public void setDdname(String ddname) {
		this.ddname = ddname;
	}

	public long getBytecount() {
		return bytecount;
	}

	public void setBytecount(long bytecount) {
		this.bytecount = bytecount;
	};
	

}
