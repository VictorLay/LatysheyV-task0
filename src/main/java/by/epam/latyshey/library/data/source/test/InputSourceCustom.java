package by.epam.latyshey.library.data.source.test;

import java.io.File;

public interface InputSourceCustom {

  String readAllData(File file);

  String findTags(String data, String tag);

  //String[] getTagsFieldsArray(String data, String tag);

  String[] findTagsFields(String data, String tag);
}
