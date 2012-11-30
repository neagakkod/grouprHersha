package model;

import org.dsrg.soenea.domain.command.CommandException;

public class PasswordService {
	public static String MD5encode(String md5) throws CommandException {
		  try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		        	sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		        }
		        return sb.toString();
		  } catch (java.security.NoSuchAlgorithmException e) {
			  throw new CommandException(e);
		  }
	}

}
