package org.unidal.web.mvc.model.entity;

import org.unidal.web.mvc.model.BaseEntity;
import org.unidal.web.mvc.model.IVisitor;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.unidal.web.mvc.model.Constants.ATTR_MODULENAME;
import static org.unidal.web.mvc.model.Constants.ENTITY_MODULE;

public class ModuleModel extends BaseEntity<ModuleModel> {
    private String m_moduleName;

    private String m_defaultInboundActionName;

    private String m_defaultTransitionName;

    private String m_defaultErrorActionName;

    private Boolean m_defaultModule;

    private Class<?> m_moduleClass;

    private Object m_moduleInstance;

    private Object m_actionResolverInstance;

    private Map<String, InboundActionModel> m_inbounds = new LinkedHashMap<String, InboundActionModel>();

    private Map<String, OutboundActionModel> m_outbounds = new LinkedHashMap<String, OutboundActionModel>();

    private Map<String, TransitionModel> m_transitions = new LinkedHashMap<String, TransitionModel>();

    private Map<String, ErrorModel> m_errors = new LinkedHashMap<String, ErrorModel>();

    public ModuleModel() {
    }

    public ModuleModel(String moduleName) {
        m_moduleName = moduleName;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitModule(this);
    }

    @Override
    public void mergeAttributes(ModuleModel other) {
        assertAttributeEquals(other, ENTITY_MODULE, ATTR_MODULENAME, m_moduleName, other.getModuleName());

        if (other.getDefaultInboundActionName() != null) {
            m_defaultInboundActionName = other.getDefaultInboundActionName();
        }

        if (other.getDefaultTransitionName() != null) {
            m_defaultTransitionName = other.getDefaultTransitionName();
        }

        if (other.getDefaultErrorActionName() != null) {
            m_defaultErrorActionName = other.getDefaultErrorActionName();
        }

        if (other.getDefaultModule() != null) {
            m_defaultModule = other.getDefaultModule();
        }
    }

    public String getDefaultInboundActionName() {
        return m_defaultInboundActionName;
    }

    public String getDefaultTransitionName() {
        return m_defaultTransitionName;
    }

    public String getDefaultErrorActionName() {
        return m_defaultErrorActionName;
    }

    public Boolean getDefaultModule() {
        return m_defaultModule;
    }

    public ModuleModel setDefaultErrorActionName(String defaultErrorActionName) {
        m_defaultErrorActionName = defaultErrorActionName;
        return this;
    }

    public ModuleModel setDefaultTransitionName(String defaultTransitionName) {
        m_defaultTransitionName = defaultTransitionName;
        return this;
    }

    public ModuleModel setDefaultInboundActionName(String defaultInboundActionName) {
        m_defaultInboundActionName = defaultInboundActionName;
        return this;
    }

    public ModuleModel addError(ErrorModel error) {
        m_errors.put(error.getActionName(), error);
        return this;
    }

    public ModuleModel addInbound(InboundActionModel inbound) {
        m_inbounds.put(inbound.getActionName(), inbound);
        return this;
    }

    public ModuleModel addOutbound(OutboundActionModel outbound) {
        m_outbounds.put(outbound.getActionName(), outbound);
        return this;
    }

    public ModuleModel addTransition(TransitionModel transition) {
        m_transitions.put(transition.getTransitionName(), transition);
        return this;
    }

    public ErrorModel findError(String actionName) {
        return m_errors.get(actionName);
    }

    public InboundActionModel findInbound(String actionName) {
        return m_inbounds.get(actionName);
    }

    public OutboundActionModel findOutbound(String actionName) {
        return m_outbounds.get(actionName);
    }

    public TransitionModel findTransition(String transitionName) {
        return m_transitions.get(transitionName);
    }

    public Object getActionResolverInstance() {
        return m_actionResolverInstance;
    }

    public ModuleModel setActionResolverInstance(Object actionResolverInstance) {
        m_actionResolverInstance = actionResolverInstance;
        return this;
    }

    public Map<String, ErrorModel> getErrors() {
        return m_errors;
    }

    public Map<String, InboundActionModel> getInbounds() {
        return m_inbounds;
    }

    public Class<?> getModuleClass() {
        return m_moduleClass;
    }

    public ModuleModel setModuleClass(Class<?> moduleClass) {
        m_moduleClass = moduleClass;
        return this;
    }

    public Object getModuleInstance() {
        return m_moduleInstance;
    }

    public ModuleModel setModuleInstance(Object moduleInstance) {
        m_moduleInstance = moduleInstance;
        return this;
    }

    public Map<String, OutboundActionModel> getOutbounds() {
        return m_outbounds;
    }

    public Map<String, TransitionModel> getTransitions() {
        return m_transitions;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_moduleName == null ? 0 : m_moduleName.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ModuleModel) {
            ModuleModel _o = (ModuleModel) obj;

            return equals(m_moduleName, _o.getModuleName());
        }

        return false;
    }

    public String getModuleName() {
        return m_moduleName;
    }

    public ModuleModel setModuleName(String moduleName) {
        m_moduleName = moduleName;
        return this;
    }

    public boolean isDefaultModule() {
        return m_defaultModule != null && m_defaultModule.booleanValue();
    }

    public ModuleModel setDefaultModule(Boolean defaultModule) {
        m_defaultModule = defaultModule;
        return this;
    }

    public ErrorModel removeError(String actionName) {
        return m_errors.remove(actionName);
    }

    public InboundActionModel removeInbound(String actionName) {
        return m_inbounds.remove(actionName);
    }

    public OutboundActionModel removeOutbound(String actionName) {
        return m_outbounds.remove(actionName);
    }

    public TransitionModel removeTransition(String transitionName) {
        return m_transitions.remove(transitionName);
    }

}
