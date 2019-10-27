package org.unidal.web.mvc.lifecycle;

import com.dianping.cat.message.MessageProducer;
import com.dianping.cat.message.Transaction;
import org.codehaus.plexus.logging.LogEnabled;
import org.codehaus.plexus.logging.Logger;
import org.unidal.lookup.annotation.Inject;
import org.unidal.lookup.annotation.Named;
import org.unidal.web.mvc.ActionContext;
import org.unidal.web.mvc.ActionException;
import org.unidal.web.mvc.model.entity.OutboundActionModel;

import static org.unidal.lookup.util.ReflectUtils.invokeMethod;

@Named(type = OutboundActionHandler.class, instantiationStrategy = Named.PER_LOOKUP)
public class DefaultOutboundActionHandler implements OutboundActionHandler, LogEnabled {
    @Inject
    private MessageProducer m_cat;

    private OutboundActionModel m_outboundAction;

    private Logger m_logger;

    public void handle(ActionContext<?> context) throws ActionException {
        Transaction t = m_cat.newTransaction("MVC", "OutboundPhase");

        try {
            invokeMethod(m_outboundAction.getMethod(), m_outboundAction.getModuleInstance(), context);
            t.setStatus(Transaction.SUCCESS);
        } catch (RuntimeException e) {
            String actionName = m_outboundAction.getActionName();

            m_cat.logError(e);
            t.setStatus(e);
            throw new ActionException("Error occured during handling outbound action(" + actionName + ")", e);
        } finally {
            t.complete();
        }
    }

    public void initialize(OutboundActionModel outboundAction) {
        m_outboundAction = outboundAction;
        m_logger.debug(getClass().getSimpleName() + " initialized for  " + outboundAction.getActionName());
    }

    public void enableLogging(Logger logger) {
        m_logger = logger;
    }
}
