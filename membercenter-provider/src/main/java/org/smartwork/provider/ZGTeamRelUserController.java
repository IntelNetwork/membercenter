package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGTeamRelUserService;
import org.smartwork.dal.entity.ZGTeamRelUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"用户团队岗位管理"})
@RestController
@RequestMapping("/zgteamreluser")
public class ZGTeamRelUserController extends BaseProvider<IZGTeamRelUserService, ZGTeamRelUser> {
}