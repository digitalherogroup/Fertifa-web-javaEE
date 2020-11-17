package com.fertifa.app.models;

public class NewsCategory {
    private final int id;
    private final String newsCategory;
    private final String description;

    public NewsCategory(NewsCategoryBuilder newsCategoryBuilder) {
        this.id= newsCategoryBuilder.id;
        this.newsCategory= newsCategoryBuilder.newsCategory;
        this.description= newsCategoryBuilder.description;
    }


    public int getId() {
        return id;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    public String getDescription() {
        return description;
    }

    public static class NewsCategoryBuilder{
        private int id;
        private String newsCategory;
        private String description;

        public NewsCategoryBuilder id(int id){
            this.id=id;
            return this;
        }

        public NewsCategoryBuilder newsCategory(String newsCategory){
            this.newsCategory=newsCategory;
            return this;
        }

        public NewsCategoryBuilder description(String description){
            this.description=description;
            return this;
        }

        public NewsCategory build(){
            return new NewsCategory(this);
        }
    }
}
