package com.stt.shiro.chapter3.permission;

import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.authz.Permission;

/**
 * BitPermission用于实现位移方式的权限，如规则是：
 * 权限字符串格式：+资源字符串+权限位+实例ID；以+开头中间通过+分割；
 * 权限：0 表示所有权限；1 新增（二进制：0001）、2 修改（二进制：0010）、4 删除（二进制：0100）、8 查看（二进制：1000）；
 * 如 +user+10 表示对资源user拥有修改/查看权限。
 * <p>
 * Created by Administrator on 2016-12-21.
 *
 * @author shitongtong
 */
public class BitPermission implements Permission {
    private String resourceIdentify;
    private int permissionBit;
    private String instanceId;

    public BitPermission(String permissionString) {
        String[] array = permissionString.split("\\+");
        int length = array.length;
        if (length > 1) {
            resourceIdentify = array[1];
        }
        if (StringUtils.isEmpty(resourceIdentify)) {
            resourceIdentify = "*";
        }
        if (length > 2) {
            permissionBit = Integer.parseInt(array[2]);
        }
        if (length > 3) {
            instanceId = array[3];
        }
        if (StringUtils.isEmpty(instanceId)) {
            instanceId = "*";
        }
    }

    @Override
    public boolean implies(Permission permission) {
        if (!(permission instanceof BitPermission)) {
            return false;
        }
        BitPermission bitPermission = (BitPermission) permission;
        if (!("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(bitPermission.resourceIdentify))) {
            return false;
        }
        if (!(this.permissionBit == 0 || (this.permissionBit & bitPermission.permissionBit) != 0)) {
            return false;
        }
        if(!("*".equals(this.instanceId) || this.instanceId.equals(bitPermission.instanceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BitPermission{" +
                "resourceIdentify='" + resourceIdentify + '\'' +
                ", permissionBit=" + permissionBit +
                ", instanceId='" + instanceId + '\'' +
                '}';
    }
}
