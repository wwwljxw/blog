package com.ljx.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljx.blog.entity.Picture;

import com.ljx.blog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author Lin
 */
@Controller
@RequestMapping("/admin")
public class PictureController {

    @Autowired
    private PictureService pictureService;


    @GetMapping("/pictures")
    public String getPicture(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model){
        PageHelper.startPage(pageNum, 10);
        List<Picture> allLink = pictureService.listPicture();
        //得到分页结果对象
        PageInfo<Picture> pageInfo = new PageInfo<>(allLink);

        model.addAttribute("pageInfo",pageInfo);
        return "admin/pictures";
    }

    @GetMapping("/pictures/{id}/input")
    public String getUpdatePicture(Model model, @PathVariable long id){
        model.addAttribute("picture", pictureService.getPicture(id));
        return "admin/pictures-input";
    }

    @PostMapping("/pictures/{id}")
    public String updatePicture(Picture picture, RedirectAttributes attributes){
        int i = pictureService.updatePicture(picture);
        if (i == 0){
            attributes.addFlashAttribute("message","编辑失败");
        }else {
            attributes.addFlashAttribute("message","编辑成功");
        }
        return "redirect:/admin/pictures";
    }


    @GetMapping("/pictures/input")
    public String getAddPicture(Model model){
        model.addAttribute("picture",new Picture());
        return "admin/pictures-input";
    }

    @PostMapping("/pictures")
    public String addPicture(Picture picture, BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()){
            return "admin/pictures-input";
        }

        int i = pictureService.savePicture(picture);
        if (i == 0){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/pictures";
    }

    @GetMapping("/pictures/{id}/delete")
    public String deletePicture(@PathVariable long id, RedirectAttributes attributes){
        pictureService.deletePicture(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/pictures";
    }
}
