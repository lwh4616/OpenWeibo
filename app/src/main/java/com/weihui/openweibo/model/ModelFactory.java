package com.weihui.openweibo.model;

import com.weihui.openweibo.model.impl.TimeLineModelImpl;
import com.weihui.openweibo.model.impl.TimeLineRemoteModelImpl;

public class ModelFactory {


    private static volatile ModelFactory factory;

    private ITimeLineDBModel timeLineDBModel;

    private ITimeLineRemoteModel timeLineRemoteModel;

    private ModelFactory() {

        timeLineDBModel = new TimeLineModelImpl();
        timeLineRemoteModel = new TimeLineRemoteModelImpl();

    }

    public static ModelFactory getInstance() {
        if (null == factory) {
            synchronized (ModelFactory.class) {
                if (null == factory) {
                    factory = new ModelFactory();
                }
            }
        }
        return factory;
    }

    public ITimeLineDBModel getTimeLineDBModel() {
        return timeLineDBModel;
    }

    public ITimeLineRemoteModel getTimeLineRemoteModel() {
        return timeLineRemoteModel;
    }
}
