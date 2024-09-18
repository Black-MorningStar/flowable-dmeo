package com.example.flowabledemo.model;

import liquibase.pro.packaged.S;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 君墨笑
 * @date 2024/9/18
 */
@Getter
@Setter
public class ProcessDefineModel {

    private String id;

    private String category;

    private String name;

    private String key;

    private String description;

    private String version;

    private String resourceName;

    private String deploymentId;

    private boolean suspended;
}
