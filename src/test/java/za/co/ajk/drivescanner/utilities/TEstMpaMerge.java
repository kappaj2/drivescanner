
package za.co.ajk.drivescanner.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import za.co.ajk.drivescanner.TestConfiguration;
import za.co.ajk.drivescanner.test_treemap.TestMapMerge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class TEstMpaMerge {

    @Autowired
    private TestMapMerge testMapMerge;

    @Test
    public void testMerge() throws Exception {
        System.out.println("Doing test!!!");

        Map<String, Object> dirMap1 = new HashMap<>();

        String dir = "andre";
        List<String> filesList1 = new ArrayList<>();
        filesList1.add("File1");
        filesList1.add("File2");

        dirMap1.put(dir, filesList1);

        Map<String, Object> dirMap2 = new HashMap<>();
        String dir2 = "File1";
        List<String> filesList2 = new ArrayList<>();
        filesList2.add("File3");
        filesList2.add("File4");

        dirMap2.put(dir2, filesList2);

        //Map<Object, Object> mergeMap = testMapMerge.merge(dirMap1, dirMap2);

        //testMapMerge.deepMerge(dirMap1, dirMap2);

        testMapMerge.deepMerge2(dirMap1, dirMap2);

//        printXML(dirMap1);
//        printXML(dirMap2);

        String jsonString = printJson(dirMap1);
        JSONObject json = new JSONObject(jsonString);
        String xml = XML.toString(json);
        System.out.println(xml);

        jsonString = printJson(dirMap1);
        json = new JSONObject(jsonString);
        xml = XML.toString(json);
        System.out.println(xml);

        String test1 = "{name: JSON, integer: 1, double: 2.0, boolean: true, nested: { id: 42 }, array: [1, 2, 3]}";

        json = new JSONObject(jsonString());
        xml = XML.toString(json);
        System.out.println(xml);
    }

    public String printJson(Map<String, Object> dirMap) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonInString = objectMapper.writeValueAsString(dirMap);
        System.out.println(jsonInString);
        return jsonInString;
    }

    public void printXML(Map<String, Object> dirMap) throws Exception{
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(dirMap);
        System.out.println(xml);
    }

    String jsonString(){
        String json = "{\n" +
                "   \"extends\": [\n" +
                "      \"eslint:recommended\",\n" +
                "      \"angular\"\n" +
                "   ],\n" +
                "   \"env\": {\n" +
                "      \"node\": true,\n" +
                "      \"browser\": true\n" +
                "   },\n" +
                "   \"rules\": {\n" +
                "      \"wrap-iife\": [\n" +
                "         2,\n" +
                "         \"inside\"\n" +
                "      ],\n" +
                "      \"eqeqeq\": 2,\n" +
                "      \"no-use-before-define\": [\n" +
                "         2,\n" +
                "         \"nofunc\"\n" +
                "      ],\n" +
                "      \"no-unused-vars\": [\n" +
                "         2,\n" +
                "         {\n" +
                "            \"vars\": \"local\",\n" +
                "            \"args\": \"none\"\n" +
                "         }\n" +
                "      ],\n" +
                "      \"no-multi-str\": 2,\n" +
                "      \"no-irregular-whitespace\": 2,\n" +
                "      \"semi\": [\n" +
                "         2,\n" +
                "         \"always\"\n" +
                "      ],\n" +
                "      \"indent\": 2,\n" +
                "      \"linebreak-style\": 0,\n" +
                "      \"eol-last\": 2\n" +
                "   }\n" +
                "}";
        return json;
    }
}
