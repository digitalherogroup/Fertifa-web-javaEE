package com.fertifa.app.models;

import java.util.List;

public class SurveyFedback {

    private List<Question> questionList;

    public SurveyFedback(List<Question> questionList) {
        this.questionList = questionList;
    }

    public SurveyFedback() {
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public String toString() {
        return "SurveyFedback{" +
                "questionList=" + questionList +
                '}';
    }

    public static class Question {
        private int questionId;
        private String question;
        private int yes;
        private int no;
        private int maybe;



        public Question(String feedBackQuestions, int yesAnswear, int noAnswears, int maybeAnswears) {
            this.question=feedBackQuestions;
            this.yes=yesAnswear;
            this.no=noAnswears;
            this.maybe=maybeAnswears;
        }

        public Question(int questionId, String question, int yes, int no, int maybe) {
            this.questionId = questionId;
            this.question = question;
            this.yes = yes;
            this.no = no;
            this.maybe = maybe;
        }

        public int getQuestionId() {
            return questionId;
        }

        public void setQuestionId(int questionId) {
            this.questionId = questionId;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public int getYes() {
            return yes;
        }

        public void setYes(int yes) {
            this.yes = yes;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public int getMaybe() {
            return maybe;
        }

        public void setMaybe(int maybe) {
            this.maybe = maybe;
        }

        @Override
        public String toString() {
            return "Question{" +
                    "questionId=" + questionId +
                    ", question='" + question + '\'' +
                    ", yes=" + yes +
                    ", no=" + no +
                    ", maybe=" + maybe +
                    '}';
        }
    }
}
