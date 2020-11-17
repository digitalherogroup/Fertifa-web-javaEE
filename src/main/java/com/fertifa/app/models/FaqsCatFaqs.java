package com.fertifa.app.models;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class FaqsCatFaqs {
    int faqId;
    int CategoryId;
    String CategoryName;
    String faqquestion;
    String faqanswear;

    public int getFaqId() {
        return faqId;
    }

    public void setFaqId(int faqId) {
        this.faqId = faqId;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getFaqquestion() {
        return faqquestion;
    }

    public void setFaqquestion(String faqquestion) {
        this.faqquestion = faqquestion;
    }

    public String getFaqanswear() {
        return faqanswear;
    }

    public void setFaqanswear(String faqanswear) {
        this.faqanswear = faqanswear;
    }
}
