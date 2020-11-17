package com.fertifa.app.models;

import java.util.Objects;

public class Packages {

    private int id;
    private String packagName;

    public Packages(String packagName) {
        this.packagName = packagName;
    }

    public Packages() {
    }

    public Packages(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPackagName() {
        return packagName;
    }

    public void setPackagName(String packagName) {
        this.packagName = packagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Packages packages = (Packages) o;
        return id == packages.id &&
                Objects.equals(packagName, packages.packagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, packagName);
    }
}
