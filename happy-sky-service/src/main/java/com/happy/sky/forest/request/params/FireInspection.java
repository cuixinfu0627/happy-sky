package com.happy.sky.forest.request.params;


import java.io.Serializable;
import java.util.List;

public class FireInspection implements Serializable{

    private String name;
    private Integer type;
    private Long unitId;
    private String unitName;
    private List<FireInspectionNodes> inspectionNodes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public List<FireInspectionNodes> getInspectionNodes() {
        return inspectionNodes;
    }

    public void setInspectionNodes(List<FireInspectionNodes> inspectionNodes) {
        this.inspectionNodes = inspectionNodes;
    }
}
