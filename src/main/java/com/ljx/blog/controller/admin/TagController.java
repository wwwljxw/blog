package com.ljx.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ljx.blog.pojo.TTag;
import com.ljx.blog.pojo.TType;
import com.ljx.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author linjx
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 分页查询标签
     */
    @GetMapping("/tags")
    public String tags(@RequestParam(value = "pagenum", defaultValue = "1") int pagenum, Model model) {
        PageInfo<TTag> allByPage = tagService.findAllByPage(pagenum, 5);
        model.addAttribute("pageInfo", allByPage);
        return "admin/tags";
    }

    @GetMapping("/tags/getInput")
    public String toAddTag(Model model) {
        //返回一个tag对象给前端th:object
        model.addAttribute("tag", new TTag());
        return "admin/tags-input";
    }

    /**
     * 添加一个标签
     */
    @PostMapping("/tags/input")
    public String addType(TTag tag, RedirectAttributes attributes) {
        TTag tTag = tagService.selectByName(tag.getName());
        if (tTag != null) {
            attributes.addFlashAttribute("msg", "请勿添加重复数据！");
            return "redirect:/admin/tags/input";
        } else {
            tagService.insert(tag.getName());
            attributes.addFlashAttribute("msg", "添加成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/tags/{id}/input")
    public String toEditTag(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.selectById(id));
        return "admin/tags-input";
    }

    /**
     * 编辑标签内容
     */
    @PostMapping("/tags/{id}")
    public String updateType(long id, TTag tag, RedirectAttributes attributes) {
        int i = tagService.updateById(tag.getName(), id);
        if (i == 1) {
            attributes.addFlashAttribute("msg", "修改成功");
        } else {
            attributes.addFlashAttribute("msg", "修改失败");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 删除标签
     */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteById(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/tags";
    }
}
