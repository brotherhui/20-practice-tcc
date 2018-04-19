package com.brotherhui.tccpoc.participant.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public abstract class CompositeTransactionParticipantController {

    private static final Logger LOG = LoggerFactory.getLogger(CompositeTransactionParticipantController.class);

    @RequestMapping(method = RequestMethod.DELETE, value = "/tcc/{participantid}", consumes = "application/tcc")
    public void cancel(
            @PathVariable("participantid") String participantid
    ) {
        LOG.info("Trying to rollback transaction [{}]", participantid);
        doCancel(participantid);
        LOG.info("Transaction [{}] rolled back", participantid);
    }

    protected abstract void doCancel(String participantid);

    @RequestMapping(method = RequestMethod.PUT, value = "/tcc/{participantid}", consumes = "application/tcc")
    public void confirm(
            @PathVariable("participantid") String participantid
    ) {
        LOG.info("Trying to commit transaction [{}]", participantid);
        doConfirm(participantid);
        LOG.info("Transaction [{}] committed", participantid);
    }

    protected abstract void doConfirm(String participantid);

}
