package ink.chevro.controller.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import result.RestResult;
import result.RestRsp;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  15:11 2020-04-11
 **/
@Api(value = "FileUploadController",tags = "公共接口")
@RestController
@RequestMapping("/api/v1/common")
public class FileUploadController {

    @PostMapping("/upload/img")
    @ApiOperation(value = "上传图片")
    public RestResult<Void> upload(@RequestParam("file") MultipartFile file) {
        RestRsp<Void> restRsp = new RestRsp<>();
        return restRsp.success(null);
    }
}
