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

    public int getAtomic_creative_id() {
        return atomic_creative_id;
    }

    public UUID getOdc_creative_id() {
        return odc_creative_id;
    }

    public void setOdc_creative_id(UUID odc_creative_id) {
        this.odc_creative_id = odc_creative_id;
    }

    public void setAtomic_creative_id(int atomic_creative_id) {
        this.atomic_creative_id = atomic_creative_id;
    }

    public String getNet_status() {
        return net_status;
    }

    public void setNet_status(String net_status) {
        this.net_status = net_status;
    }

    public String getAdx_open_auction() {
        return adx_open_auction;
    }

    public void setAdx_open_auction(String adx_open_auction) {
        this.adx_open_auction = adx_open_auction;
    }

    public String getAdx_deals() {
        return adx_deals;
    }

    public void setAdx_deals(String adx_deals) {
        this.adx_deals = adx_deals;
    }

    public String getMopub_direct() {
        return mopub_direct;
    }

    public void setMopub_direct(String mopub_direct) {
        this.mopub_direct = mopub_direct;
    }

    public String getRight_media_exchange() {
        return right_media_exchange;
    }

    public void setRight_media_exchange(String right_media_exchange) {
        this.right_media_exchange = right_media_exchange;
    }

    public String getApp_nexus() {
        return app_nexus;
    }

    public void setApp_nexus(String app_nexus) {
        this.app_nexus = app_nexus;
    }

    public String getBrightroll_for_display() {
        return brightroll_for_display;
    }

    public void setBrightroll_for_display(String brightroll_for_display) {
        this.brightroll_for_display = brightroll_for_display;
    }

    public String getMicrosoft_ad_exchange() {
        return microsoft_ad_exchange;
    }

    public void setMicrosoft_ad_exchange(String microsoft_ad_exchange) {
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
