package com.romickid.simpbook.statement;

import com.romickid.simpbook.util.Date;

public class StatementDate {
    private Date date;


    /**
     * 构建StatementDate实例
     *
     * @param tDate 日期
     */
    public StatementDate(Date tDate) {
        date = tDate;
    }

    /**
     * 获取日期
     *
     * @return 日期
     */
    public Date getDate() {
        return date;
    }

}
