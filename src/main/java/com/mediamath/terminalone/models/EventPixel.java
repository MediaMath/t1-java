package com.mediamath.terminalone.models;

import java.util.Arrays;

public class EventPixel {

    private String typ;
    private String url;

    public enum EventPixelsEnum {

        ImpSkippable("imp:skippable"), Complete("complete"), CreativeClick("creative:click"), FullScreen(
                "fullscreen"), FirstQuartile("firstQuartile"), CreativeError("creative:err"), Rewind(
                "rewind"), MidPoint("midpoint"), Start("start"), CreativeImp("creative:imp"), CreativeView(
                "creativeView"), Expand("expand"), Close("close"), Collapse(
                "collapse"), AcceptInvitation("acceptInvitation"), Mute("mute"), ThirdQuartile(
                "thirdQuartile"), Unmute("unmute"), Resume("resume"), Pause(
                "pause"), Skip("skip"), EngagedView("engagedView");

        String val;

        EventPixelsEnum(String pVal) {
            val = pVal;
        }

        public String toString() {
            return this.val;
        }

    }

    public EventPixel(String typ, String url) {
        super();
        this.typ = typ;
        this.url = url;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        if (Arrays.toString(EventPixelsEnum.values()).contains(typ)) {
            this.typ = typ;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
