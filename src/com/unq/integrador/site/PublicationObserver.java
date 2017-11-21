package com.unq.integrador.site;

import com.unq.integrador.publication.Publication;

public abstract class PublicationObserver {

    public abstract void registerTo(NotificationManager notificationManager, Publication publication);
}
