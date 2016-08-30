package com.springjpamvc.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Advs")
@XmlRootElement(name = "advertisement")
public class Advertisement {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name")
    private String name;

    @Column(name = "soname")
    private String soname;

    //@DateTimeFormat(pattern = "YYYY-MM-DD")
    @Column(name = "birthday")
    private String birthday;


    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private int age;

    @Column(name = "to_del")
    private Boolean to_del;

    public Boolean isTo_del() {
        return to_del;
    }

    public void setTo_del(Boolean to_del) {
        this.to_del = to_del;
    }

    @Column(name = "phone")
    private String phone;


    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private Photo photo;
    public Advertisement(){}
        public Advertisement(String name, String soname, String birthday, String gender, int age, String phone,Photo photo) {
        this.name = name;
        this.soname = soname;
        this.birthday=birthday;
        this.gender=gender;
        this.age = age;
        this.phone = phone;
        this.photo = photo;
        this.setTo_del(false);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getSoname() {
        return soname;
    }

    @XmlElement(name = "soname")
    public void setSoname(String soname) {this.soname = soname;}


    public String getBirthday(){return birthday;}

    @XmlElement(name = "birthday")
    public void setBirthday(String birthday){this.birthday=birthday;}


  public String getGender() {return gender;}

    @XmlElement(name = "gender")
    public void setGender(String gender) {this.gender = gender;}

    public int getAge() {
        return age;
    }

    @XmlElement(name = "age")
    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    @XmlElement(name = "phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Photo getPhoto() { return photo; }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}

