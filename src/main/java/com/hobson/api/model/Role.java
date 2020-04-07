package com.hobson.api.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2020-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String roleName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
