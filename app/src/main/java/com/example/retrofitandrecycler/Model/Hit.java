package com.example.retrofitandrecycler.Model;

public class Hit {
    private  String previewURL,largeImageURL;

    public Hit(String previewURL, String largeImageURL) {
        this.previewURL = previewURL;
        this.largeImageURL = largeImageURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }
}
