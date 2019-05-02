package com.mediamath.terminalone.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;
import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OS {
    TV_OS("TV OS",3070762),
    MAC_OS("macOS",7159),
    FIRE_OS("Fire OS", 3070782),
    ROKU_OS("Roku OS", 3070764),
    SYMBIAN_S60("Symbian S60",7345),
    ANDROID("Android", 7184),
    WINDOWS("Windows", 7335),
    CHROMECAST("Chromecast", 3070692),
    TIZEN("Tizen",3070686),
    RIM_TABLET_OS("RIM Tablet OS", 7331),
    XBOX_OS("Xbox OS", 7450),
    BLACKBERRY("BlackBerry", 7267),
    KAIOS("KaiOS", 3070681),
    MAC_OS_X("Mac OS X", 7275),
    WINDOWS_PHONE("Windows Phone", 7348),
    IOS("iOS", 7369);

    private String name;
    private int id;

    OS(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static List<OS> getAll() {
        return Arrays.asList(OS.values());
    }
}
