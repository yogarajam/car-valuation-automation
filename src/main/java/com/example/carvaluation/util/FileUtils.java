package com.example.carvaluation.util;

import com.example.carvaluation.model.CarDetails;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {
    private static final Pattern REGISTRATION_PATTERN = Pattern.compile("[A-Z]{2}\\d{2}\\s?[A-Z\\d]{3}");

    public static List<String> readRegistrations(String filePath) throws IOException {
        List<String> registrations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = REGISTRATION_PATTERN.matcher(line.toUpperCase());
                while (matcher.find()) {
                    registrations.add(matcher.group());
                }
            }
            return registrations;
        }
    }

    public static List<CarDetails> readExpectedOutput(String filePath) throws IOException {
        List<CarDetails> expectedDetails = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    expectedDetails.add(new CarDetails(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            Integer.parseInt(parts[3].trim()),
                            Double.parseDouble(parts[4].trim())
                    ));
                }
            }
            return expectedDetails;
        }
    }
}