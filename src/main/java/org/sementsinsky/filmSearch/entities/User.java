package org.sementsinsky.filmSearch.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User implements IEntity<Integer>{
    @Id
    @GenericGenerator(
            name = "user_id_seq",
            strategy = "sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "sequence",
                            value = "user_id_seq"
                    )
            })
    @GeneratedValue(generator = "user_id_seq")
    @Column(name="id")
    private Integer id;

    @Column(name="user_name", unique = true, nullable = false)
    private String username;

    @Column(name="password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "hold_over",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="film_id")})
    private Set<Film> holdOver = new HashSet<>();

    public User(){ }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public Set<Film> getHoldOver(){
        return holdOver;
    }

    public void setHoldOver(Set<Film> holdOver) {
        this.holdOver = holdOver;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setPasword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void addHoldOver(Film film){
        holdOver.add(film);
    }

    public void delHoldOver(Film film){
        holdOver.remove(film);
    }

    public boolean equals(User user){
        return this.toString().equals(user.toString());
    }

    @Override
    public String toString(){
        return  "{username='"+username+'\''+
                ",password='"+password+"'}";
    }
}