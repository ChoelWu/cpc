package com.joe.controller.index;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/12/2
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.joe.entity.IndexCourse;
import com.joe.entity.IndexCourseTag;
import com.joe.pojo.IndexCoursePOJO;
import com.joe.service.system.IndexCourseCateService;
import com.joe.service.system.IndexCourseService;
import com.joe.service.system.IndexCourseTagService;
import com.joe.service.system.IndexLessonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/index/course")
public class CourseController {
    @Resource
    private IndexCourseCateService indexCourseCateService;

    @Resource
    private IndexCourseService indexCourseService;

    @Resource
    private IndexLessonService indexLessonService;

    @Resource
    private IndexCourseTagService indexCourseTagService;

    @RequestMapping("/index.do")
    public String index(Model model) {
        List<IndexCourse> newCourseList = getNewCourse(null, 8);

        List<IndexCoursePOJO> newIndexCourseList = indexCourseToIndexCoursePOJO(newCourseList);

        model.addAttribute("newIndexCourseList", newIndexCourseList);

        return "/Index/Course/index";
    }

    /**
     * 获取新课
     *
     * @param cateNo    所属栏目
     * @param courseNum 获取条数
     * @return 返回list
     */
    public List<IndexCourse> getNewCourse(String cateNo, int courseNum) {
        QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(cateNo)) {
            indexCourseQueryWrapper.eq("course_cate_no", cateNo);
        }
        indexCourseQueryWrapper.orderByDesc("publish_time").last("limit " + courseNum);

        return indexCourseService.list(indexCourseQueryWrapper);
    }

    /**
     * 获取热门课
     *
     * @param cateNo    所属栏目
     * @param courseNum 获取条数
     * @return 返回list
     */
    public List<IndexCourse> getHotCourse(String cateNo, int courseNum) {
        QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(cateNo)) {
            indexCourseQueryWrapper.eq("course_cate_no", cateNo);
        }
        indexCourseQueryWrapper.orderByDesc("publish_time").last("limit " + courseNum);

        return indexCourseService.list(indexCourseQueryWrapper);
    }

    @RequestMapping("/detail.do")
    public String courseDetail(String courseNo, Model model) {
        QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
        indexCourseQueryWrapper.eq("course_no", courseNo);
        IndexCourse indexCourse = indexCourseService.getOne(indexCourseQueryWrapper);

        model.addAttribute("indexCourse", indexCourse);
        return "Index/Course/detail";
    }

    /**
     * 组织称前台需要的对象形式
     *
     * @param indexCourseList 输入课程列表
     * @return 返回符合要求的对象格式
     */
    private List<IndexCoursePOJO> indexCourseToIndexCoursePOJO(List<IndexCourse> indexCourseList) {
        List<IndexCoursePOJO> indexCoursePOJOList = Lists.newArrayList();
        for (IndexCourse indexCourse : indexCourseList) {
            IndexCoursePOJO indexCoursePOJO = new IndexCoursePOJO();

            String courseTagNoString = indexCourse.getCourseTags();
            String[] courseTagNoArray = courseTagNoString.split(",");
            QueryWrapper<IndexCourseTag> indexCourseTagQueryWrapper = new QueryWrapper<>();
            indexCourseTagQueryWrapper.in("course_tag_no", courseTagNoArray);
            List<IndexCourseTag> indexCourseTagList = indexCourseTagService.list(indexCourseTagQueryWrapper);

            indexCoursePOJO.setCourse(indexCourse);
            indexCoursePOJO.setCourseTagList(indexCourseTagList);

            indexCoursePOJOList.add(indexCoursePOJO);
        }

        return indexCoursePOJOList;
    }
}
