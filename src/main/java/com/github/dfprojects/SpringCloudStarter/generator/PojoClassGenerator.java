package com.github.dfprojects.SpringCloudStarter.generator;

import org.antlr.stringtemplate.StringTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.dfprojects.SpringCloudStarter.model.ClassModel;
import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

/**
 * Generator for Java POJO Classes
 *
 * @author Florian Widder
 *
 */
@Service
public class PojoClassGenerator {

    private static final Logger logger = LoggerFactory.getLogger(PojoClassGenerator.class);

    /**
     * Template for Class File
     */
    private static final String CLASS_TEMPLATE = """
			package $PACKAGENAME$;

			public class $CLASSNAME$ {

			    $ATTRIBUTS$
				$GETTER$
				$SETTER$
		    }
			""";

    /**
     * Template for an Atttribut
     */
    private static final String ATTRIBUTS_TEMPLATE = """
			private $TYPE$ $NAME$;
			""";

    /**
     * Template for a getter Method
     */
    private static final String GETTER_TEMPLATE = """
	        public $TYPE$ get$NAME$(){
	            return $NAME$;
	        }
	        """;

    /**
     * Template for a setter Method
     */
    private static final String SETTER_TEMPLATE = """
	        public void get$NAME$($TYPE$ $NAME$){
	            this.$NAME$ = $NAME$;
	        }
            """;

    private Formatter formatter = new Formatter();

    /**
     * Creates a POJO Class File
     * @param classModel TODO
     *
     * @return the generated Class File
     */
    public String generate(ClassModel classModel) {
        StringTemplate classTemplate = new StringTemplate(CLASS_TEMPLATE);
        StringTemplate attributsTemplate = new StringTemplate(ATTRIBUTS_TEMPLATE);
        StringTemplate getterTemplate = new StringTemplate(GETTER_TEMPLATE);
        StringTemplate setterTemplate = new StringTemplate(SETTER_TEMPLATE);

        StringBuilder attributsSB = new StringBuilder();
        StringBuilder getterSB = new StringBuilder();
        StringBuilder setterSB = new StringBuilder();

        classModel.getAttributs().forEach((name, type) -> {
            attributsTemplate.reset();
            attributsTemplate.setAttribute("NAME", name);
            attributsTemplate.setAttribute("TYPE", type);
            getterTemplate.reset();
            getterTemplate.setAttribute("NAME", name);
            getterTemplate.setAttribute("TYPE", type);
            setterTemplate.reset();
            setterTemplate.setAttribute("NAME", name);
            setterTemplate.setAttribute("TYPE", type);

            attributsSB.append(attributsTemplate.toString());

            getterSB.append(getterTemplate.toString());

            setterSB.append(setterTemplate.toString());
        });

        classTemplate.setAttribute("PACKAGENAME", classModel.getPackageName());
        classTemplate.setAttribute("CLASSNAME", classModel.getClassName());
        classTemplate.setAttribute("ATTRIBUTS", attributsSB.toString());
        classTemplate.setAttribute("GETTER", getterSB.toString());
        classTemplate.setAttribute("SETTER", setterSB.toString());

        try {
            return formatter.formatSource(classTemplate.toString());
        } catch (FormatterException e) {
            logger.warn(
                    "Formatting Error! Falling back to unformatted Class. Please check all names for reserved Words. (https://docs.oracle.com/javase/tutorial/java/nutsandbolts/_keywords.html)",
                    e);
            return classTemplate.toString();
        }
    }
}
