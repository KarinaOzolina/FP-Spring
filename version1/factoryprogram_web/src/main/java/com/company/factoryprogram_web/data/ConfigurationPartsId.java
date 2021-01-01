package com.company.factoryprogram_web.data;


import lombok.Data;

import java.io.Serializable;

@Data
public class ConfigurationPartsId implements Serializable {
    private int id;
    private int configuration;
    private int part;
}
