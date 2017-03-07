package com.hpit.entity;

/**
 * Created by Administrator on 2017/2/18 0018.
 */
public class Teacher {
    private String tid;
    private String tname;
    private long tage;
    private String taddress;

    public Teacher() {
    }

    public Teacher(String tname, long tage, String taddress) {
        this.tname = tname;
        this.tage = tage;
        this.taddress = taddress;
    }

    public Teacher(String tid, String tname, long tage, String taddress) {
        this.tid = tid;
        this.tname = tname;
        this.tage = tage;
        this.taddress = taddress;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public long getTage() {
        return tage;
    }

    public void setTage(long tage) {
        this.tage = tage;
    }

    public String getTaddress() {
        return taddress;
    }

    public void setTaddress(String taddress) {
        this.taddress = taddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (tage != teacher.tage) return false;
        if (tid != null ? !tid.equals(teacher.tid) : teacher.tid != null) return false;
        if (tname != null ? !tname.equals(teacher.tname) : teacher.tname != null) return false;
        if (taddress != null ? !taddress.equals(teacher.taddress) : teacher.taddress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tid != null ? tid.hashCode() : 0;
        result = 31 * result + (tname != null ? tname.hashCode() : 0);
        result = 31 * result + (int) (tage ^ (tage >>> 32));
        result = 31 * result + (taddress != null ? taddress.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tid='" + tid + '\'' +
                ", tname='" + tname + '\'' +
                ", tage=" + tage +
                ", taddress='" + taddress + '\'' +
                '}';
    }
}
