package org.xm.xmnlp.web;

import org.xm.xmnlp.Xmnlp;
import org.xm.xmnlp.dictionary.pinyin.Pinyin;
import org.xm.xmnlp.seg.CRFSegment;
import org.xm.xmnlp.seg.HMMSegment;
import org.xm.xmnlp.seg.NShortSegment;
import org.xm.xmnlp.seg.domain.Term;
import org.xm.xmnlp.tokenizer.IndexTokenizer;
import org.xm.xmnlp.tokenizer.NLPTokenizer;
import org.xm.xmnlp.tokenizer.StandardTokenizer;
import org.xm.xmnlp.tokenizer.TraditionalChineseTokenizer;

import java.util.List;

/**
 * 服务程序容器
 * Created by xuming on 2016/8/1.
 */
public class Servlet {
    private enum MethodEnum {
        BASE, NLP, INDEX, CRF, HMM, NSHORT, PINYIN, TRADITIONALCHINESE,SIMPLECHINESE, KEYWORD, SUMMARY
    }

    public static String processRequest(String input, String methodStr, String natureStr) throws Exception {
        MethodEnum methodEnum;
        if (methodStr != null) {
            methodEnum = MethodEnum.valueOf(methodStr.toUpperCase());
        } else {
            methodEnum = MethodEnum.BASE;
        }
        Boolean nature = true;
        if (natureStr != null && natureStr.toLowerCase().equals("false")) {
            nature = false;
        }
        List<Term> terms = null;
        List<Pinyin> pinyinList = null;
        switch (methodEnum) {
            case BASE:
                terms = StandardTokenizer.segment(input);
                break;
            case NLP:
                terms = NLPTokenizer.segment(input);
                break;
            case INDEX:
                terms = IndexTokenizer.segment(input);
                break;
            case CRF:
                terms = new CRFSegment().seg(input);
                break;
            case HMM:
                List<Term> hmmTerms = new HMMSegment().seg(input);
                return  hmmTerms.toString();
            case NSHORT:
                terms = new NShortSegment().seg(input);
                break;
            case PINYIN:
                pinyinList = Xmnlp.convertToPinyinList(input);
                break;
            case TRADITIONALCHINESE:
                terms = TraditionalChineseTokenizer.segment(input);
                break;
            case SIMPLECHINESE:
                String string = Xmnlp.convertToSimplifiedChinese(input);
                terms = StandardTokenizer.segment(string);
                break;
            case KEYWORD:
                List<String> keywordList = Xmnlp.extractKeyword(input, 5);
                return keywordList.toString();
            case SUMMARY:
                List<String> sentenceList = Xmnlp.extractSummary(input, 5);
                return sentenceList.toString();
                //return "<html>"+new TagContent("<font color=\"red\">", "</font>").tagContent(summary)+"...</html>" ;
            default:
                terms = StandardTokenizer.segment(input);
        }
        if (terms != null) {
            return termToString(terms, nature, methodEnum);
        }
        if (pinyinList != null) {
            return pinyinListToString(pinyinList);
        }

        return "i am error!";
    }

    private static String termToString(List<Term> terms, boolean nature, MethodEnum method) {
        if (terms == null) {
            return "Failed to parse input";
        }

        return terms.toString();
    }

    /**
     * 拼音 显示
     * @param pinyinList
     * @return
     */
    private static String pinyinListToString(List<Pinyin> pinyinList) {
        if (pinyinList == null) {
            return "Failed to parse input";
        }
        StringBuilder sb = new StringBuilder();
        for (Pinyin pinyin : pinyinList) {
            sb.append(pinyin.getPinyinWithToneMark());
            sb.append(",");
        }
        return sb.toString();
    }


}
