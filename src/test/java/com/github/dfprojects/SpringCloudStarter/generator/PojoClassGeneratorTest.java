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
     * {@link com.github.dfprojects.SpringCloudStarter.generator.PojoClassGenerator#generate(java.lang.String, java.lang.String, java.util.Map)}.
     * 
     * @throws IOException if Test File is not found
     */
    @Test
    final void testGenerate() throws IOException {
        final String packageName = "org.packageTest.test";
        final String className = "TestClass";
        final Map<String, String> attributs = new HashMap<>();
        attributs.put("intTest", "int");

        final String generatedClass = pojoClassGenerator.generate(packageName, className, attributs);

        final URL expURL = new ClassPathResource("/PojoClassGeneratorTest/TestClass.java").getURL();
        assertThat(generatedClass).isEqualTo(contentOf(expURL));
    }

}
