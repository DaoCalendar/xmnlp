package org.xm.xmnlp.seg.domain;

import org.xm.xmnlp.util.StringUtil;

import java.util.Iterator;
import java.util.List;

/**
 * 封装分词结果
 *
 * @author xuming
 */
public class Result implements Iterable<Term> {
    private List<Term> terms;

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public Result(List<Term> terms) {
        this.terms = terms;
    }


    @Override
    public Iterator<Term> iterator() {
        return terms.iterator();
    }

    public int size() {
        return terms.size();
    }

    public Term get(int i) {
        return terms.get(i);
    }

    @Override
    public String toString() {
        return toString(",");
    }

    public String toString(String split) {
        return StringUtil.joiner(terms,split);
    }

}
