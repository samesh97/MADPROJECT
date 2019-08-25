package com.mad.lk;

public class classFilmDetail {

    int filmid;
    String filmname;
    String role1;
    String role2;
    String role3;
    String role4;
    String directorname;


    public classFilmDetail(int filmid, String filmname, String role1, String role2, String role3, String role4, String directorname) {
        this.filmid = filmid;
        this.filmname = filmname;
        this.role1 = role1;
        this.role2 = role2;
        this.role3 = role3;
        this.role4 = role4;
        this.directorname = directorname;
    }

    public classFilmDetail(String filmname, String role1, String role2, String role3, String role4, String directorname) {
        this.filmname = filmname;
        this.role1 = role1;
        this.role2 = role2;
        this.role3 = role3;
        this.role4 = role4;
        this.directorname = directorname;
    }

    public int getFilmid() {
        return filmid;
    }

    public String getFilmname() {
        return filmname;
    }

    public String getRole1() {
        return role1;
    }

    public String getRole2() {
        return role2;
    }

    public String getRole3() {
        return role3;
    }

    public String getRole4() {
        return role4;
    }

    public String getDirectorname() {
        return directorname;
    }
}
