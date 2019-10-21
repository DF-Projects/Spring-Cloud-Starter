/**
 *
 */
package com.github.dfprojects.SpringCloudStarter.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.github.dfprojects.SpringCloudStarter.model.ClassModel;

/**
 * Tests für
 * {@link com.github.dfprojects.SpringCloudStarter.generator.PojoClassGenerator
 * PojoClassGenerator}
 *
 * @author Florian Widder
 *
 */
class PojoClassGeneratorTest {

    /**
     * The
     * {@link com.github.dfprojects.SpringCloudStarter.generator.PojoClassGenerator
     * PojoClassGenerator} under Test
     */
    PojoClassGenerator pojoClassGenerator = new PojoClassGenerator();

    /**
     * Test method for
     * {@link com.github.dfprojects.SpringCloudStarter.generator.PojoClassGenerator#generate(ClassModel)}.
     * 
     * @throws IOException if Test File is not found
     */
    @Test
    void testGenerateOneAttribut() throws IOException {
        final String packageName = "org.packageTest.test";
        final String className = "TestClass";
        Map<String, String> attributs = new HashMap<>();
        attributs.put("intTest", "int");

        final String generatedClass = pojoClassGenerator.generate(new ClassModel(packageName, className, attributs));

        final URL expURL = new ClassPathResource("/PojoClassGeneratorTest/testGenerateOneAttribut.java").getURL();
        assertThat(generatedClass).isEqualToIgnoringWhitespace(contentOf(expURL));
    }

    /**
     * Test method for
     * {@link com.github.dfprojects.SpringCloudStarter.generator.PojoClassGenerator#generate(ClassModel)}.
     * 
     * @throws IOException if Test File is not found
     */
    @Test
    void testGenerateThreeAttributs() throws IOException {
        final String packageName = "org.packageTest.test";
        final String className = "TestClass";
        Map<String, String> attributs = new HashMap<>();
        attributs.put("intTest", "int");
        attributs.put("sfdgfdg", "String");
        attributs.put("fsdff", "long");

        final String generatedClass = pojoClassGenerator.generate(new ClassModel(packageName, className, attributs));

        final URL expURL = new ClassPathResource("/PojoClassGeneratorTest/testGenerateThreeAttributs.java").getURL();
        assertThat(generatedClass).isEqualToIgnoringWhitespace(contentOf(expURL));
    }

    /**
     * Test method for
     * {@link com.github.dfprojects.SpringCloudStarter.generator.PojoClassGenerator#generate(ClassModel)}.
     * 
     * @throws IOException if Test File is not found
     */
    @Test
    void testGenerateNoAttributs() throws IOException {
        final String packageName = "org.packageTest.test";
        final String className = "TestClass";
        Map<String, String> attributs = new HashMap<>();

        final String generatedClass = pojoClassGenerator.generate(new ClassModel(packageName, className, attributs));

        final URL expURL = new ClassPathResource("/PojoClassGeneratorTest/testGenerateNoAttributs.java").getURL();
        assertThat(generatedClass).isEqualToIgnoringWhitespace(contentOf(expURL));
    }

}
