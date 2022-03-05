package by.epam.latyshey.library.data.source;

import java.io.File;

public interface InputSourceCustom {

  String readAllData(File file);

  String findTags(String data, String tag);

  //String[] getTagsFieldsArray(String data, String tag);
  String[] separateByDelimiter(String object, String delimiter);

  String[] findTagsFields(String data, String tag);
}
