package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGCmPostService;
import org.smartwork.dal.entity.ZGCmPost;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"公司岗位管理"})
@RestController
@RequestMapping("/zgcmpost")
public class ZGCmPostController extends BaseProvider<IZGCmPostService, ZGCmPost> {
}