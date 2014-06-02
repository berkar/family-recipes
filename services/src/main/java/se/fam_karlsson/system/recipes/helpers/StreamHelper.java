package se.fam_karlsson.system.recipes.helpers;

import org.jboss.logging.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by
 * User: joli
 * Date: 2013-07-03
 * Time: 10:54
 */
public class StreamHelper {

	private static transient Logger itsLog = Logger.getLogger(StreamHelper.class.getName());

	private static int BUFFER_SIZE = 1024 * 4;

	/**
	 * Copies input stream to output stream and then closes the input stream.
	 * Method also considers ClientAbortException (TEAMLAVIN-1052)
	 *
	 * @param in
	 * @param out
	 * @return bytes read
	 * @throws java.io.IOException
	 */
	public static int copyStream(InputStream in, OutputStream out) throws IOException {
		try {
			byte[] buff = new byte[BUFFER_SIZE];
			int bytesRead;
			int totalBytesRead = 0;
			while (-1 != (bytesRead = in.read(buff, 0, buff.length))) {
				out.write(buff, 0, bytesRead);
				totalBytesRead += bytesRead;
			}
			out.flush();
			return totalBytesRead;
		} catch (IOException e) {
			if (!e.getClass().getSimpleName().equals("ClientAbortException")) {
				throw e;
			} else {
				itsLog.debug("Client Abort caught.", e); // else ignore the client abort and output to debug
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return -1;
	}
}
