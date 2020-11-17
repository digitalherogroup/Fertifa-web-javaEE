package com.fertifa.app.models;

import java.util.Objects;

public class FaqCat {
    private int id;
    private String faqCatName;
    private int CategoryFor;

    public FaqCat(int id, String faqCatName) {
        this.id = id;
        this.faqCatName = faqCatName;
    }

    public FaqCat() {
    }

    public FaqCat(String category, int categoryFor) {
        this.faqCatName = category;
        this.CategoryFor = categoryFor;
    }


    public int getCategoryFor() {
        return CategoryFor;
    }

    public void setCategoryFor(int categoryFor) {
        CategoryFor = categoryFor;
    }

    public FaqCat(String faqCatName) {
        this.faqCatName = faqCatName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFaqCatName() {
        return faqCatName;
    }

    public void setFaqCatName(String faqCatName) {
        this.faqCatName = faqCatName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FaqCat faqCat = (FaqCat) o;
        return id == faqCat.id &&
                Objects.equals(faqCatName, faqCat.faqCatName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, faqCatName);
    }
}
