//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service.impl;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.service.CleanDeleteDataService;
import org.yidu.novel.service.impl.HibernateSupportServiceImpl;
import org.yidu.novel.utils.Utils;

public class CleanDeleteDataServiceImpl extends HibernateSupportServiceImpl implements CleanDeleteDataService {
    public CleanDeleteDataServiceImpl() {
    }

    public void cleanDeleteData() {
        int keepdays = YiDuConstants.yiduConf.getInt("keepDeleteDataDays", 5);
        String getDeleteArtcleSql = "select articleno from t_article where deleteflag='true' and modifytime < (now() -  INTERVAL \'\'{0} days\'\')";
        List articlenoList = this.yiduJdbcTemplate.queryForList(MessageFormat.format(getDeleteArtcleSql, new Object[]{Integer.valueOf(keepdays)}), Integer.class);
        Iterator chapterList = articlenoList.iterator();

        while(chapterList.hasNext()) {
            Integer getDeleteChapterSql = (Integer)chapterList.next();
            Utils.deleteDirectory(Utils.getTextDirectoryPathByArticleno(getDeleteChapterSql.intValue()));
            Utils.deleteDirectory(Utils.getArticlePicPath(getDeleteChapterSql.intValue()));
        }

        String getDeleteChapterSql1 = "select * from t_chapter where deleteflag='true' and modifytime < (now() -  INTERVAL \'\'{0} days\'\') ";
        if(articlenoList != null && articlenoList.size() > 0) {
            getDeleteChapterSql1 = getDeleteChapterSql1 + " and articlno not in (" + StringUtils.join(articlenoList, ",") + ")";
        }

        List chapterList1 = this.yiduJdbcTemplate.query(MessageFormat.format(getDeleteChapterSql1, new Object[]{Integer.valueOf(keepdays)}), new BeanPropertyRowMapper(TChapter.class));
        Iterator deleteSql = chapterList1.iterator();

        while(deleteSql.hasNext()) {
            TChapter tableSet = (TChapter)deleteSql.next();
            Utils.deleteFile(Utils.getTextFilePathByChapterno(tableSet.getArticleno().intValue(), tableSet.getChapterno()));
        }

        HashSet tableSet1 = new HashSet();
        tableSet1.add("t_user");
        tableSet1.add("t_review");
        tableSet1.add("t_chapter");
        tableSet1.add("t_system_block");
        tableSet1.add("t_bookcase");
        tableSet1.add("t_article");
        tableSet1.add("t_message");
        tableSet1.add("t_credit_history");
        String deleteSql1 = "delete from {0} where deleteflag='true' and modifytime < (now() -  INTERVAL \'\'{1} days\'\')";
        Iterator var9 = tableSet1.iterator();

        while(var9.hasNext()) {
            String table = (String)var9.next();
            this.yiduJdbcTemplate.execute(MessageFormat.format(deleteSql1, new Object[]{table, Integer.valueOf(keepdays)}));
        }

    }
}
