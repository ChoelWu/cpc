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
import com.joe.commons.app.CommonFunctions;
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
import javax.servlet.http.HttpSession;
import java.util.Date;
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

    @Resource
    private IndexLearnLogService indexLearnLogService;

    @Resource
    private IndexUserCourseService indexUserCourseService;

    /**
     * 课程首页
     *
     * @param model model
     * @return 返回页面视图
     */
    @RequestMapping("/index.do")
    public String index(Model model) {
        // 获取最新课程
        List<IndexCourse> newCourseList = getNewCourse(null, 8);
        List<IndexCoursePOJO> newIndexCourseList = indexCourseToIndexCoursePOJO(newCourseList);

        // 获取热门课程
        List<IndexCourse> hotCourseList = getHotCourse(null, 8);
        List<IndexCoursePOJO> hotIndexCourseList = indexCourseToIndexCoursePOJO(hotCourseList);

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
        model.addAttribute("hotIndexCourseList", hotIndexCourseList);
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

        // 获取热门课程
        List<IndexCourse> hotCourseList = getHotCourse(null, 8);
        List<IndexCoursePOJO> hotIndexCourseList = indexCourseToIndexCoursePOJO(hotCourseList);

        // 查询课程栏目
        QueryWrapper<IndexCourseCate> indexCourseCateQueryWrapper = new QueryWrapper<>();
        indexCourseCateQueryWrapper.eq("course_cate_no", courseChapterLessonPOJO.getCourse().getCourseCateNo());
        IndexCourseCate indexCourseCate = indexCourseCateService.getOne(indexCourseCateQueryWrapper);
        // 查询课程父栏目
        QueryWrapper<IndexCourseCate> parentIndexCourseCateQueryWrapper = new QueryWrapper<>();
        parentIndexCourseCateQueryWrapper.eq("course_cate_no", indexCourseCate.getParentCourseCateNo());
        IndexCourseCate parentIndexCourseCate = indexCourseCateService.getOne(parentIndexCourseCateQueryWrapper);

        // 课程标签列表
        QueryWrapper<IndexCourseTag> indexCourseTagQueryWrapper = new QueryWrapper<>();
        indexCourseTagQueryWrapper.orderByDesc("course_tag_use_num").last("limit 0, 8");
        List<IndexCourseTag> indexCourseTagList = indexCourseTagService.list(indexCourseTagQueryWrapper);

        // 数据绑定
        model.addAttribute("course", courseChapterLessonPOJO);
        model.addAttribute("hotIndexCourseList", hotIndexCourseList);
        model.addAttribute("indexCourseCate", indexCourseCate);
        model.addAttribute("parentIndexCourseCate", parentIndexCourseCate);
        model.addAttribute("indexCourseTagList", indexCourseTagList);

        // 返回页面视图
        return "Index/Course/detail";
    }

    /**
     * 课程播放页面
     *
     * @param lessonNo 课程编号
     * @param model    model
     * @param session  session
     * @return 返回页面视图
     */
    @RequestMapping("video.do")
    public String video(String lessonNo, Model model, HttpSession session) {
        // 获取session
        IndexUser indexUser = (IndexUser) session.getAttribute("indexUser");

        // 查询课程信息
        QueryWrapper<IndexLesson> indexLessonQueryWrapper = new QueryWrapper<>();
        indexLessonQueryWrapper.eq("lesson_no", lessonNo);
        IndexLesson indexLesson = indexLessonService.getOne(indexLessonQueryWrapper);

        // 查询课程章节和课时信息
        CourseChapterLessonPOJO courseChapterLessonPOJO = getCourseInfoList(indexLesson.getCourseNo());

        // 绑定数据
        model.addAttribute("courseChapterLesson", courseChapterLessonPOJO);
        model.addAttribute("indexLesson", indexLesson);

        // 添加课程学习记录
        setUserCourse(indexLesson, indexUser.getUserNo());

        // 记录Log
        IndexLearnLog indexLearnLog = setLog(indexLesson, indexUser.getUserNo());

        indexLearnLogService.save(indexLearnLog);

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
        indexCourseQueryWrapper.eq("course_status", "1").orderByDesc("publish_time").last("limit " + courseNum);

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
        indexCourseQueryWrapper.eq("course_status", "1").orderByDesc("course_learn_num").last("limit " + courseNum);

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
            courseTagNoString = null == courseTagNoString ? "" : courseTagNoString;
            Object[] courseTagNoArray = courseTagNoString.split(",");
            QueryWrapper<IndexCourseTag> indexCourseTagQueryWrapper = new QueryWrapper<>();
            if (0 != courseTagNoArray.length) {
                indexCourseTagQueryWrapper.in("course_tag_no", courseTagNoArray);
            }
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
        indexCourseQueryWrapper.eq("course_no", courseNo).eq("course_status", "1");
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

    /**
     * 生成学习日志
     *
     * @param indexLesson 课时
     * @param userNo      用户编号
     * @return 返回日志对象
     */
    private IndexLearnLog setLog(IndexLesson indexLesson, String userNo) {
        IndexLearnLog indexLearnLog = new IndexLearnLog();
        indexLearnLog.setLearnLogNo(CommonFunctions.generateNo("ILLNO"));
        indexLearnLog.setCourseNo(indexLesson.getCourseNo());
        indexLearnLog.setChapterNo(indexLesson.getChapterNo());
        indexLearnLog.setLessonNo(indexLesson.getLessonNo());
        indexLearnLog.setUserNo(userNo);
        indexLearnLog.setAddTime(new Date());
        indexLearnLog.setAddUserNo(userNo);
        indexLearnLog.setUpdateTime(new Date());
        indexLearnLog.setUpdateUserNo(userNo);

        return indexLearnLog;
    }

    /**
     * 添加学习记录
     *
     * @param indexLesson 课时信息
     * @param userNo      用户编号
     */
    private void setUserCourse(IndexLesson indexLesson, String userNo) {
        // 查询该课程是否存在
        QueryWrapper<IndexUserCourse> indexUserCourseQueryWrapper = new QueryWrapper<>();
        indexUserCourseQueryWrapper.eq("user_no", userNo).eq("course_no", indexLesson.getCourseNo());
        IndexUserCourse preIndexUserCourse = indexUserCourseService.getOne(indexUserCourseQueryWrapper);

        // 修改访问次数和学习人数
        QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
        indexCourseQueryWrapper.eq("course_no", indexLesson.getCourseNo());
        IndexCourse indexCourse = indexCourseService.getOne(indexCourseQueryWrapper);

        // 已经存在记录，更新没记录，没有记录添加记录
        if (null == preIndexUserCourse) {
            IndexUserCourse indexUserCourse = new IndexUserCourse();
            indexUserCourse.setUserCourseNo(CommonFunctions.generateNo("IUCNO"));
            indexUserCourse.setUserNo(userNo);
            indexUserCourse.setCourseNo(indexLesson.getCourseNo());
            indexUserCourse.setLastTime(new Date());
            indexUserCourse.setAddTime(new Date());
            indexUserCourse.setAddUserNo(userNo);
            indexUserCourse.setUpdateTime(new Date());
            indexUserCourse.setUpdateUserNo(userNo);

            indexUserCourseService.save(indexUserCourse);

            // 学习人数加一
            indexCourse.setCourseLearnNum(indexCourse.getCourseLearnNum() + 1);
        } else {
            preIndexUserCourse.setLastTime(new Date());
            preIndexUserCourse.setUpdateTime(new Date());
            preIndexUserCourse.setUpdateUserNo(userNo);

            indexUserCourseService.updateById(preIndexUserCourse);
        }

        // 访问次数加一
        indexCourse.setVisitTimes(indexCourse.getVisitTimes() + 1);
        indexCourse.setUpdateTime(new Date());
        indexCourse.setUpdateUserNo(userNo);

        indexCourseService.updateById(indexCourse);
    }
}
