package ru.itis.inform;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSeeker {
    public List<String> findFile(String path, String mask, ArrayList<String> list) {
        File file = new File(path);
        String[] DirList = file.list();
        String[] FileMask = file.list(new MaskFilter(mask));
        if (FileMask.length > 0) {
            for (String s : FileMask) {
                list.add(s);
            }
        }

        for (String s : DirList) {
            File f1 = new File(path + "/" + s);
            if (!f1.isFile()) {
                findFile(path + "/" + s, mask, list);
            }
        }
        return list;
    }
}