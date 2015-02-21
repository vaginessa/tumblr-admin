package com.donkeigy.objects.util;

/**
 * Created by cedric on 2/20/15.
 */
public class ApplicationInfo
{
    private String apiKey;
    private String apiSecret;

    public ApplicationInfo(String apiKey, String apiSecret)
    {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }
}
