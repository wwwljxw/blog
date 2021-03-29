package com.ljx.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljx.blog.entity.Type;
import com.ljx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @Description 分类管理控制器
 * @author Lin
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;


    /**
     * @Description 分页查询分类列表
     */
    @GetMapping("/types")
    public String getType(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model){
        PageHelper.startPage(pageNum, 10);
        List<Type> allType = typeService.getAllType();
        //得到分页结果对象
        PageInfo<Type> pageInfo = new PageInfo<>(allType);

        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    /**
     * @Description 跳转到修改分类页面
     */
    @GetMapping("/types/{id}/input")
    public String getUpdateType(Model model, @PathVariable long id){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types/{id}")
    public String updateType(Type type, RedirectAttributes attributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName != null){
            attributes.addFlashAttribute("message","该分类未修改或已存在，不能重复添加");
            return "redirect:/admin/types/input";
        }

        int i = typeService.updateType(type);
        if (i == 0){
            attributes.addFlashAttribute("message","编辑失败");
        }else {
            attributes.addFlashAttribute("message","编辑成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * @Description 跳转到新增分类页面
     */
    @GetMapping("/types/input")
    public String getAddType(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String addType(Type type,RedirectAttributes attributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName != null){
            attributes.addFlashAttribute("message","该分类已存在，不能重复添加");
            return "redirect:/admin/types/input";
        }

        int i = typeService.saveType(type);
        if (i == 0){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
