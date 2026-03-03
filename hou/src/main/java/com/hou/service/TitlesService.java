package com.hou.service;

import com.hou.entity.Titles;
import com.hou.mapper.TitlesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class TitlesService  {
    @Autowired
    private TitlesMapper titlesMapper;

    public List<Titles> getRandomTitlesByCategories(int countPerCategory) {
        List<Titles> titles = new ArrayList<>();
        String[] categories = {"日常生活", "学习起步", "古代文学", "商务通勤", "影视模块"};
        for (String category : categories) {
            List<Titles> categoryTitles = titlesMapper.findByBelong(category);
            if (categoryTitles.size() >= countPerCategory) {
                titles.addAll(categoryTitles.subList(0, countPerCategory));
            } else {
                // 如果某个分类的题目少于6个，则添加所有题目并可能需要在前端处理不足的情况
                titles.addAll(categoryTitles);
            }
        }
        // 如果需要随机化整个列表（而不仅仅是每个分类内的随机），可以在这里添加代码来打乱列表顺序
         Collections.shuffle(titles);
        return titles;
    }

}




