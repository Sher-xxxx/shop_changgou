package com.changgou.util;

import com.changgou.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * FastDFS文件管理
 *      文件上传
 *      文件删除
 *      文件下载...
 */
public class FastDFSUtil {
    /**
     * 1. 加载Tracker连接信息
     */
    static{
        try {
            String filename = new ClassPathResource("fdfs_client.conf").getPath();
            ClientGlobal.init(filename);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     * @return
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception {
        // 附加参数
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", fastDFSFile.getAuthor());

        // 获取TrackerServer
        TrackerServer trackerServer = getTrackServer();

        // 通过server获取Storage连接信息，创建存储Storage的连接信息
        StorageClient storageClient = getStorageClient(trackerServer);

        /**
         * 通过client访问storage，实现文件上传，并获取文件上传后的存储信息
         * 1. 文件的字节数组
         * 2. 文件扩展名
         * 3， 附加参数
         */

//        storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), null);
        String[] uploads = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), meta_list);
        return uploads;
    }

    /**
     * 获取文件信息
     * @param groupName : 文件的组名
     * @param remoteFileName : 存储路径名字
     * @return
     */
    public static FileInfo getFile(String groupName, String remoteFileName) throws Exception {
        // 获取TrackerServer
        TrackerServer trackerServer = getTrackServer();

        // 通过server获取Storage连接信息，创建存储Storage的连接信息
        StorageClient storageClient = getStorageClient(trackerServer);
        // 获取文件信息
        return storageClient.get_file_info(groupName, remoteFileName);
    }

    /**
     * 文件下载
     */
    public static InputStream downFile(String groupName, String remoteFileName) throws Exception {
        // 获取TrackerServer
        TrackerServer trackerServer = getTrackServer();

        // 通过server获取Storage连接信息，创建存储Storage的连接信息
        StorageClient storageClient = getStorageClient(trackerServer);

        byte[] buffer = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(buffer);
    }

    /**
     * 删除文件
     */
    public static void deleteFile(String groupName, String remoteFileName) throws Exception {
        // 获取TrackerServer
        TrackerServer trackerServer = getTrackServer();

        // 通过server获取Storage连接信息，创建存储Storage的连接信息
        StorageClient storageClient = getStorageClient(trackerServer);

        storageClient.delete_file(groupName, remoteFileName);

    }

    // 获取服务器
    public static StorageServer getStorage() throws IOException {
        // 创建TrackerClient对象，通过client访问server
        TrackerClient trackerClient = new TrackerClient();
        // 获取连接对象
        TrackerServer trackerServer = trackerClient.getConnection();


        return trackerClient.getStoreStorage(trackerServer);

    }

    /**
     * 获取Storage的IP和端口信息
     * @return
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) throws IOException {
        // 创建TrackerClient对象，通过client访问server
        TrackerClient trackerClient = new TrackerClient();
        // 获取连接对象
        TrackerServer trackerServer = trackerClient.getConnection();

        //获取Storage的IP和端口信息
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }

    /**
     * 获取Tracker信息
     * @return
     */
    public static String getTrackerInfo() throws IOException {
        // 创建TrackerClient对象，通过client访问server
        TrackerClient trackerClient = new TrackerClient();
        // 获取连接对象
        TrackerServer trackerServer = trackerClient.getConnection();

        // Tracker的IP，HTTP端口
        String ip = trackerServer.getInetSocketAddress().getHostString();
        int tracker_http_port = ClientGlobal.getG_tracker_http_port();
        String url = "http://"+ ip + ":"+tracker_http_port;
        return url;
    }

    /**
     * 获取Tracker
     * @return
     * @throws Exception
     */
    public static TrackerServer getTrackServer() throws Exception{
        // 创建TrackerClient对象，通过client访问server
        TrackerClient trackerClient = new TrackerClient();
        // 获取连接对象
        TrackerServer trackerServer = trackerClient.getConnection();

        return trackerServer;
    }

    /**
     * 获取StorageClient
     * @param trackerServer
     * @return
     */
    public static StorageClient getStorageClient(TrackerServer trackerServer){
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    public static void main(String[] args) throws IOException, MyException {
//        以下为测试代码
//        FileInfo file= getFile("group1", "M00/00/00/wKjThGESjZOAIfwmAD-tlGdcf4A209.jpg");
//        System.out.println(file.getSourceIpAddr());
//        System.out.println(file.getFileSize());
//        InputStream is = downFile("group1", "M00/00/00/wKjThGESjZOAIfwmAD-tlGdcf4A209.jpg");
//        FileOutputStream os = new FileOutputStream("G:/file.jpg");
//
//        byte[] buffer = new byte[1024];
//        while(is.read(buffer) != -1){
//            os.write(buffer);
//        }
//        os.flush();
//        os.close();
//        is.close();

//        deleteFile("group1", "M00/00/00/wKjThGESjZOAIfwmAD-tlGdcf4A209.jpg");

//        StorageServer storageServer = getStorage();
//        System.out.println(storageServer.getStorePathIndex());
//        System.out.println(storageServer.getInetSocketAddress().getHostName());

//        ServerInfo[] groups = getServerInfo("group1", "M00/00/00/wKjThGENUmCAK5QuABBmqcZbE8Q609.jpg");
//        for(ServerInfo group: groups){
//            System.out.println(group.getIpAddr());
//            System.out.println(group.getPort());
//        }

        System.out.println(getTrackerInfo());
    }

}
