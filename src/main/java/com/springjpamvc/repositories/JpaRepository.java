package com.springjpamvc.repositories;

import com.springjpamvc.entities.Advertisement;
import com.springjpamvc.entities.Photo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

//JPA предоставляет возможность сохранять в удобном виде Java-объекты в базе данных
//Hibernate—библиотека для языка программирования Java, предназначенная для решения задач объектно-реляционного отображения
@Component
public class JpaRepository {
    @PersistenceContext
    protected EntityManager entityManager;//создает новую запись в БД

    public List<Advertisement> list() {
        Query query = entityManager.createQuery("SELECT a FROM Advertisement a WHERE a.to_del <> 1", Advertisement.class);
        return (List<Advertisement>) query.getResultList();
    }

    public List<Advertisement> listInTray() {
        Query query = entityManager.createQuery("SELECT a FROM Advertisement a WHERE a.to_del = 1", Advertisement.class);
        return (List<Advertisement>) query.getResultList();
    }

    public byte[] getPhoto(long id) {
        try {
            Photo photo = entityManager.find(Photo.class, id);
            return photo.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Photo getPhotoEntity(long id) {
        try {
            Photo photo = entityManager.find(Photo.class, id);
            return photo;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public Advertisement getAdv(long id) {
        try {
            Advertisement adv = entityManager.find(Advertisement.class, id);
            return adv;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public List<Advertisement> list(String pattern) {
        Query query = entityManager.createQuery("SELECT a FROM Advertisement a WHERE a.to_del <> 1 and a.name LIKE :pattern", Advertisement.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return (List<Advertisement>) query.getResultList();
    }


    @Transactional
    public void add(Advertisement adv) {
            adv.setTo_del(false);
            entityManager.persist(adv);
    }

    @Transactional
    public void merge(Advertisement adv) {
        entityManager.merge(adv);
    }
    @Transactional
    public void delete(long id) {
            Advertisement adv = entityManager.find(Advertisement.class, id);
            entityManager.remove(adv);
    }
}
