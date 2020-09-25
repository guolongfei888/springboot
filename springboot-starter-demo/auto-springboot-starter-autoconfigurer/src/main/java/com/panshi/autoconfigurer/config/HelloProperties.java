package com.panshi.autoconfigurer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/***
 * @Auther: guo
 * @Date: 16:10 2020/9/25
 */
@ConfigurationProperties(prefix = "superbeyone.hello")
public class HelloProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
