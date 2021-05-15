package sample.classes;

import java.util.List;

public class ProcessedJSON {
    List<Array> data;

    public List<Array> getData() {
        return data;
    }

    public void setData(List<Array> data) {
        this.data = data;
    }

    public class Array {
        String authid;
        String name;
        String reason;
        String adminIp;
        String sid;
        String country;
        String RemovedBy;
        String RemoveType;
        String RemovedOn;
        String type;
        String ureason;
        String created;
        String ends;
        String length;
        String aid;
        String ip;
        String bid;

        public String getAuthid() {
            return authid;
        }

        public void setAuthid(String authid) {
            this.authid = authid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getAdminIp() {
            return adminIp;
        }

        public void setAdminIp(String adminIp) {
            this.adminIp = adminIp;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getRemovedBy() {
            return RemovedBy;
        }

        public void setRemovedBy(String removedBy) {
            RemovedBy = removedBy;
        }

        public String getRemoveType() {
            return RemoveType;
        }

        public void setRemoveType(String removeType) {
            RemoveType = removeType;
        }

        public String getRemovedOn() {
            return RemovedOn;
        }

        public void setRemovedOn(String removedOn) {
            RemovedOn = removedOn;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUreason() {
            return ureason;
        }

        public void setUreason(String ureason) {
            this.ureason = ureason;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getEnds() {
            return ends;
        }

        public void setEnds(String ends) {
            this.ends = ends;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

    }
}