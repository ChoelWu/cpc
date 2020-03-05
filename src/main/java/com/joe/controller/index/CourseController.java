package com.joe.controller.index;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/12/2
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 课程平台
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.joe.entity.*;
import com.joe.pojo.ChapterLessonPOJO;
import com.joe.pojo.CourseChapterLessonPOJO;
import com.joe.pojo.IndexCourseCatePOJO;
import com.joe.pojo.IndexCoursePOJO;
import com.joe.service.system.*;
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

    @Resource
    private IndexCourseBannerService indexCourseBannerService;

    @Resource
    private IndexChapterService indexChapterService;

    /**
     * 课程首页
     *
     * @param model model
     * @return 返回页面视图
     */
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

        // 获取推荐位
        List<IndexCourseBanner> indexCourseBannerList = getCourseBannerList(5);

        // 绑定数据
        model.addAttribute("newIndexCourseList", newIndexCourseList);
        model.addAttribute("courseCateList", indexCourseCatePOJOList);
        model.addAttribute("indexCourseBannerList", indexCourseBannerList);

        return "/Index/Course/index";
    }

    /**
     * 课程详情
     *
     * @param courseNo 课程编号
     * @param model    model
     * @return 返回视图
     */
    @RequestMapping("/detail.do")
    public String courseDetail(String courseNo, Model model) {
        CourseChapterLessonPOJO courseChapterLessonPOJO = getCourseInfoList(courseNo);

        // 数据绑定
        model.addAttribute("course", courseChapterLessonPOJO);

        // 返回页面视图
        return "Index/Course/detail";
    }

    /**
     * 课程播放页面
     *
     * @param lessonNo 课程编号
     * @param model    model
     * @return 返回页面视图
     */
    @RequestMapping("video.do")
    public String video(String lessonNo, Model model) {
        // 查询课程信息
        QueryWrapper<IndexLesson> indexLessonQueryWrapper = new QueryWrapper<>();
        indexLessonQueryWrapper.eq("lesson_no", lessonNo);
        IndexLesson indexLesson = indexLessonService.getOne(indexLessonQueryWrapper);

        // 查询课程章节和课时信息
        CourseChapterLessonPOJO courseChapterLessonPOJO = getCourseInfoList(indexLesson.getCourseNo());

        // 绑定数据
        model.addAttribute("courseChapterLesson", courseChapterLessonPOJO);
        model.addAttribute("indexLesson", indexLesson);

        // 返回对应的页面视图
        if (StringUtils.equals(indexLesson.getLessonType(), "")) {
            return "Index/Course/videoPlayer";
        } else if (StringUtils.equals(indexLesson.getLessonType(), "")) {
            return "Index/Course/swfPlayer";
        } else {
            return "Index/Course/swfVideoPlayer";
        }
    }

    /**
     * 获取新课
     *
     * @param cateNo    所属栏目
     * @param courseNum 获取条数
     * @return 返回list
     */
    private List<IndexCourse> getNewCourse(String cateNo, int courseNum) {
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
    private List<IndexCourse> getHotCourse(String cateNo, int courseNum) {
        QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(cateNo)) {
            indexCourseQueryWrapper.eq("course_cate_no", cateNo);
        }
        indexCourseQueryWrapper.orderByDesc("publish_time").last("limit " + courseNum);

        return indexCourseService.list(indexCourseQueryWrapper);
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

    /**
     * 获取推荐位
     *
     * @param num 需要的条数
     * @return 返回结果
     */
    private List<IndexCourseBanner> getCourseBannerList(int num) {
        QueryWrapper<IndexCourseBanner> indexCourseBannerQueryWrapper = new QueryWrapper<>();
        indexCourseBannerQueryWrapper.orderByDesc("course_banner_index").last("limit 0, " + num);

        return indexCourseBannerService.list(indexCourseBannerQueryWrapper);
    }

    /**
     * 获取课程章节课时信息
     *
     * @param courseNo 课程编号
     * @return 返回组装好的数据
     */
    private CourseChapterLessonPOJO getCourseInfoList(String courseNo) {
        // 查询课程
        QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
        indexCourseQueryWrapper.eq("course_no", courseNo);
        IndexCourse indexCourse = indexCourseService.getOne(indexCourseQueryWrapper);

        // 查询课程章节
        QueryWrapper<IndexChapter> indexChapterQueryWrapper = new QueryWrapper<>();
        indexChapterQueryWrapper.eq("course_no", indexCourse.getCourseNo()).orderByAsc("chapter_index");
        List<IndexChapter> indexChapterList = indexChapterService.list(indexChapterQueryWrapper);

        List<ChapterLessonPOJO> chapterLessonPOJOList = Lists.newArrayList();
        // 查询课时
        for (IndexChapter indexChapter : indexChapterList) {
            ChapterLessonPOJO chapterLessonPOJO = new ChapterLessonPOJO();

            QueryWrapper<IndexLesson> indexLessonQueryWrapper = new QueryWrapper<>();
            indexLessonQueryWrapper.eq("chapter_no", indexChapter.getChapterNo()).orderByAsc("lesson_index");
            List<IndexLesson> indexLessonList = indexLessonService.list(indexLessonQueryWrapper);

            // 组装章节课时
            chapterLessonPOJO.setChapter(indexChapter);
            chapterLessonPOJO.setLessonList(indexLessonList);

            chapterLessonPOJOList.add(chapterLessonPOJO);
        }

        // 组装前台格式
        CourseChapterLessonPOJO courseChapterLessonPOJO = new CourseChapterLessonPOJO();
        courseChapterLessonPOJO.setCourse(indexCourse);
        courseChapterLessonPOJO.setChapterList(chapterLessonPOJOList);

        // 返回数据
        return courseChapterLessonPOJO;
    }
}
