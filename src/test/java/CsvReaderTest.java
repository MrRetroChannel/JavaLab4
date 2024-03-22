import com.Gnidskiy.CsvReader;
import com.Gnidskiy.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class CsvReaderTest {
    @Test
    public void TestReadCsvFileNotFound() {
        Assertions.assertThrows(IOException.class, () -> CsvReader.ReadCsvIntoPersonList("asd"));
    }

    @Test
    public void TestReadCsv() {
        try {
            List<Person> personList = CsvReader.ReadCsvIntoPersonList("foreign_names.csv");

            for (Person p : personList) {
                Assertions.assertNotNull(p.getGender());
                Assertions.assertNotNull(p.getDivision());
                Assertions.assertNotNull(p.getBirthDay());
                Assertions.assertNotEquals(p.getId(), 0);
                Assertions.assertNotEquals(p.getSalary(), 0.0);
            }
        }
        catch (Exception ignored) {

        }
    }


}
