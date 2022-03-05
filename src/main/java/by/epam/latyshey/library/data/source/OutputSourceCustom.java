package by.epam.latyshey.library.data.source;

import java.io.File;

public interface OutputSourceCustom {
    boolean addNewRecord(File file, String record);
    boolean setAllData(File file, String data);

    String wrapFieldsArrayToTag(String[] dataArray, String tag);
    String wrapFieldsToTag(String data, String tag);
    String updateData(String data, String oldData, String newData);
}
