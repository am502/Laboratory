package ru.kpfu.itis.controller.editor;

import ru.kpfu.itis.model.Automobile;

import java.beans.PropertyEditorSupport;

public class AutomobileEditor extends PropertyEditorSupport {

    public void setAsText(String text) {
        setValue(new Automobile(Long.parseLong(text)));
    }

    public String getAsText(){
        return ((Automobile)getValue()).getId().toString();
    }
}