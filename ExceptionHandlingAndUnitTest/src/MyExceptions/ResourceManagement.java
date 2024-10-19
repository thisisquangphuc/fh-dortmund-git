package MyExceptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResourceManagement {
	
	public int checkContent(String srcPath, String filename) throws IOException{
		int check = 0;
		int count = 0;
		String path = srcPath + "/" + filename + ".txt";
		FileReader fr = new FileReader(path);
		BufferedReader reader = new BufferedReader(fr);
		try {
			
		    String line;
		    while ((line = reader.readLine()) != null) {
		    	count++;
//		        System.out.println("File content: " + line);
//		        System.out.println(line.length());
		        String fail = "Test fail!";
		        String succ = "Successful!";
		        if(line.equals(fail)) {
		        	check = 0;
		        	break;
		        }
		        if(line.equals(succ)) {
		        	check = 2;
		        	break;
		        }
		    }
		    if(count < 1) {
		    	check = 1;
		    }
		    
		    return check;
		} catch (IOException e) {
		    System.out.println("IOException occurred: " + e.getMessage());
		    
		} finally {
			reader.close();
	        fr.close();
		}
		return check;
	}
	
	public int checkExist(String srcPath, String filename) throws IOException{
		
		int check = 2;
		int countFile = 0;
		// Path of the specific directory 
      	String directoryPath = srcPath;

      	// Using File class create an object for specific directory
      	File directory = new File(directoryPath);
  
      	// Using listFiles method we get all the files of a directory 
      	// return type of listFiles is array
      	File[] files = directory.listFiles();
      	boolean flag = false;
        System.out.println("Searching: " + filename + ".txt");
	    // Print name of the all files present in that path
        try {
    		if (files != null) {
				for (File file : files) {
					countFile++;
					String findFile = filename + ".txt";            
					Pattern pattern = Pattern.compile(findFile, Pattern.CASE_INSENSITIVE);
				  	Matcher matcher = pattern.matcher(file.getName());
				  	boolean matchFound = matcher.find();
				  	if(matchFound) {
				      flag = true;		  	      	                 
				  	}
				}
			    if(flag == false){
			    	check = 3;
			    	System.out.println("File name not found");
//			    	throw new IOException("File not exist");
				}
			    if(countFile == 0) {
			    	check = 4;
			    }
	      
			}
    		return check;
        } catch (Exception e) {
        	System.out.println("Exception occurred while accessing file: " + e.getMessage());
        	return check;
        }          
	    		
	}
}

