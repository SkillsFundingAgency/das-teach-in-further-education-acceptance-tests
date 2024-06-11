package sfa.das.driver;

import org.apache.commons.lang3.StringUtils;
import org.htmlunit.ScriptPreProcessor;
import org.htmlunit.html.HtmlElement;
import org.htmlunit.html.HtmlPage;

public class FixTypeErrorScriptPreProcessor implements ScriptPreProcessor {
    private final ScriptPreProcessor nextScriptPreProcessor_;

    public FixTypeErrorScriptPreProcessor() {
        nextScriptPreProcessor_ = null;
    }

    public FixTypeErrorScriptPreProcessor(final ScriptPreProcessor nextScriptPreProcessor) {
        nextScriptPreProcessor_ = nextScriptPreProcessor;
    }

    @Override
    public String preProcess(final HtmlPage htmlPage, final String sourceCode, final String sourceName,
                             final int lineNumber, final HtmlElement htmlElement) {

        String patchedSourceCode = sourceCode;

        if (sourceName.contains("/site.js") && !sourceName.contains("/site.js#")) {
            patchedSourceCode = StringUtils.replace(
                    sourceCode,
                    "var t=this;",
                    "var tx=this;");
        }

        if (nextScriptPreProcessor_ != null) {
            return nextScriptPreProcessor_.preProcess(htmlPage, patchedSourceCode, sourceName, lineNumber, htmlElement);
        }

        return patchedSourceCode;
    }
}
