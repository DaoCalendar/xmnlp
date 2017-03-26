/*
 * <author>Xu Ming</author>
 * <email>huluobo624@gmail.com</email>
 * <create-date>2017</create-date>
 * <copyright>
 *  Copyright (c) 2017. All Right Reserved, http://www.pycredit.cn/
 *  This source is subject to the Apache 2.0 License.
 *  Please contact http://www.pycredit.cn/ to get more information.
 * </copyright>
 *
 */

package org.xm.xmnlp.demo;

import org.xm.xmnlp.Xmnlp;
import org.xm.xmnlp.seg.domain.Result;

/**
 * 词性标注
 */
public class DemoNature {
    public static void main(String[] args) {
        Xmnlp.Config.ShowTermNature = true;
        String text = "xmnlp工具目标是普及自然语言处理在生产环境中的应用。";
        Result segment = Xmnlp.segment(text);
        System.out.println("不显示词性：" + segment);
        Xmnlp.Config.ShowTermNature = false;
        System.out.println("显示词性：" + segment);
    }
}
