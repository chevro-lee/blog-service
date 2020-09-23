package ink.chevro.controller.blog;

import ink.chevro.admin.entity.blog.Tag;
import ink.chevro.admin.service.blog.ITagService;
import result.RestResult;
import result.RestRsp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  19:48 2019-08-08
 **/
@Api(value = "TagController",tags = "标签")
@RestController
@RequestMapping("/api/v1/blog")
public class TagController {

    @Resource
    private ITagService tagService;

    @GetMapping("/tag")
    @ApiOperation(value = "列出全部标签")
    public RestResult<List<Tag>> listAllTag() {
        RestRsp<List<Tag>> restRsp = new RestRsp<>();
        List<Tag> tagList = tagService.listAll();
        int a = 1/0;
        return restRsp.success(tagList);
    }
}
