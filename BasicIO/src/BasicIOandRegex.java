import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

public class BasicIOandRegex {
    public static void main(String[] args) throws IOException {

        String logDirectory = "logs/";
        LogManager logManager = new LogManager(logDirectory);
        
        // Create log files and put data to each files 
        logManager.createLogFile("log1.txt", "{ station A, equipment:Car1, energy_source:AC, date: 10.10.2024 }");
        logManager.createLogFile("log2.txt", "{ station B, equipment:Car2, energy_source:DC, date: 9.10.2024 }");
        logManager.createLogFile("log3.txt", "{ station C, equipment:Car3, energy_source:AC, date: 9.10.2024 }");
        System.out.println("\n");
        	
        // Move a log file
        String destDic = "moveFiles/";
        System.out.println("Moving the 'log1.txt' to " + destDic);
        logManager.moveLogFile("log1.txt", destDic);
        System.out.println("\n");

        // Delete a log file
        System.out.println("Deleting 'log.txt'...");
        logManager.deleteLogFile("log2.txt");
        System.out.println("\n");
        
        // Archive logs
        System.out.println("Archiving log files from " + destDic + " into 'logs_archive.zip'...");
        logManager.archiveLogs(destDic, "logs_archive.zip");
        System.out.println("\n");
        
        // Simulate Data Exchange Using Streams
        simulateDataExchange();

    }

    // Simulate data exchange using byte and character streams
    public static void _simulateDataExchange() {
        String data = "This is a dummy data for simalating data streams";
        System.out.println("Simulating data exchange...");

        // Byte Stream
        try (ByteArrayInputStream byteInput = new ByteArrayInputStream(data.getBytes());
             ByteArrayOutputStream byteOutput = new ByteArrayOutputStream()) {

            int byteRead;
            while ((byteRead = byteInput.read()) != -1) {
                byteOutput.write(byteRead);
            }
            System.out.println("Byte Stream Output: " + byteOutput.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Character Stream
        try (CharArrayReader charInput = new CharArrayReader(data.toCharArray());
             CharArrayWriter charOutput = new CharArrayWriter()) {

            int charRead;
            while ((charRead = charInput.read()) != -1) {
                charOutput.write(charRead);
            }
            System.out.println("Character Stream Output: " + charOutput.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void simulateDataExchange() {
        String data = "This is a dummy data for simulating data streams";
        
        File binaryFile = new File("simulate_bin_steams.bin");

        System.out.println("Simulating data exchange using byte streams...");

        // Writing data using byte stream (OutputStream)
        try (FileOutputStream fos = new FileOutputStream(binaryFile)) {
            byte[] byteData = data.getBytes(); // Convert string to byte array
            fos.write(byteData);  // Write bytes to the file
            System.out.println("Data written to binary file: " + binaryFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Character Stream
        try (CharArrayReader charInput = new CharArrayReader(data.toCharArray());
             CharArrayWriter charOutput = new CharArrayWriter()) {

            int charRead;
            while ((charRead = charInput.read()) != -1) {
                charOutput.write(charRead);
            }
            String outputString = charOutput.toString();
            System.out.println("Character Stream Output: " + outputString);

            // Write the output to a file
            try (FileWriter fileWriter = new FileWriter("simulate_char_steams.txt")) {
                fileWriter.write(outputString);
                System.out.println("Data has been written to output.txt");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}

class LogManager {
    private String logDirectory;

    public LogManager(String logDirectory) {
        this.logDirectory = logDirectory;
        createDirectory(logDirectory);  // Ensure log directory exists
    }

    // Create a log file
    public void createLogFile(String fileName, String content) {
        try {
            Path filePath = Paths.get(logDirectory, fileName);
            Files.write(filePath, content.getBytes());
            System.out.println("Created log file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error creating log file: " + e.getMessage());
        }
    }

    // Move a log file to another directory
    public void moveLogFile(String fileName, String targetDirectory) {
        try {
            createDirectory(targetDirectory);  // Ensure target directory exists
            Path sourcePath = Paths.get(logDirectory, fileName);
            Path targetPath = Paths.get(targetDirectory, fileName);
            Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved log file to: " + targetDirectory);
        } catch (IOException e) {
            System.out.println("Error moving log file: " + e.getMessage());
        }
    }

    // Delete a log file
    public void deleteLogFile(String fileName) {
        try {
            Path filePath = Paths.get(logDirectory, fileName);
            Files.delete(filePath);
            System.out.println("Deleted log file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error deleting log file: " + e.getMessage());
        }
    }

    // Archive log files into a zip file
    public void archiveLogs(String sourceDirectory, String archiveFileName) {
    	try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(Paths.get(logDirectory, archiveFileName).toFile()))) {
    		Path sourcePath = Paths.get(sourceDirectory);
            Files.walk(sourcePath).forEach(file -> {
                // Check if it's a regular file (not a directory)
                if (Files.isRegularFile(file)) {
                    try {
                        // Open the file to read its content
                        FileInputStream fis = new FileInputStream(file.toFile());

                        // Create a new entry in the zip file with the file name
                        ZipEntry zipEntry = new ZipEntry(file.getFileName().toString());
                        zipOut.putNextEntry(zipEntry);

                        // Buffer to hold file content
                        byte[] buffer = new byte[1024];
                        int bytesRead;

                        // Read the file content and write it to the zip
                        while ((bytesRead = fis.read(buffer)) > 0) {
                            zipOut.write(buffer, 0, bytesRead);
                        }

                        // Close the current entry
                        zipOut.closeEntry();
                        fis.close(); // Close FileInputStream after processing the file
                        System.out.println("Archived log file name: " + file.getFileName());
                        System.out.println("Location of archieved files: " + logDirectory);
                        
                    } catch (IOException e) {
                        System.out.println("Error archiving log file: " + file.getFileName());
                    }
                }
            });
        } catch (IOException e) {
            System.out.println("Error creating archive: " + e.getMessage());
        }
    }

    // Helper method to create directories
    private void createDirectory(String dir) {
        try {
            Files.createDirectories(Paths.get(dir));
        } catch (IOException e) {
            System.out.println("Error creating directory: " + dir);
        }
    }
}