package com.hobson.api.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
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
 * @since 2020-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String password;

    private String name;

    private String salt;

    private String phone;

    private String email;

    private Date createtime;

    private Date updatetime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
