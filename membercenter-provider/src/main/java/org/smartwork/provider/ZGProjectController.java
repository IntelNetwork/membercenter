package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGProjectService;
import org.smartwork.dal.entity.ZGProject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"项目管理"})
@RestController
@RequestMapping("/zgproject")
public class ZGProjectController extends BaseProvider<IZGProjectService, ZGProject> {
}