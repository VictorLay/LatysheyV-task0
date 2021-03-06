package by.epam.latyshey.library.data.source.impl;

import by.epam.latyshey.library.data.source.OutputSourceCustom;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OutputSourceCustomImpl implements OutputSourceCustom {

  private static final Logger logger = LogManager.getLogger(OutputSourceCustomImpl.class);

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

  /**
   * The method take array and set elements in the construction {@code <"tag"> element </"tag">}.
   *
   * @param dataArray array with elements is equal fields of object which need to wrap in tag.
   * @param tag       using for wrapping array elements on this tag.
   * @return the array of elements wrapped in the tags.
   */
  @Override
  public String wrapFieldsArrayToTag(String[] dataArray, String tag) {
    StringBuilder response = new StringBuilder();
    for (int i = 0; i < dataArray.length; i++) {
      dataArray[i] = "\n" + wrapFieldsToTag(dataArray[i], tag);
      response.append(dataArray[i]);
    }
    return response.toString();
  }

  /**
   * The method take element and set it in the construction (tag) element (/tag).
   *
   * @param data the element is equal fields of object which need to wrap in tag
   * @param tag  using for wrapping element on this tag.
   * @return the element wrapped in the tags.
   */
  @Override
  public String wrapFieldsToTag(String data, String tag) {
    data = "\n<" + tag + ">\n" + data + "\n</" + tag + ">";
    data = data.trim();
    return data;
  }

  /**
   * The method is replaces actual data to updated data.
   *
   * @param data    the string where the all data contains.
   * @param oldData the replaced data.
   * @param newData the replacing data.
   * @return updated data witch contains element newData instead oldData.
   */
  @Override
  public String updateData(String data, String oldData, String newData) {
    return data.replace(oldData, newData);
  }


}
