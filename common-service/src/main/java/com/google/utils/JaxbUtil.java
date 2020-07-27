package com.google.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Jaxb 工具类，使用时根据情况自己将standalone="yes"去掉
 * 
 * @author iuv
 * @date 2017年3月13日 上午10:33:53
 */
public class JaxbUtil {

	private static Logger logger = LoggerFactory.getLogger(JaxbUtil.class);

	/**
	 * JavaBean转换成xml
	 * 
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public static String convertToXml(Object obj, String encoding, boolean format) {
		String result = null;
		StringWriter writer = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * xml转换成JavaBean
	 * 
	 * @param xml
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertToJava(String xml, Class<T> c) {
		if (xml == null || xml.equals(""))
			return null;
		T t = null;
		StringReader reader = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			reader = new StringReader(xml);
			t = (T) unmarshaller.unmarshal(reader);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (reader != null)
				reader.close();
		}
		return t;
	}

	/**
	 * XML格式字符串转换为Map
	 *
	 * @param xml XML字符串
	 * @return XML数据转换后的Map
	 * @throws Exception
	 */
	public static Map<String, String> xmlToMap(String xml) {
		try {
			Map<String, String> data = new HashMap<>();
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			org.w3c.dom.Document doc = documentBuilder.parse(stream);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getDocumentElement().getChildNodes();
			for (int idx = 0; idx < nodeList.getLength(); ++idx) {
				Node node = nodeList.item(idx);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					org.w3c.dom.Element element = (org.w3c.dom.Element) node;
					data.put(element.getNodeName(), element.getTextContent());
				}
			}
			stream.close();
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将Map转换为XML格式的字符串
	 *
	 * @param data Map类型数据
	 * @return XML格式的字符串
	 * @throws Exception
	 */
	public static String mapToXml(Map<String, String> data) throws Exception {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
			org.w3c.dom.Document document = documentBuilder.newDocument();
			org.w3c.dom.Element root = document.createElement("xml");
			document.appendChild(root);
			for (String key: data.keySet()) {
				String value = data.get(key);
				if (value == null) {
					value = "";
				}
				value = value.trim();
				org.w3c.dom.Element filed = document.createElement(key);
				filed.appendChild(document.createTextNode(value));
				root.appendChild(filed);
			}
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			transformer.transform(source, result);
			String output = writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
			writer.close();
			return output;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
