package com.mediamath.terminalone.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.ws.rs.core.Form;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreativeHealthcheck implements T1Entity {

    private static final String entityName = "CreativeHealthchek";

    private int atomic_creative_id;
    private UUID odc_creative_id;
    private String net_status;
    private String adx_open_auction;
    private String adx_deals;
    private String mopub_direct;
    private String right_media_exchange;
    private String app_nexus;
    private String brightroll_for_display;
    private String microsoft_ad_exchange;

    public static String getEntityName() {
        return entityName;
    }

    public int getAtomicCreativeId() {
        return atomic_creative_id;
    }

    public UUID getOdcCreativeId() {
        return odc_creative_id;
    }

    public void setOdcCreativeId(UUID odc_creative_id) {
        this.odc_creative_id = odc_creative_id;
    }

    public void setAtomicCreativeId(int atomic_creative_id) {
        this.atomic_creative_id = atomic_creative_id;
    }

    public String getNetStatus() {
        return net_status;
    }

    public void setNetStatus(String net_status) {
        this.net_status = net_status;
    }

    public String getAdxOpenAuction() {
        return adx_open_auction;
    }

    public void setAdxOpenAuction(String adx_open_auction) {
        this.adx_open_auction = adx_open_auction;
    }

    public String getAdxDeals() {
        return adx_deals;
    }

    public void setAdxDeals(String adx_deals) {
        this.adx_deals = adx_deals;
    }

    public String getMopubDirect() {
        return mopub_direct;
    }

    public void setMopubDirect(String mopub_direct) {
        this.mopub_direct = mopub_direct;
    }

    public String getRightMediaExchange() {
        return right_media_exchange;
    }

    public void setRightMediaExchange(String right_media_exchange) {
        this.right_media_exchange = right_media_exchange;
    }

    public String getAppNexus() {
        return app_nexus;
    }

    public void setAppNexus(String app_nexus) {
        this.app_nexus = app_nexus;
    }

    public String getBrightrollForDisplay() {
        return brightroll_for_display;
    }

    public void setBrightrollForDisplay(String brightroll_for_display) {
        this.brightroll_for_display = brightroll_for_display;
    }

    public String getMicrosoftAdExchange() {
        return microsoft_ad_exchange;
    }

    public void setMicrosoftAdExchange(String microsoft_ad_exchange) {
        this.microsoft_ad_exchange = microsoft_ad_exchange;
    }

    @Override
    public String getEntityname() {
        return entityName;
    }

    @Override
    public Form getForm() {
        return null;
    }

    @Override
    public String getUri() {
        return null;
    }

}
