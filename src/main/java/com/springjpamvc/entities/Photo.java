package com.springjpamvc.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name="Photos")
public class Photo {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "photo_name")
    private String name;

    @Lob
    @Column(name = "photo_body")
    private byte body[];


    public Photo() {
    }

    public Photo(String name, byte[] body) {
        this.name = name;
        this.body = body;
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

    @XmlElement(name = "photo_name")
    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBody() {
        return body;
    }

    @XmlElement(name = "photo_body")
    public void setBody(byte[] body) {
        this.body = body;
    }
}
