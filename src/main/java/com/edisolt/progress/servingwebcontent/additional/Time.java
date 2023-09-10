package com.edisolt.progress.servingwebcontent.additional;

public class Time {
    public static String formatDuration(long durationMillis) {
        long milliseconds = durationMillis % 1000;
        long seconds = (durationMillis / 1000) % 60;
        long minutes = (durationMillis / (1000 * 60)) % 60;
        long hours = (durationMillis / (1000 * 60 * 60));

        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds);
    }
}
