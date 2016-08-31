package org.xm.xmnlp.ruledemo;

import org.xm.xmnlp.segword.Rule;

import java.util.Locale;
import java.util.Vector;

/**
 * 规则分词器：向前最大匹配法，向后最大匹配法，双向最大匹配法
 * Created by xuming on 2016/7/25.
 */
public class RuleSegDemo {
    public static void main(String[] args){
        System.out.println(Rule.forwardMaxSeg("北京天安门广场人民币种种族主义静态结果").toString());
        RuleSegDemo rule = new RuleSegDemo();
        rule.testRuleSeg();
    }

    public void testRuleSeg() {
        Rule rule = new Rule();
        String[] bugs =
                new String[]{
                        "北京天安门广场人民币种",
                        "干脆就把那部蒙人的闲法给废了拉倒！RT @laoshipukong : 27日，全国人大常委会第三次审议侵权责任法草案，删除了有关医疗损害责任“举证倒置”的规定。在医患纠纷中本已处于弱势地位的消费者由此将陷入万劫不复的境地。 "};
        for (String sentence : bugs) {
            Vector<String> f = Rule.forwardMaxSeg(sentence);
            System.out.print(String.format(Locale.getDefault(), "\n%s\n%s", sentence, f.toString()));
        }
        System.out.println();
        Vector<String> a0 = Rule.forwardMaxSeg("北京天安门广场人民币种，种族主义，结婚的和尚未结婚的");
        System.out.println("forwardMaxSeg:"+a0);

        Vector<String> a = Rule.reverseMaxSeg("北京天安门广场人民币种，种族主义，结婚的和尚未结婚的");
        String rms = a.toString();
        System.out.println("reverseMaxSeg:"+rms);

        Vector<String> b = Rule.biMaxSeg("北京天安门广场人民币种，种族主义，结婚的和尚未结婚的");
        System.out.println("biMaxSeg:"+b);

        Vector<String> c = Rule.forwardMinSeg("北京天安门广场人民币种，种族主义，结婚的和尚未结婚的");
        System.out.println("forwardMinSeg:"+c);

        Vector<String> d = Rule.reverseMinSeg("北京天安门广场人民币种，种族主义，结婚的和尚未结婚的");
        System.out.println("reverseMinSeg:"+d);

        Vector<String> e = Rule.biMinSeg("北京天安门广场人民币种，种族主义，结婚的和尚未结婚的");
        System.out.println("biMinSeg:"+e);

    }
}
