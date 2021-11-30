package org.optaplanner.examples.common.persistence;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import org.optaplanner.core.api.domain.solution.PlanningSolution;



public abstract class AbstractJSONSolutionImporter<Solution_> extends AbstractSolutionImporter<Solution_> {
    
    private static final String DEFAULT_INPUT_FILE_SUFFIX = "json";

    @Override
    public String getInputFileSuffix() {
        return DEFAULT_INPUT_FILE_SUFFIX;
    }

/*    public abstract XmlInputBuilder<Solution_> createXmlInputBuilder();
*
    @Override
    public Solution_ readSolution(File inputFile) {
        try (InputStream in = new BufferedInputStream(new FileInputStream(inputFile))) {
            SAXBuilder builder = new SAXBuilder(false);
            Document document = builder.build(in);
            XmlInputBuilder<Solution_> xmlInputBuilder = createXmlInputBuilder();
            xmlInputBuilder.setInputFile(inputFile);
            xmlInputBuilder.setDocument(document);
            try {
                Solution_ solution = xmlInputBuilder.readSolution();
                logger.info("Imported: {}", inputFile);
                return solution;
            } catch (IllegalArgumentException | IllegalStateException e) {
                throw new IllegalArgumentException("Exception in inputFile (" + inputFile + ")", e);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not read the file (" + inputFile.getName() + ").", e);
        } catch (JDOMException e) {
            throw new IllegalArgumentException("Could not parse the XML file (" + inputFile.getName() + ").", e);
        }
    }

    public static abstract class XmlInputBuilder<Solution_> extends InputBuilder {

        protected File inputFile;
        protected Document document;

        public void setInputFile(File inputFile) {
            this.inputFile = inputFile;
        }

        public void setDocument(Document document) {
            this.document = document;
        }

        public abstract Solution_ readSolution() throws IOException, JDOMException;

        // ************************************************************************
        // Helper methods
        // ************************************************************************

        public String getInputId() {
            return FilenameUtils.getBaseName(inputFile.getPath());
        }

        protected void assertElementName(Element element, String name) {
            if (!element.getName().equals(name)) {
                throw new IllegalStateException("Element name (" + element.getName()
                        + ") should be " + name + ".");
            }
        }

    }

*/
}
