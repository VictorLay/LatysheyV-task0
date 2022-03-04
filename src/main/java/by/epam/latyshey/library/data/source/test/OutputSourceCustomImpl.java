package by.epam.latyshey.library.data.source.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OutputSourceCustomImpl implements OutputSourceCustom {

  private static Logger logger = LogManager.getLogger(OutputSourceCustomImpl.class);

  /**
   * The method adds a new record to Data Source File.
   *
   * @param file   the package when the record will placed.
   * @param record this string will be added to existing data in the file.
   * @return value TRUE if adding new record finalized successful and FALSE in another case.
   */
  @Override
  public boolean addNewRecord(File file, String record) {
    record += "\n";
    try (OutputStream outputStream = new FileOutputStream(file, true)) {
      outputStream.write(record.getBytes(StandardCharsets.UTF_8));
      return true;
    } catch (Exception e) {
      logger.error("An error of adding new record!\n" + e.getMessage());
      return false;
    }
  }

  /**
   * The method overwrites information from the file with new data which come in the data
   * parameter.
   *
   * @param file the package when the record will placed.
   * @param data a new string data which will be written to the file.
   * @return return TRUE if recording finalized successfully and FALSE in another case.
   */
  @Override
  public boolean setAllData(File file, String data) {
    try (OutputStream outputStream = new FileOutputStream(file)) {
      data = data.trim() + "\n";
      outputStream.write(data.getBytes(StandardCharsets.UTF_8));
      return true;
    } catch (Exception e) {
      logger.error(
          "An error of re-recording. The new record adding was failed.\n" + e.getMessage());
      return false;
    }
  }

  @Override
  public String wrapFieldsArrayToTag(String[] dataArray, String tag) {
    StringBuilder response = new StringBuilder();
    for (int i = 0; i < dataArray.length; i++) {
      dataArray[i] = "\n" + wrapFieldsToTag(dataArray[i], tag);
      response.append(dataArray[i]);
    }
    return response.toString();
  }

  @Override
  public String wrapFieldsToTag(String data, String tag) {
    data = "\n<" + tag + ">\n" + data + "\n</" + tag + ">";
    data = data.trim();
    return data;
  }

  @Override
  public String updateData(String data, String oldData, String newData) {
    String res = data.replace(oldData, newData);
    return res;
  }


}
