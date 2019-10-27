package org.unidal.web.mvc.model.transform;

import org.unidal.web.mvc.model.IEntity;
import org.unidal.web.mvc.model.IVisitor;
import org.unidal.web.mvc.model.entity.*;

import static org.unidal.web.mvc.model.Constants.*;

public class DefaultXmlBuilder implements IVisitor {

    private IVisitor m_visitor = this;

    private int m_level;

    private StringBuilder m_sb;

    private boolean m_compact;

    public DefaultXmlBuilder() {
        this(false);
    }

    public DefaultXmlBuilder(boolean compact) {
        this(compact, new StringBuilder(4096));
    }

    public DefaultXmlBuilder(boolean compact, StringBuilder sb) {
        m_compact = compact;
        m_sb = sb;
        m_sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n");
    }

    public String buildXml(IEntity<?> entity) {
        entity.accept(m_visitor);
        return m_sb.toString();
    }

    protected void startTag(String name) {
        startTag(name, false, null);
    }

    protected void startTag(String name, boolean closed, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
        startTag(name, null, closed, dynamicAttributes, nameValues);
    }

    protected void startTag(String name, Object text, boolean closed, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
        indent();

        m_sb.append('<').append(name);

        int len = nameValues.length;

        for (int i = 0; i + 1 < len; i += 2) {
            Object attrName = nameValues[i];
            Object attrValue = nameValues[i + 1];

            if (attrValue != null) {
                m_sb.append(' ').append(attrName).append("=\"").append(escape(attrValue)).append('"');
            }
        }

        if (dynamicAttributes != null) {
            for (java.util.Map.Entry<String, String> e : dynamicAttributes.entrySet()) {
                m_sb.append(' ').append(e.getKey()).append("=\"").append(escape(e.getValue())).append('"');
            }
        }

        if (text != null && closed) {
            m_sb.append('>');
            m_sb.append(escape(text, true));
            m_sb.append("</").append(name).append(">\r\n");
        } else {
            if (closed) {
                m_sb.append('/');
            } else {
                m_level++;
            }

            m_sb.append(">\r\n");
        }
    }

    protected void indent() {
        if (!m_compact) {
            for (int i = m_level - 1; i >= 0; i--) {
                m_sb.append("   ");
            }
        }
    }

    protected String escape(Object value) {
        return escape(value, false);
    }

    protected String escape(Object value, boolean text) {
        if (value == null) {
            return null;
        }

        String str = value.toString();
        int len = str.length();
        StringBuilder sb = new StringBuilder(len + 16);

        for (int i = 0; i < len; i++) {
            final char ch = str.charAt(i);

            switch (ch) {
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '"':
                    if (!text) {
                        sb.append("&quot;");
                        break;
                    }
                default:
                    sb.append(ch);
                    break;
            }
        }

        return sb.toString();
    }

    protected void tagWithText(String name, String text, Object... nameValues) {
        if (text == null) {
            return;
        }

        indent();

        m_sb.append('<').append(name);

        int len = nameValues.length;

        for (int i = 0; i + 1 < len; i += 2) {
            Object attrName = nameValues[i];
            Object attrValue = nameValues[i + 1];

            if (attrValue != null) {
                m_sb.append(' ').append(attrName).append("=\"").append(escape(attrValue)).append('"');
            }
        }

        m_sb.append(">");
        m_sb.append(escape(text, true));
        m_sb.append("</").append(name).append(">\r\n");
    }

    protected void element(String name, String text, boolean escape) {
        if (text == null) {
            return;
        }

        indent();

        m_sb.append('<').append(name).append(">");

        if (escape) {
            m_sb.append(escape(text, true));
        } else {
            m_sb.append("<![CDATA[").append(text).append("]]>");
        }

        m_sb.append("</").append(name).append(">\r\n");
    }

    @Override
    public void visitDomain(DomainModel domain) {
        startTag(ENTITY_DOMAIN, null);

        if (!domain.getModules().isEmpty()) {
            for (ModuleModel module : domain.getModules().values().toArray(new ModuleModel[0])) {
                module.accept(m_visitor);
            }
        }

        endTag(ENTITY_DOMAIN);
    }

    protected void startTag(String name, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
        startTag(name, null, false, dynamicAttributes, nameValues);
    }

    protected void endTag(String name) {
        m_level--;

        indent();
        m_sb.append("</").append(name).append(">\r\n");
    }

