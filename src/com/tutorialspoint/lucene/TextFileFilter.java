package com.tutorialspoint.lucene;
import java.io.File;
//import java.io.FileFilter;
public class TextFileFilter {
	public boolean accept(File pathname) {
		return pathname.getName().toLowerCase().endsWith(".txt");
	}
}