package org.smartwork.provider;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 类概述:Api新建公司，修改，设置岗位管理
 * @创建人 Tom
 * @创建时间 2020/3/16
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/${smartwork.verision}/team-user-pro")
@Api(tags = {"Api新建公司，修改，设置岗位管理"})
@Slf4j
public class ZGTeamRelUserProvider {
}
