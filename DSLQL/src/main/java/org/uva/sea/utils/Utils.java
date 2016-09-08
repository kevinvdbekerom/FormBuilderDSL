package org.uva.sea.utils;

import java.io.File;
import java.io.IOException;

import org.codehaus.plexus.util.FileUtils;

public class Utils {
	
	public static String readFileToString(File f) {
		try {
		return FileUtils.fileRead(f);
		} catch (IOException e) {
			println(e.getMessage());
		}
		return "";
	}
	
	public static void println(String s) {
		System.out.println(s);
	}
	
}
