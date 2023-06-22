package com.epam.esm.controller;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import com.epam.esm.models.Gift;
import com.epam.esm.models.Tag;
import com.epam.esm.dao.TagDao;

@Controller
public class HomeController {

    @Autowired
    private final TagDao tagDao;

    public HomeController(TagDao tagDao) {
	this.tagDao = tagDao;
    }

    @RequestMapping(value="/")
    public ModelAndView test(HttpServletResponse response) throws IOException{
	return new ModelAndView("home");
    }

    @RequestMapping(value = "/gift",
		    method = RequestMethod.GET,
		    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Gift getGift(HttpServletResponse response) throws IOException{
	Gift gift = new Gift();
	gift.setId(1);
	gift.setName("My gift");
	gift.setPrice(new BigDecimal(12.34));
	gift.setDuration(0);
	Date date = new Date();
	gift.setCreateDate(date);
	gift.setLastUpdateDate(date);
	return gift;
    }

    @RequestMapping(value="/gift",
		    method=RequestMethod.POST)
    public @ResponseBody Gift postGift(@RequestBody Gift gift) throws IOException{
	gift.setDuration(gift.getDuration() + 2);
	return gift;
    }

    @RequestMapping(value="/tag",
		    method=RequestMethod.POST)
    public @ResponseBody Tag postGift(@RequestBody Tag tag) throws IOException{
	tagDao.insert(tag);
	return tag;
    }
}
