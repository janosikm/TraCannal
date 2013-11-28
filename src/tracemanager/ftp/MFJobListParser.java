package tracemanager.ftp;

import java.util.Scanner;

import it.sauronsoftware.ftp4j.FTPListParser;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPListParseException;

public class MFJobListParser implements FTPListParser {
	public FTPFile[] parse(String[] lines) throws FTPListParseException {
		int size = lines.length;
		if (size == 0) {
			return new FTPFile[0];
		}
		if (lines[0].startsWith("JOBNAME  JOBID")) {
				size--;
				String[] lines2 = new String[size];
				for (int i = 0; i < size; i++) {
					lines2[i] = lines[i + 1];
				}
				lines = lines2;
			}			
		FTPJob[] ret = new FTPJob[size];
		for (int i = 0; i < size; i++) {
			Scanner sc = new Scanner(lines[i]).useDelimiter("\\s+");
			ret[i] = new FTPJob();
			ret[i].setName(sc.next());
			ret[i].setJobID(sc.next());
			ret[i].setOwner(sc.next());
			ret[i].setJobClass(sc.next());
			ret[i].setStatus(sc.next());
		}
		return ret;
		
	}
}