    @Override
    public void visitError(ErrorModel error) {
        startTag(ENTITY_ERROR, true, null, ATTR_ACTIONNAME, error.getActionName());
    }

    @Override
    public void visitField(PayloadFieldModel field) {
        startTag(ENTITY_FIELD, true, null, ATTR_NAME, field.getName(), ATTR_FORMAT, field.getFormat(), ATTR_FILE, field.getFile(), ATTR_DEFAULTVALUE, field.getDefaultValue(), ATTR_MULTIPLEVALUES, field.getMultipleValues());
    }

    @Override
    public void visitInbound(InboundActionModel inbound) {
        startTag(ENTITY_INBOUND, null, ATTR_ACTIONNAME, inbound.getActionName(), ATTR_TRANSITIONNAME, inbound.getTransitionName(), ATTR_ERRORACTIONNAME, inbound.getErrorActionName());

        tagWithText(ELEMENT_CONTEXTCLASS, inbound.getContextClass() == null ? null : String.valueOf(inbound.getContextClass()));

        tagWithText(ELEMENT_PAYLOADCLASS, inbound.getPayloadClass() == null ? null : String.valueOf(inbound.getPayloadClass()));

        endTag(ENTITY_INBOUND);
    }

    @Override
    public void visitModule(ModuleModel module) {
        startTag(ENTITY_MODULE, null, ATTR_MODULENAME, module.getModuleName(), ATTR_DEFAULTINBOUNDACTIONNAME, module.getDefaultInboundActionName(), ATTR_DEFAULTTRANSITIONNAME, module.getDefaultTransitionName(), ATTR_DEFAULTERRORACTIONNAME, module.getDefaultErrorActionName(), ATTR_DEFAULTMODULE, module.getDefaultModule());

        tagWithText(ELEMENT_MODULECLASS, module.getModuleClass() == null ? null : String.valueOf(module.getModuleClass()));

        if (!module.getInbounds().isEmpty()) {
            for (InboundActionModel inbound : module.getInbounds().values().toArray(new InboundActionModel[0])) {
                inbound.accept(m_visitor);
            }
        }

        if (!module.getOutbounds().isEmpty()) {
            for (OutboundActionModel outbound : module.getOutbounds().values().toArray(new OutboundActionModel[0])) {
                outbound.accept(m_visitor);
            }
        }

        if (!module.getTransitions().isEmpty()) {
            for (TransitionModel transition : module.getTransitions().values().toArray(new TransitionModel[0])) {
                transition.accept(m_visitor);
            }
        }

        if (!module.getErrors().isEmpty()) {
            for (ErrorModel error : module.getErrors().values().toArray(new ErrorModel[0])) {
                error.accept(m_visitor);
            }
        }

        endTag(ENTITY_MODULE);
    }

    @Override
    public void visitObject(PayloadObjectModel object) {
        startTag(ENTITY_OBJECT, true, null, ATTR_NAME, object.getName());
    }

    @Override
    public void visitOutbound(OutboundActionModel outbound) {
        startTag(ENTITY_OUTBOUND, true, null, ATTR_ACTIONNAME, outbound.getActionName());
    }

    @Override
    public void visitPath(PayloadPathModel path) {
        startTag(ENTITY_PATH, true, null, ATTR_NAME, path.getName());
    }

    @Override
    public void visitPayload(PayloadModel payload) {
        startTag(ENTITY_PAYLOAD, null, ATTR_PAYLOADCLASS, payload.getPayloadClass() == null ? null : payload.getPayloadClass().getName());

        if (!payload.getFields().isEmpty()) {
            for (PayloadFieldModel field : payload.getFields().toArray(new PayloadFieldModel[0])) {
                field.accept(m_visitor);
            }
        }

        if (!payload.getPaths().isEmpty()) {
            for (PayloadPathModel path : payload.getPaths().toArray(new PayloadPathModel[0])) {
                path.accept(m_visitor);
            }
        }

        if (!payload.getObjects().isEmpty()) {
            for (PayloadObjectModel object : payload.getObjects().toArray(new PayloadObjectModel[0])) {
                object.accept(m_visitor);
            }
        }

        endTag(ENTITY_PAYLOAD);
    }

    @Override
    public void visitTransition(TransitionModel transition) {
        startTag(ENTITY_TRANSITION, true, null, ATTR_TRANSITIONNAME, transition.getTransitionName());
    }
}
