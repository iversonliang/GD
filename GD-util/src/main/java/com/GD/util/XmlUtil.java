package com.GD.util;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlUtil {

	public static void main(String[] args) throws Exception {
		File file = new File("D:\\article.xml");
		System.out.println(file);
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file);
		XmlUtil.parseXml(doc);
	}

	private static void parseXml(Document doc) {
		Element root = doc.getRootElement();
		int i=0;
		for (Iterator iter = root.elementIterator("item"); iter.hasNext();) {// 遍历line结点的所有子节点,也可以使用root.elementIterator()
			Element element = (Element) iter.next();
			System.out.println(i++);

			// Attribute lidAttr = element.attribute("lid");// 获取<line>元素的属性
			//
			// for (Iterator iterInner = element.elementIterator("station");
			// iterInner.hasNext();) { // 遍历station结点的所有子节点
			// Element elementInner = (Element) iterInner.next();
			//
			// String sid = elementInner.elementText("sid");//
			// 获取<station>元素下<sid></sid>的值
			// String sname = elementInner.elementText("sname");//
			// 获取<station>元素下<sname></sname>的值
			//
			// System.out.println("----站--sid:" + sid);
			// System.out.println("----站--sname:" + sname);
			// }

		}
	}
}
