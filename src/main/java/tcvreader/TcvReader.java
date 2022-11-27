package tcvreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TcvReader {
    private final String tcvFile = "categories.tsv";
    private Map<String, String> map = new HashMap();

    /**
     * приходит название покупки, а не категория. Если названия в файле категорий нет, то должна выбиратья категория другое.
     *
     * @return
     */
    public String checkCategoriesInFile(Map<String, String> mapFromJson) {
        try (BufferedReader TSVFile = new BufferedReader(new FileReader(tcvFile))) {

            String dataRaw;
            while ((dataRaw = TSVFile.readLine()) != null) {
                String str[] = dataRaw.split("\\t");
                map.put(str[0], str[1]);
                validationMap(mapFromJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Другое";
    }

    private boolean validationMap(Map<String, String> mapFromJson) {
        for (Map.Entry<String, String> pair : map.entrySet()) {
            String key = pair.getKey();
            for (Map.Entry<String, String> pair2 : mapFromJson.entrySet()) {
                String value = pair2.getValue();
                if (value.equals(key)) {
                    System.out.println("Совпадение в файле!");
                    break;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}