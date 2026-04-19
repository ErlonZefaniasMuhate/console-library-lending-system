package com.emuhate.library.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final Logger instance = new Logger();
    private final File LOG_FILE;

    private Logger(){
        File logDirectory = new File("logs");
        if (!logDirectory.exists() && !logDirectory.mkdir()){
            System.err.println("ERROR: Failed to create logs directory");
            System.exit(1);
        }
        LOG_FILE = new File(logDirectory, "library_log.txt");
    }

    public static Logger getInstance(){ return instance; }

    public void logTransaction(LogType type, String reportMessage) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            bw.write("[" + type + "] " + reportMessage + " (" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + ")");
            bw.newLine();
        } catch (IOException e) {
            System.err.println("ERROR: Logging failed " + e.getMessage());
        }
        System.out.println(reportMessage);
    }
}
