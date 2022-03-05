package by.epam.latyshey.library.data.source.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputSourceCustomImpl implements InputSourceCustom {

  private static final Logger logger = LogManager.getLogger(InputSourceCustomImpl.class);

  @Override
  public String readAllData(File file) {
    StringBuilder result;
    try (Reader reader = new InputStreamReader(new FileInputStream(file))) {
      char[] array = new char[1024];
      int count = reader.read(array);
      result = new StringBuilder();
      while (count > 0) {
        result.append(new String(array, 0, count));
        count = reader.read(array);
      }
      return result.toString();
    } catch (Exception e) {
      logger.error("The file can't be read.\n" + e.getMessage());
      return null;
    }
  }

  @Override
  public String findTags(String data, String tag) {
    Pattern pattern = Pattern.compile(
        "\\s?(<" + tag + ">)+?\\s*\\n+[\\s\\S]*?(</" + tag + ">)+?\\s*?");
    Matcher matcher = pattern.matcher(data);
    StringBuilder inter = new StringBuilder();
    while (matcher.find()) {
      inter.append(data.substring(matcher.start(), matcher.end()).trim());
      inter.append(Delimiter.DELIM6 + "\n");
    }

    return inter.toString().trim();
  }

  /**
   * The method is searches usages of chosen tag and splits to String array this information
   * which was between tags.
   */
  @Override
  public String[] findTagsFields(String data, String tag) {
    String tagsString = findTags(data, tag);
    return getTagsFieldsArray(tagsString, tag);
  }

  private String[] getTagsFieldsArray(String data, String tag) {
    data = data.replace("<" + tag + ">", "");
    data = data.replace("</" + tag + ">", "");
    String[] dataArray = data.split(Delimiter.DELIM6);
    for (int i = 0; i < dataArray.length; i++) {
      dataArray[i] = dataArray[i].trim();
    }
    return dataArray;
  }

  @Override
  public String[] separateByDelimiter(String object, String delimiter){
    return object.split(delimiter);
  }

}




