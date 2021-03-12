package com.ljx.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ljx.blog.pojo.TType;
import com.ljx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     *  分页查询分类
     */
    @GetMapping("/types")
    public String types(@RequestParam(value = "pagenum",defaultValue = "1") int pagenum, Model model){
        PageInfo<TType> allByPage = typeService.findAllByPage(pagenum, 5);
        model.addAttribute("pageInfo",allByPage);
        return "admin/types";
    }

    @GetMapping("/types/getInput")
    public String toAddType(Model model){
        //返回一个type对象给前端th:object
        model.addAttribute("type", new TType());
        return "admin/types-input";
    }

    /**
     *  添加一个分类
     */
    @PostMapping("/types/input")
    public String addType(TType tType, RedirectAttributes attributes) {
        TType type = typeService.selectByName(tType.getName());
        if (type != null) {
            attributes.addFlashAttribute("msg", "请勿添加重复数据！");
            return "redirect:/admin/types/getInput";
        } else {
            typeService.insert(tType.getName());
            attributes.addFlashAttribute("msg", "添加成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/types/{id}/input")
    public String toEditType(@PathVariable Long id, Model model){
        model.addAttribute("type", typeService.selectById(id));
        return "admin/types-input";
    }

    /**
     *  编辑分类内容
     */
    @PostMapping("/types/{id}")
    public String updateType(long id, TType tType, RedirectAttributes attributes){
        int i = typeService.updateById(tType.getName(), id);
            if (i == 1){
                attributes.addFlashAttribute("msg","修改成功");
            }else {
                attributes.addFlashAttribute("msg","修改失败");
            }
            return "redirect:/admin/types";
    }

    /**
     *  删除分类
     */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteById(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/types";
    }
}
