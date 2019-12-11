package top.xuxuzhaozhao.demo.core.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import top.xuxuzhaozhao.demo.core.constant.ProjectConstant;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class UploadActionUtil {
    public static List<String> uploadFile(HttpServletRequest request) throws Exception {
        List<String> list = new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext()
        );
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = multipartRequest.getFileNames();
            while (iterator.hasNext()) {
                //取得上传文件
                MultipartFile file = multipartRequest.getFile(iterator.next());
                if (file != null) {
                    //当前上传文件的文件名称
                    String fileName = file.getOriginalFilename();
                    //名称不为空，则表示文件存在
                    assert fileName != null;
                    if (!"".equals(fileName.trim())) {
                        String fileType = fileName.substring(fileName.lastIndexOf("."));

                        //新文件名字
                        String tempFileName = UUID.randomUUID().toString() + fileType;

                        //存入文件夹
                        String folderPath = ProjectConstant.SAVE_FILE_PATH + File.separator + folderName();
                        File folder = new File(folderPath);
                        if (!folder.exists() && !folder.isDirectory()) {
                            folder.mkdirs();
                        }
                        File uploadFile = new File(folderPath + File.separator + tempFileName);
                        file.transferTo(uploadFile);
                        fileName = folderName() + File.separator + tempFileName;
                        list.add(ProjectConstant.SAVE_FILE_PATH + "//" + fileName);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 当前日期当文件夹名
     */
    private static String folderName() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }
}
