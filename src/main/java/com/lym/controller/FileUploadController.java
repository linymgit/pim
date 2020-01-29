package com.lym.controller;

import com.lym.entity.Result;
import com.lym.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @Date 2020/1/27
 * @auth linyimin
 * @Desc
 **/
@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

//    @Auth
    @ResponseBody
    @RequestMapping(value = "/avatar",method = RequestMethod.POST)
    public Result ModifyUser(@RequestParam("avatar") MultipartFile file) throws IOException {
        if(Objects.nonNull(file)){
            String path;// 文件路径
            String type;//文件类型
            String fileName=file.getOriginalFilename();// 文件原名
            logger.info("上传的源文件名称:"+fileName);
            type= fileName.contains(".") ?fileName.substring(fileName.lastIndexOf(".")+1):null;
            if(type != null){
                if("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())){
                    // 项目在容器中实际发布运行的根路径
                    String realPath= System.getProperty("java.io.tmpdir");
                    // 自定义的文件名称
                    String trueFileName= System.currentTimeMillis() +fileName;
                    // 设置存放图片文件的路径
                    path=realPath+"/upload/"+trueFileName;
                    logger.info("图片存储的路径是:"+path);
                    // 转存文件到指定的路径
                    file.transferTo(new File(path));
                    return ResultUtil.getSuccess(path);
                }else {
                    return ResultUtil.getError("文件类型错误");
                }
            }else{
                return ResultUtil.getError("文件类型为空");
            }
        }else{
            return ResultUtil.getError("没有找到相对应的文件");
        }
    }
}
