package com.mad.lk;

public class classFilmDetail {

    private int film_id;
    private String film_name;
    private String role_1;
    private String role_2;
    private String role_3;
    private String role_4;
    private String director_name;
    private byte photo1;
    private byte photo2;


    public classFilmDetail(int film_id, String film_name, String role_1, String role_2, String role_3, String role_4, String director_name, byte photo1, byte photo2) {
        this.film_id = film_id;
        this.film_name = film_name;
        this.role_1 = role_1;
        this.role_2 = role_2;
        this.role_3 = role_3;
        this.role_4 = role_4;
        this.director_name = director_name;
        this.photo1 = photo1;
        this.photo2 = photo2;
    }

    public int getFilm_id() {
        return film_id;
    }

    public String getFilm_name() {
        return film_name;
    }

    public String getRole_1() {
        return role_1;
    }

    public String getRole_2() {
        return role_2;
    }

    public String getRole_3() {
        return role_3;
    }

    public String getRole_4() {
        return role_4;
    }

    public String getDirector_name() {
        return director_name;
    }

    public byte getPhoto1() {
        return photo1;
    }

    public byte getPhoto2() {
        return photo2;
    }
}
