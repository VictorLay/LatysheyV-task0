package by.epam.latyshey.library.data.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class InputSourceCustom {

  private final String DEL;
  public InputSourceCustom(String DEL) {
    this.DEL = DEL;
  }

  public String getDataFromFile(File file1) {
    StringBuilder result;
    String response = null;

    try (Reader reader = new InputStreamReader(new FileInputStream(file1))) {
      char[] array = new char[1024];
      int count = reader.read(array);
      result = new StringBuilder();
      while (count > 0) {
        result.append(new String(array, 0, count));
        count = reader.read(array);
      }
      response = result.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public   String[] getObjectsArray(String data){
    return data.split("\n");
  }

  public   String[] getDataArrayOfObject(String object){
    return object.split(DEL);
  }

}
