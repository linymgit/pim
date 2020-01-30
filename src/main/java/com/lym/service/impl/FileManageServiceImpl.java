package com.lym.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lym.entity.FileLog;
import com.lym.entity.FileLogExample;
import com.lym.entity.Result;
import com.lym.entity.param.FileLogListParam;
import com.lym.mapper.FileLogMapper;
import com.lym.service.FileManageService;
import com.lym.utils.ResultUtil;
import com.lym.utils.SnowFlakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @Date 2020/1/30
 * @auth linyimin
 * @Desc
 **/
@Service
public class FileManageServiceImpl implements FileManageService {

    static Logger logger = LoggerFactory.getLogger(FileManageServiceImpl.class);

    @Autowired
    private FileLogMapper fileLogMapper;

    @Override
    public PageInfo<FileLog> records(FileLogListParam fileLogListParam) {
        PageHelper.startPage(fileLogListParam.getPageNum(), fileLogListParam.getPageSize());
        FileLogExample fileLogExample = new FileLogExample();
        FileLogExample.Criteria criteria = fileLogExample.createCriteria();
        criteria.andUseridEqualTo(fileLogListParam.getUserId());
        if (Objects. nonNull(fileLogListParam.getKeyWord()) && !fileLogListParam.getKeyWord().equals("")) {
            criteria.andResourceNameLike(fileLogListParam.getKeyWord());
        }
        List<FileLog> fileLogs = fileLogMapper.selectByExample(fileLogExample);
        return new PageInfo(fileLogs);
    }

    @Override
    public Result upload(MultipartFile file, Long userId) {
        if (Objects.nonNull(file)) {
            String path;// 文件路径
            String type;//文件类型
            String fileName = file.getOriginalFilename();// 文件原名
            if (logger.isDebugEnabled()) {
                logger.info("文件中心-上传的源文件名称:" + fileName);
            }
            type = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1) : null;
            if (type != null) {
                FileLog fileLog = new FileLog();
                fileLog.setId(SnowFlakeUtil.nextId());
                fileLog.setResourceName(fileName);
                fileLog.setUserid(userId);
                // 项目在容器中实际发布运行的根路径
                String realPath = System.getProperty("java.io.tmpdir");
                // 设置存放图片文件的路径
                path = realPath + "/upload/" + fileName;
                if (logger.isDebugEnabled()) {
                    logger.info("文件中心-文件存储的路径是:" + path);
                }
                // 转存文件到指定的路径
                try {
                    file.transferTo(new File(path));
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
                fileLog.setResourceUrl("/resources/" + fileName);
                fileLogMapper.insertSelective(fileLog);
                return ResultUtil.getSuccess(fileLog);
            } else {
                return ResultUtil.getError("文件类型为空");
            }
        }
        return ResultUtil.getSuccess();
    }

    @Override
    public Result removeFile(FileLog fileLog) {
        FileLog exist = fileLogMapper.selectByPrimaryKey(fileLog.getId());
        if (Objects.isNull(exist)) {
            return ResultUtil.getSuccess();
        }
        String filePath = System.getProperty("java.io.tmpdir");
        filePath += "/upload/"+ exist.getResourceName();
        File file = new File(filePath);
        if(file.isFile()){
            boolean ok = file.delete();
            if (ok) {
               fileLogMapper.deleteByPrimaryKey(exist.getId());
            }else if (logger.isInfoEnabled()){
                logger.warn("file.delete() return false and file path is "+filePath);
            }
        }
        return ResultUtil.getSuccess();
    }
}
