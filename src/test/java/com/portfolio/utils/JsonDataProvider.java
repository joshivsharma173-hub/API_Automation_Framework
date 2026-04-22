package com.portfolio.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonDataProvider {

    // Reads users.json and returns 2D Object array for TestNG DataProvider
    public static Object[][] getUserData() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            // Read the JSON file
            JsonNode rootArray = mapper.readTree(
                new File("src/test/resources/testdata/users.json")
            );

            int size = rootArray.size();
            Object[][] data = new Object[size][1];

            for (int i = 0; i < size; i++) {
                JsonNode userNode = rootArray.get(i);

                // Replace {{time}} placeholder with current timestamp
                // to ensure unique emails every run
                String name   = userNode.get("name").asText();
                String email  = userNode.get("email").asText()
                                .replace("{{time}}", 
                                String.valueOf(System.currentTimeMillis()));
                String gender = userNode.get("gender").asText();
                String status = userNode.get("status").asText();

                data[i][0] = new String[]{name, email, gender, status};
            }

            return data;

        } catch (IOException e) {
            throw new RuntimeException("Failed to read users.json", e);
        }
    }
}