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
        
        
        String date[] = new String[]{"28.09", "29.09", "30.09", "01.10", "02.10", "03.10", "04.10", "05.10", "06.10", "07.10"};
		
		EnergySource energySource[] = new EnergySource[] {
				new EnergySource("Petrol"), new EnergySource("DC"), new EnergySource("AC")
				};
		  
		ChargingStation stations[] = new ChargingStation[] {
				new ChargingStation(0, "Essen", new EnergySource[]{energySource[0], energySource[2]}),
				new ChargingStation(1, "Dortmund", new EnergySource[]{energySource[1]}),
				new ChargingStation(2, "Hamburg", new EnergySource[]{energySource[0], energySource[1]}),
				new ChargingStation(3, "Bochum", new EnergySource[]{energySource[0], energySource[1], energySource[2]}),
				new ChargingStation(4, "Dusseldorf", new EnergySource[]{energySource[0]})
				};
		
		Equipment equips[] = new Equipment[]{
				new Equipment(0, "Car", 
						new String[]{date[3], date[5]}, 
						new ChargingStation[]{stations[3], stations[4]}, 
						new EnergySource[]{stations[3].getEnergyAvail()[1], stations[4].getEnergyAvail()[0]}), 
				new Equipment(1, "Bike", 
						new String[]{date[1], date[5], date[8]}, 
						new ChargingStation[]{stations[0], stations[1], stations[3]}, 
						new EnergySource[]{stations[0].getEnergyAvail()[1], stations[1].getEnergyAvail()[0], stations[3].getEnergyAvail()[1]}),
		};
		
		eachDayInfo(logManager, date, equips);
		System.out.println();
		eachStationInfo(logManager, stations, equips);
		System.out.println();
		eachEnergyInfo(logManager, energySource, equips);
		System.out.println();
		systemInfo(logManager, equips);

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

    public static void systemInfo(LogManager logManage, Equipment[] equips) {
		String printInfo = "";
		
		for(int i=0; i<equips.length; i++) {
			printInfo += "Equipment" + equips[i].getId() + ", " + equips[i].getName() + " :\n";
			for(int j=0; j<equips[i].getDateCharging().length; j++) {
				printInfo = printInfo + 
						String.format("\tcharging at %s station\t by %s\t on %s.\n",
						equips[i].getStations()[j].getName(), 
						equips[i].getSources()[j].getName(), 
						equips[i].getDateCharging()[j]);
			}
		}
//		System.out.println("system_log.txt:\n" + printInfo);
		logManage.createLogFile("system_log.txt", printInfo); 
	}
	
	public static void eachDayInfo(LogManager logManage, String[] date, Equipment[] equips) {
		String dateCharging[];
		String printInfo;
		
		for(int i=0; i<date.length; i++) {
//			printInfo = String.format("On %s :\n", date[i]);
			printInfo = "";
			for(int j=0; j<equips.length; j++) {
				dateCharging = equips[j].getDateCharging();
				for(int k=0; k<dateCharging.length; k++) {
					if (dateCharging[k].equals(date[i])) {
						printInfo = printInfo + 
								String.format("Equiqment %s\t charging at %s station\t by %s.\n",
								equips[j].getName(), 
								equips[j].getStations()[k].getName(), 
								equips[j].getSources()[k].getName());
					}
				}
			}
//			System.out.println(date[i] + "_log.txt:\n" + printInfo);
			logManage.createLogFile(date[i] + "_file.txt", printInfo); 
		}
	}
	
	
	public static void eachStationInfo(LogManager logManage, ChargingStation[] stations, Equipment[] equips) {
		String printInfo;
		ChargingStation[] stat;
		
		for(int i=0; i<stations.length; i++) {
//			printInfo = stations[i].getName() + " : ";
			printInfo = "";
			for(int j=0; j<equips.length; j++) { 
				stat = equips[j].getStations();
				for (int k=0; k<stat.length; k++) {
					if (stat[k].getId() == stations[i].getId()) {
						printInfo = printInfo + 
								String.format("Equiqment %s\t charging on %s\t by %s.\n",
								equips[j].getName(), 
								equips[j].getDateCharging()[k], 
								equips[j].getSources()[k].getName());
					}
				}
			}
//			System.out.println(stations[i].getName() + "_log.txt:\n" + printInfo);
			logManage.createLogFile(stations[i].getName() + "_log.txt", printInfo);  
		}
	}
	
	
	public static void eachEnergyInfo(LogManager logManage, EnergySource[] sources, Equipment[] equips) {
		String printInfo;
		EnergySource[] srcs;
		
		for(int i=0; i<sources.length; i++) {
//			printInfo = sources[i].getName() + " : ";
			printInfo = "";
			for(int j=0; j<equips.length; j++) { 
				srcs = equips[j].getSources();
				for (int k=0; k<srcs.length; k++) {
					if (srcs[k].getName() == sources[i].getName()) {
						printInfo = printInfo + 
								String.format("Equiqment %s\t charging on %s\t at %s station.\n",
								equips[j].getName(), 
								equips[j].getDateCharging()[k], 
								equips[j].getStations()[k].getName());
					}
				}
			}
//			System.out.println(sources[i].getName() + "_log.txt:\n" + printInfo);
			logManage.createLogFile(sources[i].getName() + "_log.txt", printInfo); 
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