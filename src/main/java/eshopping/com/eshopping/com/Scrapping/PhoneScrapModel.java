package eshopping.com.eshopping.com.Scrapping;

import java.util.ArrayList;

public class PhoneScrapModel {
    public String BODY;
    public String LAUNCH;
    public String PLATFORM;

    public String BATTERY;
    public  String NETWORK;
    public String SELFIE_CAMERA;
    public String MAIN_CAMERA;
    public String MISC;
    public String FEATURES;
    public String DISPLAY;
    public String COMMS;
    public  String MEMORY;
    public String SOUND;


    public String getBODY() {
        return BODY;
    }

    public void setBODY(String BODY) {
        this.BODY = BODY;
    }

    public String getLAUNCH() {
        return LAUNCH;
    }

    public void setLAUNCH(String LAUNCH) {
        this.LAUNCH = LAUNCH;
    }

    public String getPLATFORM() {
        return PLATFORM;
    }

    public void setPLATFORM(String PLATFORM) {
        this.PLATFORM = PLATFORM;
    }

    public String getBATTERY() {
        return BATTERY;
    }

    public void setBATTERY(String BATTERY) {
        this.BATTERY = BATTERY;
    }

    public String getNETWORK() {
        return NETWORK;
    }

    public void setNETWORK(String NETWORK) {
        this.NETWORK = NETWORK;
    }

    public String getSELFIE_CAMERA() {
        return SELFIE_CAMERA;
    }

    public void setSELFIE_CAMERA(String SELFIE_CAMERA) {
        this.SELFIE_CAMERA = SELFIE_CAMERA;
    }

    public String getMAIN_CAMERA() {
        return MAIN_CAMERA;
    }

    public void setMAIN_CAMERA(String MAIN_CAMERA) {
        this.MAIN_CAMERA = MAIN_CAMERA;
    }

    public String getMISC() {
        return MISC;
    }

    public void setMISC(String MISC) {
        this.MISC = MISC;
    }

    public String getFEATURES() {
        return FEATURES;
    }

    public void setFEATURES(String FEATURES) {
        this.FEATURES = FEATURES;
    }

    public String getDISPLAY() {
        return DISPLAY;
    }

    public void setDISPLAY(String DISPLAY) {
        this.DISPLAY = DISPLAY;
    }

    public String getCOMMS() {
        return COMMS;
    }

    public void setCOMMS(String COMMS) {
        this.COMMS = COMMS;
    }

    public String getMEMORY() {
        return MEMORY;
    }

    public void setMEMORY(String MEMORY) {
        this.MEMORY = MEMORY;
    }

    public String getSOUND() {
        return SOUND;
    }

    public void setSOUND(String SOUND) {
        this.SOUND = SOUND;
    }





    @Override
    public String toString() {
        return "Phone{" +
                "BODY='" + BODY + '\'' +
                ", LAUNCH='" + LAUNCH + '\'' +
                ", PLATFORM='" + PLATFORM + '\'' +
                ", BATTERY='" + BATTERY + '\'' +
                ", NETWORK='" + NETWORK + '\'' +
                ", SELFIE_CAMERA='" + SELFIE_CAMERA + '\'' +
                ", MAIN_CAMERA='" + MAIN_CAMERA + '\'' +
                ", MISC='" + MISC + '\'' +
                ", FEATURES='" + FEATURES + '\'' +
                ", DISPLAY='" + DISPLAY + '\'' +
                ", COMMS='" + COMMS + '\'' +
                ", MEMORY='" + MEMORY + '\'' +
                ", SOUND='" + SOUND + '\'' +

                '}';
    }

    public  PhoneScrapModel(String BODY, String LAUNCH, String PLATFORM, String BATTERY, String NETWORK, String SELFIE_CAMERA, String MAIN_CAMERA, String MISC, String FEATURES, String DISPLAY, String COMMS, String MEMORY, String SOUND ) {
        this.BODY = BODY;
        this.LAUNCH = LAUNCH;
        this.PLATFORM = PLATFORM;
        this.BATTERY = BATTERY;
        this.NETWORK = NETWORK;
        this.SELFIE_CAMERA = SELFIE_CAMERA;
        this.MAIN_CAMERA = MAIN_CAMERA;
        this.MISC = MISC;
        this.FEATURES = FEATURES;
        this.DISPLAY = DISPLAY;
        this.COMMS = COMMS;
        this.MEMORY = MEMORY;
        this.SOUND = SOUND;

    }
}
