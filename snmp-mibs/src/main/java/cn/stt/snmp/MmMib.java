package cn.stt.snmp;

import lombok.Data;

/**
 * @Author shitt7
 * @Date 2020/7/8 15:22
 */
@Data
public class MmMib {
    private String name;
    private String value;
    private String parent;
    private String syntax;
    private String access;
    private String status;
}
