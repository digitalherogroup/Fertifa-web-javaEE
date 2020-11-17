package com.fertifa.app.affiliate.builder;

import java.sql.Timestamp;

public class AffiliateStatics {

    private final int id;
    private final int affiliateId;
    private final String affiliateEmail;
    private final int clickId;
    private final int registerId;
    private final int fiveSecondsClick;
    private final int lessFiveSecondsClick;
    private final Timestamp dateOfStatic;
    private final String ip;

    public AffiliateStatics(StaticBuilder staticbuilder) {
        this.id = staticbuilder.id;
        this.affiliateId = staticbuilder.affiliateId;
        this.affiliateEmail = staticbuilder.affiliateEmail;
        this.clickId = staticbuilder.clickId;
        this.registerId = staticbuilder.registerId;
        this.fiveSecondsClick = staticbuilder.fiveSecondsClick;
        this.lessFiveSecondsClick =staticbuilder.lessFiveSecondsClick;
        this.dateOfStatic = staticbuilder.dateOfStatic;
        this.ip = staticbuilder.ip;
    }

    public int getId() {
        return id;
    }

    public String getAffiliateEmail() {
        return affiliateEmail;
    }

    public String getIp() {
        return ip;
    }

    public int getAffiliateId() {
        return affiliateId;
    }

    public int getClickId() {
        return clickId;
    }

    public int getRegisterId() {
        return registerId;
    }

    public Timestamp getDateOfStatic() {
        return dateOfStatic;
    }

    public int getFiveSecondsClick() {
        return fiveSecondsClick;
    }

    public int getLessFiveSecondsClick() {
        return lessFiveSecondsClick;
    }

    public static class StaticBuilder {
        private int id;
        private int affiliateId;
        private String affiliateEmail;
        private int clickId;
        private int registerId;
        private int fiveSecondsClick;
        private int lessFiveSecondsClick;
        private Timestamp dateOfStatic;
        private String ip;

        public StaticBuilder id(int id) {
            this.id = id;
            return this;
        }

        public StaticBuilder affiliateId(int affiliateId) {
            this.affiliateId = affiliateId;
            return this;
        }

        public StaticBuilder affiliateEmail(String affiliateEmail) {
            this.affiliateEmail = affiliateEmail;
            return this;
        }

        public StaticBuilder lessFiveSecondsClick(int lessFiveSecondsClick){
            this.lessFiveSecondsClick = lessFiveSecondsClick;
            return this;
        }

        public StaticBuilder clickId(int clickId) {
            this.clickId = clickId;
            return this;
        }

        public StaticBuilder registerId(int registerId) {
            this.registerId = registerId;
            return this;
        }

        public StaticBuilder dateOfStatic(Timestamp dateOfStatic) {
            this.dateOfStatic = dateOfStatic;
            return this;
        }

        public StaticBuilder fiveSecondsClick(int fiveSecondsClick) {
            this.fiveSecondsClick = fiveSecondsClick;
            return this;
        }

        public StaticBuilder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public AffiliateStatics build() {
            return new AffiliateStatics(this);
        }
    }
}


