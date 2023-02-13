package model;

public class Classes{
   private int id;
   private String className;
   private String schoolName;

    public Classes() {
    }

    public Classes(String className, String schoolName) {
        this.className = className;
        this.schoolName = schoolName;
    }

    public Classes(int id, String className, String schoolName) {
        this.id = id;
        this.className = className;
        this.schoolName = schoolName;
    }

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String className() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String schoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
