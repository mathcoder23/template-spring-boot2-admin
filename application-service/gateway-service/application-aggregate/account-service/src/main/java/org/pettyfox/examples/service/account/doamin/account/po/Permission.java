package org.pettyfox.examples.service.account.doamin.account.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.pettyfox.base.comm.type.BaseEnum;
import org.pettyfox.base.web.dao.BaseTreeEntity;

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
@TableName("t_permission")
@ApiModel(value = "Permission对象", description = "")
public class Permission extends BaseTreeEntity {

    private static final long serialVersionUID = 1L;


    private Integer orderNum;

    private String scope;

    private String sn;

    @ApiModelProperty(value = "权限类型,[MENU(1):菜单]")
    private Type type;

    private String menuPath;

    private String menuComponent;

    private String menuIcon;

    private String menuMeta;

    @ApiModelProperty(value = "是否启用")
    private Boolean enable;

    public enum Type implements BaseEnum<Type, Integer> {
        /**
         * 权限类型,[MENU(1):菜单]
         */
        MENU(1, "菜单");

        private final int value;
        private final String name;

        Type(int value, String name) {
            this.value = value;
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public Integer getValue() {
            return value;
        }
    }


}
