package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGProjectPerService;
import org.smartwork.dal.entity.ZGProjectPer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"参与项目人员管理"})
@RestController
@RequestMapping("/zgprojectper")
public class ZGProjectPerController extends BaseProvider<IZGProjectPerService, ZGProjectPer> {
}