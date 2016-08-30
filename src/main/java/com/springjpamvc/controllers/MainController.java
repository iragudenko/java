package com.springjpamvc.controllers;

import com.springjpamvc.entities.AdvList;
import com.springjpamvc.entities.Advertisement;
import com.springjpamvc.entities.Photo;
import com.springjpamvc.repositories.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


@Controller
public class MainController {// part controller

    @Autowired//Spring сам найдет нужный beanи подставит его значение в свойство, которое отмечено аннотацией
    private JpaRepository jpaRepository;

     @RequestMapping({"/","home"})
     public ModelAndView info(){
        return new ModelAndView("info", "advs", jpaRepository.list());
    }



    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam(value="pattern") String pattern) {
        return new ModelAndView("info", "advs", jpaRepository.list(pattern));
    }

    @RequestMapping(value = "/add_page")
    public ModelAndView addPage() {
        return new ModelAndView("add_page");
    }

    @RequestMapping(value = "/tray")
    public ModelAndView tray() {
        return new ModelAndView("tray", "advs", jpaRepository.listInTray());
    }


    @RequestMapping(value = "/add_page", method = RequestMethod.POST)
    public ModelAndView addPageGet() {
        return new ModelAndView("add_page");
    }

    @RequestMapping("/image/{file_id}")
    public void getFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("file_id") long fileId) {
        try {
            byte[] content = jpaRepository.getPhoto(fileId);
            response.setContentType("image/png");
            response.getOutputStream().write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addAdv(@RequestParam(value="name") String name,
                               @RequestParam(value="soname") String soname,
                               @RequestParam(value="gender") String gender,
                               @RequestParam(value="birthday") String birthday,
                               @RequestParam(value="age") int age,
                               @RequestParam(value="phone") String phone,
                               @RequestParam(value="photo") MultipartFile photo,
                               HttpServletRequest request,
                               HttpServletResponse response)
    {
        try {
            Advertisement adv = new Advertisement(
                    name,  soname, birthday, gender, age,phone,
                    photo.isEmpty() ? null : new Photo(photo.getOriginalFilename(), photo.getBytes()));
            jpaRepository.add(adv);
            return new ModelAndView("info", "advs", jpaRepository.list());
        } catch (IOException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
    }

    @RequestMapping(value = "/process_checked", method = RequestMethod.POST)
    public ModelAndView processChecked(HttpServletRequest request)
    {
        String[] str= request.getParameterValues("selectrow[]");
        String whatToDo  = request.getParameter("submit");
        if (str!= null)
            for(String idstr : str){
                if (whatToDo.equals("delete")){
                    jpaRepository.delete(Long.valueOf(idstr));
                }
                else if (whatToDo.equals("restore")){
                    Advertisement adv = jpaRepository.getAdv(Long.valueOf(idstr));
                    adv.setTo_del(false);
                    jpaRepository.merge(adv);
                }
            }
        return new ModelAndView("tray", "advs", jpaRepository.listInTray());

    }

    @RequestMapping(value = "/confirm_edit", method = RequestMethod.POST, produces={"text/html;charset=UTF-8"})
    public ModelAndView editAdv(@RequestParam(value="id") Long id,
                               @RequestParam(value="photo_id") Long photo_id,
                               @RequestParam(value="name") String name,
                               @RequestParam(value="soname") String soname,
                               @RequestParam(value="gender") String gender,
                               @RequestParam(value="birthday") String birthday,
                               @RequestParam(value="age") int age,
                               @RequestParam(value="phone") String phone,
                               @RequestParam(value="photo") MultipartFile photo,
                               HttpServletRequest request,
                               HttpServletResponse response)
    {
            Advertisement adv = jpaRepository.getAdv(id);
            Photo photoEntity = null;
            if (photo_id == null && !photo.isEmpty()) {
                try {
                    photoEntity = new Photo(photo.getOriginalFilename(), photo.getBytes());
                    adv.setPhoto(photoEntity);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (photo_id != null && !photo.isEmpty()) {
                photoEntity = jpaRepository.getPhotoEntity(photo_id);
                try {
                    photoEntity.setBody(photo.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                photoEntity.setName(photo.getOriginalFilename());
                adv.setPhoto(photoEntity);
            }

        adv.setName(name);

        adv.setSoname(soname);
        adv.setGender(gender);
        adv.setBirthday(birthday);
        adv.setAge(age);
        adv.setPhone(phone);


            jpaRepository.merge(adv);
            return new ModelAndView("info", "advs", jpaRepository.list());
    }

    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam(value="id") long id) {
        jpaRepository.delete(id);
        return new ModelAndView("tray", "advs", jpaRepository.listInTray());
    }

    @RequestMapping("/move_to_tray")
     public ModelAndView moveToTray(@RequestParam(value="id") long id) {
        Advertisement adv = jpaRepository.getAdv(id);
        adv.setTo_del(true);
        jpaRepository.merge(adv);
        return new ModelAndView("info", "advs", jpaRepository.list());
    }

    @RequestMapping("/restore")
    public ModelAndView restore(@RequestParam(value="id") long id) {
        Advertisement adv = jpaRepository.getAdv(id);
        adv.setTo_del(false);
        jpaRepository.merge(adv);
        return new ModelAndView("tray", "advs", jpaRepository.listInTray());
    }


    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value="id") long id) {
        return new ModelAndView("edit", "adv", jpaRepository.getAdv(id));
    }

    @RequestMapping("/import_xml")
    public ModelAndView importXml(@RequestParam(value = "xmlfile") MultipartFile mfile) {
        InputStream inputStream = null;
        Reader reader = null;
        try {
            inputStream = mfile.getInputStream();
            reader = new InputStreamReader(inputStream, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JAXBContext jaxbContext = null;
        Unmarshaller unmarshaller = null;
        AdvList advList = null;
        try {
            jaxbContext = JAXBContext.newInstance(AdvList.class);
            unmarshaller = jaxbContext.createUnmarshaller();
            advList = (AdvList) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        for (Advertisement adv : advList.getAdvList()) {
            jpaRepository.add(adv);
        }

        return new ModelAndView("info", "advs", jpaRepository.list());
    }


    @RequestMapping("/*")
    public ModelAndView unsecure(){
        ModelAndView modelAndView = new ModelAndView("unsecure");
        return modelAndView;
    }
}
