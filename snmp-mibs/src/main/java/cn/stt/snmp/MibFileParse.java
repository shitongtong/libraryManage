package cn.stt.snmp;

import net.percederberg.mibble.Mib;
import net.percederberg.mibble.MibLoader;
import net.percederberg.mibble.MibLoaderException;
import net.percederberg.mibble.MibValue;
import net.percederberg.mibble.MibValueSymbol;
import net.percederberg.mibble.snmp.SnmpObjectType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @Author shitt7
 * @Date 2020/7/8 15:24
 */
public class MibFileParse {
//    @SuppressWarnings( { "rawtypes" })
    public List<MmMib> getMib(String filePath) throws IOException, MibLoaderException {
        File file = new File(filePath);
        MibLoader ml = new MibLoader();
        Mib mib = ml.load(file);
        String mibName = mib.getName();
        System.err.println("mibName===" + mibName);
        System.err.println("-------------------------------------");
        String syntax = "";
        String access = "";
        String status = "";
        List<MmMib> list = new ArrayList<MmMib>();
        Collection c = mib.getAllSymbols();
        Iterator it = c.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if (obj instanceof MibValueSymbol) {
                MmMib mo = new MmMib();
                MibValueSymbol mvs = (MibValueSymbol) obj;
                SnmpObjectType sot = null;
                if (mvs.getType() instanceof SnmpObjectType) {
                    sot = (SnmpObjectType) mvs.getType();
                }
                if (sot != null ) {
                    syntax = sot.getSyntax().getName();
                    access = sot.getAccess().toString();
                    status = sot.getStatus().toString();
                }
                //是否为表的列
                boolean isTableColumn = mvs.isTableColumn();
                String name = mvs.getName();
                MibValue value = mvs.getValue();
                MibValueSymbol parent = mvs.getParent();
                String parentValue = "";
                System.err.println("name==" + name);
                System.err.println("value==" + value);
                System.err.println("isTableColumn==" + isTableColumn);
                if (parent != null) {
                    parentValue = parent.getValue().toString();
                    if (parent.getParent()==null){
                        System.err.println("supperParentName======" + mibName);
                        System.err.println("supperParentValue=====" + parentValue);
                    }
                    System.err.println("parentName=" + parent.getName());
                    System.err.println("parentValue=" + parent.getValue());
                } else {
                }
                System.err.println("syntax=" + syntax);
                System.err.println("access=" + access);
                System.err.println("status=" + status);
                System.err.println("-------------------------------------");
                mo.setName(name);
                mo.setValue(value.toString());
                mo.setParent(parentValue);
                mo.setSyntax(syntax);
                mo.setAccess(access);
                mo.setStatus(status);
                list.add(mo);
            }
        }
        /*
        MibValueSymbol mvs = mib.getSymbolByOid("1.3.6.1.2.1.10");
        System.err.println("mvs.getName()=" + mvs.getName());
        System.err.println("mvs.getValue()=" + mvs.getValue());
        MibValueSymbol parent = mvs.getParent();
        System.err.println("parent=" + parent);  */
        return list;
    }

    public static void main(String[] args) throws IOException, MibLoaderException {
        MibFileParse t = new MibFileParse();
        char a = File.separatorChar;
        String abPath = System.getProperty("user.dir");
        String filePath = abPath + a + "src" + a + "main" + a + "webapp" + a + "mib" + a + "ISM-HUAWEI-MIB.mib";
        //失败
        filePath = "D:\\workDir\\北蔡项目\\告警\\锐捷-交换机\\standard\\SNMPv2-MIB-3418.mib";
//        filePath = "D:\\workDir\\青浦平安\\采集\\v2.x_mib_20180711\\mib\\standard\\802-1x-2004-MIB.mib";
        filePath = "D:\\snmp\\switch-sys-mib.mib";
        filePath = "C:\\Users\\shitt7\\Downloads\\mibble-mibs-2.9.3\\mibs\\ietf\\RFC1158-MIB";
        filePath = "D:\\workDir\\北蔡项目\\告警\\锐捷-交换机\\standard\\IF-MIB-2233.mib";
        filePath = "C:\\Users\\shitt7\\Downloads\\powernet432.mib";
        t.getMib(filePath);
    }
}
