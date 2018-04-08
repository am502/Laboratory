package ru.itis.inform;

import java.io.File;
import java.io.FilenameFilter;

public class MaskFilter implements FilenameFilter {
    private String mask;

    public MaskFilter(String Mask) {
        mask = Mask;
    }

    public boolean accept(File f, String name) {
        return name.contains(mask);
    }
}