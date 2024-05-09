package com.plum.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * ClassName: UserAuthProperties
 * Package: properties
 * description
 * Author: Plum
 * Crete : 2024/5/9 22:42
 * Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "spzx.auth")
public class UserAuthProperties {
    private List<String> noAuthUrls;
}
