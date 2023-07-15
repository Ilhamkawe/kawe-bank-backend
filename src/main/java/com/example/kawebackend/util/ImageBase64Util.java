package com.example.kawebackend.util;

public class ImageBase64Util {

    public static String getFileExtension(String imageValue) {
        String[] parts = imageValue.split(",");
        if (parts.length > 0) {
            String header = parts[0];
            if (header.contains("jpeg")) {
                return ".jpg";
            } else if (header.contains("png")) {
                return ".png";
            } else if (header.contains("gif")) {
                return ".gif";
            }
        }
        // Tambahkan tipe file lain sesuai kebutuhan
        return "";
    }

}
