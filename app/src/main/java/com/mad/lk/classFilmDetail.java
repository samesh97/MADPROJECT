package com.mad.lk;

public class classFilmDetail {

    private int film_id;
    private String film_name;
    private String role_1;
    private String role_2;
    private String role_3;
    private String role_4;
    private String director_name;


    public classFilmDetail(int film_id, String film_name, String role_1, String role_2, String role_3, String role_4, String director_name) {
        this.film_id = film_id;
        this.film_name = film_name;
        this.role_1 = role_1;
        this.role_2 = role_2;
        this.role_3 = role_3;
        this.role_4 = role_4;
        this.director_name = director_name;
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

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public void setRole_1(String role_1) {
        this.role_1 = role_1;
    }

    public void setRole_2(String role_2) {
        this.role_2 = role_2;
    }

    public void setRole_3(String role_3) {
        this.role_3 = role_3;
    }

    public void setRole_4(String role_4) {
        this.role_4 = role_4;
    }

    public void setDirector_name(String director_name) {
        this.director_name = director_name;
    }



}
