package com.life.majiang.community.community.service;

import com.life.majiang.community.community.dto.PageDto;
import com.life.majiang.community.community.dto.QuestionDTO;
import com.life.majiang.community.community.mapper.QuestionMapper;
import com.life.majiang.community.community.mapper.UserMapper;
import com.life.majiang.community.community.model.Question;
import com.life.majiang.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    public PageDto list(Integer page, Integer size) {
        PageDto pageDto = new PageDto();
        int totalCount = questionMapper.count();
        pageDto.setPagination(totalCount,page,size);
        if(page<1){
            page=1;
        }
        if(page>pageDto.getTotalPage()){
            page=pageDto.getTotalPage();
        }
        // offset = size*(page-1)
        int offset = size * (page-1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> lists = new ArrayList<>();

        for(Question question:questions){
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            lists.add(questionDTO);
        }

      pageDto.setQuestions(lists);
        return pageDto;
    }
}
