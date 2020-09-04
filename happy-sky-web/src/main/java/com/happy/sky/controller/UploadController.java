package com.happy.sky.controller;

import com.happy.sky.common.utils.ConfigConstant;
import com.happy.sky.common.utils.FileUploadParam;
import com.happy.sky.validator.RRException;
import com.happy.sky.view.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @name: UploadController <tb>
 * @title: 文件上传  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/9/2 15:37<tb>
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    /**
     * 上传文件
     */
    @PostMapping("/file")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }

        String originalFilename = file.getOriginalFilename();
        String fileExpandName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());
        if(!ConfigConstant.filePattern.matcher(fileExpandName.toUpperCase()).find()){
            return R.error("文件格式不支持");
        }

        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        FileUploadParam uploadParam = FileUploadParam.getByType(FileUploadParam.USER_IMG.getType());

        String url = "";
        return R.ok().put("url", url);
    }

}
