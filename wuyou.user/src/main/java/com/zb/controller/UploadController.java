package com.zb.controller;

import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.zb.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class UploadController {
    @Autowired
    private UploadService uploadService;
    @PostMapping("/singlefile")
    public Object singleFileUpload(HttpServletRequest request, @RequestParam(required = false,value = "files") MultipartFile[] files) {
        System.out.println(files);

        for (MultipartFile file : files) {

            if (Objects.isNull(file) || file.isEmpty()) {
                return "文件为空，请重新上传";
            }
            try{
                //根据时间戳创建文件名
                String fileName = System.currentTimeMillis() + file.getOriginalFilename();

                //创建文件的实际路径
                String destFileName = request.getServletContext().getRealPath("") + "uploaded" + File.separator + fileName;
                System.out.println(destFileName);
                //根据文件路径创建文件对应的实际文件
                File destFile = new File(destFileName);
                //创建文件实际路径
                destFile.getParentFile().mkdirs();
                //将文件传到对应的文件位置
                file.transferTo(destFile);


                Response response = uploadService.uploadFile(destFile);
                System.out.println(response);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.hash);
                System.out.println(putRet.key);//这个就是从七牛云获取的文件名

            }catch (IOException e){
                e.printStackTrace();
            }
//            announceService.save(announce);  //存入数据库

            /*try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get("F:\\img\\" + file.getOriginalFilename());
                //如果没有files文件夹，则创建
                if (!Files.isWritable(path)) {
                    Files.createDirectories(Paths.get("F:\\img\\"));
                }
                //文件写入指定路径
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
                return "后端异常...";
            }*/
        }
        return "文件上传成功";
    }
}