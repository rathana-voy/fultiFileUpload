package org.hrd.uploadmultifiles.entity;

/**
 * Created by ratha on 08-Aug-17.
 */
public class File {
    private String fileName;
    private String absolutePath;
    private String handlerPath;
    private String resourcePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getHandlerPath() {
        return handlerPath;
    }

    public void setHandlerPath(String handlerPath) {
        this.handlerPath = handlerPath;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileName='" + fileName + '\'' +
                ", absolutePath='" + absolutePath + '\'' +
                ", handlerPath='" + handlerPath + '\'' +
                ", resourcePath='" + resourcePath + '\'' +
                '}';
    }
}
