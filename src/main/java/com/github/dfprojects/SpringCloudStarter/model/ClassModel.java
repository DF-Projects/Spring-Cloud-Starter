package com.github.dfprojects.SpringCloudStarter.model;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassModel implements Serializable {
    private static final long serialVersionUID = -2996429647687963286L;
    private String packageName;
    private String className;
    private Map<String, String> attributs;
}