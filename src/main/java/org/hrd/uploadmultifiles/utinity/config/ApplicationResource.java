package org.hrd.uploadmultifiles.utinity.config;

import org.springframework.stereotype.Component;

/**
 * Created by ratha on 08-Aug-17.
 */

@Component
public class ApplicationResource {

    public String getUploadPath(){
        return "/opt/project/upload/user-profiles/";
    }
    public String getHandlerPath(){
        return "/resources/upload/user-profiles/";
    }

}
