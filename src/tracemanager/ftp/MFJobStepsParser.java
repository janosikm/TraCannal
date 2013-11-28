package tracemanager.ftp;

import java.util.Scanner;

import it.sauronsoftware.ftp4j.FTPListParser;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPListParseException;

public class MFJobStepsParser implements FTPListParser {
	public FTPFile[] parse(String[] lines) throws FTPListParseException {
		int size = lines.length;
		if (size == 0) {
			return new FTPFile[0];
		}
		if (lines[0].startsWith("JOBNAME  JOBID")) {
				String[] lines2 = new String[size];
				int j = 0;
				for (int i = 4; i < size - 1; i++) {
					lines2[j] = lines[i];
					j++;
				}
				size = size - 5;
				lines = lines2;
			}			
		FTPJobStep[] ret = new FTPJobStep[size];
		for (int i = 0; i < size; i++) {
			//Scanner sc = new Scanner(lines[i]).useDelimiter("\\s+");
			ret[i] = new FTPJobStep();
			int pos = 0;
			lines[i] = lines[i].trim();
			ret[i].setId(Integer.parseInt(lines[i].substring(pos,pos+3).trim()));
			pos += 4;
			ret[i].setName(lines[i].substring(pos,pos+8).trim());
			pos += 9;
			ret[i].setProcstep(lines[i].substring(pos,pos+8).trim());
			pos += 9;
			ret[i].setStepclass(lines[i].substring(pos,pos+1).trim());
			pos += 2;
			ret[i].setDdname(lines[i].substring(pos,pos+8).trim());
			pos += 9;
			ret[i].setBytecount(Long.parseLong(lines[i].substring(pos,lines[i].length()).trim()));
		}
		return ret;
		
	}
}
