package com.happy.sky.config;

import com.happy.sky.common.utils.FileUploadParam;
import com.happy.sky.validator.RRException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @name: VsftpUtilConfig <tb>
 * @title: ftp工具类  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/9/2 15:53 <tb>
 */
@Component
public class VsFtpUtilConfig {

    private static Logger logger = LoggerFactory.getLogger(VsFtpUtilConfig.class);

    //通过properties文件自动注入
    @Value("${ftp.host}")
    private String host;    //ftp服务器ip
    @Value("${ftp.port}")
    private int port;        //ftp服务器端口
    @Value("${ftp.username}")
    private String username;//用户名
    @Value("${ftp.password}")
    private String password;//密码
    @Value("${ftp.basePath}")
    private String basePath;//存放文件的基本路径

    private FTPClient ftpClient;
    boolean success = false;
    int reply;

    //测试的时候把这个构造函数打开，设置你的初始值，然后在代码后面的main方法运行测试
    public VsFtpUtilConfig() {
        host = "192.168.14.56";
        port = 21;
        username = "static_uploader";
        password = "0pfTaaoU4wa0TJ5lGWG5";
        basePath = "upload/xf/device/";
        //初始化
        init();
    }

    private void init() {
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(host, port);//连接FTP服务器
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            boolean loginStatus = ftpClient.login(username, password);//登录
            logger.info("step-1 upload file login vsftp status:{}", loginStatus);
            if (!loginStatus) {
                throw new RuntimeException("vsftp登录验证错误,请核对vsftp配置信息");
            }
            ftpClient.setConnectTimeout(10000);
            ftpClient.setFileType(2);
            reply = ftpClient.getReplyCode();
            logger.info("step-2 upload file vsftp replyCode:{}", reply);
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                logger.info("step-2 upload file vsftp replyCode error");
                throw new RuntimeException("vsftp登录验证错误,请核对vsftp配置信息");
            }
        } catch (Exception e) {
            throw new RRException("上传文件失败，请核对vsftp配置信息", e);
        }
    }

    public String upload(byte[] data, String fileName, String path) {
        try {
            InputStream inputStream = new ByteArrayInputStream(data);

            String uploadPath = basePath + path;
            boolean changeDirectoryFlag = ftpClient.changeWorkingDirectory(uploadPath);
            logger.info("step-3 upload file vsftp changeDirectory result:{}", changeDirectoryFlag);
            ftpClient.enterLocalPassiveMode();
            success = ftpClient.storeFile(fileName, inputStream);
            logger.info("step-4 upload file vsftp result:{},weburl:{}", success, fileName);
            if (!success) {
                throw new RuntimeException("上传vsftp文件出错");
            }
            inputStream.close();
            ftpClient.logout();
            return fileName;
        } catch (Exception e) {
            throw new RRException("上传文件失败，请核对vsftp配置信息", e);
        }
    }

    /**
     * @param path     上传文件存放在服务器的路径
     * @param filename 上传文件名
     * @param input    输入流
     * @return
     */
    public boolean fileUpload(String path, String filename, InputStream input) {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(host, port); // 连接FTP服务器
            boolean loginStatus = ftp.login(username, password); // 登陆FTP服务器
            logger.info("step-1 upload file login vsftp status:{}", loginStatus);
            if (!loginStatus) {
                throw new RuntimeException("vsftp登录验证错误,请核对vsftp配置信息");
            }
            ftp.setConnectTimeout(10000);
            //设置文件编码格式
            ftp.setControlEncoding("UTF-8");
            //ftp通信有两种模式
            //PORT(主动模式)客户端开通一个新端口(>1024)并通过这个端口发送命令或传输数据,期间服务端只使用他开通的一个端口，例如21
            //PASV(被动模式)客户端向服务端发送一个PASV命令，服务端开启一个新端口(>1024),并使用这个端口与客户端的21端口传输数据
            //由于客户端不可控，防火墙等原因，所以需要由服务端开启端口，需要设置被动模式
            ftp.enterLocalPassiveMode();
            //设置传输方式为流方式
            ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            //获取状态码，判断是否连接成功
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                logger.info("step-2 upload file vsftp replyCode error:FTP服务器拒绝连接");
                throw new RuntimeException("FTP服务器拒绝连接");
            }
            //转到上传文件的根目录
            if (!ftp.changeWorkingDirectory(basePath)) {
                throw new RuntimeException("根目录不存在，需要创建");
            }
            //判断是否存在目录
            if (!ftp.changeWorkingDirectory(path)) {
                String[] dirs = path.split("/");
                //创建目录
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    //判断是否存在目录
                    if (!ftp.changeWorkingDirectory(dir)) {
                        //不存在则创建
                        if (!ftp.makeDirectory(dir)) {
                            throw new RuntimeException("子目录创建失败");
                        }
                        //进入新创建的目录
                        ftp.changeWorkingDirectory(dir);
                    }
                }
                //设置上传文件的类型为二进制类型
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                //上传文件
                if (!ftp.storeFile(filename, input)) {
                    return false;
                }
                input.close();
                ftp.logout();
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return false;
    }

    /**
     * @param filename  文件名，注意！此处文件名为加路径文件名，如：/2015/06/04/aa.jpg
     * @param localPath 存放到本地第地址
     * @return
     */
    public boolean downloadFile(String filename, String localPath) {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(host, port);
            ftp.login(username, password);
            //设置文件编码格式
            ftp.setControlEncoding("UTF-8");
            //ftp通信有两种模式
            //PORT(主动模式)客户端开通一个新端口(>1024)并通过这个端口发送命令或传输数据,期间服务端只使用他开通的一个端口，例如21
            //PASV(被动模式)客户端向服务端发送一个PASV命令，服务端开启一个新端口(>1024),并使用这个端口与客户端的21端口传输数据
            //由于客户端不可控，防火墙等原因，所以需要由服务端开启端口，需要设置被动模式
            ftp.enterLocalPassiveMode();
            //设置传输方式为流方式
            ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            //获取状态码，判断是否连接成功
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                throw new RuntimeException("FTP服务器拒绝连接");
            }

            int index = filename.lastIndexOf("/");
            //获取文件的路径
            String path = filename.substring(0, index);
            //获取文件名
            String name = filename.substring(index + 1);
            //判断是否存在目录
            if (!ftp.changeWorkingDirectory(basePath + path)) {
                throw new RuntimeException("文件路径不存在：" + basePath + path);
            }
            //获取该目录所有文件
            FTPFile[] files = ftp.listFiles();
            for (FTPFile file : files) {
                //判断是否有目标文件
                logger.info("文件名:{}", name);
                if (file.getName().equals(name)) {
                    //如果找到，将目标文件复制到本地
                    File localFile = new File(localPath + "/" + file.getName());
                    OutputStream out = new FileOutputStream(localFile);
                    ftp.retrieveFile(file.getName(), out);
                    out.close();
                }
            }
            ftp.logout();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public boolean deleteFile(String filename) {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(host, port);
            ftp.login(username, password);
            //设置编码格式
            ftp.setControlEncoding("UTF-8");
            ftp.enterLocalPassiveMode();
            //获取状态码，判断是否连接成功
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                throw new RuntimeException("FTP服务器拒绝连接");
            }
            int index = filename.lastIndexOf("/");
            //获取文件的路径
            String path = filename.substring(0, index);
            //获取文件名
            String name = filename.substring(index + 1);
            //判断是否存在目录,不存在则说明文件存在
            if (!ftp.changeWorkingDirectory(basePath + path)) {
                return true;
            }
            if (ftp.deleteFile(name)) {
                clearDirectory(ftp, basePath, path);
                ftp.logout();
                return true;
            }
            ftp.logout();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * @param ftp
     * @param basePath
     * @param path     以path为根，递归清除上面所有空的文件夹，直到出现不为空的文件夹停止，最多清除到basePath结束
     * @throws IOException
     */
    private void clearDirectory(FTPClient ftp, String basePath, String path) throws IOException {
        //如果路径长度小于2，说明到顶了
        if (path.length() < 2) {
            return;
        }
        //如果当前目录文件数目小于1则删除此目录
        if (ftp.listNames(basePath + path).length < 1) {
            //删除目录
            logger.info("删除目录:{},{}", basePath, path);
            ftp.removeDirectory(basePath + path);
            int index = path.lastIndexOf("/");
            //路径向上一层
            path = path.substring(0, index);
            //继续判断
            clearDirectory(ftp, basePath, path);
        }
    }

    //两个功能其中一个使用的话另一个需要注释
    public static void main(String[] args) {
        //上传测试--------------------------------------
        FileInputStream in;
        try {
            in = new FileInputStream(new File("E:\\测试文件\\image\\avatars.jpg"));
            VsFtpUtilConfig ftputil = new VsFtpUtilConfig();
//            boolean flag = ftputil.fileUpload("sky/user", "av.jpg", in);
//            logger.info("上传文件结果返回：{}", flag);

            FileUploadParam uploadParam = FileUploadParam.getByType(FileUploadParam.USER_IMG.getType());
            byte[] bytesByFile = getBytesByFile("E:\\测试文件\\image\\avatars.jpg");
            String fileName = ftputil.upload(bytesByFile, "av.jpg", "user_img");
            String fileUrl = uploadParam.getWebUrl() + fileName;
            logger.info("上传文件结果返回：{}", fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        //下载测试--------------------------------------
        /*String filename="/2015/06/04/aa.jpg";
        String localPath="F:\\";
        VsFtpUtilConfig ftputil=new VsFtpUtilConfig();
        ftputil.downloadFile(filename, localPath);*/

        //删除测试--------------------------------------
         /*VsFtpUtilConfig ftputil = new VsFtpUtilConfig();
        boolean flag = ftputil.deleteFile("/2015/06/04/va.jpg");
        System.out.println(flag);*/
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    @Override
    public String toString() {
        return "VsFtpUtilConfig [host=" + host + ", port=" + port + ", username=" + username + ", password=" + password
                + ", basePath=" + basePath + "]";
    }

    //将文件转换成Byte数组
    public static byte[] getBytesByFile(String pathStr) {
        File file = new File(pathStr);
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //将Byte数组转换成文件
    public static void getFileByBytes(byte[] bytes, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists()) {// 判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
