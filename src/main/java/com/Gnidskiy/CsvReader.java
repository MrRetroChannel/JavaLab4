package com.Gnidskiy;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CsvReader {
    static Map<String, Gender> genders = new HashMap<String, Gender>() { {
            put("Male", Gender.MALE);
            put("Female", Gender.FEMALE);
            put("Attack helicopter", Gender.BATTLE_HELICOPTER);
        }
    };

    static SimpleDateFormat dateParser = new SimpleDateFormat("dd.MM.yyyy");

    /**
     *
     * @param csvFilePath - path to the csv file resource
     * @return List of People from csv file
     * @throws IOException - throws if the file was not found in resources
     * @throws ParseException - throws if salary or id were incorrect
     */
    public static List<Person> ReadCsvIntoPersonList(String csvFilePath) throws IOException, ParseException {
        Map<String, Division> divisionPool = new HashMap<>();

        List<Person> personList = new ArrayList<>();

        try (InputStream in = CsvReader.class.getClassLoader().getResourceAsStream(csvFilePath)) {
            BufferedReader reader = in == null ? null : new BufferedReader(new InputStreamReader(in));

            if (reader == null)
                throw new FileNotFoundException(csvFilePath);

            reader.readLine();
            String nextLine;

            while ((nextLine = reader.readLine()) != null) {
                String[] line = nextLine.split(";");

                Person newPerson = new Person();
                newPerson.setId(Integer.parseInt(line[0]));
                newPerson.setName(line[1]);
                newPerson.setGender(genders.get(line[2]));
                newPerson.setBirthDay(dateParser.parse(line[3]));

                Division division = divisionPool.get(line[4]);
                if (division == null)
                    divisionPool.put(line[4], new Division(divisionPool.size() + 1, line[4]));

                newPerson.setDivision(divisionPool.get(line[4]));
                newPerson.setSalary(Double.parseDouble(line[5]));

                personList.add(newPerson);
            }
        }

        return personList;
    }
}