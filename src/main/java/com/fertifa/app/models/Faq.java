package com.fertifa.app.models;

import java.util.Objects;

public class Faq {
    private int id;
    private int faqCatId;
    private String faqQuestion;
    private String faqAnswear;
    private int CategoryFor;

    public Faq(int faqCatId, String faqQuestion, String faqAnswear) {
        this.faqCatId = faqCatId;
        this.faqQuestion = faqQuestion;
        this.faqAnswear = faqAnswear;
    }

    public Faq(String faqQuestion, String faqAnswear) {
        this.faqQuestion = faqQuestion;
        this.faqAnswear = faqAnswear;
    }

    public Faq() {
    }

    public Faq(int faqCatId, String faqQuestion, String faqAnswear, int categoryFor) {
        this.faqCatId = faqCatId;
        this.faqQuestion = faqQuestion;
        this.faqAnswear = faqAnswear;
        this.CategoryFor = categoryFor;

    }

    public int getCategoryFor() {
        return CategoryFor;
    }

    public void setCategoryFor(int categoryFor) {
        CategoryFor = categoryFor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFaqCatId() {
        return faqCatId;
    }

    public void setFaqCatId(int faqCatId) {
        this.faqCatId = faqCatId;
    }

    public String getFaqQuestion() {
        return faqQuestion;
    }

    public void setFaqQuestion(String faqQuestion) {
        this.faqQuestion = faqQuestion;
    }

    public String getFaqAnswear() {
        return faqAnswear;
    }

    public void setFaqAnswear(String faqAnswear) {
        this.faqAnswear = faqAnswear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faq faq = (Faq) o;
        return id == faq.id &&
                faqCatId == faq.faqCatId &&
                Objects.equals(faqQuestion, faq.faqQuestion) &&
                Objects.equals(faqAnswear, faq.faqAnswear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, faqCatId, faqQuestion, faqAnswear);
    }
}
