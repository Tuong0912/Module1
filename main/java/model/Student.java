package model;


public class Student {
   private int id;
    private String names;
    private int points;
    private Classes aClass;

    public Student(String names, int points, Classes aClass) {
        this.names = names;
        this.points = points;
        this.aClass = aClass;
    }

    public Student(int id, String names, int points, Classes aClass) {
        this.id = id;
        this.names = names;
        this.points = points;
        this.aClass = aClass;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Classes aClass() {
        return aClass;
    }

    public void setaClass(Classes aClass) {
        this.aClass = aClass;
    }

    public Student() {

    }

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String names() {
        return names;
    }

    public void setName(String names) {
        this.names = names;
    }

    public int points() {
        return points;
    }

    public void setPoint(int points) {
        this.points = points;
    }
}
