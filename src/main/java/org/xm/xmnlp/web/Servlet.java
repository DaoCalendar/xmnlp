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
 * Created by xuming on 2016/8/1.
 */
public class Servlet {
    private enum MethodEnum{
        BASE,NLP,INDEX,CRF,HMM,NSHORT,PINYIN,TRADITIONALCHINESE,KEYWORD,SUMMARY
    }
    public static String processRequest(String input, String methodStr,String natureStr) throws  Exception{
        MethodEnum methodEnum = MethodEnum.BASE;
        if(methodStr !=null){
            methodEnum = MethodEnum.valueOf(methodStr.toUpperCase());

        }else{
            methodEnum = MethodEnum.BASE;
        }
        Boolean nature = true;
        if(natureStr !=null && natureStr.toLowerCase().equals("false")){
            nature = false;
        }
        List<Term> terms = null;
        List<Pinyin> pinyinList=null;
        List<String> keywordList=null;
        List<String> sentenceList =null;
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
                terms = new HMMSegment().seg(input);
                break;
            case NSHORT:
                terms = new NShortSegment().seg(input);
                break;
            case PINYIN:
                pinyinList = Xmnlp.convertToPinyinList(input);
                break;
            case TRADITIONALCHINESE:
                terms =TraditionalChineseTokenizer.segment(input);
                break;
            case KEYWORD:
                 keywordList = Xmnlp.extractKeyword(input, 10);
                break;
            case SUMMARY:
                sentenceList = Xmnlp.extractSummary(input, 3);
//                return "<html>"+new TagContent("<font color=\"red\">", "</font>").tagContent(summary)+"...</html>" ;
            default:
                terms = StandardTokenizer.segment(input);
        }
        if(terms !=null){
            return termToString(terms, nature, methodEnum);
        }
        if (pinyinList != null) {
//            return pinyinListToString(pinyinList, nature);
        }
        if (keywordList != null) {
//            return keyWordsToString(keyWords, nature);
        }
        return "i am error!";
    }
    private static String termToString(List<Term> terms, boolean nature, MethodEnum method) {
        if (terms == null) {
            return "Failed to parse input";
        }
        if (nature && method != MethodEnum.NLP ) {

        }
        StringBuilder sb = new StringBuilder();
        for (Term term : terms) {
            String tmp = null ;
            if(method == MethodEnum.NLP){
                tmp = term.toString() ;
            }else{
                tmp = term.word;
            }

            if (nature && !"null".equals(term.getNature().toString())) {
                tmp += "/" + term.getNature().toString();
            }
            sb.append(tmp + "\t");
        }
        return terms.toString();
    }

}
