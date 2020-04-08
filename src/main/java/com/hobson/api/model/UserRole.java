package com.hobson.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yan hongbo
 * @since 2020-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("USER_ROLE")
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Double id;

    @TableField("USER_ID")
    private Double userId;

    @TableField("ROLE_ID")
    private Double roleId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
