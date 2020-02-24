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
import com.joe.commons.app.AppResponse;
import com.joe.entity.IndexCourse;
import com.joe.entity.IndexCourseCate;
import com.joe.entity.IndexCourseTag;
import com.joe.pojo.CourseCate;
import com.joe.pojo.IndexCourseCatePOJO;
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
        // 获取课程
        List<IndexCourse> newCourseList = getNewCourse(null, 8);
        List<IndexCoursePOJO> newIndexCourseList = indexCourseToIndexCoursePOJO(newCourseList);

        // 查询出所有的课程分类
        QueryWrapper<IndexCourseCate> indexCourseCateQueryWrapper = new QueryWrapper<>();
        indexCourseCateQueryWrapper.orderByAsc("course_cate_level", "course_cate_index");
        List<IndexCourseCate> indexCourseCateList = indexCourseCateService.list(indexCourseCateQueryWrapper);

        // 获取课程分类
        List<IndexCourseCatePOJO> indexCourseCatePOJOList = Lists.newArrayList();

        // 循环遍历课程分类，生成前台需要的格式
        for (IndexCourseCate indexCourseCate : indexCourseCateList) {
            if (StringUtils.equals("1", indexCourseCate.getCourseCateLevel())) {
                // 创建课程分类对象
                IndexCourseCatePOJO indexCourseCatePOJO = new IndexCourseCatePOJO();
                // 子课程分类列表
                List<IndexCourseCate> adminChildCourseCateList = Lists.newArrayList();

                List<String> childCateNoList = Lists.newArrayList();

                // 循环组织子课程分类
                for (IndexCourseCate adminChildCourseCate : indexCourseCateList) {
                    if (StringUtils.equals(indexCourseCate.getCourseCateNo(), adminChildCourseCate.getParentCourseCateNo())) {
                        adminChildCourseCateList.add(adminChildCourseCate);
                        childCateNoList.add(indexCourseCate.getCourseCateNo());
                    }
                }


                // 存储父课程分类
                indexCourseCatePOJO.setIndexCourseCate(indexCourseCate);
                // 存储子课程分类
                indexCourseCatePOJO.setChildCourseCateList(adminChildCourseCateList);

                // 存储推荐课程
                if (!childCateNoList.isEmpty()) {
                    // 查询父分类的推荐课程
                    QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
                    indexCourseQueryWrapper.in("course_cate_no", childCateNoList).orderByDesc("visit_times").last("limit 0, 4");
                    List<IndexCourse> indexCourseList = indexCourseService.list(indexCourseQueryWrapper);

                    indexCourseCatePOJO.setPresentCourseList(indexCourseList);
                }

                // 存储课程分类
                indexCourseCatePOJOList.add(indexCourseCatePOJO);
            }
        }

        model.addAttribute("newIndexCourseList", newIndexCourseList);
        model.addAttribute("courseCateList", indexCourseCatePOJOList);

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
