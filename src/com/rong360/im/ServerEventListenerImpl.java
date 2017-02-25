package com.rong360.im;

import net.openmob.mobileimsdk.server.event.ServerEventListener;
import org.apache.mina.core.session.IoSession;

/**
 * Created by chengchao on 2017/2/25.
 */
public class ServerEventListenerImpl implements ServerEventListener {

    @Override
    public int onVerifyUserCallBack(String paramString1, String paramString2, String extra) {
        System.out.println("======================onVerifyUserCallBack");
        return 0;
    }

    @Override
    public void onUserLoginAction_CallBack(int paramInt, String paramString, IoSession paramIoSession) {
        System.out.println("====================onUserLoginAction_CallBack");
    }

    @Override
    public void onUserLogoutAction_CallBack(int paramInt, Object paramObject) {
        System.out.println("=======================onUserLogoutAction_CallBack");
    }

    @Override
    public boolean onTransBuffer_CallBack(int paramInt1, int paramInt2, String paramString1, String paramString2) {
        System.out.println("==========================onTransBuffer_CallBack");
        return true;
    }

    @Override
    public void onTransBuffer_C2C_CallBack(int paramInt1, int paramInt2, String paramString) {
        System.out.println("=========================onTransBuffer_C2C_CallBack");

    }

    @Override
    public boolean onTransBuffer_C2C_RealTimeSendFaild_CallBack(int paramInt1, int paramInt2, String paramString1, String paramString2) {
        System.out.println("=====================onTransBuffer_C2C_RealTimeSendFaild_CallBack");
        return true;
    }
}
