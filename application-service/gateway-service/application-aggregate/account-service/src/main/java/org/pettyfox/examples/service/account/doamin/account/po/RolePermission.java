package org.pettyfox.examples.service.account.doamin.account.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.pettyfox.base.web.dao.BaseEntitySnowId;

/**
 * <p>
 * 
 * </p>
 *
 * @author Petty Fox
 * @since 2021-04-15
 */
@Getter
@Setter
@TableName("t_role_permission")
@ApiModel(value="RolePermission对象", description="")
public class RolePermission extends BaseEntitySnowId
{

    private static final long serialVersionUID = 1L;



    private Long roleId;

    private Long permissionId;


}
