package org.example.model.abstraction;

import org.example.model.Geography;

public class GeographyParser implements IParse<Geography> {

    @Override
    public Geography parse(String data) {
        String cleanedInput = data.strip();
        String[] parts = cleanedInput.split(";");

        Geography geography = new Geography();
        geography.setId(Integer.parseInt(parts[0]));
        geography.setType(parts[1]);
        geography.setName(parts[2]);
        geography.setCode(parts[3]);

        String parentIdValue = parts[4];
        if (!"NULL".equals(parentIdValue)) {
            geography.setParentId(Integer.parseInt(parentIdValue));
        }

        return geography;
    }
}
