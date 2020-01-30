package com.lym.service;

import com.github.pagehelper.PageInfo;
import com.lym.entity.FileLog;
import com.lym.entity.Result;
import com.lym.entity.param.FileLogListParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Date 2020/1/30
 * @auth linyimin
 * @Desc 文件中心服务
 **/
public interface FileManageService {
    /**
     *获取文件上传记录
     */
    PageInfo<FileLog> records(FileLogListParam fileLogListParam);

    /**
     *上传文件
     */
    Result upload(MultipartFile file,Long userId);

    /**
     *删除文件
     */
    Result removeFile(FileLog fileLog);
}
