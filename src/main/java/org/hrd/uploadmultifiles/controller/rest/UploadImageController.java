package org.hrd.uploadmultifiles.controller.rest;

import org.hrd.uploadmultifiles.entity.File;
import org.hrd.uploadmultifiles.utinity.FileUploader;
import org.hrd.uploadmultifiles.utinity.config.ApplicationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ratha on 08-Aug-17.
 */
@RestController
@RequestMapping("api/v01/upload")
public class UploadImageController {

    private FileUploader fileUploader;
    private ApplicationResource appResource;
    @Autowired
    public UploadImageController(FileUploader fileUploader, ApplicationResource appResource){
        this.fileUploader=fileUploader;
        this.appResource=appResource;
    }
    @PostMapping("profile")
    public ResponseEntity<Map<String, Object>> uploadUserProfile(@RequestParam("file") MultipartFile file){
        Map<String , Object> map=new HashMap<>();
        try{
            System.out.println(this.appResource.getUploadPath());
            System.out.println(this.appResource.getHandlerPath());
            fileUploader.setResourcePath(this.appResource.getUploadPath());
            fileUploader.setResourcesHandler(this.appResource.getHandlerPath());
            fileUploader.setMultipartFile(file);

            /*/upload file to server/
            **parameter @Param oldFileName if null,it upload new file to server. but in contrast it update existing
            * file that you provide the name to @Param oldFileName
            **
            */
            if(fileUploader.upload(null)) {
                File profileInfo = new File();
                profileInfo.setAbsolutePath(fileUploader.getAbsolutePath());
                profileInfo.setFileName(fileUploader.getFilename());
                profileInfo.setHandlerPath(fileUploader.getResourcesHandler());
                profileInfo.setResourcePath(fileUploader.getResourcePath());
                map.put("DATA", profileInfo);
                map.put("MESSAGE", "UPLOAD SUCCESS");
                map.put("STATUS", true);
                map.put("CODE", 200);
            }else{
                map.put("DATA", null);
                map.put("MESSAGE" , "HAVE AN ERROR ");
                map.put("STATUS",false);
                map.put("CODE","500");
            }
        }catch (Exception e){
            map.put("DATA", null);
            map.put("MESSAGE" , "HAVE AN ERROR ");
            map.put("STATUS",false);
            map.put("CODE","500");
            e.printStackTrace();
        }
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

}
