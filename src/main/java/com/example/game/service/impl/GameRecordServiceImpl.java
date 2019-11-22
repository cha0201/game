package com.example.game.service.impl;

import com.example.game.dao.GameRecordDao;
import com.example.game.dao.params.GameRecordParams;
import com.example.game.entity.GameRecord;
import com.example.game.entity.GameRecordStatistics;
import com.example.game.request.GameRecordRequest;
import com.example.game.response.QueryGameRecordResponse;
import com.example.game.response.QueryGameTaskResponse;
import com.example.game.service.GameRecordService;
import com.example.game.util.DateUtils;
import com.example.game.value.AccountVo;
import com.example.game.value.GameRecordConditionVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service
public class GameRecordServiceImpl implements GameRecordService {


    @Autowired
    private GameRecordDao gameRecordDao;

    @Override
    public List<AccountVo> getRecordAccountList() {
        List<Map<String, Object>> accountMapList = gameRecordDao.getRecordAccountList();
        List<AccountVo> accountVoList = accountMapList.stream().map(accountMap ->
                new AccountVo((String) accountMap.get("record_account"),
                        (String) accountMap.get("record_account"))).collect(toList());
        return accountVoList;
    }

    @Override
    public List<GameRecordStatistics> getRecordStatistics(String time, String account, String operator) {
        Date startDateTime = null;
        Date endDateTime = new Date();
        String timeSql = " record_date as date,";
        if (!StringUtils.isEmpty(time)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            LocalDateTime inputDate = LocalDateTime.now();
            // 0本周，1下周，-1上周
            if ("1".equals(time)) {
                startDateTime = Date.from(DateUtils.weekStart(0).atZone(ZoneOffset.ofHours(8)).toInstant());

            } else if ("2".equals(time)) {
                startDateTime = Date.from(DateUtils.weekStart(-1).atZone(ZoneOffset.ofHours(8)).toInstant());

            } else if ("3".equals(time)) {
                //本月
                startDateTime = Date.from(DateUtils.monthStart(0).atZone(ZoneOffset.ofHours(8)).toInstant());
            }
            timeSql = " concat('" + sdf.format(startDateTime) + "'" + ",'~','" + sdf.format(endDateTime) + "') as date,";
        }
        if (StringUtils.isEmpty(account) && StringUtils.isEmpty(operator)) {
            timeSql = " record_date as date,";
        }

        String columnSql = null;
        String accountSql = " record_account as account, ";
        String operatorSql = " (select user_nick_name from game_user where user_id=game_record.game_user_id) as operator ";
        StringBuffer columnCondition = new StringBuffer();
        String columnCondition1 = timeSql + " record_account as account, 1 as pourNum, record_amount as pourAmount, record_valid_amount as effectAmount, record_gain profitAmount, (select user_nick_name from game_user where user_id=game_record.game_user_id) as operator ";
        if (StringUtils.isEmpty(account) && !StringUtils.isEmpty(operator)) {
            accountSql = " '' as account, ";
        } else if (!StringUtils.isEmpty(account) && StringUtils.isEmpty(operator)) {
            operatorSql = " '' as operator ";
        }
        if (!StringUtils.isEmpty(account) || !StringUtils.isEmpty(operator)) {
            if (StringUtils.isEmpty(time)) {
                timeSql = " '' as date,";
            }
        }


        columnCondition.append(timeSql);
        columnCondition.append(accountSql);
        columnCondition.append(" count(id) as pourNum, ");
        columnCondition.append(" sum(record_amount) as pourAmount, sum(record_valid_amount) as effectAmount,sum(record_gain) profitAmount,");
        columnCondition.append(operatorSql);
        columnSql = columnCondition.toString();
        if (StringUtils.isEmpty(account) && StringUtils.isEmpty(operator)) {
            columnSql = columnCondition1;
        }

        StringBuffer condition = new StringBuffer();
        if (!StringUtils.isEmpty(account) || !StringUtils.isEmpty(operator)) {
            condition.append(" group by ");
        }

        if (!StringUtils.isEmpty(account)) {
            condition.append("record_account");
            if (StringUtils.isEmpty(operator)) {

            }
        }

        if (!StringUtils.isEmpty(operator)) {
            if (!StringUtils.isEmpty(account)) {
                condition.append(",");
            }
            condition.append("game_user_id");

        }

        if (!StringUtils.isEmpty(account) || !StringUtils.isEmpty(operator)) {
            condition.append(" having ");
        }

        if (!StringUtils.isEmpty(account)) {
            condition.append("record_account='").append(account).append("'");
        }

        if (!StringUtils.isEmpty(operator)) {
            if (!StringUtils.isEmpty(account)) {
                condition.append(" and");
            }
            condition.append(" game_user_id='").append(operator).append("'");

        }

        return gameRecordDao.selectGameRecordStatisticsResult(new GameRecordConditionVo(startDateTime, endDateTime, account, operator, columnSql, condition.toString()));
    }

    @Override
    public QueryGameRecordResponse getRecordList(Integer currentPage, Integer pageSize, String account, String operator) {
        PageHelper.startPage(currentPage, pageSize);
        Page gamePage = gameRecordDao.getGameRecordList(new GameRecordParams(account, operator));
        return QueryGameRecordResponse.builder()
                .total(gamePage.getTotal())
                .pageNum(gamePage.getPageNum())
                .pageSize(gamePage.getPageSize())
                .pages(gamePage.getPages())
                .data(gamePage.getResult())
                .build();
    }

    @Override
    public int updateRecord(GameRecordRequest gameRecordRequest) {
        GameRecord gameRecord=new GameRecord();
        int result=0;
        BeanUtils.copyProperties(gameRecordRequest,gameRecord);
        if(gameRecordRequest.getId()!=null && gameRecordRequest.getId()!=0){
            gameRecord.setUpdateTime(new Date());
            result=gameRecordDao.updateByPrimaryKeySelective(gameRecord);
        }else{
            //日期+账号+下注笔数+下注金额+输赢
            String recordUuid=MD5(gameRecord.getRecordDate()+""+gameRecord.getRecordCount()+gameRecord.getRecordAmount().toString()+gameRecord.getRecordGain().toString()).toLowerCase();
            gameRecord.setRecordUuid(recordUuid);
            gameRecord.setCreateTime(new Date());
            result=gameRecordDao.insertSelective(gameRecord);
        }
        return result;
    }

    public final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
