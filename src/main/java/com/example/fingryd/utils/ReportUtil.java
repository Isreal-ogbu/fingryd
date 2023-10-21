package com.example.fingryd.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class ReportUtil {

    @Value(value = "${filename}")
    private String logLocation;
    public void accountCreationReport(String name) {

        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(logLocation, true))){

            String message = String.format("A customer with Description (Name : %s) just created an account at %s\n", name, LocalDateTime.now());
            fileWriter.write(message);
        } catch ( IOException e){
            throw new RuntimeException("An unexpected error occurred...");
        }
    }
}
