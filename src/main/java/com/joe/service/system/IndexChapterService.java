package com.joe.service.system;

import com.joe.entity.IndexChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joe.pojo.Chapter;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author joe
 * @since 2019-12-03
 */
public interface IndexChapterService extends IService<IndexChapter> {
    List<Chapter> listChapter(String courseNo);
}
