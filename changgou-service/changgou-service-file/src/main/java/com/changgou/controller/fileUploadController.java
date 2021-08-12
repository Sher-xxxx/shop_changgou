package com.changgou.controller;

import com.changgou.file.FastDFSFile;
import com.changgou.util.FastDFSUtil;
import entity.Result;
import entity.StatusCode;
import org.csource.common.MyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
public class fileUploadController {
    @PostMapping("/upload")
    public Result upload(@RequestParam(value = "file")MultipartFile file) throws IOException, MyException {
        // 调用FastDFSUtil工具类
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),
                file.getBytes(),
                StringUtils.getFilenameExtension(file.getOriginalFilename())
        );
        String[] uploads = FastDFSUtil.upload(fastDFSFile);
//        String url = "http://192.168.211.132:8080/"+ uploads[0] + "/" + uploads[1];
        String url = FastDFSUtil.getTrackerInfo()+'/' + uploads[0] + "/" + uploads[1];
        return new Result(true, StatusCode.OK, "上传成功", url);
    }
}
