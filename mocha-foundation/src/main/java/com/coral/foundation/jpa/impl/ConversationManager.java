package com.coral.foundation.jpa.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceUnit;
import com.google.common.base.Preconditions;

/**
 * 
 * Currently we have only one global conversation per vaadin application
 * a conversation will keep a entityManager open until it is ended
 * 
 * @author Coral
 * @deprecated didn't use
 */
public class ConversationManager {
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    private EntityManager em;

    private void checkState() {
        Preconditions.checkState(inProgress(), "You have to begin a conversation first!");
    }
    
    public EntityManager getEntityManager() {
        checkState();
        return em;
    }
    
    public void startTranscation() {
        checkState();
        em.getTransaction().begin();
    }
    
    public void commit() {
        checkState();
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }
    
    public void rollback() {
        checkState();
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
    
    public void begin() {
        Preconditions.checkState(!inProgress(), "A conversation is in progress, you cannot begin another");
        em = emf.createEntityManager();
        em.setFlushMode(FlushModeType.COMMIT);
    }
    
    public void end() {
        checkState();
        em.close();
        em = null;
    }
    
    public void cancel() {
        checkState();
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        em.close();
        em = null;
    }
    
    public boolean inProgress() {
        return em != null && em.isOpen();
    }
}
