package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGCmRelUserService;
import org.smartwork.dal.entity.ZGCmRelUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"用户公司岗位管理"})
@RestController
@RequestMapping("/zgcmreluser")
public class ZGCmRelUserController extends BaseProvider<IZGCmRelUserService, ZGCmRelUser> {
}