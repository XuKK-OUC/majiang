package com.life.majiang.community.community.service;

import com.life.majiang.community.community.dto.PageDto;
import com.life.majiang.community.community.dto.QuestionDTO;
import com.life.majiang.community.community.exception.CustomizeErrorCode;
import com.life.majiang.community.community.exception.CustomizeException;
import com.life.majiang.community.community.mapper.QuestionExtMapper;
import com.life.majiang.community.community.mapper.QuestionMapper;
import com.life.majiang.community.community.mapper.UserMapper;
import com.life.majiang.community.community.model.Question;
import com.life.majiang.community.community.model.QuestionExample;
import com.life.majiang.community.community.model.User;
import org.apache.ibatis.session.RowBounds;
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
    @Autowired
    private QuestionExtMapper questionExtMapper;
    public PageDto list(Integer page, Integer size) {
        PageDto pageDto = new PageDto();
        int totalCount =(int) questionMapper.countByExample(new QuestionExample());
        pageDto.setPagination(totalCount,page,size);
        if(page<1){
            page=1;
        }
        if(page>pageDto.getTotalPage()){
            page=pageDto.getTotalPage();
        }
        // offset = size*(page-1)
        int offset = size * (page-1);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset,size));
        List<QuestionDTO> lists = new ArrayList<>();

        for(Question question:questions){
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            lists.add(questionDTO);
        }

      pageDto.setQuestions(lists);
        return pageDto;
    }

    public PageDto listByUserId(int userid, Integer page, Integer size) {
        PageDto pageDto = new PageDto();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userid);
        int totalCount = (int)questionMapper.countByExample(questionExample);
        pageDto.setPagination(totalCount,page,size);
        if(page<1){
            page=1;
        }
        if(page>pageDto.getTotalPage()){
            page=pageDto.getTotalPage();
        }
        // offset = size*(page-1)
        int offset = size * (page-1);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userid);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDTO> lists = new ArrayList<>();

        for(Question question:questions){
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            lists.add(questionDTO);
        }

        pageDto.setQuestions(lists);
        return pageDto;
    }

    public QuestionDTO getById(int id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question==null){
            throw new CustomizeException(CustomizeErrorCode.Question_Not_Found);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return  questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            //第一次创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModify(question.getGmtCreate());
            question.setCommentCount(0);
            question.setViewCount(0);
            question.setLikeCount(0);
            questionMapper.insert(question);
        }
        else{
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModify(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(updateQuestion, example);
        }

    }

    public void incView(int id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
