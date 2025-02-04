package task06.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import task06.pojo.Category;
import task06.service.CategoryServiceImpl;
import task06.util.Page;

import java.util.List;

/**
 * @author Administrator
 */
@Controller
public class CategoryController {
	final
	CategoryServiceImpl categoryService;

	@Autowired
	public CategoryController(CategoryServiceImpl categoryService) {
		this.categoryService = categoryService;
	}

	//获取数据库的所有信息
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public ModelAndView listCategory(Page page) {
		System.out.println("page:" + page.toString());
		System.out.println("GET    category");
		ModelAndView mav = new ModelAndView();
		List<Category> cs = categoryService.list(page);
		int total = categoryService.total();
		page.caculateLast(total);
		// 放入转发参数
		mav.addObject("cs", cs);
		// 放入jsp路径
		mav.setViewName("listCategory");
		return mav;
	}

	@RequestMapping(value = "/category2" ,method = RequestMethod.GET)
	@ResponseBody
	public Object listCategory1(Page page) {
		List<Category> cs = categoryService.list(page);
		int total = categoryService.total();
		page.caculateLast(total);
		return cs;
	}




	//跳转至 JSON`格式的页面
	@RequestMapping(value = "/categoryJ", method = RequestMethod.GET)
	public String listCategoryJ(Model model) {
		System.out.println(" controller 开始写入");

		List<Category> categoryListJ = categoryService.list();
		model.addAttribute("categoryListJ", categoryListJ);
		return "listCategoryJ";
	}

	//增加信息
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public ModelAndView addCategory(Category category) {
		System.out.println("POST    category");
		System.out.println("category.getName():" + category.getName());
		categoryService.add(category);
		ModelAndView mav = new ModelAndView("redirect:/category");
		return mav;
	}

	//根据ID删除相应的信息
	@RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteCategory(Category category) {
		System.out.println("DELETE/id    category");
		categoryService.delete(category);
		ModelAndView mav = new ModelAndView("redirect:/category");
		return mav;
	}

	//根据ID获取相应的信息
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public ModelAndView editCategory(Category category) {
		System.out.println("GET/id    category");
//        System.out.println (id);
		Category c = categoryService.get(category.getId());
		ModelAndView mav = new ModelAndView("editCategory");
		mav.addObject("c", c);
		return mav;
	}

	//根据ID更新相应的信息
	@RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
	public ModelAndView updateCategory(Category category) {
		System.out.println("PUT/id    category");
		System.out.println("category\t" + category);
		categoryService.update(category);
		System.out.println("category.name" + category.getName());
		ModelAndView mav = new ModelAndView("redirect:/category");
		return mav;
	}

}
