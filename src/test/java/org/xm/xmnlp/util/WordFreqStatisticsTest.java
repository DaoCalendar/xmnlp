/*
 * <author>Xu Ming</author>
 * <email>huluobo624@gmail.com</email>
 * <create-date>2016</create-date>
 * <copyright>
 *  Copyright (c) 2016. All Right Reserved, http://www.pycredit.cn/
 *  This source is subject to the Apache 2.0 License.
 *  Please contact http://www.pycredit.cn/ to get more information.
 * </copyright>
 *
 */

package org.xm.xmnlp.util;

import org.junit.Test;
import org.xm.xmnlp.tokenizer.NLPTokenizer;

/**
 * @author xuming
 */
public class WordFreqStatisticsTest {

    //词频统计
    WordFreqStatistics statistic = new WordFreqStatistics(NLPTokenizer.SEGMENT);
    @Test
    public void seg() throws Exception {
        //开始分词
        statistic.seg("数据建模和算法是自然语言处理课的基础吧，写代码不算什么。下雨天，明天有关于分子和原子的课程，" +
                "下雨了也要去听自然语言处理课");
        //输出词频统计结果
        System.out.println(statistic.getStatisticsMap());
        //输出词频统计结果
        statistic.dump();
    }

    @Test
    public void clear() throws Exception {
        statistic.reset();
        //输出词频统计结果
        System.out.println(statistic.getStatisticsMap());
    }
}